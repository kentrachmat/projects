#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define MAX 6

/*Question 1, 3*/
char *nom_temp1(){
    static int ctr = 0;
    static char tab[] = "fileN";
    tab[4] = (ctr++) +'0';
    return tab;
}

char *nom_temp2(){
    char *new = malloc(MAX * sizeof(char));
    static int inc = 0;
    if (new == NULL)
        return NULL;
    snprintf(new, MAX, "file%d",inc++);
    return new;
}

/*
Question 2
Type static et dynamique
*/

/*
Question 4
l'impact est que nous aurons la même valeur pour 
chaque test (la valeur de la dernière appel), 
c'est parce que on a utilisé des variables statiques 
et chaque appel remplacera la valeur précédente
*/
void test_nom_temp1() {
    char *file1, *file2, *file3, *file4;
    puts("Type Static : \n");

    file1 = nom_temp1();
    printf("Fichier 1 : %s\n", file1);
    file2 = nom_temp1();
    printf("Fichier 2 : %s\n", file2);
    file3 = nom_temp1();
    printf("Fichier 3 : %s\n", file3);
    file4 = nom_temp1();
    printf("Fichier 4 : %s\n\n", file4);
}

/*Question 5*/
void test_nom_temp2() {
    char *file1, *file2, *file3, *file4;
    puts("Type Dynamique : \n");

    file1 = nom_temp2();
    printf("Fichier 1 : %s\n", file1);
    free(file1);

    file2 = nom_temp2();
    printf("Fichier 2 : %s\n", file2);
    free(file2);

    file3 = nom_temp2();
    printf("Fichier 3 : %s\n", file3);
    free(file3);

    file4 = nom_temp2();
    printf("Fichier 4 : %s\n", file4);
    free(file4);
}

int main(void){
    test_nom_temp1();
    test_nom_temp2();
    return 0;
}
