#include <stdio.h>
#include <stdlib.h>
//open
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
//read
#include <unistd.h>


int main(int argc, char const *argv[])
{
    // Etape 1 Initialisation
    int i, nbOctetLu, fd, taille_tampon;
    char* buff = NULL;

    // Etape 2 Vérification des paramètres
    if (argc < 3){
        fprintf(stderr, "Usage: %s TAILLE_TAMPON <pathname>\n", argv[0]);
        exit(EXIT_FAILURE);     
    }
    taille_tampon = atoi(argv[1]);
    buff = malloc(taille_tampon);


    // Etape 3  Ouverture en boucle des fichiers dans le cas où "cat" prend plusieurs fichiers en paramètres.
    for (i = 2; i < argc; i++)
    {
        // etape 3.1 Ouverture d'un fichier
        if ((fd = open(argv[i], O_RDONLY)) == -1)
        {
            perror("open");
            exit(EXIT_FAILURE);
        }

        // etape 3.2 Lecture du fichier et écriture dans la sortie standard
        while((nbOctetLu = read(fd,buff,taille_tampon))){
              write(STDOUT_FILENO,buff,nbOctetLu);
        }
    
    close(fd);
    }

    // etape 4 Fermeture
    free(buff);
    return 0;
}

  
  
  


