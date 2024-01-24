#include "struct.h"
#include "lstar.h"

int main(int argc, char **argv){
  if (argc == 1){
    fd = STDIN_FILENO;
  }
  else if (argc ==2){
    fd = open(argv[1],O_RDONLY);
  }
  else{
    fprintf(stderr,"usage %s <pathname>",argv[0]);
    exit(EXIT_FAILURE);
  }
  lstar_V4();
  return 0;
}
