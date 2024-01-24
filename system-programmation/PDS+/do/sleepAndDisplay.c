#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>


/*
 *  Cette fonction prend deux arguments obligatoirement :
 *  le premier représente ce que la fonction doit retourner.
 *  le second indique le temps (en seconde) que doit attendre la fonction avant de finir.
 */
int main(int argc,char *argv[]) {
    int returnValue = EXIT_FAILURE;

    if(argc != 3){
        fprintf(stderr,"Usage : %s {true,false} {nombre de seconde}\n",argv[0]);
        fflush(stdout);
        exit(EXIT_FAILURE);
    }

    if(!strcmp(argv[1],"true")){
        returnValue = EXIT_SUCCESS;
    }

    sleep(atoi(argv[2]));

    printf("%s ",argv[1]);
    fflush(stdout);

    return (returnValue);
}