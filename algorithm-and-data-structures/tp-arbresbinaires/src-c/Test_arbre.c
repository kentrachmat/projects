#include <stdio.h>
#include <stdlib.h>

#include "ArbreBinaire.h"
#include "Affichage.h"

#define max(a,b) ((a)>(b)?(a):(b))

#define DEBUT_ARBRE_MYSTERIEUX 12
#define FIN_ARBRE_MYSTERIEUX 24

/* Manipulation d'arbres binaires */

Noeud_t arbre1 (void) {
   Noeud_t arbre,sous_arbre_gauche,sous_arbre_droit;

   arbre = CreerNoeud(12);
   sous_arbre_gauche = CreerNoeud(9);
   AjouterGauche(arbre,sous_arbre_gauche);
   sous_arbre_droit = CreerNoeud(8);
   AjouterDroite(arbre,sous_arbre_droit);

   SauverArbreDansFichier(arbre,"arbre1");
   return arbre;
}

Noeud_t arbre2 (void) {
   Noeud_t arbre,sous_arbre_gauche,sous_arbre_gauche_droit,sous_arbre_gauche_droit_gauche;

   arbre = CreerNoeud(12);
   sous_arbre_gauche = CreerNoeud(9);
   AjouterGauche(arbre,sous_arbre_gauche);
   sous_arbre_gauche_droit = CreerNoeud(5);
   AjouterDroite(sous_arbre_gauche,sous_arbre_gauche_droit);
   sous_arbre_gauche_droit_gauche = CreerNoeud(7);
   AjouterDroite(sous_arbre_gauche_droit,sous_arbre_gauche_droit_gauche);

   SauverArbreDansFichier(arbre,"arbre2");
   return arbre;
}

Noeud_t arbre3 (void) {
   Noeud_t arbre,sous_arbre_g,sous_arbre_d,sous_arbre_g_d,sous_arbre_g_g,sous_arbre_d_d,sous_arbre_d_d_g,sous_arbre_d_d_d;
   arbre = CreerNoeud(12);

   sous_arbre_g = CreerNoeud(9);
   AjouterGauche(arbre,sous_arbre_g);
   sous_arbre_d = CreerNoeud(8);
   AjouterDroite(arbre,sous_arbre_d);

   sous_arbre_g_g = CreerNoeud(1);
   AjouterGauche(sous_arbre_g,sous_arbre_g_g);
   sous_arbre_g_d = CreerNoeud(5);
   AjouterDroite(sous_arbre_g,sous_arbre_g_d);
   sous_arbre_d_d = CreerNoeud(4);
   AjouterDroite(sous_arbre_d,sous_arbre_d_d);

   sous_arbre_d_d_g = CreerNoeud(7);
   AjouterGauche(sous_arbre_d_d,sous_arbre_d_d_g);
   sous_arbre_d_d_d = CreerNoeud(6);
   AjouterDroite(sous_arbre_d_d,sous_arbre_d_d_d);

   SauverArbreDansFichier(arbre,"arbre3");
   return arbre;
}

void imprimer (Noeud_t a) {
   if (EstVide(a))
      return;
   if (!Gauche(a) && !Droite(a)) 
      printf("%ld ",ValeurDuNoeud(a));
   else{
      if (Gauche(a)) 
         imprimer(Gauche(a));
      printf("%ld ",ValeurDuNoeud(a));
      if (Droite(a)) 
         imprimer(Droite(a));
   }
}

int taille (Noeud_t a) { 
   if (EstVide(a)){
      return 0;
   }
   return 1 + taille(Gauche(a)) + taille(Droite(a));
}

int hauteur (Noeud_t a) { 
   if (EstVide(a))
      return -1;
   if ((a->gauche && a->gauche->droite == a) && (a->droite && a->droite->gauche == a))
      return 1;
   return 1 + max(hauteur(a->gauche), hauteur(a->droite));
}

