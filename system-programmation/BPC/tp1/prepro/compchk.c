#ifndef SIZE
#error "définissez SIZE avec l'option -D SIZE=n"
#define SIZE 0
#endif
#if SIZE & (SIZE-1)
#warning "SIZE devrait être une puissance de 2."
#endif

int main(void)
{
    return SIZE;
}
