#include <pthread.h>
#include <stdio.h>
#include <assert.h>
#include <unistd.h>
#include <semaphore.h>
#define N 20

int compteur = 0;
sem_t sem1, sem2;

struct fonct_arg_s{
    int i;
};

void a(int i) {
    sleep(1);
    printf("a%d\n", i);
    sleep(1);
}

void b(int i) {
    sleep(1);
    printf("b%d\n", i);
    sleep(1);
} 

void *p(int n) {
    assert(n>=0);
    int i;
    a(n);
    assert(sem_wait(&sem2) == 0); 
    compteur++;
    if(compteur < N){
        assert(sem_post(&sem2) == 0);
        assert(sem_wait(&sem1) == 0);
    } 
    else {
        for (i = 0; i < N-1; i++){
            assert(sem_post(&sem1) == 0);
        }
    }
    b(n);
    return NULL;
}

void *fonct_wrapper(void *arg) {
    struct fonct_arg_s *p_arg = arg;
    p(p_arg->i);
    return NULL;
}

int main() {
    pthread_t tid[N];
    struct fonct_arg_s fa[N];
    int i;

    assert(sem_init(&sem1, 1, 0) == 0);
    assert(sem_init(&sem2, 1, 1) == 0);

    for(i=0; i < N ;i++){
        fa[i].i = i;
        pthread_create(&tid[i], NULL, &fonct_wrapper, &fa[i]);
    }

    for(i=0; i < N ; i++){
        pthread_join(tid[i],NULL);
    }

    sem_destroy(&sem1);
    sem_destroy(&sem2);

    return 0;
}