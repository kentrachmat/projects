/* 
   mcu - terminaison sur erreur
*/

#include <stdio.h>
#include <stdlib.h>

#include "mcu_fatal.h"

/* Termine l'exécution du programme si le prédicat assert est faux
   - affiche le message sur la sortie d'erreur
   - termine le programme par exit() et retourne la valeur status.
*/
void fatal(int assert, const char message[], int status)
{
    if (!assert) {
        fprintf(stderr, "%s\n", message);
        exit(status);
    }
}

