#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Question 1 
On a un problème lorsque la mémoire est 
insuffisante pour allouer une chaîne en double
*/
char *mstrdup(const char *str){
  size_t len = strlen(str) + 1;
  char *new = malloc(len);
  if (new == NULL)
    return NULL;
  return memcpy(new, str, len);
}

/*Question 2 */
int main(int argc, char *argv[]){
    int i;
    char * copy = NULL;

    for(i=1; i< argc; i++){
        copy = mstrdup(argv[i]);
        printf("Address de copie    : %p\n",(void *)copy);
        printf("Address de argv[%d] : %p\n",i,(void *)argv[i]);
        printf("%s ", copy);
        puts("\n");
        free(copy);
    }  
    return 0;
}
