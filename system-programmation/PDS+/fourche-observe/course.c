#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define MAX_FILS 10
#define MAX_COMPTEUR 100000000

void counter(int ctr, int pid){
    int i = 0;

    // Compte jusqu'a 100 millions
    while (i < MAX_COMPTEUR){
        i++;
    }

    // Affichage du message
    printf("Processus numéro %d (pid : %d) \"Je suis à mi-chemin je continue ma course !\" \n\n",ctr, pid);
    fflush(stdout);

     // Compte jusqu'a 100 millions
    while (i < MAX_COMPTEUR){
        i++;
    }
}
 

int main() {
    int status;
    pid_t idProcessus;

    //Creation des fils
    for(int i = 1; i < MAX_FILS+1; i++){
            switch (fork()){
                case -1:
                    perror("fork : erreur dans la création d'un fork");
                    exit(EXIT_FAILURE);
                case 0:
                    counter(i, getpid());
                    exit(EXIT_SUCCESS);
            }
        }
        
    // attente des fils 
    for(int i=1; i < MAX_FILS+1; i++){
        if((idProcessus = wait(&status) )==-1){
            perror("wait");
            exit(EXIT_FAILURE);
        }
        printf("le proccessus %d est arrivé en %d positions ! \n",idProcessus,i);
        fflush(stdout);
    }
}
 