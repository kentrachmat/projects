CFLAGS=-ansi -Wall -pedantic
CFLAGS2=-Wall -pedantic
CC =gcc
DELETE =module/*.o module/main_files_entiers module/main_files_generique module/trier

all: files_entiers

main_files_entiers: files_entiers.o main_files_entiers.o 
	$(CC) $(CFLAGS2) -o module/main_files_entiers module/files_entiers.o module/main_files_entiers.o 

main_files_entiers.o: main_files_entiers.c
	$(CC) $(CFLAGS) -c $^ -o module/main_files_entiers.o

files_entiers.o: files_entiers.c
	$(CC) $(CFLAGS) -c $^ -o module/files_entiers.o

main_files_generique: files_generique.o main_files_generique.o
	$(CC) $(CFLAGS2) -o module/main_files_generique module/files_generique.o module/main_files_generique.o

main_files_generique.o: main_files_generique.c
	$(CC) $(CFLAGS) -c $^ -o module/main_files_generique.o

files_generique.o: files_generique.c
	$(CC) $(CFLAGS) -c $^ -o module/files_generique.o

trier: trier.o files_entiers.o files_generique.o
	$(CC) $(CFLAGS2) -o module/trier module/trier.o module/files_entiers.o module/files_generique.o

trier.o: trier.c
	$(CC) $(CFLAGS) -c $^ -o module/trier.o

clean:
	rm -f $(DELETE)

.PHONY: all clean

