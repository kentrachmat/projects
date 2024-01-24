#include <stdlib.h>             /* pour random() */
#include <string.h>             /* pour memcmp() */
#include <assert.h>             /* pour assert() */
#include <stdio.h>
#define SIZE 1021

/*Question 1 copie generique*/
void mmemcpy(void *to, const void *from, unsigned int size){
  char *cto = (char*)to;
  const char *cfrom = (char*)from;
  while (size--)
    *cto++ = *cfrom++;
}

/*Question 1 echange generique*/
void memswap(void* a, void* b, int size)
{  
    void *tmp = malloc(size);
    memcpy(tmp, a, size);
    memcpy(a, b, size);
    memcpy(b, tmp, size);
    free(tmp);
}

/*Question 2 copie generique*/
void test_mmemcpy()
{
    char     tc_orig[SIZE], tc_dest[SIZE];
    long int ti_orig[SIZE], ti_dest[SIZE];
    int i;

    /* initialisation */
    for(i=0 ; i<SIZE ; i++) {
        tc_orig[i] = random() % 256;
        tc_dest[i] = random() % 256;
        ti_orig[i] = random(); 
        ti_dest[i] = random();
    }

    /* copie */
    mmemcpy(tc_dest, tc_orig, SIZE);
    mmemcpy(ti_dest, ti_orig, SIZE * sizeof(long int));

    /* vérification */
    assert(memcmp(tc_orig, tc_dest, SIZE) == 0); 
    assert(memcmp(ti_orig, ti_dest, SIZE * sizeof(long int)) == 0);
}

/*Question 2 echange generique*/
void test_mmemswap() 
{
    char     a[SIZE], b[SIZE];
    long int c[SIZE], d[SIZE];
    char     testa[SIZE], testb[SIZE];
    long int testc[SIZE], testd[SIZE];
    int i;

    /* initialisation */
    for(i=0 ; i<SIZE ; i++) {
        a[i] = random() % 256;
        b[i] = random() % 256;
        c[i] = random(); 
        d[i] = random();
    }

    memcpy(a, testa, SIZE);
    memcpy(b, testb, SIZE);
    memcpy(c, testc, SIZE);
    memcpy(d, testd, SIZE);
    
    
    /* echange */
    memswap(b,a, SIZE);
    memswap(c, d, SIZE * sizeof(long int));

    /* vérification */
    assert(memcmp(testb, a, SIZE) == 0);  
    assert(memcmp(testa, b, SIZE) == 0);  
    assert(memcmp(testd, c, SIZE) == 0);  
    assert(memcmp(testc, d, SIZE) == 0);
}

int main(void){
    test_mmemcpy();
    test_mmemswap();
    printf("Tous les tests sont passes !\n");
    return 0;
}
