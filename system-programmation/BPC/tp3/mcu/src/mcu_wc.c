#include <stdio.h>
#include "mcu_putint.h"

int main (void)
{
	int i = 0;
	int c;
	while(((c = getchar()) != '\n') && (c != EOF)){
		i++;
	}
    putdec(i+1);
	putchar('\n');
    return 0;
}
