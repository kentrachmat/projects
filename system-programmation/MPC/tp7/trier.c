#include "files_entiers.h"
#include "files_generique.h"
#define TAB 5

/* fusion de deux files d'entiers triées.
   condition d'utilisation : f1 et f2 triées
   effet de bord : f1 et f2 vides
   résultat : une nouvelle file d'entiers triée */
struct ififo_s * ififo_merge(struct ififo_s *f1, struct ififo_s *f2){
    struct ififo_s *ififo;
    int val;
    ififo = ififo_new();
    while( f1->fin != NULL ){
        ififo_dequeue(f1, &val);
        ififo_enqueue(ififo,val);
    }
    while( f2->fin != NULL ){
        ififo_dequeue(f2, &val);
        ififo_enqueue(ififo,val);
    }
    ififo_del(f1);
    ififo_del(f2);
    return ififo;
}


void trier(struct gfifo_s* gfifo){
  struct ififo_s  *fifo1, *fifo2;
  if(gfifo->debut != NULL){
    while(gfifo->debut->suivant != NULL){
      gfifo_dequeue(gfifo,(void **) &fifo1);
      gfifo_dequeue(gfifo,(void **) &fifo2);
      gfifo_enqueue(gfifo, ififo_merge(fifo1 , fifo2));
    }
  }
}


int main(void){
    int i;
    int tab1[TAB] = {2,4,6,8,10};
    int tab2[TAB] = {1,3,5,7,9};
    struct ififo_s *fifo1, *fifo2, *fifoMerge;
    struct gfifo_s *gfifo;

    fifo1 = ififo_new();
    fifo2 = ififo_new();
    gfifo = gfifo_new();

    for(i=0; i<TAB; i++){
        ififo_enqueue(fifo1, tab1[i]);
        gfifo_enqueue(gfifo, fifo1);
        ififo_enqueue(fifo2, tab2[i]);
        gfifo_enqueue(gfifo, fifo2);
    }

    puts("FIFO 1: ");
    ififo_apply(fifo1, print_int); putchar('\n');
    puts("FIFO 2: ");
    ififo_apply(fifo2, print_int); putchar('\n');

    puts("FIFO MERGE: ");
    fifoMerge = ififo_merge(fifo1, fifo2);
    ififo_apply(fifoMerge, print_int); putchar('\n');

    puts("FIFO TRIER: ");
    trier(gfifo);
    gfifo_apply(gfifo, printg_int); putchar('\n');
  
    gfifo_del(gfifo);
    ififo_del(fifoMerge);
    return 0;
}
