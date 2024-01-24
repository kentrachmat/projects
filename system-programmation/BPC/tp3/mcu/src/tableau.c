#define SIZE 12
#include <stdio.h>

void copier_chaine(char c1[], char c2[]){
    int i;
    for(i=0 ;c1[i] != 0 ;i++){
        c2[i]=c1[i];
    }
    c2[i] = 0;
}

void copier_tableau(int src[], int dest[], int taille){
    int i;
    for(i=0 ;i<taille ;i++)
        dest[i]=src[i];
}

int main(void)
{
    int i,j;
    int i_src,i_dest;
    int tsrc[SIZE] = {1,2,3,4,0,-1,9,8,7,10};
    int tdest[SIZE];
    char csrc[10] = "Bonjour";
    char cdest[10];

    /* Exercise 1 Q1 
    for(i=0 ;i<SIZE ;i++)
        tdest[i]=tsrc[i];
    */
   
    /* Exercise 1 Q2  */
    for(i_src=0, i_dest=0 ;i_src < SIZE ;i_src++){
        if(tsrc[i_src] > 0){
            tdest[i_dest]=tsrc[i_src];
            i_dest++;
        }
    }

    /* Print Exercise 1 */
    for(j=0;j<i_dest;j++){
       printf("%d, ",tdest[j]);
    }
    putchar('\n');
    
    /* 
    Exercise 2  
    On crée une fonction qui copie le tableau de la source 
    vers la destination avec une certaine longueur 
    sur le troisième paramètre.
    */
    copier_tableau(tsrc,tdest,12);

   /* Print Exercise 2 */
    for(j=0;j<10;j++){
       printf("%d, ",tdest[j]);
    }
    putchar('\n');

    /* 
    Exercise 3
    nous imitons la fonction de strcpy().
    */
    copier_chaine(csrc,cdest);

    /* Print Exercise 3 */
    for(j=0;j<10;j++){
       putchar(cdest[j]);
    }
    putchar('\n');
    
    return 0;
}
