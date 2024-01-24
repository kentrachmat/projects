#include <stdio.h>
#define MAX 10

typedef int (fct)(int); 

/*Question 1,2,3*/
int filter_int(const int *src, int *dst, unsigned int size, fct *fa){
    int i,n;
    int* src_int = (int*)src;
    int* dst_int = (int*)dst;
    for(n=i=0; i<size; i++){
        if( fa(src_int[i]) ){
            dst_int[n++] = src_int[i];
        }
    }
    return n;
}

int f(int a){
    return (a % 2 == 0)? 1:0;
}

int main(void){
    int i,end;
    int src[MAX] = {1,2,3,4,5,6,7,8,9,10};
    int dest[MAX];
    end = filter_int(src,dest,MAX,&f);

    printf("les entiers pairs : \n");
    for(i=0; i < end ;i++){
        printf("%d\n",dest[i]);
    }
    return 0;
}
