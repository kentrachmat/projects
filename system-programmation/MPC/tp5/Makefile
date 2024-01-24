CFLAGS=-ansi -Wall -pedantic
CFLAGS2=-Wall -pedantic
CC =gcc
DELETE =module/*.o module/mprintenv module/dichotomique module/generique

all: printenv

mprintenv: mprintenv.o
	$(CC) $(CFLAGS2) -o module/mprintenv module/$^

mprintenv.o: mprintenv.c
	$(CC) $(CFLAGS) -c $^ -o module/mprintenv.o 

dichotomique: dichotomique.o
	$(CC) $(CFLAGS2) -o module/dichotomique module/$^

dichotomique.o: dichotomique.c
	$(CC) $(CFLAGS) -c $^ -o module/dichotomique.o 

generique: generique.o
	$(CC) $(CFLAGS2) -o module/generique module/$^

generique.o: generique.c
	$(CC) $(CFLAGS) -c $^ -o module/generique.o 

filter: filter.o
	$(CC) $(CFLAGS2) -o module/filter module/$^

filter.o: filter.c
	$(CC) $(CFLAGS) -c $^ -o module/filter.o 

clean:
	rm -f $(DELETE)

.PHONY: all clean

