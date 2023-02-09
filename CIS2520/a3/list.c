#include"list.h"

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
void push(Performance *performance, Node **list_ptr, void *src, unsigned int width)
{
	Node *new_node = malloc(sizeof(Node));
	if(new_node == NULL)
	{
		fprintf(stderr, "Unable to allocate memory for the node structure\n");
		return;
	}
	new_node->data = malloc(width);
	// copy width bytes of data from source to new_node->data
	memcpy(new_node->data, src, width);
	new_node->next = *list_ptr; // copy address of *list_ptr (head pointer) to the new node
	*list_ptr = new_node; // head pointer now points at the new node
	// increment performance mallocs and writes
	performance->mallocs++;
	performance->writes++;
}
void readHead(Performance *performance, Node **list_ptr, void *dest, unsigned int width)
{
	if(*list_ptr == NULL)
	{
		fprintf(stderr, "List is empty! Cannot read head.\n");
		return;
	}
	// Copy width bytes of data to dest from *list_ptr->data
	memcpy(dest, (*list_ptr)->data, width);
	// increment performance reads
	performance->reads++;
}
void pop(Performance *performance, Node **list_ptr, void *dest, unsigned int width)
{
	if(*list_ptr == NULL)
	{
		fprintf(stderr, "List is empty! Cannot pop.\n");
		return;
	}
	// Copy width bytes of data to dest from *list_ptr->data
	memcpy(dest, (*list_ptr)->data, width);
	// Move *list_ptr to next, while keeping the address for the head of the list in a temporary pointer
	Node *tmp = *list_ptr;
	*list_ptr = (*list_ptr)->next;
	// Free first node of list
	free(tmp->data);
	free(tmp);
	// increment performance reads and frees
	performance->reads++;
	performance->frees++;
}
Node **next(Performance *performance, Node **list_ptr)
{
	Node *node = *list_ptr;
	if(node == NULL)
	{
		fprintf(stderr, "List is empty!");
		return NULL;
	}
	// increment performance reads
	performance->reads++;
	// return next pointer of *list_ptr
	return &node->next;
}
int isEmpty(Performance *performance, Node **list_ptr)
{
	if(*list_ptr == NULL)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}
void freeList(Performance *performance, Node **list_ptr)
{
	// While the list is not empty, pop items off of the list
	while(!isEmpty(performance, list_ptr))
	{
		pop(performance, list_ptr, NULL, 0); // Since we don't need the data that is copied in the pop function, we can just send a null pointer and a copy width of 0 bytes
	}
}
void readItem(Performance *performance, Node **list_ptr, unsigned int index, void *dest, unsigned int width)
{
	Node **node = list_ptr;
	int i;
	for(i = 0; i < index; i++)
	{
		node = next(performance, node);
	}
	readHead(performance, node, dest, width);
}
void appendItem(Performance *performance, Node **list_ptr, void *src, unsigned int width)
{
	Node **node = list_ptr;
	while(!isEmpty(performance, node))
	{
		node = next(performance, node);
	}
	push(performance, node, src, width);
}
void insertItem(Performance *performance, Node **list_ptr, int index, void *src, unsigned int width)
{
	if(index < 0)
	{
		fprintf(stderr, "Error: index must be a positive integer\n");
		return;
	}
	else if (index == 0)
	{
		push(performance, list_ptr, src, width);
	}
	else
	{
		Node **node = next(performance, list_ptr);
		int i;
		for(i = 1; i < index; i++)
		{
			node = next(performance, node);
		}
		push(performance, node, src, width);
	}
}
void prependItem(Performance *performance, Node **list_ptr, void *src, unsigned int width)
{
	insertItem(performance, list_ptr, 0, src, width);
}
void deleteItem(Performance *performance, Node **list_ptr, int index)
{
	Node **node = list_ptr;
	int i;
	for(i = 0; i < index; i++)
	{
		node = next(performance, node);
	}
	pop(performance, node, NULL, 0);
}
int findItem(Performance *performance, Node **list_ptr, int (*compar)(const void *, const void *), void *target, unsigned int width)
{
	Node **node = list_ptr;
	void *tmp = malloc(width);
	int i = 0;
	while(!isEmpty(performance, node))
	{
		readHead(performance, node, tmp, width);
		if(compar(tmp, target) == 0)
		{
			free(tmp);
			return i;
		}
		node = next(performance, node);
		i++;
	}
	free(tmp);
	return -1;
}