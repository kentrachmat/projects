#include <stdio.h>
#include <stdlib.h>

/*
Question 1
un type de int ** (pointeur qui pointe à un pointeur)
*/

/*Question 2*/
void swap_ptr(int **p, int **q){
    int *b;
    b = *p; 
    *p = *q;
    *q = b;
}

/*Question 3*/
int main() {
    int a, b;
    int *p = &a;
    int *q = &b;

    swap_ptr(&p, &q);

    if ((p == &b) && (q == &a)) { 
        printf("OK ;)\n"); 
        exit(EXIT_SUCCESS);
    } else {
        printf("KO ;(\n"); 
        exit(EXIT_FAILURE);
    }    
}
