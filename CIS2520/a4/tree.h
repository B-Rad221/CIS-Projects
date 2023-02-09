#ifndef TREE_H
#define TREE_H
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct Node
{
	void *data;
	struct Node *lt;
	struct Node *gte;
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
void attachNode(Performance *performance, Node **node_ptr, void *src, unsigned int width);
int comparNode(Performance *performance, Node **node_ptr, int (*compar)(const void *, const void *), void *target);
Node **next(Performance *performance, Node **node_ptr, int direction);
void readNode(Performance *performance, Node **node_ptr, void *dest, unsigned int width);
void detachNode(Performance *performance, Node **node_ptr);

// Derived Functions
void addItem(Performance *performance, Node **node_ptr, int (*compar)(const void *, const void *), void *src, unsigned int width);
void freeTree(Performance *performance, Node **node_ptr);

// Search Functions
int searchItem(Performance *performance, Node **node_ptr, int (*compar)(const void *, const void *), void *target, unsigned int width);
#endif
