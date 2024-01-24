#include <stdio.h>
#include <stdlib.h>
#define SIZE 10

/*Question 1*/
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

/*Question 2*/
int main(void){
    float tab[]={1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9 ,2.0};
    float * a = search_dicho(1.1, tab, SIZE);
    float * b = search_dicho(1.9, tab, SIZE);
    float * c = search_dicho(2.1, tab, SIZE);

    if ((a == tab) && (b == tab+8) && (c == NULL)){
        printf("OK ;)\n"); 
        exit(EXIT_SUCCESS);
    } else{
        printf("KO ;(\n"); 
        exit(EXIT_FAILURE);
    }
	return 0;
}
