#include <pthread.h>
#include <stdio.h>
#include <assert.h>
#include <unistd.h>
#include <semaphore.h>
#include <pthread.h>

#define N 20

sem_t array_sem[N];

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

void *p(int i) {
    assert(i >= 0);
    a(i);
    for(int j = 0 ; j < N ; j++){
        if(j != i){
            sem_post(&array_sem[i]);
        }        
    }
    sem_wait(&array_sem[i]);
    b(i);
    return NULL;
}

void *fonct_wrapper(void *arg) {
    struct fonct_arg_s *p_arg = arg;
    p(p_arg->i);
    return NULL;
}

int main() {
    int i;
    pthread_t tid[N];
    struct fonct_arg_s fa[N]; 

    for (i = 0; i < N; i++){
        assert(sem_init(&array_sem[i], 0, 0) == 0);
    }

    for (i = 0; i < N; i++){
        fa[i].i = i;
        pthread_create(&tid[i], NULL, &fonct_wrapper, &fa[i]);
    }

    for (i = 0; i < N; i++){
        pthread_join(tid[i], NULL);
    }

    for (i = 0; i < N; i++){
        sem_destroy(&array_sem[i]); 
    }

    return 0;
}
