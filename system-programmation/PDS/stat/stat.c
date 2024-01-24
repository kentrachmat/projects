#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <pwd.h>
#include <grp.h>

int main(int argc, char **argv)
{
  struct stat stuctStat;

  if (argc != 2)
  {
    fprintf(stderr, "Erreur avec le nombre d'arguments, veuillez utiliser un et un seul argument Exemple : %s <pathname>\n", argv[0]);
    exit(EXIT_FAILURE);
  }

  if (stat(argv[1], &stuctStat) == -1)
  {
    perror("stat"); //Cette fonction affiche, sur la sortie d'erreur stderr et sous forme textuelle, le message d'erreur associé à la variable errno.
    exit(EXIT_FAILURE);
  }

  printf("Nom du fichier : %s\n",argv[1]);
  printf("Taille : %lld octets      Blocs : %d        Blocs d'E/S : %ld  \n",(long long) stuctStat.st_size,(int) stuctStat.st_blocks,(long) stuctStat.st_blksize);
  printf("Type du fichier : ");
  switch (stuctStat.st_mode & S_IFMT) {
    case S_IFBLK:  printf("périphérique bloc\n");       break;
    case S_IFCHR:  printf("périphérique caractère\n");  break;
    case S_IFDIR:  printf("répertoire\n");              break;
    case S_IFIFO:  printf("FIFO/tube\n");               break;
    case S_IFLNK:  printf("lien symbolique\n");         break;
    case S_IFREG:  printf("fichier ordinaire\n");       break;
    case S_IFSOCK: printf("socket\n");                  break;
    default:       printf("inconnu ?\n");               break;
  }
  printf("Numéro d'inoeud  : %ld \n", (long) stuctStat.st_ino);
  printf("Nombre de liens : %d\n", (int) stuctStat.st_nlink);
  printf("UID : (%d/%s)   GID :(%d/%s)  ",(int)stuctStat.st_uid,getpwuid(stuctStat.st_uid)->pw_name,(int)stuctStat.st_gid,getgrgid(stuctStat.st_gid)->gr_name);
  printf("Nom utilisateur : %s\n",getpwuid(stuctStat.st_uid)-> pw_gecos);      
  printf("Droit : %o%o (octal)\n",stuctStat.st_mode&(S_ISUID|S_ISGID|S_ISVTX)	,stuctStat.st_mode & (S_IRWXU | S_IRWXG | S_IRWXO));
  /*note : Le Set user ID(ISUID) permet d'executer le ficher ou le programme avec les droits du propriétaire*/
  printf("Dernier accès au fichier  : %s", ctime(&stuctStat.st_atime));
  printf("Dernière modification du fichier: %s", ctime(&stuctStat.st_mtime));
  printf("Dernier changement d'état : %s", ctime(&stuctStat.st_ctime));
  exit(EXIT_SUCCESS); 
}
