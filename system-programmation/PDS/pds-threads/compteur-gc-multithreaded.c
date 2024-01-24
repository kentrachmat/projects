#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <assert.h>
#include <pthread.h>

struct arg_s{
    char *bloc;
    unsigned long taille;
    unsigned long res;
};


unsigned long compteur_gc(char *bloc, unsigned long taille) {
    unsigned long i, cptr = 0;

    for (i = 0; i < taille; i++)
        if (bloc[i] == 'G' || bloc[i] == 'C')
            cptr++;

    return cptr;
}

void *fonct_wrapper(void *arg) {                                                
    struct arg_s *args = arg;                                                                                                                       
    args->res = compteur_gc(args->bloc, args->taille);                                    
                                                                                
    return NULL;                                                                
}


int main(int argc, char *argv[]) {
    struct stat st;
    int fd, i, nbThreads;
    char *tampon;
    int lus;
    off_t taille = 0;
    struct timespec debut, fin;
    unsigned long cptr = 0;

    assert(argc > 2);

    /* Combien de threads ? */
    assert(atoi(argv[2]) > 0);
    nbThreads = atoi(argv[2]);
    pthread_t tid[nbThreads];
    struct arg_s arg[nbThreads];

    /* Quelle taille ? */
    assert(stat(argv[1], &st) != -1);
    tampon = malloc(st.st_size);
    assert(tampon != NULL);

    /* Chargement en mémoire */
    fd = open(argv[1], O_RDONLY);
    assert(fd != -1);
    while ((lus = read(fd, tampon + taille, st.st_size - taille)) > 0)
        taille += lus;
    assert(lus != -1);
    assert(taille == st.st_size);
    close(fd);

    /* Calcul proprement dit */
    assert(clock_gettime(CLOCK_MONOTONIC, &debut) != -1);
    int reste = taille % nbThreads;
    unsigned long octets_par_thread = taille / nbThreads;
    arg[0].taille = octets_par_thread + reste; 
    arg[0].bloc = tampon;
    pthread_create(&tid[0], NULL, &fonct_wrapper, &arg[0]);
    for (i = 1 ; i < nbThreads ; i++){
        arg[i].taille = octets_par_thread;
        arg[i].bloc = tampon + octets_par_thread * i + reste;
        pthread_create(&tid[i], NULL, &fonct_wrapper, &arg[i]);
    }
    for (i = 0; i < nbThreads; i++){
        pthread_join(tid[i], NULL);
        cptr = arg[i].res + cptr;
    }

    assert(clock_gettime(CLOCK_MONOTONIC, &fin) != -1);

    /* Affichage des résultats */
    printf("Nombres de GC:   %ld\n", cptr);
    printf("Taux de GC:      %lf\n", ((double) cptr) / ((double) taille));

    fin.tv_sec  -= debut.tv_sec;
    fin.tv_nsec -= debut.tv_nsec;
    if (fin.tv_nsec < 0) {
        fin.tv_sec--;
        fin.tv_nsec += 1000000000;
    }
    printf("Durée de calcul: %ld.%09ld\n", fin.tv_sec, fin.tv_nsec);
    printf("(Attention: très peu de chiffres après la virgule sont réellement significatifs !)\n");

    return 0;
}
