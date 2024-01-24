#include <pthread.h>
#include <stdio.h>
#include <semaphore.h>
#define N 20

sem_t sem;

void *unique() {
    static int count = 0;
    int r;
    sem_wait(&sem);
    r = count++;
    printf("%d\n",r);
    sem_post(&sem);
    return (void *) r; 
}

int main(){
    pthread_t tid[N];
    int i;
    sem_init(&sem,0,1);
    for (i=0; i<N; i++){
        pthread_create(&tid[i], NULL, &unique, NULL);
    }
      for (i=0; i<N; i++){
        pthread_join(tid[i], NULL);
    }
    return 0;
}
