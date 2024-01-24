CC =gcc
CFLAGS = -Wall -Werror -ansi -pedantic
CFLAGS2 = -Wall -Werror -pedantic

all : numbers-test

numbers-test.o: numbers-test.c put_numbers.h
	$(CC) $(CFLAGS) -c $< -o module/numbers-test.o

put_numbers.o: put_numbers.c put_numbers.h
	$(CC) $(CFLAGS) -c $< -o module/put_numbers.o

numbers-test: module/numbers-test.o module/put_numbers.o
	$(CC) $(CFLAGS2) -o module/$@  $^
	./module/$@

clean:
	rm -f module/*.o
	rm -f module/numbers-test
	
.PHONY:clean all