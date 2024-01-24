#include <unistd.h>
#include <assert.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <string.h>
#include <stdio.h>
#include "makeargv.h"

#define TRUE 1
#define FALSE 0


int generique(int nbCommande, void (*funcGestionProcess)(), pid_t all_child[], int returnExit) {

    int flagReturnValue = !returnExit;
    int i;
    int status;
    for (i = 0; i < nbCommande; i++) {
        if (wait(&status) == -1) {
            perror("wait");
            exit(EXIT_FAILURE);
        }

        if (WIFEXITED(status) && WEXITSTATUS(status) == returnExit) {
            flagReturnValue = returnExit;
            funcGestionProcess(returnExit, nbCommande, all_child);
        }
    }
    return flagReturnValue;
}

int conjonction(int nbCommande, void (*funcGestionProcess)(int), pid_t all_child[]) {
    return (generique(nbCommande, funcGestionProcess, all_child, EXIT_FAILURE));
}


int disjonction(int nbCommande, void (*funcGestionProcess)(int), pid_t all_child[]) {
    return (generique(nbCommande, funcGestionProcess, all_child, EXIT_SUCCESS));
}


void stopTheProgramAndkillOtherProcess(int returnValue, int nbCommande, pid_t all_child[]) {
    int i;
    for (i = 0; i < nbCommande; i++) {
        kill(all_child[i], SIGTERM);
    }
    exit(returnValue);
}

void stopTheProgramButDontKillOtherProcess(int returnValue) {
    exit(returnValue);
}

void waitProgramButDontStopOtherProcess() {
}

int main(int argc, char *argv[]) {
   
    pid_t *all_child;
    char **cmdargv, **arg;
    int (*funcStatus)() = conjonction;
    void (*funcGestionProcess)() = waitProgramButDontStopOtherProcess;
    int ido = 1, i, res, nbCommande, posTableauChild = 0;
    pid_t pidChild;



    /* Gestion des différents arguments */
    int flagArgs = TRUE;
    while (flagArgs && ido < argc) {
        flagArgs = FALSE;
        if (!strcmp(argv[ido], "-a")) {
            funcStatus = conjonction;
            flagArgs = TRUE;
        } else if (!strcmp(argv[ido], "-o")) {
            funcStatus = disjonction;
            flagArgs = TRUE;
        }
        if (!strcmp(argv[ido], "-c")) {
            funcGestionProcess = stopTheProgramButDontKillOtherProcess;
            flagArgs = TRUE;
        } else if (!strcmp(argv[ido], "-k")) {
            funcGestionProcess = stopTheProgramAndkillOtherProcess;
            flagArgs = TRUE;
        }
        ido++;
    }
    ido--;


    /* tableau des processus */
    nbCommande = argc - ido;

    all_child = malloc(sizeof(pid_t) * nbCommande);

    /* Gestion des commandes */
    for (i = ido ; i < argc; i++) {
        cmdargv = makeargv(argv[i]);
        assert(cmdargv != NULL);
        switch (pidChild = fork()) {
            case -1:
                perror("fork");
                exit(EXIT_FAILURE);
            case 0 :
                arg = cmdargv;
                execvp(cmdargv[0], arg);
                assert(0);

            default:
                all_child[posTableauChild] = pidChild;

        }
        freeargv(cmdargv);
        posTableauChild++;
    }

    res = funcStatus(nbCommande, funcGestionProcess, all_child);

    free(all_child);
    exit(res);
}