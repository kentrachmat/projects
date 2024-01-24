#include <stdio.h>
#include <stdlib.h>
int main (int argc, char *argv[])
{
    int i;
    for(i = 1; i < argc ; i++) {
        printf("%s ", argv[i]);
    }
    putchar('\n');
    exit(EXIT_SUCCESS);
    /*
    Question 1
    % mecho "Hello world !"
    -> argv = ["mecho", "Hello world !"]

    % mecho Hello world !
    -> argv = ["mecho", "Hello", "world", "!"]

    Question 2
    Ce programme va nous montrer les arguments inséré 
    (car la boucle commence à 1).
    int argc = la quantité de l'argument+1
    char *argv[] = l'argument

    Pour exécute le program :
    ./module/division
    */
}
