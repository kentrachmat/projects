CC      = gcc
CFLAGS  = -Wall -Wextra
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

all : cat mcat-lib

cat : cat.c
	$(CC) $(CFLAGS) cat.c -o cat

mcat-lib : mcat-lib.c
	$(CC) $(CFLAGS) mcat-lib.c -o mcat-lib
	
clean:
	rm -f *.dat images/*.png 'cat' mcat-lib
	rm -r cat.dSYM/
	rm -r mcat-lib.dSYM/
.PHONY: clean
