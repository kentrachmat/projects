#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>
#include <sys/types.h>

#define INCONNUE -1
#define TAB_SIZE 30

static int fibtab[TAB_SIZE];
static sem_t semtab[TAB_SIZE];


struct arg_s {
    int n;
    int ret;
};

int fib(int n);

void *fonct_wrapper(void *arg)
{
    struct arg_s *args = (struct arg_s *) arg;
    args->ret=fib(args->n);
    return NULL;
}

int fib (int n)
{
    int ret = 0;

    if (n<2){
        return n;
    }

    sem_wait(&semtab[n]);

    if (fibtab[n] == INCONNUE ){

        struct arg_s x,y;
        x.n = n-1;
        y.n = n-2;
        pthread_t tid1,tid2;

        pthread_create(&tid1,NULL,fonct_wrapper,(void *)&x);
        pthread_create(&tid2,NULL,fonct_wrapper,(void *)&y);

        pthread_join(tid1,NULL);
        pthread_join(tid2,NULL);

        fibtab[n] = x.ret + y.ret;
    }
    ret = fibtab[n];

    sem_post(&semtab[n]);

    return ret;
}


int main(int argc, char **argv){
    int n,res;

    if(argc <2){
        printf("Veuillez saisir le nombre de fibonacci " );
        exit(EXIT_FAILURE);
    }

    for (int i=0; i<TAB_SIZE; i++){
        fibtab[i] = INCONNUE;
        sem_init(&semtab[i],0,1);
    }

    n = atoi(argv[1]);
    res = fib(n);

    printf("fib(%d) = %d\n",n,res);
    return 0;
}
