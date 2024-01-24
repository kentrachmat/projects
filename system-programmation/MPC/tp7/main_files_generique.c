#include "files_generique.h"
#define TAB 5

/*
> make main_files_generique
*/
/*Q2*/
void test_gfifo(){
    struct gfifo_s *gfifo;
    int tab[TAB] = {12,13,14,15,16};
    int i ;
    void *deleted;

    gfifo = gfifo_new();
    
    for(i=0; i<TAB; i++){
        gfifo_enqueue(gfifo, &tab[i]);  
        gfifo_apply(gfifo, printg_int); putchar('\n');
    } 
    
    printf("size : %d\n\n",gfifo_size(gfifo));
    

    for(i=0; i<TAB; i++){
        gfifo_dequeue(gfifo, &deleted);  
        printf("delete : %d \n",*(int *)deleted);
        gfifo_apply(gfifo, printg_int); putchar('\n');
    }

    gfifo_apply(gfifo, printg_int); putchar('\n');
    gfifo_del(gfifo);
}

int main(void){
    test_gfifo();
    return 0;
}
