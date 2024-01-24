#include <stdio.h>
#include "mcu_fatal.h"

int readl(char line[]){
	/*
	Autres solution !
	int i;
	for (i = 0; 1 ;i++){
		line[i] = getchar();
		if(line[i] == '\n') break;
	}
	line[i+1] = 0;
	*/

	int i = 0;
	int c;
	while(((c = getchar()) != '\n') && (c != EOF)){
		fatal((i-1> MAXLINE),"Erreur de Longueur",1);
		line[i] = c;
		i++;
	}
	line[i] = 0;
	return i;
}
