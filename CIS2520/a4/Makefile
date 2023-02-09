CC = gcc
CFLAGS = -Wall -pedantic -std=c99

all: tree.o ttest

tree.o: tree.c
	$(CC) $(CFLAGS) -c tree.c

ttest.o: ttest.c
	$(CC) $(CFLAGS) -c ttest.c

ttest: ttest.o
	$(CC) $(CFLAGS) -o ttest ttest.o tree.o

clean:
	-rm *.o ttest
