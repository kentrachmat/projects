#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

extern char **environ;

/*Question 1*/
int nvar(){
    /*                  Autre facon de le faire
    char **penv = environ;
    int i = 0;
    for(penv = environ; *penv != (char *) 0;penv++){
        i++;
    }
    return i;
    */

    int i = 0;
    while(environ[i]) {
        i++;
    }
    return i;
    
}

/*Question 2*/
void mprintenvAll(){
    int i = 0;
    while(environ[i]) {
        printf("%s\n", environ[i++]); 
    }
}

/*Question 3*/
char * mprintenv(char *s){
    int i = 0, n;
    while(environ[i]) {
        for(n = 0; toupper(*s) == environ[i][n]; n++){
            s = s+sizeof(char);
        }
        if(*s == '\0' && environ[i][n] == '='){
            return environ[i]+n+1;
        } 
        i++;
    }
    return NULL;
}

int main(int argc, char *argv[]){

    int i;
    char * toUp;

    printf("\nnvar() : %d\n",nvar());
    
    /*Question 2*/
    /* printf("mprintenv() : \n\n");
    mprintenvAll();  */
   
    for(i = 1; i < argc ; i++) {
        toUp = mprintenv(argv[i]);
        printf("%s \n",toUp);
    }
    exit(EXIT_SUCCESS);
    return 0;
}
