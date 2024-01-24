#include <stdio.h>
#include "mcu_putint.h"
#include "mcu_macros.h"
#include "mcu_readl.h"

char linerev[MAXLINE];
int n;

void rev (char tab[]){
	int first,sec;
    n = readl(tab);

    for (first = n - 1, sec = 0; first >= 0; first--, sec++){
        linerev[sec] = tab[first];
	}
    linerev[sec] = 0;
}

int main (void)
{
	char line1[MAXLINE];
    char line2[MAXLINE];

	int j = 0;

    rev(line1);
    for (j=0; j<n; j++)
        putchar(linerev[j]);
    putchar('\n') ;

    rev(line2);
    for (j=0; j<n; j++)
        putchar(linerev[j]);
        
    putchar('\n') ;
    return 0;
}
