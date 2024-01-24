CC      = gcc
CFLAGS  = -Wall -Wextra
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g
LDFLAGS = -g

FILE = DNSsimple send receive dns 

tout: ${FILE}

dns: dns.o  
DNSsimple: DNSsimple.o
send: send.o
receive: receive.o

# Règles implicites pour la compilation et l’édition de liens
%.o: %.c
	${CC} ${CFLAGS} -c $<

%: %.o
	${CC} ${LDFLAGS} -o $@ $^

%: %.c

# Règles de nettoyage
clean:
	rm -f core *.o

realclean: clean
	rm -f ${FILE}

.PHONY: tout clean realclean
