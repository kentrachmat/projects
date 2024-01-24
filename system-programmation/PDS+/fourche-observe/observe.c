#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define MAX_FILS 5
#define MAX_COMPTEUR 100000000

//2) Chaque processus affiche leurs PID ET s'endorme pendant 5 seconde
void counter(){
    while (1){
        printf("pid : %d | ppid : %d\n", getpid(), getppid());
        fflush(stdout);
        sleep(8);
    }
}

//4) Attente de la terminaison des fils
void waitFils(){
    pid_t pid_fils;
    int status;

    for(int i=0; i < MAX_FILS; i++){

        //Cas d'erreur
        if( (pid_fils = wait(&status)) == -1){
            perror("wait");
            exit(EXIT_FAILURE);
        }

        //Cas pour chaque fils
        else {
            printf("Fin du Processus numéro : %d", pid_fils);
            fflush(stdout);
            printf("\n");
        }
    }
}
 
int main() {
    //1) Création des processus fils
    for(int i = 0; i < MAX_FILS; i++){
        switch (fork()){
            case -1:
                perror("fork : erreur dans la création d'un fork");
                exit(EXIT_FAILURE);
            case 0:
                counter();
                exit(EXIT_SUCCESS);
        }
    }

    //3) Attente saisie clavier
    sleep(0.8);
    fflush(stdout);
    printf("Veuilliez saisir une touche avant de continuer ...\n");
    fflush(stdout);
    getchar();


    //4) Attente de la terminaison des fils
    waitFils();

    return EXIT_SUCCESS;
}
 