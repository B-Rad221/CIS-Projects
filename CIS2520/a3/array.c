#include"array.h"

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

Array *newArray(Performance *performance, unsigned int width, unsigned int capacity)
{
	Array *array = malloc(sizeof(Array));
	if(array == NULL)
	{
		fprintf(stderr, "Unable to allocate memory for the array structure\n");
		return NULL;
	}
	array->width = width;
	array->capacity = capacity;
	array->nel = 0;
	array->data = malloc(width * capacity);
	if(array->data == NULL)
	{
		fprintf(stderr, "Unable to allocate memory for the data in the array structure.\n");
		free(array);
		return NULL;
	}
	performance->mallocs++;
	return array;
}
void readItem(Performance *performance, Array *array, unsigned int index, void *dest)
{
	if(index >= array->nel)
	{
		fprintf(stderr, "Unable to access index of array.\n");
		return;
	}
	unsigned int start = array->width * index;
	memcpy(dest, (char *)array->data + start, array->width);
	performance->reads++;
}

void writeItem(Performance *performance, Array *array, unsigned int index, void *src)
{
	if(index > array->nel || index >= array->capacity)
	{
		fprintf(stderr, "Out of bounds error. Unable to write item to array.\n");
		return;
	}
	if(index == array->nel)
	{
		array->nel++;
	}
	unsigned int start = array->width * index;
	memcpy((char *)array->data + start, src, array->width);
	performance->writes++;
}
void contract(Performance *performance, Array *array)
{
	if(array->nel == 0)
	{
		fprintf(stderr, "Error, cannot contract array any further.\n");
		return;
	}
	array->nel--;
}

void freeArray(Performance *performance, Array *array)
{
	free(array->data);
	free(array);
	performance->frees++;
}

void appendItem(Performance *performance, Array *array, void *src)
{
	writeItem(performance, array, array->nel, src);
}
void insertItem(Performance *performance, Array *array, unsigned int index, void *src)
{
	void *tmp = malloc(array->width);
	int i;
	//Moves items 1 position towards the end of the array, starting at the end and going backwards until it reaches index.
	for(i = array->nel - 1; i >= index; i--)
	{
		readItem(performance, array, i, tmp);
		writeItem(performance, array, i + 1, tmp);
	}
	writeItem(performance, array, index, src);
	free(tmp);
}
void prependItem(Performance *performance, Array *array, void *src)
{
	// Move all items 1 position towards the end of the array
	int i;
	void *tmp = malloc(array->width);
	for(i = array->nel - 1; i >= 0; i--)
	{
		readItem(performance, array, i, tmp);
		writeItem(performance, array, i + 1, tmp);
	}
	writeItem(performance, array, 0, src);
	free(tmp);
}
void deleteItem(Performance *performance, Array *array, unsigned int index)
{
	// Move all items at index + 1 and higher towards the beginning of the array
	int i;
	void *tmp = malloc(array->width);
	for(i = index + 1; i < array->nel; i++)
	{
		readItem(performance, array, i, tmp);
		writeItem(performance, array, i - 1, tmp);
	}
	contract(performance, array);
	free(tmp);
}
int findItem(Performance *performance, Array *array, int (*compar)(const void *, const void *), void *target)
{
	int i;
	void *tmp = malloc(array->width);
	for(i = 0; i < array->nel; i++)
	{
		readItem(performance, array, i, tmp);
		if(compar(tmp, target) == 0)
		{
			free(tmp);
			return i;
		}
	}
	free(tmp);
	return -1;
}
int searchItem(Performance *performance, Array *array, int (*compar)(const void *, const void *), void *target)
{
	int low = 0;
	int mid = 0;
	int high = array->nel;
	void *tmp = malloc(array->width);
	while(high >= low)
	{
		mid = (high + low)/2;
		readItem(performance, array, mid, tmp);
		if(compar(tmp, target) > 0)
		{
			high = mid - 1;
		}
		else if(compar(tmp, target) < 0)
		{
			low = mid + 1;
		}
		else if(compar(tmp, target) == 0)
		{
			free(tmp);
			return mid;
		}
	}
	free(tmp);
	return -1;
}