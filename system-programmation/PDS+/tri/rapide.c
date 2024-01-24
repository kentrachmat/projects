#include <stdlib.h>
#include <stdint.h>
#include <assert.h>
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

#include "pile.h"
#include "tri.h"
#include "rapide.h"
#include "main.h"

unsigned long seuil_bloc_long = 1;
base_t *tableau;

/* structure pour notre variable partagée */
typedef struct {
    int running;
    pile *pile;
    pthread_mutex_t mutex;
    pthread_cond_t cond;
} rapide_struct;

#define min(a,b) (((a)<(b))?(a):(b))
#define max(a,b) (((a)>(b))?(a):(b))

/* initialisation de la structure */
void init(rapide_struct *tri, pile *p){
    tri->pile = p;
    tri->running = 0;
    pthread_mutex_init(&tri->mutex, NULL); // tri->mutex = PTHREAD_MUTEX_INITIALIZER;
    pthread_cond_init(&tri->cond, NULL); // tri->cond = PTHREAD_COND_INITIALIZER;
}

/* Étape élémentaire du tri rapide : découpe le bloc b en 0, 1 ou 2 blocs
 * Dans le cas normal, découpe en 2 blocs, les éléments inférieurs au
 * pivot, et ceux supérieurs au pivot
 * Si un bloc contient moins de 1 élément, il n’est pas retourné
 */
int rapide_decoupebloc(bloc_t b, bloc_t bret[2]) {
    pos_t g, d;
    base_t pivot, tmp;
    bloc_t b1, b2;
    int nb_ret = 0;

    if(b.debut >= b.fin) {
        /* Arrive uniquement dans le cas d’un tri d’un tableau de
         * taille 1 au départ */
        assert (b.debut == b.fin);
        return 0;
    }

    /* Définit une petite macro pour échanger deux cases de tableau en
     * passant par la variable tmp */
#define echange(p1,p2)                     \
    do {                                   \
        tmp         = tableau[p1];         \
        tableau[p1] = tableau[p2];         \
        tableau[p2] = tmp;                 \
    } while(0)

    pivot = tableau[b.debut];
    g = b.debut + 1;
    d = b.fin;

    while (g < d) {
        while (g < d && tableau[g] <= pivot)
            g++;
        while (d > g && tableau[d] > pivot)
            d--;
        if (g < d)
            echange(g, d);
    }

    b1.debut = b.debut;
    b2.fin = b.fin;

    if (tableau[g] <= pivot) {
        echange(g, b.debut);
        b1.fin   = g - 1;
        b2.debut = min(g + 1, b2.fin);
    } else if (g > b.debut + 1) {
        echange(g - 1, b.debut);
        b1.fin   = max(g - 2, b1.debut);
        b2.debut = g;
    } else {                    /* sinon le pivot est le plus petit, donc déjà bien placé */
        b1.fin   = b.debut;
        b2.debut = b.debut + 1;
    }

    if (b1.debut < b1.fin)
        bret[nb_ret++] = b1;
    if (b2.debut < b2.fin)
        bret[nb_ret++] = b2;

    return nb_ret;
}

/* Effectue un tri rapide séquentiel */
void rapide_seq(bloc_t bloc_init) {
    pile p;
    int i, nb_blocs;
    bloc_t bloc;
    bloc_t blocs[2];

    init_pile(&p);
    empile(&p, bloc_init);

    /* Principe du tri rapide séquentiel :
     * tant qu’il y a des blocs à trier, dépile un bloc, le découpe en
     * (au maximum) deux sous-blocs non-encore triés et les empile */
    do {
        bloc = depile(&p);
        nb_blocs = rapide_decoupebloc(bloc, blocs);
        for (i = 0; i < nb_blocs; i++)
            empile(&p, blocs[i]);
    } while (!pile_vide(&p));
}

/* depiler un pile en utilisant la fonction de verrouillage 
et de déverrouillage et attendez que la pile soit vide */
bloc_t depile_thread(rapide_struct *tri) {
    pthread_mutex_lock(&tri->mutex);
    // File d'attente si il n'y a pas de bloc a trier
    while (pile_vide(tri->pile))
    {
        pthread_cond_wait(&tri->cond, &tri->mutex);
    }
    bloc_t bloc = depile(tri->pile);
    pthread_mutex_unlock(&tri->mutex);
    return bloc;
}

/* empiler un pile en utilisant la fonction de verrouillage 
et de déverrouillage et envoyer un signal quand c'est fait */
void *empile_thread(rapide_struct *tri, bloc_t bloc) {
    pthread_mutex_lock(&tri->mutex);
    empile(tri->pile, bloc);
    if (tri->running == 0)
    {
        pthread_cond_broadcast(&tri->cond);
    } else {
        pthread_cond_signal(&tri->cond);
    }
    pthread_mutex_unlock(&tri->mutex);
    return NULL;
}

/* inspiré de la fonction rapide seq mais nous utilisons notre propre 
fonction depiler et empiler afin de protéger la variable partagée */
void *rapide_thread(void *content) {
    rapide_struct *tri = (rapide_struct *)content;
    bloc_t bloc;
    bloc_t blocs[2];
    int i, nb_blocs;
    
    do
    {
        pthread_mutex_lock(&tri->mutex);
        tri->running++;
        bloc = depile_thread(tri);
        nb_blocs = rapide_decoupebloc(bloc, blocs);
        for (i = 0; i < nb_blocs; i++)
            empile_thread(tri, blocs[i]);
        tri->running--;
        pthread_mutex_unlock(&tri->mutex);
    } while (!pile_vide(tri->pile));

    return NULL;
}

void rapide(pos_t taille, unsigned int nb_threads) {
    unsigned int i;
    pile p;
    bloc_t bloc;
    rapide_struct tri;
    bloc.debut = 0;
    bloc.fin = taille - 1;

    if (nb_threads == 0){
        rapide_seq(bloc);
        return;
    }
    
    assert(nb_threads >= 1);

    // p->dessus = 0;
    init_pile(&p);
    assert(pile_vide(&p) == 1);

    empile(&p, bloc);
    assert(pile_vide(&p) != 1);
 
    init(&tri, &p);

    pthread_t *threads = malloc(sizeof(pthread_t) * nb_threads); //pthread_t threads[nb_threads];
    assert(tri.running == 0);

    for (i = 0; i < nb_threads; i++){
        pthread_create(&threads[i], NULL, rapide_thread, (void *)&tri);
    }

    for (i = 0; i < nb_threads; i++){
        pthread_join(threads[i], NULL);
    }

    free(threads);
    pthread_mutex_destroy(&tri.mutex);
    pthread_cond_destroy(&tri.cond);
} 
