#include <stdio.h>

/*Question 1*/
void swap_int(int *a, int *b){
    int x;
    x = *a;
    *a = *b;
    *b = x;
}
/*Question 2*/
int main(void){
    int a, b;
    printf("Entrez la première valeur : ");
    scanf("%d", &a);

    printf("Entrez la deuxième valeur : ");
    scanf("%d", &b);

    printf("int 1 : %d, int 2 : %d \n", a, b);
    
    swap_int(&a, &b);
    
    printf("Après avoir changé la valeur\n");
    printf("int 1 : %d, int 2 : %d \n", a, b);

    return 0;
}
