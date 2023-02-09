CC = gcc
CFLAGS = -Wall -pedantic -std=c99

all: list.o array.o test1 test2_list test2_array test3

list.o: list.c
	$(CC) $(CFLAGS) -c list.c

array.o: array.c
	$(CC) $(CFLAGS) -c array.c

test1.o: test1.c
	$(CC) $(CFLAGS) -c test1.c

test1: test1.o
	$(CC) $(CFLAGS) -o test1 test1.o list.o

test2_list.o: test2.c
	$(CC) $(CFLAGS) -DLIST -c test2.c -o test2_list.o

test2_array.o: test2.c
	$(CC) $(CFLAGS) -DARRAY -c test2.c -o test2_array.o

test2_list: test2_list.o
	$(CC) $(CFLAGS) -DLIST -o test2_list test2_list.o list.o

test2_array: test2_array.o
	$(CC) $(CFLAGS) -DARRAY -o test2_array test2_array.o array.o

test3.o: test3.c
	$(CC) $(CFLAGS) -c test3.c

test3: test3.o
	$(CC) $(CFLAGS) -o test3 test3.o list.o

clean:
	rm *.o test1 test2_list test2_array test3