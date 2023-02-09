#include"tree.h"

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

void attachNode(Performance *performance, Node **node_ptr, void *src, unsigned int width)
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
	new_node->lt = NULL;
	new_node->gte = NULL;
	*node_ptr = new_node; // node_pointer now points at the new node
	// increment performance mallocs and writes
	performance->mallocs++;
	performance->writes++;
}

int comparNode(Performance *performance, Node **node_ptr, int (*compar)(const void *, const void *), void *target)
{
	performance->reads++;
	return(compar(target, (*node_ptr)->data));
}

Node **next(Performance *performance, Node **node_ptr, int direction)
{
	if(*node_ptr == NULL)
	{
		fprintf(stderr, "Tree is empty!");
		return NULL;
	}
	Node *node = *node_ptr;
	performance->reads++;
	if(direction < 0)
	{
		return &node->lt;
	}
	else
	{
		return &node->gte;
	}
}

void readNode(Performance *performance, Node **node_ptr, void *dest, unsigned int width)
{
	if(*node_ptr == NULL)
	{
		fprintf(stderr, "Tree is empty!");
		return;
	}
	// Copy width bytes of data to dest from *node_ptr->data
	memcpy(dest, (*node_ptr)->data, width);
	performance->reads++;
}

void detachNode(Performance *performance, Node **node_ptr)
{
	if(*node_ptr == NULL)
	{
		fprintf(stderr, "Tree is empty!");
		return;
	}
	Node *tmp = *node_ptr;
	// set node_ptr to null
	*node_ptr = NULL;
	// free node
	free(tmp->data);
	free(tmp);
	performance->frees++;
}

void addItem(Performance *performance, Node **node_ptr, int (*compar)(const void *, const void *), void *src, unsigned int width)
{
	Node **node = node_ptr;
	while(*node != NULL)
	{
		if(comparNode(performance, node, compar, src) < 0)
		{
			node = next(performance, node, -1); // negative direction goes to lt child of node
		}
		else
		{
			node = next(performance, node, 1); // positive direction goes to gte child of node
		}
	}
	// node now points to NULL, which means we need to add the item now.
	attachNode(performance, node, src, width);
}

void freeTree(Performance *performance, Node **node_ptr)
{
	Node **node = node_ptr;
	while(*node_ptr != NULL)
	{
		if((*node)->gte != NULL) // if gte node exists
		{
			node = next(performance, node, 1);
		}
		else if((*node)->lt != NULL) // if gte node doesn't exist but lt node does
		{
			node = next(performance, node, -1);
		}
		else // lt and gte branches are NULL. This is a leaf node
		{
			detachNode(performance, node);
			// reset node pointer to root
			node = node_ptr;
		}
	}
}

int searchItem(Performance *performance, Node **node_ptr, int (*compar)(const void *, const void *), void *target, unsigned int width)
{
	Node **node = node_ptr;
	while(*node != NULL)
	{
		if(comparNode(performance, node, compar, target) < 0)
		{
			node = next(performance, node, -1); // negative direction goes to lt child of node
		}
		else if(comparNode(performance, node, compar, target) > 0)
		{
			node = next(performance, node, 1); // positive direction goes to gte child of node
		}
		else // target found
		{
			// copy width bytes of data from (*node->data) to target
			memcpy(target, (*node)->data, width);
			return 1;
		}
	}
	// target not found
	return 0;
}
