 
#ifndef FILES_ENTIERS
#define FILES_ENTIERS
#include <stdio.h>
#include <stdlib.h>

/*Q1*/
struct ififo_node_s {
    int valeur;
    struct ififo_node_s * suivant;
};

/*Q2*/
struct ififo_s{
    struct ififo_node_s * debut;
    struct ififo_node_s * fin;
};

/*Q3
Comment est représentée la file vide ?
- la file est vide quand debut == NULL / debut = fin = NULL
*/

struct ififo_s *ififo_new();
int ififo_is_empty(struct ififo_s *f);
int ififo_enqueue(struct ififo_s *f, int val);
int ififo_dequeue(struct ififo_s *f, int *val);
int ififo_head(const struct ififo_s *f);
typedef void (func_t)(int);
int ififo_apply(struct ififo_s *f, func_t *fn);
void ififo_del(struct ififo_s *f);
void print_int(int i);

#endif
