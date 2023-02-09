#include "util.h"
#include "hashfn.h"
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	if(argc >= 3)
	{
		FILE *fp, *vhsFp;
		char *string = malloc(argc + 1);
		strcpy(string, argv[1]);
		fp = fopen(string, "r");
		string[strlen(string) - 2] = 'v';
		string[strlen(string) - 1] = 'h';
		string[strlen(string)] = 's';
		string[strlen(string) + 1] = '\0';
		vhsFp = fopen(string, "r");
		free(string);
		if(fp == NULL)
		{
			fprintf(stderr, "Error: Could not open the specified file.\n");
			return -1;
		}

		char search[STRLEN];
		int i;
		strcpy(search, argv[2]); // Copies the first part of the search term
		for (i = 3; i < argc; i++)
		{ 
			strcat(search, " "); // Places a space between each word
			strcat(search, argv[i]); // Concatenates each command line argument into the search term
		}
		int capacity = get_capacity(vhsFp); // Get the capacity of the hash table in the vhs file
		int valHash = hashfn(search, capacity);
		int index, originalHash;
		originalHash = valHash;
		char val[STRLEN];
		read_index(vhsFp, valHash, &index); // Reads the vhs file and returns the index stored at the location specified by valHash
		read_val(fp, index, val); // Reads the kv file at the location specified by index and returns the string val
		while(strcmp(val, search) != 0)
		{
			valHash++;
			if(feof(vhsFp))
			{
				valHash = 0;
			}
			read_index(vhsFp, valHash, &index); 
			read_val(fp, index, val);
			if(valHash == originalHash)
			{
				printf("NOT FOUND\n");
				return 0;
			}
		}
		char key[STRLEN];
		read_key(fp, index, key);
		printf("%s\n", key);
		fclose(fp);
		fclose(vhsFp);
	}
	else
	{
		fprintf( stderr, "Usage: %s filename.kv 'search term'\n", argv[0]);
		return -1;
	}
	return 0;
}