#include "files_entiers.h"

/*Q4*/
struct ififo_s *ififo_new(){
    struct ififo_s *newHead = NULL;
    newHead = malloc(sizeof(struct ififo_s));
    if(newHead == NULL){
        return NULL;
    }
    newHead->debut = NULL;
    newHead->fin = NULL;
    return newHead;
}

/*Q5*/
int ififo_is_empty(struct ififo_s *f){
    if(f == NULL){
        return -1;
    }
    return f->debut == NULL && f->fin == NULL;
}

/*Q6*/
int ififo_enqueue(struct ififo_s *f, int val){
    struct ififo_node_s *data;

    data = malloc(sizeof(struct ififo_node_s));
    if(data == NULL){
        return -1;
    }
    data->valeur = val;
    data->suivant = NULL;
    if(ififo_is_empty(f)){
        f->fin = f->debut = data;
    }
    else{
        f->debut->suivant= data;
        f->debut=data; 
    }
    return 0;
}

/*Q7*/
int ififo_dequeue(struct ififo_s *f, int *val){
    struct ififo_node_s *data;
    if(ififo_is_empty(f)){
        return -1;
    }
    data = f->fin;
    *val = data->valeur;
    f->fin = data->suivant;
    free(data);
    return 0;
}

/*Q8*/
int ififo_head(const struct ififo_s *f){
    if(f->debut == NULL){
        return -1;
    }
    return f->debut->valeur;
}

/*Q9*/
int ififo_apply(struct ififo_s *f, func_t *fn){
    struct ififo_node_s *newhead = f->fin;
    while(newhead != NULL){
        fn(newhead->valeur);
        newhead = newhead->suivant;
    }
    return 0;
}

/*Q10*/
void ififo_del(struct ififo_s *f){
    struct ififo_node_s *head = f->fin;
    while(head != NULL){
        free(head);
        f->fin = f->fin->suivant;
        head = f->fin;
    }
    free(f);
}

void print_int(int i){
    printf("→ %d ", i);
}
