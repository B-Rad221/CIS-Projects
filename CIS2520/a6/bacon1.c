#include "util.h"
#include "hashfn.h"
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	if(argc >= 2)
	{
		FILE *fp, *vhsFp, *khsFp, *Fp, *tkhsFp;
		char actor[STRLEN];
		int i;
		strcpy(actor, argv[1]); // Copies the first part of the search term
		for (i = 2; i < argc; i++)
		{ 
			strcat(actor, " "); // Places a space between each word
			strcat(actor, argv[i]); // Concatenates each command line argument into the search term
		}
		fp = fopen("name.basics.kv", "r");
		vhsFp = fopen("name.basics.vhs", "r");
		if(fp == NULL)
		{
			fprintf(stderr, "Error: Could not open the specified file.\n");
			return -1;
		}
		int capacity = get_capacity(vhsFp); // Get the capacity of the hash table in the vhs file
		int valHash = hashfn(actor, capacity);
		int index, originalHash;
		originalHash = valHash;
		char val[STRLEN];
		read_index(vhsFp, valHash, &index); // Reads the vhs file and returns the index stored at the location specified by valHash
		read_val(fp, index, val); // Reads the kv file at the location specified by index and returns the string val
		while(strcmp(val, actor) != 0)
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
				fclose(fp);
				fclose(vhsFp);
				return 0;
			}
		}
		char nameKey[STRLEN];
		read_key(fp, index, nameKey);
		// Find Kevin Bacon's key
		valHash = hashfn("Kevin Bacon", capacity);
		originalHash = valHash;
		read_index(vhsFp, valHash, &index);
		read_val(fp, index, val);
		while(strcmp(val, "Kevin Bacon") != 0)
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
				printf("KEVIN BACON NOT FOUND\n");
				return 0;
			}
		}
		char baconKey[STRLEN];
		read_key(fp, index, baconKey);

		fclose(fp);
		fp = fopen("title.principals.kv", "r");
		khsFp = fopen("title.principals.khs", "r");
		Fp = fopen("title.basics.kv", "r");
		tkhsFp = fopen("title.basics.khs", "r");
		capacity = get_capacity(khsFp);
		int keyHash = hashfn(nameKey, capacity);
		originalHash = keyHash;
		char key[STRLEN];
		while(1)
		{
			read_index(khsFp, keyHash, &index);
			read_keyval(fp, key, val);
			if(strcmp(nameKey, val) == 0)
			{
				int hash = hashfn(key, capacity);
				int OH = hash;
				int ind;
				char titleKey[STRLEN];
				read_index(khsFp, hash, &ind);
				read_keyval(fp, titleKey, val);
				while(1)
				{
					if(strcmp(key, titleKey) == 0 && strcmp(val, baconKey) == 0)
					{
						int c = get_capacity(tkhsFp);
						int titleHash = hashfn(titleKey, c);
						int oh = titleHash;
						int in;
						read_index(tkhsFp, titleHash, &in);
						read_key(Fp, in, key);
						while(strcmp(titleKey, key) != 0)
						{
							titleHash++;
							if(feof(tkhsFp))
							{
								titleHash = 0;
							}
							read_index(tkhsFp, titleHash, &in);
							read_key(Fp, in, key);
							if(titleHash == oh)
							{
								printf("TITLE NOT FOUND\n");
								fclose(fp);
								fclose(vhsFp);
								fclose(khsFp);
								fclose(Fp);
								fclose(tkhsFp);
								return 0;
							}
						}
						char titleName[STRLEN];
						read_val(Fp, in, titleName);
						printf("%s\n", titleName);
						fclose(fp);
						fclose(vhsFp);
						fclose(khsFp);
						fclose(Fp);
						fclose(tkhsFp);
						return 0;
					}
					hash++;
					read_index(khsFp, hash, &ind);
					read_key(fp, ind, titleKey);
					read_val(fp, ind, val);
					if(feof(khsFp))
					{
						hash = 0;
					}
					if(hash == OH)
					{
						break;
					}
				}
			}
			keyHash++;
			if(feof(khsFp))
			{
				keyHash = 0;
			}
			if(keyHash == originalHash)
			{
				fclose(fp);
				fclose(vhsFp);
				fclose(khsFp);
				fclose(Fp);
				fclose(tkhsFp);
				return 0;
			}
		}
		fclose(fp);
		fclose(vhsFp);
		fclose(khsFp);
		fclose(Fp);
		fclose(tkhsFp);
	}
	else
	{
		fprintf( stderr, "Usage: %s 'actor name'\n", argv[0]);
		return -1;
	}
}