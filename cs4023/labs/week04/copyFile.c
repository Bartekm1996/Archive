#include <stdio.h>
#include <stdlib.h>

int main()
{
  char c;
  FILE *from,*to;/*declare file address to with file address from*/
 
  from = fopen("file.txt", "r");
  to=fopen("copy.txt","w+");/*open in Write mode*/
  if (from == NULL)
  {
    perror("file.txt");
    exit(1);
  }

  /* file exists, so start reading */
  while ((c = getc(from)) != EOF)
    putc(c, to);

  fclose(to);
  fclose(from);/*Close both files that have been opened*/

  exit(0);
}
