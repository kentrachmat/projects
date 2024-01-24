/* Ceci est un commentaire */

/**
 * Nous allons utiliser la fonction "putchar()" de la librairie 
 * standard du langage C pour sortir le caractère de code ASCII
 * "c" sur le terminal. Cette fonction retourne -1 en cas d'ereur.
 * Voici la déclaration de cette fonction :
 **/
extern int putchar (int c);

/**
 * Nous définissons maintenant la fonction "main()" : nous allons 
 * écrire le corps de cette fonction.
 * "main()" est la fonction qui sera exécutée au démarrage de 
 * notre programme.
 * Cette fonction ne prend pas de paramètre et retourne 0 si le
 * programme termine correctement (une autre valeur en cas d'erreur).
 **/

// L'indentation avec K&R, Allman
int main() 
{
    putchar(72);
    putchar(0x69);
    putchar('!');
    putchar('\n');
    return 0;
}