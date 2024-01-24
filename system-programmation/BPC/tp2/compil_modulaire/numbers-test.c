#include "put_numbers.h"

int main()
{
    int c = 5;
    int random,i = 0;
    srand(time(0));

    printf("\nputbin test\n");
    for (i = 0; i < c; i++) {
    random = (rand() % (50 - (-25)) + 1) -25; 
    printf("input %d : ",random);
    putbin(random); 
    putchar('\n');
    }
    
    printf("\nputhex test\n");
    for (i = 0; i < c; i++) {
    random = (rand() % (75 - (-25)) + 1) -25; 
    printf("input %d : ",random);
    puthex(random); 
    putchar('\n');
    }

    printf("\nputdec test\n");
    for (i = 0; i < c; i++) {
    random = (rand() % (75 - (-25)) + 1) -25;
    printf("input %d : ",random);
    putdec(random); 
    putchar('\n');
    }
    putchar('\n');
}
