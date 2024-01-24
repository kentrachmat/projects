#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
  int i,caracLu;
  FILE * inputFile;

  //verification des paramètres
  if (argc < 2)
  {
    fprintf(stderr, "Usage: %s <pathname>\n", argv[0]);
    exit(EXIT_FAILURE);
  }

  //ouverture en boucle des fichiers dans le cas ou mcat-lib prend plusieurs fichiers en paramètres.
  for(i = 1 ; i < argc ; i++)
  {
    //lecture d'un fichier
    if ((inputFile= fopen(argv[i],"r")) == NULL)
    {
      perror("fopen");
      exit(EXIT_FAILURE);
    }
    //lecture tant que le fichier n'est pas a la fin
    while((caracLu = fgetc(inputFile)) != EOF)
    {
      fputc(caracLu,stdout);
    }
    fclose(inputFile);
  }
  //fermeture
  return 0;
}
