#include "ttt.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	init_boards();
	init_board(START_BOARD);
  	join_graph(START_BOARD);
  	compute_score();
	int i;
	for(i = 1; i < argc; i++)
	{
		int hash = atoi(argv[i]);
		print_node(htable[hash]);
	}
	return 0;
}