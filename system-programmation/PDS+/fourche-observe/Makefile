CC      = gcc
CFLAGS  = -Wall -Wextra
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g
FILES   = course observe multif

all : $(FILES)

course : course.c
	$(CC) $(CFLAGS) $< -o $@

observe : observe.c
	$(CC) $(CFLAGS) $< -o $@

multif : multif.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm $(FILES)

.PHONY: clean

