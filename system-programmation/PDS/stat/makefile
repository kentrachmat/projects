CC      = gcc
CFLAGS  = -Wall -Wextra
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

all : stat

mstat : stat.c
	$(CC) $(CFLAGS) mstat.c -o stat

clean :
	rm stat
	rm -r stat.dSYM
	
.PHONY: clean
