#include "files_generique.h"

struct gfifo_s *gfifo_new(){
    struct gfifo_s *newHead = NULL;
    newHead = malloc(sizeof(struct gfifo_s));
    if(newHead == NULL){
        return NULL;
    }
    newHead->debut = NULL;
    newHead->fin = NULL;
    return newHead;
}

int gfifo_del(struct gfifo_s *f){
    struct gfifo_node_s *head = f->fin;
    while(head != NULL){
        free(head);
        f->fin = f->fin->suivant;
        head = f->fin;
    }
    free(f);
    return 0;
}

int gfifo_size(struct gfifo_s *f){
    struct gfifo_node_s *newhead = f->fin;
    int i=0;
    while(newhead != NULL){
        i++;
        newhead = newhead->suivant;
    }
    return i;
}

int gfifo_enqueue(struct gfifo_s *f, void *p){
    struct gfifo_node_s *data;

    data = malloc(sizeof(struct gfifo_node_s));
    if(data == NULL){
        return -1;
    }
    data->valeur = p;
    data->suivant = NULL;
    if(f->debut==NULL && f->fin==NULL){
        f->fin = f->debut = data;
    }
    else{
        f->debut->suivant= data;
        f->debut=data; 
    }
    return 0;
}

int gfifo_dequeue(struct gfifo_s *f, void **p){
    struct gfifo_node_s *data;
    if(f->debut == NULL && f->fin == NULL){
        return -1;
    }
    data = f->fin;
    (*p) = data->valeur;
    f->fin = data->suivant;
    free(data);
    return 0;
}

int gfifo_apply(struct gfifo_s *f, gfunc_t *fn){
    struct gfifo_node_s *newhead = f->fin;
    while(newhead != NULL){
        fn(newhead->valeur);
        newhead = newhead->suivant;
    }
    return 0;
}

void printg_int(void * i){
    printf("→ %d ", *((int *)i));
}
