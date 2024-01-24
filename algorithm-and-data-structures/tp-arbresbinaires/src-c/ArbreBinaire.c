#ifndef ARBREBINAIRE
#include "ArbreBinaire.h"
#endif
#include <stdio.h>
#include <stdlib.h>

static 
Noeud_t 
AllocationNoeudBinaire
(value_t val)
{
	Noeud_t noeud ;
	noeud = malloc(sizeof(struct NoeudBinaire_m)) ;
	noeud -> gauche = NULL ;
	noeud -> droite  = NULL ;
	noeud -> val = val ;
	return noeud ;
}

Noeud_t
CreerArbreVide
(void)
{
   return NULL;
}

Noeud_t 
CreerNoeud
(value_t val )
{
       return AllocationNoeudBinaire(val) ;
}

void
DetruireArbre
(Noeud_t racine)
{
  if (! EstVide(racine)) {
    DetruireArbre(Gauche(racine));
    DetruireArbre(Droite(racine));
    free(racine);
  }
}

int
EstVide
(Noeud_t n)
{
	return n == NULL ;
}


value_t 
ValeurDuNoeud
(Noeud_t n) 
{
	return n->val ;
}

void
AjouterGauche
(Noeud_t racine,Noeud_t noeud) {
   if (!EstVide(racine->gauche))
      {
         fprintf(stderr, "Error: Il y a un sous-arbre gauche\n");
         exit(SOUS_ARBRE_EXISTANT) ;
      }
   RemplacerGauche(racine, noeud);
}

void
AjouterDroite
(Noeud_t racine,Noeud_t noeud) {
   if (!EstVide(racine->droite))
      {
         fprintf(stderr, "Error: Il y a un sous-arbre droit\n");
         exit(SOUS_ARBRE_EXISTANT) ;
      }
   RemplacerDroite(racine, noeud);
}

void
RemplacerGauche
(Noeud_t racine, Noeud_t noeud) {
  racine->gauche = noeud;
}

void
RemplacerDroite
(Noeud_t racine, Noeud_t noeud) {
  racine->droite = noeud;
}

Noeud_t
Gauche(Noeud_t n)
{
  return n->gauche;
}

Noeud_t
Droite(Noeud_t n)
{
  return n->droite;
}


int
EstFeuille
(Noeud_t a)
{
   if (EstVide(a))
      { 
         fprintf(stderr, "Error: Le noeud est vide\n");
         exit(ARBREVIDE) ;
      }
   return (EstVide(Gauche(a)) && EstVide(Droite(a)));
}







