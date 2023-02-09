#ifndef ARRAY_H
#define ARRAY_H
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct Array
{
	unsigned int width; // Size in bytes of each element of the array
	unsigned int nel; // current size of the array
	unsigned int capacity; // Maximum size of the array
	void *data;
}Array;

typedef struct Performance
{
	unsigned int reads;
	unsigned int writes;
	unsigned int mallocs;
	unsigned int frees;
}Performance;

Performance *newPerformance();
Array *newArray(Performance *performance, unsigned int width, unsigned int capacity);
void readItem(Performance *performance, Array *array, unsigned int index, void *dest);
void writeItem(Performance *performance, Array *array, unsigned int index, void *src);
void contract(Performance *performance, Array *array);
void freeArray(Performance *performance, Array *array);
//derived functions:
void appendItem(Performance *performance, Array *array, void *src);
void insertItem(Performance *performance, Array *array, unsigned int index, void *src);
void prependItem(Performance *performance, Array *array, void *src);
void deleteItem(Performance *performance, Array *array, unsigned int index);
// search functions
int findItem(Performance *performance, Array *array, int (*compar)(const void *, const void *), void *target);
int searchItem(Performance *performance, Array *array, int (*compar)(const void *, const void *), void *target);
#endif