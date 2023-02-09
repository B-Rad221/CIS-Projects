CC = gcc
CFLAGS = -Wall -pedantic -std=c99

all: util.o hashfn.o actors actors.o bacon1 bacon1.o key2val key2val.o val2key val2key.o buildidx buildidx.o

util.o: util.c
	$(CC) $(CFLAGS) -c util.c

hashfn.o: hashfn.c
	$(CC) $(CFLAGS) -c hashfn.c

actors.o: actors.c
	$(CC) $(CFLAGS) -c actors.c

actors: actors.o
	$(CC) $(CFLAGS) -o actors actors.o util.o hashfn.o

bacon1.o: bacon1.c
	$(CC) $(CFLAGS) -c bacon1.c

bacon1: bacon1.o
	$(CC) $(CFLAGS) -o bacon1 bacon1.o util.o hashfn.o

key2val.o: key2val.c
	$(CC) $(CFLAGS) -c key2val.c

key2val: key2val.o
	$(CC) $(CFLAGS) -o key2val key2val.o util.o hashfn.o

val2key.o: val2key.c
	$(CC) $(CFLAGS) -c val2key.c

val2key: val2key.o
	$(CC) $(CFLAGS) -o val2key val2key.o util.o hashfn.o

buildidx.o: buildidx.c
	$(CC) $(CFLAGS) -c buildidx.c

buildidx: buildidx.o
	$(CC) $(CFLAGS) -o buildidx buildidx.o util.o hashfn.o

clean:
	-rm *.o val2key buildidx key2val bacon1 actors