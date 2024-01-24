#include <stdio.h>

void foo(int s) {
    printf("foo() appele avec: %d\n", s);
}

void bar(int s) {
    printf("foo() appele avec: %d\n", s);
}

int main() {
    int tab[10] = {1,5,8,3,1,9,10,25,32,42}; int i;
    int sum = 0;

    for (i = 0; i <= 9 ; i++) 
    {
        sum = sum + tab[i];
    }
    
    foo(sum); bar(sum);
    //foo() appele avec: 136
    //foo() appele avec: 136  
}
