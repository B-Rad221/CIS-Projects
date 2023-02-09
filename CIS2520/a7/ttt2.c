#include "ttt.h"
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <string.h>

void init_boards()
{
	int i;
	for(i = 0; i < HSIZE; i++)
	{
		htable[i].init = 0;
	}
}

int depth(Board board)
{
	int count = 0;
	int i;
	for(i = 0; i < 9; i++)
	{
		if(board[pos2idx[i]] == 'X' || board[pos2idx[i]] == 'O')
		{
			count++;
		}
	}
	return count;
}

char winner(Board board)
{
	int count = 0;
	int n, j;
	// This checks all of the numbers in the WINS array for X
	for(n = 0; n < 8; n++)
	{
		for(j = 0; j < 3; j++)
		{
			if(board[pos2idx[WINS[n][j]]] == 'X') // board[pos2idx[WINS[n][j]]] is the character on the board at the position in the WINS array at n, j
			{
				count++;
			}
			else
			{
				count = 0;
				break; // Breaks out of the j for loop, so that it doesn't needlessly read more numbers from the WINS array
			}
		}
		if(count == 3) // X has won!
		{
			return 'X';
		}
	}
	if(count == 3) // X has won!
	{
		return 'X';
	}
	count = 0;
	// This checks all of the numbers in the WINS array for O
	for(n = 0; n < 8; n++)
	{
		for(j = 0; j < 3; j++)
		{
			if(board[pos2idx[WINS[n][j]]] == 'O') // board[pos2idx[WINS[n][j]]] is the character on the board at the position in the WINS array at n, j
			{
				count++;
			}
			else
			{
				count = 0;
				break; // Breaks out of the j for loop, so that it doesn't needlessly read more numbers from the WINS array
			}
		}
		if(count == 3) // O has won!
		{
			return 'O';
		}
	}
	if(count == 3) // O has won!
	{
		return 'O';
	}
	if(depth(board) == 9) // Board is filled and no one has won
	{
		return '-';
	}
	// Game is incomplete
	return '?';
}

char turn(Board board)
{
	if (depth(board) == 9) // Board is filled
	{
		return '-';
	}
	if(depth(board) % 2 == 0) // The remainder of the depth when divided by 2 is zero when it is X's turn
	{
		return 'X';
	}
	else
	{
		return 'O';
	}
}

void init_board(Board board)
{
	int hash = board_hash(board);
	htable[hash].init = 1;
	htable[hash].turn = turn(board);
	htable[hash].depth = depth(board);
	strcpy(htable[hash].board, board);
	htable[hash].winner = winner(board);
}

void join_graph(Board board)
{
	int hash = board_hash(board);
	int i;
	for(i = 0; i < 9; i++)
	{
		if(board[pos2idx[i]] == 'X' || board[pos2idx[i]] == 'O')
		{
			htable[hash].move[i] = -1; // Stores a value of negative one in all of the illegal move positions
		}
		else // The position is valid
		{
			Board new_board;
			strcpy(new_board, board);
			new_board[pos2idx[i]] = turn(board); // Places the marker in the new board at pos2idx[i]
			int new_hash = board_hash(new_board);
			htable[hash].move[i] = new_hash; // Stores the new board's hash in the move array
			if(htable[new_hash].init == 0) // The hash table doesn't contain an entry for the new board
			{
				init_board(new_board); // Creates a new entry for the board
				join_graph(new_board);
			}
		}
	}
}

void compute_score()
{
	int i;
	for(i = HSIZE; i > -1; i--) // Reverse order ensures that the nodes are evaluated in order of decreasing depth. (Because of the hash function, higher depths are represented by larger key numbers)
	{
		if(htable[i].init == 1)
		{
			char w = winner(htable[i].board);
			char t = turn(htable[i].board);
			if(w == 'X')
			{
				htable[i].score = 1;
			}
			else if(w == 'O')
			{
				htable[i].score = -1;
			}
			else if(w == '-')
			{
				htable[i].score = 0;
			}
			else // Not a leaf node in the tree
			{
				if(t == 'X') // Computes the maximum value in the child nodes
				{
					int j, max;
					max = -2;
					for(j = 9; j > -1; j--)
					{
						max = imax(htable[htable[i].move[j]].score, max); // htable[i].move[j] is the hash for the board node that contains the move into the j position
					}
					htable[i].score = max;
				}
				else // Computes the minimum value in the child nodes
				{
					//find_min_score(htable[i], &htable[i].score);
					int j, min;
					min = 2;
					for(j = 9; j > -1; j--)
					{
						min = imin(htable[htable[i].move[j]].score, min); // htable[i].move[j] is the hash for the board node that contains the move into the j position
					}
					htable[i].score = min;

				}
			}
		}
	}
}

int best_move(int board)
{
	char t = turn(htable[board].board);
	if(t == 'X')
	{
		int j, max, moveidx;
		max = INT_MIN;
		for(j = 0; j < 9; j++)
		{
			if(htable[board].move[j] != -1)
			{
				max = imax(htable[htable[board].move[j]].score, max);
				if(max == htable[htable[board].move[j]].score)
				{
					moveidx = j;
				}
			}
		}
		return moveidx;
	}
	else
	{
		int j, min, moveidx;
		min = INT_MAX;
		for(j = 0; j < 9; j++)
		{
			if(htable[board].move[j] != -1)
			{
				min = imin(htable[htable[board].move[j]].score, min);
				if(min == htable[htable[board].move[j]].score)
				{
					moveidx = j;
				}
			}
		}
		return moveidx;
	}
}

int imax(int a, int b)
{
	if(a > b)
	{
		return a;
	}
	return b;
}

int imin(int a, int b)
{
	if(a < b)
	{
		return a;
	}
	return b;
}