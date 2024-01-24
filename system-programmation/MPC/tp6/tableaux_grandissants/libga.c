#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "libga.h"

int ga_set(struct ga_s *ga, unsigned int index, int val){
    int i, *new = NULL;
    if(index < ga->ga_size){
        *(ga->ga_elements + index) = val;
        return 0;
    }
    else{
        new = malloc(sizeof(int) * (index+1));
        if(new == NULL){
            return -1;
        }

        for(i=0; i<ga->ga_size; i++){
            *(new + i) = *(ga->ga_elements + i);
        }
        *(new + index) = val;
        ga->ga_elements = new;
        ga->ga_size = index+1;
        return 0;
    }
    return -1;
}

int ga_get(struct ga_s *ga, unsigned int index, int *val){
    if(index >= ga->ga_size){
        return -1;
    }
    *val = *(ga->ga_elements + index); 
    return 0;
}

int ga_new(struct ga_s *ga){
    ga->ga_size = 0;
    ga->ga_elements = malloc(sizeof(int));
    if(ga->ga_elements == NULL){
        return -1;
    }
    return 0;
}

int ga_del(struct ga_s *ga){
    free(ga->ga_elements);
    ga->ga_size = 0;
    return 0;
}
