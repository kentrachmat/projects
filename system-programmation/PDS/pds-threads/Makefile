CC      = gcc
CFLAGS  = -Wall -Wextra
CFLAGS += -g
LDFLAGS = -pthread
TOUS := aleazard compteur-gc compteur-gc-multithreaded rdv rdvQ4 rdvQ3 TDexo4 fib

all: ${TOUS}

TDexo4: TDexo4.c
	${CC} ${CFLAGS} $< -o $@ ${LDFLAGS}

fib: fib.c
	${CC} ${CFLAGS} $< -o $@ ${LDFLAGS} 

aleazard: aleazard.c
	${CC} ${CFLAGS} $< -o $@ ${LDFLAGS} 
	
rdvQ3 : rdvQ3.c
	${CC} ${CFLAGS} $< -o $@ ${LDFLAGS} 

rdv : rdv.c
	${CC} ${CFLAGS} $< -o $@ ${LDFLAGS} 

rdvQ4 : rdvQ4.c
	${CC} ${CFLAGS} $< -o $@ ${LDFLAGS} 

compteur-gc: compteur-gc.c
	${CC} ${CFLAGS} $< -o $@ ${LDFLAGS}

compteur-gc-multithreaded: compteur-gc-multithreaded.c
	${CC} ${CFLAGS} $< -o $@ ${LDFLAGS}

clean:
	rm -f core *.o fib ${TOUS} genome
	rm -fr *.dSYM

.PHONY: all clean