int nbFeuilles(Noeud_t a) {
   if(EstVide(a))
      return 0;
   if(EstVide(a->gauche) && EstVide(a->droite))
      return 1;
   else
      return nbFeuilles(a->gauche) + nbFeuilles(a->droite);
}

/* Comptage d'arbres */

int nbArbres(int n) {
   int i, res;
   if(n < 0) 
      return -1;
   if (n == 0) 
      return 1;
   else {
    res = 0;
    for(i = 0; i < n; i++){
      res += nbArbres(i)*nbArbres(n-i-1);
    }
    return res;
   }
}

int nbArbresEfficace(int n) {
   int i, j, res;
   int *tab ;
   tab=(int *) malloc((n+1) * sizeof(int));
   tab[0] = 1;
   if(n < 0) return -1;
   if (n == 0) return 1;

   for(i=1; i<=n; i++){
      res = 0;
      for(j=0; j<i; j++){
         res += tab[j]*tab[i-j-1];
      }
      tab[i] = res;
   }
   return tab[n];
}

/* Manipulation d'arbres binaires de recherche */

Noeud_t abr1 (void) {
   Noeud_t tree = CreerNoeud(6);
   Noeud_t tree1 = CreerNoeud(4);
   Noeud_t tree2 = CreerNoeud(2);
   Noeud_t tree3 = CreerNoeud(7);
   Noeud_t tree4 = CreerNoeud(5);
   Noeud_t tree5 = CreerNoeud(1);
   
   AjouterGauche(tree2, tree5);
   AjouterDroite(tree1, tree4);
   AjouterGauche(tree1, tree2);
   AjouterDroite(tree, tree3);
   AjouterGauche(tree, tree1);

   SauverArbreDansFichier(tree,"abr1");
   return tree;
}
 
Noeud_t ajouter (value_t v, Noeud_t a) {
   if(!Gauche(a) && v <= ValeurDuNoeud(a)){
      AjouterGauche(a, CreerNoeud(v));
      return a;
   }
   if(!Droite(a) && v > ValeurDuNoeud(a)){
      AjouterDroite(a, CreerNoeud(v));
      return a;
   } 
   if(Gauche(a) && v <= ValeurDuNoeud(a)){
      ajouter(v, Gauche(a));
   }
   if(Droite(a) && v > ValeurDuNoeud(a)){
      ajouter(v, Droite(a));
   } 
   return a;
}
 
Noeud_t abr2 (void) {
   Noeud_t tree = CreerNoeud(5);
   ajouter(4, tree);
   ajouter(2, tree);
   ajouter(7, tree);
   ajouter(6, tree);
   ajouter(1, tree);

   SauverArbreDansFichier(tree,"abr2");
   return tree;
}

Noeud_t abr3 (void) {
   Noeud_t tree = CreerNoeud(7);
   ajouter(1, tree);
   ajouter(4, tree);
   ajouter(5, tree);
   ajouter(6, tree);
   ajouter(2, tree);

   SauverArbreDansFichier(tree,"abr3");
   return tree;
}

int appartient (value_t v, Noeud_t a) {
   int res = 0;
   if(EstVide(a)){
      res = 0;
   } else{
      if(ValeurDuNoeud(a)  == v){
         res += 1;
      }
      if(ValeurDuNoeud(a) > v){
         res += appartient(v,Gauche(a));
      } else{
         res += appartient(v,Droite(a));
      }
   }
   return res; 
}
 
int valeur_minimale (Noeud_t abr) {
   if(EstVide(abr)){
      return -1;
   }
   if(EstVide(Gauche(abr))){
      return ValeurDuNoeud(abr);
   }
   return valeur_minimale(Gauche(abr));
}

int valeur_maximale (Noeud_t abr) {
   if(EstVide(abr)){
      return -1;
   }
   if(EstVide(Droite(abr))){
      return ValeurDuNoeud(abr);
   }
   return valeur_maximale(Droite(abr));
}

/* Entier mysterieux */

