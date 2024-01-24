#include <stdlib.h>
#include <time.h>
#include "functions.h"

int main ()
{
    srand(time(0));
    int random,error1, error2, c = 5;

    printf("Exo 17\n");

    for (int i = 0; i < c; i++) {
    random = rand() % (9 + 1); // 0 <= random <= 9

    printf("input %d : ",random);
    put_digit(random); 
    putchar('\n');
    }

    error1 = put_digit(12); 
    printf("Input 12 : %d \n\n",error1); // -1

    printf("Exo 18\n");

    for (int i = 0; i < c; i++) {
    random = (rand() % (15 + 1)); // 0 <= random <= 15
    
    printf("input %d : ",random);
    put_hdigit(random); 
    putchar('\n');
    }

    error2 = put_hdigit(17); 
    printf("Input 17 : %d \n\n",error2); // -1

    printf("Exo 20\n");
    int i = -2147483648;
    putdec(-i);
    putchar('\n');

    printf("\nExo 21\n");
    for (int i = 0; i < c; i++) {
    random = (rand() % (50 - (-50)) + 1) -50; // -50 <= random <= 50
    printf("input %d : ",random);
    putbin(random); 
    putchar('\n');
    }
    
    printf("\nExo 22\n");
    for (int i = 0; i < c; i++) {
    random = (rand() % (75 - (-25)) + 1) -25; // -25 <= random <= 75
    printf("input %d : ",random);
    puthex(random); 
    putchar('\n');
    }

    return 0;
}