#include "struct.h"
#include "util.h"
#include "arrondi.h"

/*Exercice 19
Taille du premier fichier dans une archive
*/
void lstar_V1(void){
  struct posix_header s;
  
  readArchive(&s,HEADER_SIZE);
  printf("Size : %ld \n", strtol(s.size, 0, 8));
}

/*Exercice 20 
Taille des deux premiers fichiers dans une archive*/
void lstar_V2(void){
  struct posix_header s;
  struct posix_header scontenue;
  long size = 0 ;

  //lecture et affichage de taille du 1er fichier
  readArchive(&s,HEADER_SIZE);
  size=strtol(s.size, 0, 8);
  printf("Size : %ld \n",size);

  //Lecture du contenu
  readArchive(&scontenue,arrondi512(size)); 
  
  readArchive(&s,HEADER_SIZE);
  printf("Size : %ld \n", strtol(s.size, 0, 8));
}


/*Exercice 21 
Liste des tailles des fichiers
*/
void lstar_V3 (void)
{
  struct posix_header s;
  struct posix_header scontenue;
  long size = 0 ;
  readArchive(&s,HEADER_SIZE);
  s.magic[TMAGLEN-1]='\0';
  while (!strncmp(s.magic, TMAGIC,TMAGLEN) && !strncmp(s.version, TVERSION, TVERSLEN)){

    //affichage de la taille du fichier
    size=strtol(s.size, 0, 8);
    printf("Size : %ld \n",size);

    //Lecture du contenu
    readArchive(&scontenue,arrondi512(size)); 

    //lecture du fichier suivant
    readArchive(&s,HEADER_SIZE);

    s.magic[TMAGLEN-1]='\0';
  }
}

/*Exercice 22
Liste des tailles et des informations d'un fichier
*/
int lstar_V4 (void)
{
  struct posix_header s, scontenue;
  char droit[DROIT_MAX_SIZE],type1[1],type2[TYPE_FICHIER_MAX_SIZE],time[TIME_MAX_SIZE];
  long size = 0 ;
  long localTime;
  unsigned long chk1 , chk2;
  
  
  
  readArchive(&s,HEADER_SIZE);
  s.magic[TMAGLEN-1]='\0';
  while (!strncmp(s.magic, TMAGIC,TMAGLEN) && !strncmp(s.version, TVERSION, TVERSLEN)){
    localTime = strtol(s.mtime, 0, 8);
    check_type(s.typeflag, type1, type2);
    chk1=0;

    //calcul du chcksum
    chk1 = check_chksum(&s);
    chk2= strtol(s.chksum, 0, 8);
    if (chk1 != chk2) {
    fprintf(stderr, "archive corrompu !!\n");
    return EXIT_FAILURE;
    }


    //affichage du type de fichier (version caractère)
    printf("%s",type1);

    //affichage des droits
    droits(strtol(s.mode,0, 8), droit);
    printf("%s ",droit);

    //affichage GID/UID
    s.gname[TGIDLEN-1]='\0';
    s.gname[TUDILEN-1]='\0';
    printf("%s/%s    ",s.gname,s.uname);

    //affichage de la date de modification
    strftime(time, TIME_MAX_SIZE,"%Y-%m-%d %H:%M",localtime(&localTime));
    printf("%s   ",time);

    //affichage du type de fichier (chaine de caractère)
    printf("%s ",type2);

    //affichage de la taille du fichier
    size=strtol(s.size, 0, 8);
    printf("%ld ",size);

    //affichage du nom du fichier
    s.name[TNAMELEN-1]='\0';
    if(s.prefix[0]=='\0'){
      printf("%s\n",s.name);
    }
    else{
      printf("%s/%s\n",s.prefix,s.name);
    }
    
    //Lecture du contenu
    readArchive(&scontenue,arrondi512(size)); 

    //lecture du fichier suivant
    readArchive(&s,HEADER_SIZE);

    s.magic[TMAGLEN-1]='\0';
  }

  return(0);
}