#include <stdio.h>

int put_digit(int digit)
{
    if (0<= digit && digit <= 9){
    int code_ascii = digit + 48; 
    if (putchar(code_ascii) == EOF){ //  EOF ou -1
        return -1;
    }
    return 0;
    }
    return -1;
}

int putdec(long int nombre)
{
    if (nombre == 0) putchar('0');

    if (nombre < 0) {
        nombre = -nombre;
        putchar('-');
    }
    
    int index = 1000000000;

    while(index > 0 && ((nombre / index) % 10 == 0)){
        index = index / 10;
    }

    while(index > 0){
        int c = (nombre / index) % 10;
        c = (c<0) ? -c : c;
        put_digit(c);
        index = index / 10;
    }
    return 0;
}

int main() {
    int ln1 = __LINE__;
    printf("Line : %d\n",ln1); // 40
    putdec(214);
    printf("\n");
    putdec(500);
    int ln2 = __LINE__;
    printf("\nLine : %d\n",ln2); // 45
}
