#include <stdio.h>
#include "mcu_putint.h"
#include "mcu_macros.h"
#include "mcu_readl.h"
#include "mcu_fatal.h"
#define NUMCIBLE 30

int n1,n2,n3;

int copier (char cible[], char source[]){
    int i;

    fatal((NUMCIBLE<n3),"Le tableau de cible est trop petit",3);

	for(i = 0; i<n3; i++){
		cible[i] = source[i];
	}
    cible[i] = 0;
    return i;
}

int comparer (char chaine1[], char chaine2[]){
    int i = 0;
    while(chaine1[i] == chaine2[i] && chaine1 != '\0'){
        if(chaine1[i] == chaine2[i]){
            return 0;
        }
        i++;
    }
    return 1;
}

int main (void)
{
    int j = 0;
	char line1[MAXLINE];
    char line2[MAXLINE];
    char src[MAXLINE];
    char cib[NUMCIBLE];

	printf("line 1 : ");
	n1 = readl(line1);

    printf("line 2 : ");
    n2 = readl(line2);

    printf("comparer(line1,line2): ");
    putdec(comparer(line1,line2));
    putchar('\n');
    putchar('\n');

    printf("New line : ");
    
    n3 = readl(src);

    printf("Copied line : ");
    for (j=0; j<copier(cib,src); j++)
        putchar(cib[j]);
        
    putchar('\n');
    return 0;
}