Noeud_t construitArbreEntierMysterieux (value_t i, value_t j) {
   int div = (i+j)/2;
   Noeud_t noeud = CreerNoeud(div);
   if(div+1 <= j){
      AjouterDroite(noeud, construitArbreEntierMysterieux(div+1, j));
   }
   if(i <= div-1){
      AjouterGauche(noeud, construitArbreEntierMysterieux(i, div-1));
   }
   return noeud;
}

void jouer (int i, int j, int k) {
   int c=0;
   Noeud_t tree = construitArbreEntierMysterieux(i,j);
   while((ValeurDuNoeud(tree) != k) && (Gauche(tree) || Droite(tree))){
      if((i>k) || (j<k)){
         printf("%d. ERROR\n", ++c);
         break;
      }
      if(k < ValeurDuNoeud(tree)){
         printf("%d. Est-ce %ld ?\n", ++c, ValeurDuNoeud(tree));
         printf("%d. Trop grand\n", c);
         tree = Gauche(tree);
      }
      if(k > ValeurDuNoeud(tree)){
         printf("%d. Est-ce %ld ?\n", ++c, ValeurDuNoeud(tree));
         printf("%d. Trop petit\n", c);
         tree = Droite(tree);
      }
   }
   if(ValeurDuNoeud(tree) == k){
      printf("%d. Est-ce %ld ?\n", ++c, ValeurDuNoeud(tree));
      printf("%d. Gagné !\n\n", c);
   }
}

/* Tests sur les arbres binaires */

void testArbreBinaire(Noeud_t a) {
   imprimer(a); printf("\n");
   printf("Taille     = %d\n",(taille(a)));
   printf("Hauteur    = %d\n",(hauteur(a)));
   printf("nbFeuilles = %d\n\n",(nbFeuilles(a)));
}

/* Tests sur les arbres binaires de recherche */
void testABR (Noeud_t a) {
   int i;
   imprimer(a); printf("\n");
   printf("Taille     = %d\n",(taille(a)));
   printf("Hauteur    = %d\n",(hauteur(a)));
   printf("nbFeuilles = %d\n",(nbFeuilles(a)));
   printf("Valeurs présentes dans l'arbre : ");
   for (i = 0; i <= 10; i++) {
      if (appartient(i,a)) {
         printf("%d ",i);
      }
   }
   printf("\n");
}
 
/* Programme principal */
int main (int argc, char **argv) {

   int i;

   Noeud_t a1 = arbre1();
   Noeud_t a2 = arbre2();
   Noeud_t a3 = arbre3();

   testArbreBinaire(a1);
   testArbreBinaire(a2);
   testArbreBinaire(a3);

   DetruireArbre(a1);
   DetruireArbre(a2);
   DetruireArbre(a3);

   for (i = 0; i <= 19; i++) {
      printf("(nbArbres) Le nombre d'arbres à %d noeuds est %d\n",i,(nbArbres(i)));
   }
   puts("\n");
   for (i = 0; i <= 19; i++) {
      printf("(nbArbresEfficace) Le nombre d'arbres à %d noeuds est %d\n",i,(nbArbresEfficace(i)));
   }
   puts("\n");

   a1 = abr1();
   a2 = abr2();
   a3 = abr3();
   
   testABR(a1);
   testABR(a2);
   testABR(a3);
   
   DetruireArbre(a1);
   DetruireArbre(a2);
   DetruireArbre(a3);

   printf("Arbre mysterieux entre %d et %d:\n", DEBUT_ARBRE_MYSTERIEUX, FIN_ARBRE_MYSTERIEUX);
   imprimer(construitArbreEntierMysterieux(DEBUT_ARBRE_MYSTERIEUX, FIN_ARBRE_MYSTERIEUX));
   printf("\n\n");

   jouer(DEBUT_ARBRE_MYSTERIEUX, FIN_ARBRE_MYSTERIEUX, 15);
   return 0;
}
