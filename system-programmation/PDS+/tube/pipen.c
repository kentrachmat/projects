#include <unistd.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void getArgs(int x, char *tab[], char *argv[], int argc){

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
            tab[posArg] = argv[j];
            posArg++;
        }
        j++;
    }

    tab[posArg] = (char *) NULL;
}

int main(int argc, char *argv[])
{


    //Etape 1 : Verification des arguments 
    assert(strcmp(argv[argc-1], "to") != 0); 
    assert(argc >= 4); 
    
    //Etape 2 : Inialisation des variables
    char *argv_cmd[255];
    int i;
    int nbto = 0;
    
    for(i = 0 ; i < argc ; i++){
    	if(strcmp(argv[i], "to") == 0){
    		nbto++;
    	}
    }
    int fd[nbto][2];


    i = 0;
    while(i < nbto){
        //Etape 3 : Relie la commande i à la commande i+1
        assert(pipe(fd[i]) != -1); 

        switch(fork())
        {
            case -1 :
                return EXIT_FAILURE;

            case 0 :
                assert(dup2(fd[i][1], STDOUT_FILENO) != -1); 
                assert(close(fd[i][1]) != -1);

                if(i > 0){
                    
                    // duplique son descripteur d’accès en lecture sur le tube créé à l’itération précédente vers son entrée standard
                    assert(dup2(fd[i-1][0], STDIN_FILENO) != -1); 

                    // ferme tous les morceaux de tubes inutilisés
                    assert(close(fd[i-1][0]) != -1);
                }

              

                // exécute la commande commande i et termine
                getArgs(i, argv_cmd, argv, argc);
                assert(execvp(argv_cmd[0], argv_cmd) != -1);
                exit(EXIT_FAILURE);

            default :
            // le père ferme les morceaux de tube inutiles
            assert(close(fd[i][1]) != -1); // partie en écriture sur le tube qui vient d’être créé
            if(i > 0){
                assert(close(fd[i-1][0]) != -1); // partie en lecture sur le tube de l’itération précédente
            }
        }
        i++;
    }

    // le père exécute la dernière commande
    assert(dup2(fd[i-1][0], STDIN_FILENO) != -1);
    assert(close(fd[i-1][0]) != -1);
    getArgs(i, argv_cmd, argv, argc);
    assert(execvp(argv_cmd[0], argv_cmd) != -1);
    exit(EXIT_FAILURE);
}
