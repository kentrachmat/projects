#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>


typedef int (*func_t) (char *);

static int testfunc(char *);



int multif (func_t f[], char *args[], int n);



int main(int argc, char *argv[]) {
    int nbArgs=argc-1;

    //Gestion des arguments 
    if (nbArgs < 1){
        fprintf(stderr, "Usage: %s {true,false}\n", argv[0]);
        exit(EXIT_FAILURE);
    }
   
    char *args[nbArgs];
    func_t f[nbArgs];

    //Création du tableau d'arguments et de fonction 
    for (int i = 1; i < argc; i++){
        args[i-1]=argv[i];
        f[i-1]=testfunc;
    }
    
    //Execution de la fonction multif()
    int res = multif(f,args,nbArgs);
    /*
    printf("Resultat de multif() = %d\n",res);
    fflush(stdout);
    */
    exit(res);
}



int multif (func_t f[], char *args[], int n){
    int status;
    int resFonction;
    //Création des processus et exécution des fonctions
    for(int i=0;i<n;i++){
        switch (fork()){
            case -1:
                perror("fork : erreur dans la création d'un fork");
                exit(EXIT_FAILURE);
            case 0:
                resFonction= f[i](args[i]);
                /*
                printf("Execuction de la fonction f%d, le resultat est %d \n",i,resFonction);
                fflush(stdout);
                */
                exit(resFonction);
        }
    }

    //Attente des  processus et récupération de leurs status
    int flag = EXIT_SUCCESS;
    for(int i=0;i<n;i++){
        if(wait(&status)==-1){
            perror("wait");
            exit(EXIT_FAILURE);
        }

        if(WIFEXITED(status) && WEXITSTATUS(status)== EXIT_FAILURE) {
            flag = EXIT_FAILURE;
        }
    }
    return (flag);
}


int testfunc(char args[]){
    int numReturn = EXIT_FAILURE;
    if(strcmp(args,"true") == 0){
        numReturn = EXIT_SUCCESS;
    }
    else if(strcmp(args,"sleep5") ==0){
        numReturn = EXIT_SUCCESS;
        sleep(5);
    }
    else if(strcmp(args,"sleep2") ==0){
        numReturn = EXIT_SUCCESS;
        sleep(2);
    }
    return (numReturn);
}