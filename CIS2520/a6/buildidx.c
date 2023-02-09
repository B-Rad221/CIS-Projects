#include "util.h"
#include "hashfn.h"
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	if(argc == 3)
	{
		long capacity = strtol(argv[2], NULL, 10);
		FILE *fp, *khsFp, *vhsFp;
		char *string = malloc(argc + 1);
		strcpy(string, argv[1]);
		fp = fopen(string, "r");
		string[strlen(string) - 1] = 'h';
		string[strlen(string)] = 's';
		string[strlen(string) + 1] = '\0';
		khsFp = fopen(string, "w+");
		string[strlen(string) - 3] = 'v';
		vhsFp = fopen(string, "w+");
		free(string);
		if(fp == NULL)
		{
			fprintf(stderr, "Error: Could not open the specified file.\n");
			return -1;
		}
		write_empty(khsFp, capacity);
		write_empty(vhsFp, capacity);
		int i = 0;
		while(!feof(fp))
		{
			char key[STRLEN];
			char val[STRLEN];
			read_keyval(fp, key, val);
			int keyHash = hashfn(key, (int)capacity);
			int valHash = hashfn(val, (int)capacity);
			int index, originalHash;
			read_index(khsFp, keyHash, &index);
			originalHash = keyHash;
			while(index != -1)
			{
				if(feof(khsFp))
				{
					keyHash = -1; // Increments to zero after this. read_index will set the file pointer back to the beginning of the file.
				}
				keyHash++;
				read_index(khsFp, keyHash, &index);
				if(index != -1 && keyHash == originalHash)
				{
					break;
				}
			}
			write_index(khsFp, i, keyHash);
			read_index(vhsFp, valHash, &index);
			originalHash = valHash;
			while(index != -1)
			{
				if(feof(vhsFp))
				{
					valHash = -1; // Increments to zero after this. read_index will set the file pointer back to the beginning of the file.
				}
				valHash++;
				read_index(vhsFp, valHash, &index);
				if(index != -1 && valHash == originalHash)
				{
					break;
				}
			}
			write_index(vhsFp, i, valHash);
			i++;
		}

		fclose(fp);
		fclose(khsFp);
		fclose(vhsFp);
	}
	else
	{
		fprintf( stderr, "Usage: %s filename.kv capacity\n", argv[0]);
		return -1;
	}
	return 0;
}