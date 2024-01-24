CFLAGS=-ansi -Wall -pedantic
CFLAGS2=-Wall -pedantic
CC =gcc

all: input output

input: input.o
	$(CC) $(CFLAGS2) -o module/input module/$^

output: output.o 
	$(CC) $(CFLAGS2) -o module/output module/$^

input.o: input.c
	$(CC) $(CFLAGS) -c $^ -o module/input.o 

output.o: output.c
	$(CC) $(CFLAGS) -c $^ -o module/output.o 

exo5: exo5.c
	cc -o module/exo5 exo5.c
	./module/exo5 > module/data.bin
	hd module/data.bin

test: input output
	./module/output > module/output.bin
	hd ./module/output.bin
	./module/input < module/output.bin
	

clean:
	rm -f module/*.o module/output module/exo5 module/input module/*.bin

.PHONY: all clean

