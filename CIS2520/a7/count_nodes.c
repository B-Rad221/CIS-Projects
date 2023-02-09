#include "ttt.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	init_boards();
	init_board(START_BOARD);
  	join_graph(START_BOARD);
  	int count = 0;
	int i;
	for(i = 0; i < HSIZE; i++)
	{
		if(htable[i].init == 1)
		{
			count++;
		}
	}
	printf("%d\n", count);
	return 0;
}