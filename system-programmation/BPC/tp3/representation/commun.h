/* ------------------------------
   commun.h
   Définition des types
   ------------------------------------------------------------
*/

#define NB_ETU 30

struct etudiant_s {
    unsigned int numero;
    float moyenne;
    char nom[32];
    char prenom[32];
};

union bloc_u {
    struct etudiant_s etu[30];
    char data[30*sizeof(struct etudiant_s)];
};
