#include <stdio.h>

static int put_digit(int digit)
{
    if (0<= digit && digit <= 9){
    int code_ascii = digit + 48; 
    if (putchar(code_ascii) == EOF){
        return -1;
    }
    return 0;
    }
    return -1;
}

int putdec(long int nombre)
{
    int index = 1000000000;
    if (nombre == 0) putchar('0');

    if (nombre < 0) {
        nombre = -nombre;
        putchar('-');
    }

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
