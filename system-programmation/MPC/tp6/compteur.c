#include <stdio.h>
#include <stdlib.h>

/* Question 1 */
int compteur(){
    static int cpt = 0;
    return cpt++;
}

/*Question 2 */
int main(){
    int i;

    printf("Compteur :\n");
    for (i=0; i<10; i++){
        printf("%d\n",compteur());
    }
    return 0;
}

/*
    On peut aussi utiliser le pointeur

int compteur(int *ptr){
    return (*ptr)++;
}
*/
