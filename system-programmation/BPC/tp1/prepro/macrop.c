extern int putchar(int c);

#define bit(i,j) (i>>j)&1

int main() {
    int i=16;
    int i0=bit(i,0);
    int i4=bit(i,4);
    putchar('0'+i0);
    putchar('0'+i4);
    putchar('\n'); 
    putchar('0'+bit(i,0));
    putchar('0'+bit(i,4));
    putchar('\n'); 
}
