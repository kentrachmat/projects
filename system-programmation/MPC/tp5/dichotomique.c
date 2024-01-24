#include <stdio.h>
#include <stdlib.h>
#define SIZE 10

/*Question 1*/
float * search_interval(float v, float *tab, float *end){
    int size = end - tab;
    int mid = size/2;

    if (tab[mid] == v) 
        return &tab[mid];

    else if (size <= 1) 
        return NULL;

    else if ( v < tab[mid] ){
        return search_interval(v, tab , end - mid );
    } else {
        return search_interval(v, tab + mid + 1, end);
    }
}

/*Question 2*/
float * search_dicho(float v, float *tab, int size){
    float *res = NULL;
    int mid = (size/2);
    if(size < 1){
        res = NULL;
    }
    else if(tab[mid] == v){
        res = tab + mid;
    }
    else if(tab[mid] > v){
        res = search_dicho(v, tab, mid);
    }
    else if(tab[mid] < v){
        res = search_dicho(v, &tab[mid + 1], mid);
    }
    return res;
}

int main(void){
    float tab[SIZE]={1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9 ,2.0};
    float * a = search_interval(1.1, tab, &tab[SIZE-1]);
    float * b = search_interval(1.9, tab, &tab[SIZE-1]);
    float * c = search_interval(2.1, tab, &tab[SIZE-1]);

    /*verification*/
    float * d = search_dicho(1.1, tab, SIZE);
    float * e = search_dicho(1.9, tab, SIZE);
    float * f = search_dicho(2.1, tab, SIZE);
    
    if (  ((a == tab) && (b == tab+8) && (c == NULL)) && 
          ((d == tab) && (e == tab+8) && (f == NULL)) ){
        printf("OK ;)\n"); 
        exit(EXIT_SUCCESS);
    } else{
        printf("KO ;(\n"); 
        exit(EXIT_FAILURE);
    }
	return 0;
}
