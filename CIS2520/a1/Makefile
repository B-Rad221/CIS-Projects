CFLAGS = -std=c99 -Wall -pedantic
CC = gcc

all: bandb.o test_bits2ll test_bits2ull test_getbytes test_getbits test_spff test_dpff

bandb.o: bandb.c
	$(CC) $(CFLAGS) -c bandb.c

test_bits2ull.o: test_bits2ull.c
	$(CC) $(CFLAGS) -c test_bits2ull.c

test_bits2ll.o: test_bits2ll.c
	$(CC) $(CFLAGS) -c test_bits2ll.c 

test_bits2ull: test_bits2ull.o bandb.o
	$(CC) $(CFLAGS) -o test_bits2ull test_bits2ull.o bandb.o

test_bits2ll: test_bits2ll.o bandb.o
	$(CC) $(CFLAGS) -o test_bits2ll test_bits2ll.o bandb.o

test_getbytes.o: test_getbytes.c
	$(CC) $(CFLAGS) -c test_getbytes.c

test_getbytes: test_getbytes.o bandb.o
	$(CC) $(CFLAGS) -o test_getbytes test_getbytes.o bandb.o

test_getbits.o: test_getbits.c
	$(CC) $(CFLAGS) -c test_getbits.c

test_getbits: test_getbits.o bandb.o
	$(CC) $(CFLAGS) -o test_getbits test_getbits.o bandb.o

test_spff.o: test_spff.c
	$(CC) $(CFLAGS) -c test_spff.c

test_spff: test_spff.o bandb.o
	$(CC) $(CFLAGS) -o test_spff test_spff.o bandb.o

test_dpff.o: test_dpff.c
	$(CC) $(CFLAGS) -c test_dpff.c

test_dpff: test_dpff.o bandb.o
	$(CC) $(CFLAGS) -o test_dpff test_dpff.o bandb.o

clean:
	rm *.o test_bits2ll test_bits2ull test_getbytes test_getbits test_spff test_dpff