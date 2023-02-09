#include<stdio.h>
#include<stdlib.h>
#include<string.h>

unsigned long long bits2ull(char *bits)
{
	int i;
	unsigned long long result = 0;
	for(i = 0; i <= strlen(bits); i++)
	{
		if(bits[i] == '1')
		{
			// 1<<(strlen(bits)-i-1) shifts the bit 1 left a number of places equal to the location of the bit we are reading
			// through shifting left the value of result increases by 1*2^(strlen(bits)-i-1) effectively translating binary to decimal.
			result += 1<<(strlen(bits)-i-1);
		}
	}
	return (result);
}

long long bits2ll(char *bits)
{
	int i;
	long long result = 0;
	for(i = 0; i < strlen(bits); i++)
	{
		if(bits[i] == '1')
		{
			if(i == 0)
			{
				//In two's compliment, the most significant bit becomes negative
				result += -1<<(strlen(bits)-i-1);
			}
			else
			{
				result += 1<<(strlen(bits)-i-1);
			}
		}
	}
	return (result);
}

void getbytes(unsigned char dest[], int bytes, void *src, int reverse)
{
	unsigned char *source = src;
	int i;
	for(i = 0; i < bytes; i++)
	{
		// reverse is true when it is anything other than 0.
		if(reverse)
		{
			if(source[strlen((char *)source)-i-1] != '\0')
			{
				dest[i] = source[strlen((char *)source)-i-1];
			}
		}
		else
		{
			if(source[i] != '\0')
			{
				dest[i] = source[i];
			}
		}
	}
	dest[i] = '\0';
}

void getbits(char dest[], int bytes, void *src, int start, int end)
{
	unsigned char *source = src;
	int i;
	//Loop stops when it reads the bit at location of end+1.
	for(i = 0; i < start - end; i++)
	{
		int currentbyte = (start-i) / 8; 
		int currentbit = (start-i) % 8;
		//printf("byte: %d -> %d, bit: %d\n", currentbyte, bytes-currentbyte-1, currentbit);
		//bytes-currentbyte-1 is equal to the index of the byte in the src pointer.
		//(1<<start_remainder & source[bytes-start_quotient-1]) is zero when the current bit (start_remainder) is zero, and non-zero when the current bit is 1.
		 if((1<<currentbit & source[bytes-currentbyte-1]) == 0)
		 {
		 	//printf("bit: %d, byte: %d, %d\n", currentbit, currentbyte, source[bytes-currentbyte-1]);
		 	dest[i] = '0';
		 }
		 else
		 {
		 	dest[i] = '1';
		 }
	}
	dest[i] = '\0';
}

void spff(char *sign, char *exponent, char *significand, float *src)
{
	int i;
	//Concepts taken from this website: https://www.google.com/search?client=firefox-b-e&q=float+to+binary+in+c
	//In order to accurately represent the float as binary, the memory must be copied to an unsigned int.
	unsigned int source;
	memcpy(&source, src, sizeof(source));
	for(i = 0; i < (sizeof(float)*8); i++)
	{
		//significand: bits 0-22
		if(i < 23)
		{
			//since source is an unsigned int, 1<<i is changed to 1u<<i
			if((1u<<i & source) == 0)
			{
				significand[22-i] = '0';
			}
			else
			{
				significand[22-i] = '1';
			}
		}
		//exponent: bits 23-30
		if(i > 22 && i < 31)
		{
			if((1u<<i & source) == 0)
			{
				exponent[7-(i-23)] = '0';
			}
			else
			{
				exponent[7-(i-23)] = '1';
			}
		}
		//sign: bit 31
		if(i == 31)
		{
			if((1u<<i & source) == 0)
			{
				sign[0] = '0';
			}
			else
			{
				sign[0] = '1';
			}
		}
	}
	significand[23] = '\0';
	exponent[8] = '\0';
	sign[1] = '\0';
}

void dpff(char *sign, char *exponent, char *significand, double *src)
{
	int i;
	unsigned long source;
	memcpy(&source, src, sizeof(source));
	for(i = 0; i < (sizeof(double)*8); i++)
	{
		//significand: bits 0-51
		if(i < 52)
		{
			//since source is an unsigned long, 1<<i is changed to 1ul<<i
			if((1ul<<i & source) == 0)
			{
				significand[51-i] = '0';
			}
			else
			{
				significand[51-i] = '1';
			}
		}
		//exponent: bits 52-62
		if(i > 51 && i < 63)
		{
			if((1ul<<i & source) == 0)
			{
				exponent[10-(i-52)] = '0';
			}
			else
			{
				exponent[10-(i-52)] = '1';
			}
		}
		//sign: bit 63
		if(i == 63)
		{
			if((1ul<<i & source) == 0)
			{
				sign[0] = '0';
			}
			else
			{
				sign[0] = '1';
			}
		}
	}
	significand[52] = '\0';
	exponent[11] = '\0';
	sign[1] = '\0';
}