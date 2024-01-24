#include <stdio.h>

int f(int x)
{
    return 2*x;
}
int main()
{
    int ln1 = __LINE__; // 9
    putchar('0'+f(3));
    int ln2 = __LINE__; // 11
    return f(3);
}
