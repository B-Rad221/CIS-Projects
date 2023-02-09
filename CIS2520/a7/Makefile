C = gcc
CFLAGS = -Wall -pedantic -std=c99

all: ttt1.o ttt2.o count_nodes count_nodes.o show_node show_node.o alphatoe alphatoe.o

ttt1.o: ttt1.c
	$(CC) $(CFLAGS) -c ttt1.c

ttt2.o: ttt2.c
	$(CC) $(CFLAGS) -c ttt2.c

count_nodes.o: count_nodes.c
	$(CC) $(CFLAGS) -c count_nodes.c

count_nodes: count_nodes.o
	$(CC) $(CFLAGS) -o count_nodes count_nodes.o ttt1.o ttt2.o

show_node.o: show_node.c
	$(CC) $(CFLAGS) -c show_node.c

show_node: show_node.o
	$(CC) $(CFLAGS) -o show_node show_node.o ttt1.o ttt2.o

alphatoe.o: alphatoe.c
	$(CC) $(CFLAGS) -c alphatoe.c

alphatoe: alphatoe.o
	$(CC) $(CFLAGS) -o alphatoe alphatoe.o ttt1.o ttt2.o

clean:
	-rm *.o count_nodes show_node alphatoe