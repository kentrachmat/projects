CC      = gcc
CFLAGS  = -Wall -Wextra
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g
FILES   = pipe2 pipen

all : $(FILES)

pipe2 : pipe2.c
	$(CC) $(CFLAGS) $< -o $@

pipen : pipen.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm $(FILES)

.PHONY: clean

