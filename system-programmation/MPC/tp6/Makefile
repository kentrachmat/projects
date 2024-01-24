CFLAGS=-ansi -Wall -pedantic
CFLAGS2=-Wall -pedantic
CC =gcc
DELETE =module/*.o module/compteur module/dupliquer module/nom_temp

all: compteur

compteur: compteur.o
	$(CC) $(CFLAGS2) -o module/compteur module/$^

compteur.o: compteur.c
	$(CC) $(CFLAGS) -c $^ -o module/compteur.o 

dupliquer: dupliquer.o
	$(CC) $(CFLAGS2) -o module/dupliquer module/$^

dupliquer.o: dupliquer.c
	$(CC) $(CFLAGS) -c $^ -o module/dupliquer.o 

nom_temp: nom_temp.o
	$(CC) $(CFLAGS2) -o module/nom_temp module/$^

nom_temp.o: nom_temp.c
	$(CC) $(CFLAGS) -c $^ -o module/nom_temp.o 

clean:
	rm -f $(DELETE)

.PHONY: all clean

