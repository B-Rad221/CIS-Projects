#ifndef LIST_H
#define LIST_H
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct Node
{
	void *data;
	struct Node *next;
}Node;

typedef struct Performance
{
	unsigned int reads;
	unsigned int writes;
	unsigned int mallocs;
	unsigned int frees;
}Performance;

// Base Functions
Performance *newPerformance();
void push(Performance *performance, Node **list_ptr, void *src, unsigned int width);
void readHead(Performance *performance, Node **list_ptr, void *dest, unsigned int width);
void pop(Performance *performance, Node **list_ptr, void *dest, unsigned int width);
Node **next(Performance *performance, Node **list_ptr);
int isEmpty(Performance *performance, Node **list_ptr);

// Derived Functions
void freeList(Performance *performance, Node **list_ptr);
void readItem(Performance *performance, Node **list_ptr, unsigned int index, void *dest, unsigned int width);
void appendItem(Performance *performance, Node **list_ptr, void *src, unsigned int width);
void insertItem(Performance *performance, Node **list_ptr, int index, void *src, unsigned int width);
void prependItem(Performance *performance, Node **list_ptr, void *src, unsigned int width);
void deleteItem(Performance *performance, Node **list_ptr, int index);

// Search Functions
int findItem(Performance *performance, Node **list_ptr, int (*compar)(const void *, const void *), void *target, unsigned int width);

#endif