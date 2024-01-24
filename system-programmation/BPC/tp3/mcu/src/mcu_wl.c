#include <stdio.h>
#include "mcu_putint.h"

int main (void)
{
	int j = 0;
	int c;
	while(((c = getchar()) != '\n') && (c != EOF)){
		if(c == ' '){
			j++;
		}
	}
    putdec(j+1);
	putchar('\n');
    return 0;
}
