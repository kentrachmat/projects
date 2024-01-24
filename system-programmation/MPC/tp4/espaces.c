#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>

/*Question 1*/
char* skip_spaces(char s[]){
    int i;
    for (i = 0; s[i] == ' '; i++){
        if(s[i] != ' ')
            return NULL; 
    }
    return &s[i];
}

int main(int argc, char *argv[])
{
    char * strip;
    int i;
    assert(argc == 2);
    
    printf("argv  : %s\n", argv[1]);
    strip = skip_spaces(argv[1]);
    printf("strip : %s\n", strip);
    for (i=0 ; strip[i]; i++) 
        strip[i] = toupper(strip[i]);

    printf("strip : %s\n", strip);
    printf("argv  : %s\n", argv[1]);

    exit(EXIT_SUCCESS);
    /*
    Question 2
    % ./module/strip_spaces_tst "FOO BAR"
    argv  : FOO BAR
    strip : FOO BAR
    strip : FOO BAR
    argv  : FOO BAR
    - ça ne change rien car il n'y a pas d'espace devant et les lettres sont déjà en majuscules

    % ./module/strip_spaces_tst "       FOO BAR"
    argv  :        FOO BAR
    strip : FOO BAR
    strip : FOO BAR
    argv  :        FOO BAR
    - il supprime l'espace devant le texte

    % ./module/strip_spaces_tst "Foo Bar"
    argv  : Foo Bar
    strip : Foo Bar
    strip : FOO BAR
    argv  : FOO BAR
    - il rend le mot en majuscule car la fonction toupper qui a importé par <ctype.h>

    % ./module/strip_spaces_tst "       Foo Bar"
    argv  :        Foo Bar
    strip : Foo Bar
    strip : FOO BAR
    argv  :        FOO BAR
    - il supprime l'espace devant le texte & il rend le mot en majuscule car la fonction toupper qui a importé par <ctype.h>
     */

}
