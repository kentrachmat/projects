#include "struct.h"


void readArchive(struct posix_header *s,long sizeMax){
  int octetLuTotal = 0;
  ssize_t octetLuCourant = 0;
  while (octetLuTotal < sizeMax){
    octetLuCourant=read(fd,((unsigned char*)s), HEADER_SIZE);
    if (octetLuCourant == -1 ){
      perror("pread");
      exit(EXIT_FAILURE);
    }
    octetLuTotal+=octetLuCourant;
  }
  if(octetLuTotal != sizeMax){
    fprintf(stderr,"Erreur avec l'appel systeme pread");
    exit(EXIT_FAILURE);
  }
}

int check_chksum(struct posix_header* s) {
    int sum = 0;
    for (int i = 0; i < 512; i++) {
      sum += (i >= 148 && i<= 155) ? 32 : *((char*)s+i);
    }
    return sum;
}

void droits(mode_t mode, char * droit) {
  for (int i = 0; i < 9; i++) {
    droit[i] = (mode & (1 << (8-i))) ? DROIT[i%3] : '-';
  }
  droit[9] = '\0';
}

void check_type(char c, char *type1, char *type2) 
{
  switch (c) {
    case REGTYPE:   
      strcpy(type2, "fichier ordinaire");  
      strcpy(type1, "-");  
      break;
    case AREGTYPE:  
      strcpy(type2, "fichier ordinaire");  
      strcpy(type1, "-");     
      break;
    case LNKTYPE:   
      strcpy(type2, "lien physique");      
      strcpy(type1, "-");     
      break;
    case SYMTYPE:   
      strcpy(type2, "lien symbolique");    
      strcpy(type1, "l");      
      break;
    case CHRTYPE:                                               
      strcpy(type1, "-");     
      break;
    case BLKTYPE:                                               
      strcpy(type1, "-");     
      break;
    case DIRTYPE:   
      strcpy(type2, "répertoire");         
      strcpy(type1, "d");      
      break;
    case FIFOTYPE:                                              
      strcpy(type1, "-");     
      break;
    case CONTTYPE:                                              
      strcpy(type1, "-");     
      break;
    default:       
      puts("error");                         
      strcpy(type1, "_");      
      break;
  }
}