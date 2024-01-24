all: test

numbers: numbers.c
	cc -o numbers numbers.c
	./numbers

numbers-test: numbers-test.o
	gcc -o numbers-test numbers-test.o

numbers-test.o: numbers-test.c
	gcc -c numbers-test.c

test: numbers-test.o numbers-test
	./numbers-test

compare: test
	./numbers-test > tmp
	diff numbers-out.txt tmp
	@echo "il n'y a pas de différence entre tmp et numbers-out.txt"

clean:
	rm -f *.o numbers-test tmp

.PHONY: all test clean compare
