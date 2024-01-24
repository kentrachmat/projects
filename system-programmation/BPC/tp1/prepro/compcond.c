extern int putchar(int c);

#if NO_LOG==1

int logchar(int c) {
    return 0;
}

#else

int logchar(int c) {
    return putchar(c);
}

#endif

int main() {
    int i=1;
    i=3*i;
    logchar('0'+i);
    return i;
}
