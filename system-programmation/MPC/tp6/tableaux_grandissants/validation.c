/*
  Validation (à minima) de la bibliothèque de gestion de
  tableaux grandissants.

  Principe :
  - lecture d'une séquenece de valeurs entières sur l'entrée standard
    et rangement dans un tableau grandissant
  - tri à bulle de ce tableau grandissant
  - affichage des éléments ce tableau
  
*/   

#include <stdio.h>
#include <stdlib.h>
#include "libga.h"

int
main(void) {
    struct ga_s tab;            /* le tableau grandissant */
    int val;
    int i, j;
    int count;

    /* Initialisation et allocation mémoire du tableau grandissant */
    ga_new(&tab);

    /* Lecture d'entiers, un par ligne, et mémorisation dans le
       tableau grandissant */
    count = 0; 
    while (scanf("%u\n", &val) == 1) {
        ga_set(&tab, count, val);
        count++;
    }
    
    /* Tri à bulles du tableau */ 
    for (i = 0; i < count - 1; i++) {
        for (j = i + 1; j < count; j++) {
            int vali, valj;         /* valeurs aux indices i et j du tableau */

            ga_get(&tab, i, &vali); /* vali = tab[i] */ 
            ga_get(&tab, j, &valj); /* valj = tab[j] */ 
            
            if (vali > valj) {         /*  échange */                
                ga_set(&tab, i, valj); /* tab[i] = valj */
                ga_set(&tab, j, vali); /* tab[j] = vali */
            }        
        }
    }

    /* Affichage du tableau trié, une valeur par ligne */
    for (i = 0; i < count; i++) {
        ga_get(&tab, i, &val);
        printf("%u\n", val);
    }
    
    /* Libération mémoire */
    ga_del(&tab);

    exit(EXIT_SUCCESS);
}

