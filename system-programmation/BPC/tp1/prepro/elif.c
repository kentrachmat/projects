extern int putchar(int c);
extern int getchar(void);

#define DIGIT 'D'
#define LOWER 'L'
#define UPPER 'U'
#define OTHER 'O'

int
main()
{
    int c;
    for (;;) {
        c = getchar();
        if ('0'<= c && c<='9')
            putchar(DIGIT);
        else if ('a'<=c && c<='z')
            putchar(LOWER);
        else if ('A'<=c && c<='Z')
            putchar(UPPER);
        else 
            putchar(OTHER);
    }
    return 0;
}
