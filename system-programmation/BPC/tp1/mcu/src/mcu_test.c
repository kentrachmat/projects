#include <stdio.h>
#include "mcu_macros.h"
#include "mcu_fatal.h"
#include "mcu_readl.h"
#include "mcu_putint.h"

int
main (void)
{
    int line[MAXLINE];
    int i,j;
    /* on affiche une ligne de l'entrée standard */
    i = readl(line);
    for (j=0; j<i; j++)
        putchar(line[j]);
    putchar('\n') ;
    /* on fait un affichage */
    putdec(i) ;
    putchar('\n') ;
    /* on termine par un problème */
    fatal(1==0, "1==0 is not true", 2) ;
    return 0;
}
