/* ------------------------------
   output.c
   Écriture de données
   ------------------------------------------------------------
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

#include "commun.h"

int main(void) {
    struct etudiant_s monEtudiant;
    union bloc_u bloc;
    int i,nb;

    srand(time(NULL));
    nb = (rand() % 30) + 1;

    for (i = 0; i < 30; i++){
        if(i < nb){
            monEtudiant.numero = i+1;
            monEtudiant.moyenne = rand() % 21;

            /* Exercise 11*/
            strcpy(monEtudiant.nom,"RACHMAT");
            strcpy(monEtudiant.prenom,"Kent");
        }
        else {
            monEtudiant.numero = -1;
        }
        bloc.etu[i] = monEtudiant;
    }
    for(i = 0; i < sizeof (union bloc_u); i++){
        putchar(bloc.data[i]);
    }

    return 0;
}
