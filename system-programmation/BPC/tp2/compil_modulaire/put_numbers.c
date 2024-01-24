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

static int put_hdigit(int h)
{
    if (0<= h && h <= 9){
        if (put_digit(h) == -1){
            return -1;
        }
    }
    if (10<= h && h <= 15){
    int code_ascii = h + 55; 
    if (putchar(code_ascii) == EOF){
        return -1;
    }
    return 0;
    }
    return -1;
}

int putdec(long int nombre)
{
    /* nombre de chifres max -> 10 chiffres*/
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

int putbin(unsigned int b)
{   
    /*afficher tous les bits excepté le derniere*/
    if(b > 1) putbin(b >> 1);
    
    /*derniere bit*/
    putchar('0' + (b & 1));
    return 0;
}


int puthex(unsigned int h)
{   
    if(h > 15) puthex(h >> 4);
    put_hdigit(h & 15);
    return 0;
   
}

void newline()
{
    putchar('\n');
}
