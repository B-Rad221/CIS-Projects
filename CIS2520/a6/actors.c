#include "util.h"
#include "hashfn.h"
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	if(argc >= 2)
	{
		FILE *fp, *vhsFp, *khsFp, *Fp, *nkhsFp;
		char title[STRLEN];
		int i;
		strcpy(title, argv[1]); // Copies the first part of the search term
		for (i = 2; i < argc; i++)
		{ 
			strcat(title, " "); // Places a space between each word
			strcat(title, argv[i]); // Concatenates each command line argument into the search term
		}
		fp = fopen("title.basics.kv", "r");
		vhsFp = fopen("title.basics.vhs", "r");
		if(fp == NULL)
		{
			fprintf(stderr, "Error: Could not open the specified file.\n");
			return -1;
		}

		int capacity = get_capacity(vhsFp); // Get the capacity of the hash table in the vhs file
		int valHash = hashfn(title, capacity);
		int index, originalHash;
		originalHash = valHash;
		char val[STRLEN];
		read_index(vhsFp, valHash, &index); // Reads the vhs file and returns the index stored at the location specified by valHash
		read_val(fp, index, val); // Reads the kv file at the location specified by index and returns the string val
		while(strcmp(val, title) != 0)
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
		char titleKey[STRLEN];
		read_key(fp, index, titleKey);
		fclose(fp);
		fclose(vhsFp);
		fp = fopen("title.principals.kv", "r");
		khsFp = fopen("title.principals.khs", "r");
		Fp = fopen("name.basics.kv", "r");
		nkhsFp = fopen("name.basics.khs", "r");
		capacity = get_capacity(khsFp);
		int keyHash = hashfn(titleKey, capacity);
		originalHash = keyHash;
		char key[STRLEN];
		while(1)
		{
			read_index(khsFp, keyHash, &index); // Reads the khs file and returns the index stored at the location specified by keyHash
			read_keyval(fp, key, val); // Reads the kv file and returns the strings key and val
			if(strcmp(titleKey, key) == 0)
			{
				int cap = get_capacity(nkhsFp); // Get the capacity of the hash table in the khs file
				int hash = hashfn(val, cap);
				int ind, OH;
				OH = hash;
				read_index(nkhsFp, hash, &ind); // Reads the khs file and returns the index stored at the location specified by keyHash
				read_key(Fp, ind, key); // Reads the kv file at the location specified by index and returns the string key
				while(strcmp(key, val) != 0)
				{
					hash++;
					if(feof(nkhsFp))
					{
						keyHash = 0;
					}
					read_index(nkhsFp, hash, &ind); // Reads the khs file and returns the index stored at the location specified by keyHash
					read_key(Fp, ind, key); // Reads the kv file at the location specified by index and returns the string key
					if(hash == OH)
					{
						printf("NOT FOUND\n");
						return 0;
					}
				}
				char actorName[STRLEN];
				read_val(Fp, ind, actorName);
				printf("%s\n", actorName);
			}
			keyHash++;
			if(feof(khsFp))
			{
				keyHash = 0;
			}
			if(keyHash == originalHash)
			{
				fclose(fp);
				fclose(khsFp);
				fclose(nkhsFp);
				fclose(Fp);
				return 0;
			}
		}
		fclose(fp);
		fclose(khsFp);
		fclose(nkhsFp);
		fclose(Fp);
	}
	else
	{
		fprintf( stderr, "Usage: %s 'movie title'\n", argv[0]);
		return -1;
	}
	return 0;
}