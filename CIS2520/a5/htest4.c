#include "hash.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define STRLEN 128
#define TOTAL_RECORDS 100
#define CAPACITY 120


struct record
{
  char last_name[STRLEN];
  char first_name[STRLEN];
  char phone_number[13];
};

void read_file( struct record record[TOTAL_RECORDS] )
{
  FILE *fp;
  char buffer[ STRLEN ];
  int i;

  fp = fopen( "names1.txt", "r" );
  fgets( buffer, STRLEN, fp );

  for (i=0;i<TOTAL_RECORDS;i++)
  {
    // this code is not robust, buffer overflows or EOF not detected
    fscanf( fp, "%s %s", record[i].first_name, record[i].last_name );
    fscanf( fp, "%s", record[i].phone_number );
  }

  fclose( fp );
}


int char2int( unsigned char c )
  // convert a printable character to an integer in the range 0-26.
{
  if ( isupper(c) )
  {
    return (int)(c-'A');        // A=0, B=1, C=2, ...
  }
  if ( islower(c) )
  {
    return (int)(c-'a');        // a=0, b=1, c=2, ...
  }
  return 26;                    // anything else = 26
}

int str2int( char *s, int max )
  // convert a string into a number between 0 and max (not inclusive)
{
  char *c;

 
  unsigned long number, column, new;

  // convert number to base 27 number
  number = 0;
  column = 1;
  for (c=s;(*c);c++)
  {
    number += char2int(*c) * column;
    column *= 27;
  }

  // convert number to a base max number and add up the column values
  new = 0;
  while (number)
  {
    new = (new + (number % max)) % max;
    number = number/max;
  }

  return (int)new;
}

int hash_first_name( void *ptr, int max )
{
  struct record *rec;

  rec = ptr;
  return str2int( rec->first_name, max );
}

int hash_last_name( void *ptr, int max )
{
  struct record *rec;

  rec = ptr;
  return str2int( rec->last_name, max );
}

int comp_first_name( const void *ptr1, const void *ptr2 )
{
  const struct record *rec1, *rec2;
  int result;

  rec1 = ptr1;
  rec2 = ptr2;

  result = strcmp( rec1->first_name, rec2->first_name );

#ifdef DEBUG
  printf( "strcmp( \"%s\", \"%s\" )=%d\n", 
          rec1->first_name, rec2->first_name, result );
#endif
  return result;
}

int comp_last_name( const void *ptr1, const void *ptr2 )
{
  const struct record *rec1, *rec2;
  int result;

  rec1 = ptr1;
  rec2 = ptr2;

  result = strcmp( rec1->last_name, rec2->last_name );
#ifdef DEBUG
  printf( "strcmp( \"%s\", \"%s\" )=%d\n", 
          rec1->last_name, rec2->last_name, result );
#endif
  return result;
}


void print_hash( struct HashTable *table )
{
  int i, hash_idx;
  void *ptr;

  for (i=0;i<table->capacity;i++)
  {
    ptr = table->data[i];
    if (ptr)
    {
      hash_idx = table->hash( ptr, table->capacity );
      if (i!=hash_idx)
      {
        printf( "[%d] \"%s\" hash=%d \"%s\"\n", i, (char *)table->data[i],
	                                     hash_idx, (char *)table->data[hash_idx] );
      }
    }
  }
}

int main( int argc, char **argv )
{
  struct record record[TOTAL_RECORDS];	// the records
  struct record rec;

  struct Performance *performance = newPerformance();
  struct HashTable *by_last_name;	// a HashTable based on last_name

  int i;


  // read names and phone numbers for the file, into record
  read_file( record );
  printf( "File read\n" );

  // create HashTable and store all the records in them
  by_last_name = createTable( performance, CAPACITY, 
                              &hash_last_name, comp_last_name );
  for (i=0;i<TOTAL_RECORDS;i++)
  {
    addElement( performance, by_last_name, &(record[i]) );
  }
  printf( "Hashes built\n" );

  strcpy( rec.last_name, "Bonilla" );
  removeElement( performance, by_last_name, &rec );

  strcpy( rec.last_name, "Jacobs" );
  removeElement( performance, by_last_name, &rec );

  strcpy( rec.last_name, "Rowley" );
  removeElement( performance, by_last_name, &rec );

  strcpy( rec.last_name, "Peterson" );
  removeElement( performance, by_last_name, &rec );

  printf( "hashAccuracy( by_last_name )=%d\n", hashAccuracy( by_last_name ) );
  //print_hash( by_last_name );

  rehash(by_last_name );
  printf( "hashAccuracy( by_last_name )=%d\n", hashAccuracy( by_last_name ) );
  //print_hash( by_last_name );


  freeTable( performance, by_last_name );
  free( performance );
}
