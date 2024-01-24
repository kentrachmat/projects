#define ARBREBINAIRE

#define SOUS_ARBRE_EXISTANT 1 
#define BADARGUMENT 2
#define ARBREVIDE 3

typedef signed long int value_t ;
typedef struct NoeudBinaire_m * Noeud_t ;


struct NoeudBinaire_m 
{
	value_t  val ;
	Noeud_t  gauche ;
	Noeud_t  droite ;
} ;


/* cr\'ee un arbre vide */
Noeud_t CreerArbreVide(void) ;

/* cr\'ee une feuille avec une valeur associ\'ee */
Noeud_t CreerNoeud(value_t) ;

/* detruit l'arbre passe en parametre */
void DetruireArbre(Noeud_t);

/* pr\'edicat de test qu'un noeud est vide */
int EstVide (Noeud_t);

/* pr\'edicat de test qu'un noeud est une feuille.  

   CU: le noeud ne doit pas être vide, sinon produit une erreur. */
int EstFeuille (Noeud_t);

/* retourne la valeur associ\'e au noeud pass\'e en param\`etre.

   CU: le noeud ne doit pas \^etre vide */
value_t ValeurDuNoeud(Noeud_t) ;

/* ajoute en fils gauche au noeud premier argument le noeud second
   argument.

   CU: le fils gauche doit \^etre vide, sinon produit une erreur
*/
void AjouterGauche(Noeud_t,Noeud_t) ;

/* ajoute en sous-arbre droit au noeud en premier argument, le noeud en second
   argument.

   CU: le sous-arbre droit doit \^etre vide sinon produit une erreur
*/
void AjouterDroite(Noeud_t,Noeud_t) ;

/* remplace le sous-arbre gauche du noeud en premier argument par le noeud
   second argument.
*/
void RemplacerGauche(Noeud_t,Noeud_t) ;

/* remplace le sous-arbre droit du noeud premier argument par le noeud
   second argument.
*/
void RemplacerDroite(Noeud_t,Noeud_t) ;

/* retourne le sous-arbre gauche du noeud premier argument */
Noeud_t Gauche(Noeud_t);

/* retourne le sous-arbre droit du noeud premier argument */
Noeud_t Droite(Noeud_t);


