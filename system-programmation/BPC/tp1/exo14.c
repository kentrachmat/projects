#include <stdio.h>

int main() 
{

long int A = 256;
char B = 'A' ;

//printf("A : %c \n", A); erreur car A a un type long
printf("B : %c \n", B ); 
printf("1. B + 1 : %d \n", B + 1); // 66
printf("2. B + '1' : %d \n", B + '1'); // 114
printf("3. B + A : %d \n", B + A); // 321

printf("4. B=65 : %d \n", B = 65); // 65
printf("5. B==65 : %d \n", B == 65); // 1
printf("6. B=256 : %d \n", B = 256); // 0

B = 'A';
printf("7. B++ : %d \n", B++); // 65
B = 'A';
printf("8. ++B : %d \n", ++B); // 66
printf("9. B=255 : %d \n", B = 255); // -1
}