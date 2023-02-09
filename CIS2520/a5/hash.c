#include"hash.h"

Performance *newPerformance()
{
	Performance *performance = malloc(sizeof(Performance));
	if(performance == NULL) // When the malloc function fails, it returns a null pointer
	{
		fprintf(stderr, "Unable to allocate memory for the performance structure\n");
		return NULL;
	}
	performance->reads = 0;
	performance->writes = 0;
	performance->mallocs = 0;
	performance->frees = 0;
	return performance;
}

HashTable *createTable(Performance *performance, unsigned int capacity, int (*hash)(void *, int), int compar(const void *, const void *))
{
	HashTable *new_hash = malloc(sizeof(HashTable));
	if(new_hash == NULL)
	{
		fprintf(stderr, "Error: Unable to allocate memory for the hash table.\n");
		return NULL;
	}
	new_hash->capacity = capacity;
	new_hash->nel = 0;
	new_hash->hash = hash;
	new_hash->compar = compar;
	new_hash->data = malloc(capacity * 8); // Size of void pointer is 8 bytes
	if(new_hash->data == NULL)
	{
		fprintf(stderr, "Error: Unable to allocate memory for the data in the hash table.\n");
		free(new_hash);
		return NULL;
	}
	int i;
	for(i = 0; i < capacity; i++)
	{
		new_hash->data[i] = NULL; // Set each pointer in the data array to NULL
		performance->reads++;
	}
	performance->mallocs++;
	return new_hash;
}

void addElement(Performance *performance, HashTable *table, void *src)
{
	if(table->nel == table->capacity)
	{
		fprintf(stderr, "Error: Hash table is full. Cannot add element.\n");
		return;
	}
	int i = table->hash(src, table->capacity); // get index to start at
	while(table->data[i] != NULL)
	{
		if(i == table->capacity - 1)
		{
			i = -1; // search continues at index of 0.  (There is an i++ after this)
		}
		i++;
		if(performance != NULL)
		{
			performance->reads++;
		}
	}
	table->data[i] = src;
	table->nel++;
	if(performance != NULL)
	{
		performance->writes++;
	}
}

int getIdx(Performance *performance, HashTable *table, void *src)
{
	int i = table->hash(src, table->capacity); // get index to start at
	int originalIndex = i;
	if(table->compar(src, table->data[i]) == 0)
	{
		return i;
	}
	else if(i == table->capacity) // If i is equal to the capacity, set i to 0
	{
		i = 0;
	}
	else
	{
		i++;
	}
	performance->reads++;
	while(i != originalIndex)
	{

		if(table->data[i] != NULL)
		{
			if(table->compar(src, table->data[i]) == 0)
			{
				return i;
			}
		}
		if(i == table->capacity - 1)
		{
			i = -1; // search continues at index of 0.  (There is an i++ after this)
		}
		i++;
		performance->reads++;
	}
	return -1; // This will only happen when i reaches the original index
}

void freeTable(Performance *performance, HashTable *table)
{
	free(table->data);
	free(table);
	performance->frees++;
}

void *getElement(Performance *performance, HashTable *table, void *src)
{
	int i = getIdx(performance, table, src);
	if(i == -1)
	{
		return NULL;
	}
	else
	{
		return table->data[i];
	}
}

void removeElement(Performance *performance, HashTable *table, void *target)
{
	int i = getIdx(performance, table, target);
	if(i == -1)
	{
		fprintf(stderr, "Error: Element not found. Cannot remove element\n");
		return;
	}
	else
	{
		table->data[i] = NULL;
		table->nel--;
		performance->writes++;
	}
}

int hashAccuracy(HashTable *table)
{
	int i;
	int sum = 0;
	for(i = 0; i < table->capacity; i++)
	{
		if(table->data[i] != NULL)
		{
			int hash = table->hash(table->data[i], table->capacity);
			if(i < hash)
			{
				sum += i + (table->capacity - hash);
			}
			else
			{
				sum += i - hash;
			}
		}
	}
	return sum;
}

void rehash(HashTable *table)
{
	int i;
	// loop through all the data in the table and check if there is a location that is closer to the hash value of the data
	for(i = 0; i < table->capacity; i++)
	{
		if(table->data[i] != NULL)
		{
			int hash = table->hash(table->data[i], table->capacity);
			if(i < hash)
			{
				// Loop until capacity reached
				while(hash < table->capacity)
				{
					if(table->data[hash] == NULL)
					{
						table->data[hash] = table->data[i];
						table->data[i] = NULL;
						hash = table->capacity; // end while loop, at the end of the loop hash will equal table->capacity + 1
					}
					hash++;
				}
				// Set up for next loop
				if(hash == table->capacity + 1)
				{
					hash = i; // skip the next loop
				}
				else // If a place was not found in the above loop, hash will equal table->capacity
				{
					hash = 0;
				}
				// loop from 0 to i or skip entirely
				while(hash < i)
				{
					if(table->data[hash] == NULL)
					{
						table->data[hash] = table->data[i];
						table->data[i] = NULL;
						hash = i; // end the while loop
					}
					hash++;
				}
			}
			else
			{
				// loop from the original hash to i, checking if there is a spot closer to the original hash
				while(hash < i)
				{
					if(table->data[hash] == NULL)
					{
						table->data[hash] = table->data[i];
						table->data[i] = NULL;
						hash = i; // end the while loop
					}
					hash++;
				}
			}
		}
	}
}