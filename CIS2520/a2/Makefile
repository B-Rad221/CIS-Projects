CC = gcc
CFLAGS = -Wall -pedantic -std=c99

all: array.o test1 test2 test3 test4 test5 test6 test7 test8

array.o: array.c
	$(CC) $(CFLAGS) -c array.c

test1.o: test1.c
	$(CC) $(CFLAGS) -c test1.c

test1: test1.o
	$(CC) $(CFLAGS) -o test1 test1.o array.o

test2.o: test2.c
	$(CC) $(CFLAGS) -c test2.c

test2: test2.o
	$(CC) $(CFLAGS) -o test2 test2.o array.o

test3.o: test3.c
	$(CC) $(CFLAGS) -c test3.c

test3: test3.o
	$(CC) $(CFLAGS) -o test3 test3.o array.o

test4.o: test4.c
	$(CC) $(CFLAGS) -c test4.c

test4: test4.o
	$(CC) $(CFLAGS) -o test4 test4.o array.o

test5.o: test5.c
	$(CC) $(CFLAGS) -c test5.c

test5: test5.o
	$(CC) $(CFLAGS) -o test5 test5.o array.o

test6.o: test6.c
	$(CC) $(CFLAGS) -c test6.c

test6: test6.o
	$(CC) $(CFLAGS) -o test6 test6.o array.o

test7.o: test7.c
	$(CC) $(CFLAGS) -c test7.c

test7: test7.o
	$(CC) $(CFLAGS) -o test7 test7.o array.o

test8.o: test8.c
	$(CC) $(CFLAGS) -c test8.c

test8: test8.o
	$(CC) $(CFLAGS) -o test8 test8.o array.o

clean:
	rm *.o test1 test2 test3 test4 test5 test6 test7 test8