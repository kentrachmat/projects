CC      = gcc

CFLAGS  = -Wall -Wextra
CFLAGS += -g
CFLAGS += -pthread

LDFLAGS = -pthread

TOUS := tri-rapide

all: ${TOUS}

tri-rapide: pile.o tri.o rapide.o main.o
	${CC} ${LDFLAGS} -o $@ $^

%.o: %.c
	${CC} ${CFLAGS} -c $<

%: %.o
	${CC} ${LDFLAGS} -o $@ $^

%: %.c

# Dépendances, générées par gcc -MM pile.c tri.c rapide.c main.c
pile.o: pile.c pile.h
tri.o: tri.c main.h tri.h pile.h
rapide.o: rapide.c pile.h tri.h rapide.h main.h
main.o: main.c tri.h pile.h rapide.h main.h

clean:
	rm -f core *.o

realclean: clean
	rm -f ${TOUS}

.PHONY: all clean realclean
