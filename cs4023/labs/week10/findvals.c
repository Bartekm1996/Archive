#include <time.h>
#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <omp.h>

#include "utils.h"

int main(int argc, char **argv)
{
  char *refStr, *tolStr;
  refStr = tolStr = NULL;

  int v = 0;
	//Read in / Check arguments placement. 
  int ind;
  for (ind = 1; ind < argc; ind++)
  {
    if (strcmp(argv[ind], "-r") == 0) { refStr = argv[ind+1]; ind++; }
    if (strcmp(argv[ind], "-t") == 0) { tolStr = argv[ind+1]; ind++; }
    if (strcmp(argv[ind], "-v") == 0) { v = 1; }
  }
	//Checking if arguments were passed in.
  if ((argc != 5 && argc != 6) || refStr == NULL || tolStr == NULL)
  {
    fprintf(stderr, "Usage: %s [-v] -r ref -t tol\n", argv[0]);
    exit(1);
  }

  //Change Strings to float.
  float ref = strtof(refStr, 0);
  float tol = strtof(tolStr, 0);
  if (tol < 0.0)
    tol *= -1;                 
	//Reading two numbers
  int rct, cct;
  fscanf(stdin, "%d", &rct);
  fscanf(stdin, "%d", &cct);
	//Allocating memory
  float** rows = malloc(rct * sizeof(float *));
  if (rows == 0)
  {
    fprintf(stderr, "Couldn't alocate sufficient space.\n");
    exit(1);
  }
  int i;
  for (i = 0; i < rct; i++)
  {
    float* row = malloc(cct * sizeof(float));
    if (row == 0)
    {
      fprintf(stderr, "Couldn't alocate sufficient row space.\n");
      exit(1);
    }
    rows[i] = row;
  }

  int r, c;
  for (r = 0; r < rct; r++)
    for (c = 0; c < cct; c++)
      fscanf(stdin, "%f", &rows[r][c]);

  struct tm *local;
  time_t Start;
	//Read and record clock
  time(&Start);  
  local = localtime(&Start);
  printf("# Start time and date: %s", asctime(local));

  struct timeval start, FinishedTim;
  gettimeofday(&start, NULL);

  //Checking data
  int Matches = 0;
	#pragma omp parallel for private(r) private(c)
  for (r = 0; r < rct; r++)
    for (c = 0; c < cct; c++)
      if (approxEqual(rows[r][c], ref, tol) == 1)
      {
			if (v)
	  		fprintf(stdout, "r=%d, cc=%d: %.6f (thread= %d)\n",
								r, c, rows[r][c], omp_get_thread_num());
				#pragma omp critical(Matches)
				Matches++;
      }

  //Print put Matches.
  fprintf(stdout, "Found %d approximate matches.\n", Matches);
	//Print out Elapsed time.
  gettimeofday(&FinishedTim, NULL);
  double ticks = (FinishedTim.tv_sec * 1000000 + FinishedTim.tv_usec) - (start.tv_sec * 1000000 + start.tv_usec);
  fprintf(stdout, "Elapsed time: %.6f(s)\n", ticks/1000000);

  exit(0);
}
