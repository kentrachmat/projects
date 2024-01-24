CC =gcc
CFLAGS = -Wall -Werror -ansi -pedantic
CFLAGS2 = -Wall -Werror -pedantic

all : mcu_test

mcu_putint.o: src/mcu_putint.c
	$(CC) $(CFLAGS) -c $^ -o src/mcu_putint.o

mcu_fatal.o: src/mcu_fatal.c
	$(CC) $(CFLAGS) -c $^ -o src/mcu_fatal.o

mcu_readl.o: src/mcu_readl.c
	$(CC) $(CFLAGS) -c $^ -o src/mcu_readl.o

mcu_test.o: src/mcu_test.c
	$(CC) $(CFLAGS) -c $^ -o src/mcu_test.o

mcu_test: src/mcu_putint.o src/mcu_fatal.o src/mcu_readl.o src/mcu_test.o
	$(CC) $(CFLAGS2) -o build/mcu_test $^

mcu_wc.o: src/mcu_wc.c
	$(CC) $(CFLAGS) -c $^ -o src/mcu_wc.o

mcu_wc: src/mcu_wc.o src/mcu_putint.o
	$(CC) $(CFLAGS2) -o build/mcu_wc $^

mcu_wl.o: src/mcu_wl.c
	$(CC) $(CFLAGS) -c $^ -o src/mcu_wl.o

mcu_wl: src/mcu_wl.o src/mcu_putint.o
	$(CC) $(CFLAGS2) -o build/mcu_wl $^

mcu_rev.o: src/mcu_rev.c
	$(CC) $(CFLAGS) -c $^ -o src/mcu_rev.o

mcu_rev: src/mcu_rev.o src/mcu_putint.o src/mcu_readl.o src/mcu_fatal.o
	$(CC) $(CFLAGS2) -o build/mcu_rev $^

mcu_uniq.o: src/mcu_uniq.c
	$(CC) $(CFLAGS) -c $^ -o src/mcu_uniq.o

mcu_uniq: src/mcu_uniq.o src/mcu_putint.o src/mcu_readl.o src/mcu_fatal.o
	$(CC) $(CFLAGS2) -o build/mcu_uniq $^

clean:
	rm -f src/*.o
	rm -f build/mcu_*
	rm -f essai
	
.PHONY:clean all