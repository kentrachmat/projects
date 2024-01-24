CFLAGS=-ansi -Wall -pedantic
CFLAGS2=-Wall -pedantic
CC =gcc
DELETE =module/*.o module/echanger module/division module/argument module/echanger_pointeur module/strip_spaces_tst module/dichotomique

all: echanger 

echanger: echanger.o
	$(CC) $(CFLAGS2) -o module/echanger module/$^

echanger.o: echanger.c
	$(CC) $(CFLAGS) -c $^ -o module/echanger.o 

division: division.o
	$(CC) $(CFLAGS2) -o module/division module/$^

division.o: division.c
	$(CC) $(CFLAGS) -c $^ -o module/division.o 

argument: argument.o
	$(CC) $(CFLAGS2) -o module/argument module/$^

argument.o: argument.c
	$(CC) $(CFLAGS) -c $^ -o module/argument.o 

espaces: espaces.o
	$(CC) $(CFLAGS2) -o module/strip_spaces_tst module/$^

espaces.o: espaces.c
	$(CC) $(CFLAGS) -c $^ -o module/espaces.o 

echanger_pointeur: echanger_pointeur.o
	$(CC) $(CFLAGS2) -o module/echanger_pointeur module/$^

echanger_pointeur.o: echanger_pointeur.c
	$(CC) $(CFLAGS) -c $^ -o module/echanger_pointeur.o 

dichotomique: dichotomique.o
	$(CC) $(CFLAGS2) -o module/dichotomique module/$^

dichotomique.o: dichotomique.c
	$(CC) $(CFLAGS) -c $^ -o module/dichotomique.o 

clean:
	rm -f $(DELETE)

.PHONY: all clean

