#include <time.h>
#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>

#include "utils.h"

#define NUM_THREADS 2



typedef struct sharedobject {
 float** d_matrix; 
 int sh_rct;   
 int sh_cct;   
  float sh_ref; 
 float sh_tol;
 int verbose;  
} so_t;


so_t* shared_data;   
pthread_t callThd[NUM_THREADS];
pthread_mutex_t mutexcount;

int sh_count=0;

void *runner(void *param);


int main(int argc, char **argv)
{
  	char *refStr, *tolStr;
 	refStr = tolStr = NULL;

  	int verbose = 1;
	int re=1;
 	int t=1;
	int numOfE= argc;
	int w;
  for (w=0;w<numOfE;w++)
	{
		if((strcmp(argv[w], "-v")==0))
		{
			verbose=0;
		}
		else if((strcmp(argv[w], "-r") == 0))
		{
			refStr=argv[w+1];
			re=0;
			
		}
		else if((strcmp(argv[w], "-t") == 0))
		{
			tolStr=argv[w+1];
			t=0;
		}
		
	}
	if(re+t==2)
	{
	 return 1;
	}
		
	if (argc < 5 || refStr == NULL || tolStr == NULL)
	{
		  fprintf(stderr, "Usage: %s -r ref -t tol\n", argv[0]);
		  exit(1);
	}


	struct tm *local;
	time_t start, end;
	time(&start);  
	local = localtime(&start);

	printf("# Start time and date: %s", asctime(local));
	struct timeval tim;
	gettimeofday(&tim, NULL);
	double t1=tim.tv_sec+(tim.tv_usec/1000000.0);

  float ref=strtof(refStr,NULL);
  float tol=strtof(tolStr,NULL);
  if(tol<0)
  {
  	tol=tol*-1;
  }

  	int rct, cct; 
	scanf("%d", &rct);
	scanf("%d", &cct);
  	shared_data = malloc(sizeof(so_t));  
  
  
  float** sh_rows = (float **) malloc(rct * sizeof(float *)); 
  	if (sh_rows == 0)
		{
			fprintf(stderr, "Couldn't alocate sufficient space.\n");
			exit(1);
		}

	int i;
	for (i = 0; i < rct; i++)
	{
		float* row = (float *) malloc(cct * sizeof(float));
		if (row == 0)
		{
			fprintf(stderr, "Couldn't alocate sufficient row space.\n");
			exit(1);
		}
		sh_rows[i]=row;
	}
int j;
  for (i = 0; i < rct; i++)
  {
     for (j = 0; j < cct ; j++)
     {
       scanf("%f", &sh_rows[i][j]);
     }
  }



  shared_data->sh_rct = rct;
  shared_data->sh_cct = cct;
  shared_data->d_matrix = sh_rows;
  shared_data->sh_ref = ref;
  shared_data->sh_tol = tol;
  shared_data->verbose = verbose; 


 

  pthread_t tid[NUM_THREADS];   
  int t_index[NUM_THREADS]={0,1};     

  llist* plist=NULL;	

  
  int ti=0;
  for (ti=0;ti<NUM_THREADS;ti++){

      
      pthread_create(&tid[ti],NULL,runner,&t_index[ti]);

  } 

  for (ti=0; ti<NUM_THREADS;ti++){
      pthread_join(tid[ti], (void **) &plist);   
      print_list(plist);                  

  }
  fprintf(stdout, "Found %d approximate matches.\n", sh_count);


 
  gettimeofday(&tim, NULL);	
	double t2=tim.tv_sec+(tim.tv_usec/1000000.0);
	fprintf(stdout, "Elapsed time: %.6f(s)\n", t2-t1);


  
	pthread_mutex_destroy(&mutexcount);

  exit(0);
}

void *runner(void *param){

  int tid = *((int *) param);
  int s_row = 0;
  int e_row = 0;
	if (tid == 0 )
	{
		s_row = 0;
		e_row = (shared_data->sh_rct/2);
	}
	else
	{
		s_row = (shared_data->sh_rct/2);
		e_row = shared_data->sh_rct;
	}
  int r=0,c=0;
  float** sh_rows = shared_data->d_matrix;
  float ref = shared_data->sh_ref;
  float tol = shared_data->sh_tol;
  llist* out_list=NULL;


  for (r = s_row; r < e_row; r++)
  {
    for (c = 0; c < shared_data->sh_cct; c++)
     {
    	if (approxEqual(sh_rows[r][c], ref, tol) == 1)
			{	
	    	pthread_mutex_lock(&mutexcount);
	    	sh_count++;
	    	pthread_mutex_unlock(&mutexcount);
	  		if(shared_data->verbose == 0)
	  		{
	 	  		out_list = add_list(out_list, r, c, sh_rows[r][c]);
	 	  	}
	    }
 	}
 }
  
  pthread_exit((void*)out_list);
}
