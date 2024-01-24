CC      = gcc
CFLAGS  = -Wall -Wextra
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g
DELETE  = module/*.o module/lstar module/arrondi
MODULE = module
SRC = src

all : $(MODULE)/main


$(MODULE)/main : $(MODULE)/main.o $(MODULE)/lstar.o $(MODULE)/arrondi.o $(MODULE)/util.o
	$(CC) $(CFLAGS) $^ -o $@

$(MODULE)/main.o : $(SRC)/main.c $(MODULE)/lstar.o $(MODULE)/arrondi.o $(MODULE)/util.o
	$(CC) $(CFLAGS) -c $(SRC)/main.c -o $@

$(MODULE)/lstar.o : $(SRC)/lstar.c $(SRC)/lstar.h $(SRC)/struct.h $(SRC)/util.h $(SRC)/arrondi.h
	mkdir -p module
	$(CC) $(CFLAGS) -c $(SRC)/lstar.c -o $@

$(MODULE)/arrondi.o: $(SRC)/arrondi.c $(SRC)/arrondi.h
	$(CC) $(CFLAGS) -c $(SRC)/arrondi.c -o $@

$(MODULE)/util.o: $(SRC)/util.c $(SRC)/util.h $(SRC)/struct.h
	$(CC) $(CFLAGS) -c $(SRC)/util.c -o $@

clean:
	rm -f $(DELETE)

.PHONY: all clean