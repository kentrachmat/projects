#include "ArbreBinaire.h"
#include "Affichage.h"

int
main
(void)
{
  Noeud_t arbre,sous_arbre_gauche,sous_arbre_droit, sous_arbre_droit_droit  ;

  arbre = CreerNoeud(1) ;
  sous_arbre_gauche = CreerNoeud(2) ;
  AjouterGauche(arbre,sous_arbre_gauche);
  sous_arbre_droit = CreerNoeud(3) ;
  AjouterDroite(arbre,sous_arbre_droit);
  sous_arbre_droit_droit = CreerNoeud(4) ;
  AjouterDroite(sous_arbre_droit,sous_arbre_droit_droit);
	
  SauverArbreDansFichier(arbre,"exemple1");

  DetruireArbre(arbre);
        
  return 0 ;
}
