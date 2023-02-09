#include "util.h"
#include "hashfn.h"
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	if(argc == 3)
	{
		FILE *fp, *khsFp;
		char *string = malloc(argc + 1);
		strcpy(string, argv[1]);
		fp = fopen(string, "r");
		string[strlen(string) - 1] = 'h';
		string[strlen(string)] = 's';
		string[strlen(string) + 1] = '\0';
		khsFp = fopen(string, "r");
		free(string);
		if(fp == NULL)
		{
			fprintf(stderr, "Error: Could not open the specified file.\n");
			return -1;
		}

		char search[STRLEN];
		strcpy(search, argv[2]);
		int capacity = get_capacity(khsFp); // Get the capacity of the hash table in the khs file
		int keyHash = hashfn(search, capacity);
		int index, originalHash;
		originalHash = keyHash;
		char key[STRLEN];
		read_index(khsFp, keyHash, &index); // Reads the khs file and returns the index stored at the location specified by keyHash
		read_key(fp, index, key); // Reads the kv file at the location specified by index and returns the string key
		while(strcmp(key, search) != 0)
		{
			keyHash++;
			if(feof(khsFp))
			{
				keyHash = 0;
			}
			read_index(khsFp, keyHash, &index); // Reads the khs file and returns the index stored at the location specified by keyHash
			read_key(fp, index, key); // Reads the kv file at the location specified by index and returns the string key
			if(keyHash == originalHash)
			{
				printf("NOT FOUND\n");
				return 0;
			}
		}
		char val[STRLEN];
		read_val(fp, index, val);
		printf("%s\n", val);
		fclose(fp);
		fclose(khsFp);
	}
	else
	{
		fprintf( stderr, "Usage: %s filename.kv 'search term'\n", argv[0]);
		return -1;
	}
	return 0;
}