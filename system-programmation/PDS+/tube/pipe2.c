#include <unistd.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void getArgs(int x, char *argv_cmd[], char *argv[], int argc){

    int i = 0, j = 1, posArg = 0;
    bool notto = true;

    while(i < x){
        if(strcmp(argv[j], "to") == 0){
            i++;
        }
        j++;
    }

    while(notto && j < argc){
        if(strcmp(argv[j], "to") == 0){
            notto = false;
        }
        else{
            argv_cmd[posArg] = argv[j];
            posArg++;
        }
        j++;
    }

    argv_cmd[posArg] = (char *) NULL;
}

int main(int argc, char *argv[])
{
    //Etape 1 : Verification des arguments 
    assert(strcmp(argv[argc-1], "to") != 0); 
    assert(argc >= 4); 
    
    //Etape 2 : Inialisation des variables
    char *argv_cmd[255];
    int pid;
    int fd[2];
    assert(pipe(fd) != -1);

    //Etape 3 : Gestion des processus 
    switch(pid=fork())
    {
        case -1 :
            return EXIT_FAILURE;

        case 0 : /* fils */
            assert(close(fd[0]) != -1);

            assert(dup2(fd[1], STDOUT_FILENO) != -1);
            assert(close(fd[1]) != -1);

            getArgs(0, argv_cmd, argv, argc);

            assert(execvp(argv_cmd[0], argv_cmd) != -1);
            exit(EXIT_FAILURE);

        default : /* père */
            assert(close(fd[1]) != -1);

            assert(dup2(fd[0], STDIN_FILENO) != -1);
            assert(close(fd[0]) != -1);

            getArgs(1, argv_cmd, argv, argc);

            assert(execvp(argv_cmd[0], argv_cmd) != -1);
            exit(EXIT_FAILURE);
    }
    return 0;
}
