#ifndef HASH_H
#define HASH_H
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<limits.h>

typedef struct HashTable
{
	unsigned int capacity;
	unsigned int nel;
	void **data;
	int (*hash)(void *, int);
	int (*compar)(const void *, const void *);
}HashTable;

typedef struct Performance
{
	unsigned int reads;
	unsigned int writes;
	unsigned int mallocs;
	unsigned int frees;
}Performance;

// Base Functions
Performance *newPerformance();
HashTable *createTable(Performance *performance, unsigned int capacity, int (*hash)(void *, int), int compar(const void *, const void *));
void addElement(Performance *performance, HashTable *table, void *src);
int getIdx(Performance *performance, HashTable *table, void *src);
void freeTable(Performance *performance, HashTable *table);

// Derived Functions
void *getElement(Performance *performance, HashTable *table, void *src);
void removeElement(Performance *performance, HashTable *table, void *target);

// Hash Accuracy Functions
int hashAccuracy(HashTable *table);
void rehash(HashTable *table);

#endif