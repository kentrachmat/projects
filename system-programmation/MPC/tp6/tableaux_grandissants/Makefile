CC = gcc
CFLAGS = -Wall -O0 -g -fsanitize=address

test: validation
	./validation < donnees.txt > tmp
	diff -q tmp sortie.txt

validation: validation.o libga.o
	$(CC) $(CFLAGS) -o validation validation.o libga.o

validation.o: validation.c
	$(CC) $(CFLAGS) -c validation.c

libga.o: libga.c
	$(CC) $(CFLAGS) -c libga.c

clean:
	rm -f *.o validation tmp

.PHONY: all clean
