CC=gcc
OPT=-Wall -ansi -pedantic -g

all:Test_arbre

%_arbre: %_arbre.c Affichage.o ArbreBinaire.o
	$(CC) $(OPT) -o $@  $^

%.pdf: %.gv
	dot -Tpdf -o $@ $^

Affichage.o : Affichage.c
	$(CC) $(OPT) -c $<

ArbreBinaire.o: ArbreBinaire.c ArbreBinaire.h
	$(CC) $(OPT) -c $<

clean:
	rm -f *.gv *.pdf *.o *_arbre
