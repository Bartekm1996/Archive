/*  This program reads the contents of a file "file.txt" and sends them over a pipe. 
    The program also prints out the contents of file.txt to standard out. However, 
    the purpose of this program is to provide skeleton code for the students to modify
    and implement a file copying program in a file named 'copyFile.c' that takes command
    line arguments to select whether to encrypt the input data, and the names of the input 
    and output files. */   

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#include "utils.h"

#define READ_END 0
#define WRITE_END 1

int main(int argc, char **argv)
{
  
  char c;
  FILE *from,*to;

  /* File descriptor array to hold two ends of the pipe */
  int pfd[2];

  pid_t pid;


  /* Add code to check for -f parameter */
  int flipping = (strcmp(argv[1], "-f") == 0);

  /* Add code to read in the names for input and output files */
  if (flipping)
    {
       from = fopen(argv[2], "r");
       to=fopen(argv[3],"w+");
       if (from == NULL)
	{
	  perror(argv[2]);
	  return 1;
	}
        if(to== NULL)
	  {
	    perror(argv[3]);
	    return 1;
	  }
    }
  else
    {
      from = fopen(argv[1], "r");
      to=fopen(argv[2],"w+");/*open in Write mode*/
      if (from == NULL)
	{
	  perror(argv[1]);
	  return 1;
	}
       if(to== NULL)
	 {
	   perror(argv[2]);
	   return 1;
	 }
    }

  /* Add error checking code as above, to check if output file opens without returing NULL */
 

  // create pipe
  if (pipe(pfd) == -1) {
	fprintf(stderr,"Pipe failed");

	/* close ALL the open files */
	fclose(from);


	return 1;
  }

  /* now create the processes */
  pid = fork();

  if (pid < 0) { /* error occurred */
	fprintf(stderr, "Fork Failed");

	/* close ALL the open files */
	fclose(from);

	return 1;
  }
  if (pid > 0) {  /* parent */
	/* close the end of the pipe we do not need */
	close(pfd[READ_END]);

	/* read from the input file and write to the pipe */
	while ((c = getc(from)) != EOF){
	  if(flipping)
	  /* Add code to flip c before outputting to the pipe */
	  flipChar(c)

		        
	    write(pfd[WRITE_END],&c, 1);
	}
	/* close the input file and the write end of the pipe */
	fclose(from);
	close(pfd[WRITE_END]);

	wait(NULL);
  }
  else{  /* child process */

         /* ADD code to close the end of the pipe we do not need */
	  fclose(from);
	  close(pfd[WRITE_END]);
	  read(pfd[READ_END],c,1);
	  while(c!= EOF)
	    putc(c,to);
	}
	  



         /* Add code to read from the READ_END of the pipe and write to the output file.
            Then decide where to call fclose on output file and close the  */


  }

  return 0;
}
