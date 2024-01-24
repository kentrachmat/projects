#include "minmax.h"
#include "abs.h"

int putchar(int c);

int main() {
    putchar('0' + min(3, 4));
    putchar('0' + max(3, 4));
    putchar('0' + abs(-2));
    return 0;
}
