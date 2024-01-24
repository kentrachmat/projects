all: main

main: foo.o main.o
	cc -o $@ $^
	./$@

main.o: main.c
	cc -c $^

foo.o: foo.c
	cc -c $^

clean:
	rm -f *.o
	rm main

.PHONY:clean all