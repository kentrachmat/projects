
#ifndef FILES_GENERIQUE
#define FILES_GENERIQUE
#include <stdio.h>
#include <stdlib.h>

/*Q1*/
struct gfifo_node_s {           /* une cellule de la liste chaînée */
    void *valeur;
    struct gfifo_node_s * suivant;
};
struct gfifo_s {                /* une file */
    struct gfifo_node_s * debut;
    struct gfifo_node_s * fin;
};

struct gfifo_s *gfifo_new();
int gfifo_del(struct gfifo_s *f);

int gfifo_size(struct gfifo_s *f);

int gfifo_enqueue(struct gfifo_s *f, void *p);
int gfifo_dequeue(struct gfifo_s *f, void **p);

typedef void (gfunc_t)(void *);
void printg_int(void *i);
int gfifo_apply(struct gfifo_s *f, gfunc_t *fn);

#endif
