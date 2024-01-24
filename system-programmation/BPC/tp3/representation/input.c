/* ------------------------------
   input.c
   Lecture de données
   ------------------------------------------------------------
*/

#include <stdio.h>
#include <stdlib.h>

#include "commun.h"

int main(void) {
    int i;
    int nb = 0;
    struct etudiant_s monEtudiant;
    union bloc_u bloc;

    for(i = 0; i<sizeof (bloc.etu); i++){
        bloc.data[i] = getchar();
    }

    /* Exercise 10/11
    monEtudiant = bloc.etu;

    printf("Affichage etudiant: \n");
    printf("Prenom: %s\n",monEtudiant.prenom);
    printf("Nom: %s\n",monEtudiant.nom);
    printf("Numero: %d\n",monEtudiant.numero);
    printf("Moyenne: %f\n",monEtudiant.moyenne);
    */

    /* Exercise 12 */
    for (i = 0; i < 30; i++){
        monEtudiant = bloc.etu[i];
        if(monEtudiant.numero != -1){
            printf("\nAffichage etudiant: Mr.%s %s\n",monEtudiant.prenom,monEtudiant.nom);
            printf("Numero: %d\n",monEtudiant.numero);
            printf("Moyenne: %f\n",monEtudiant.moyenne);
            nb++;
        }
    }

    printf("\nOn a affiché %d etudiants \n", nb);
    return 0;
}
