
#ifndef LIBGA_H
#define LIBGA_H

struct ga_s {
    unsigned int ga_size;             /* nombre d'éléments alloués */
    int *ga_elements;                 /* les éléments */
}; 


int ga_set(struct ga_s *ga, unsigned int index, int val);
int ga_get(struct ga_s *ga, unsigned int index, int *val);

int ga_new(struct ga_s *ga);
int ga_del(struct ga_s *ga);

#endif
