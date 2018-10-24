#include <fstream>
#include <iostream>

using std::cin;
using std::cout;
using std::cerr;
using std::endl;
using std::ofstream;
#include <vector>

#include <math.h>
#include <stdlib.h>

void readArr(int, double **);
void reFormat(int, double **);

int main(int argc, char *argv[])
{
	int ac = atoi(argv[2]);
  int ar = atoi(argv[1]);
  

  if (ac != ar)
  {
    cerr<< "Matrix dimensions mismatch; exiting.\n";
    exit(-1);
  }
  	double **A = new double*[ar];	
					
  for (int i = 0; i < ar; i++)
    A[i] = new double[ac];

  	readArr(ar, A);
	reFormat(ar ,A);

}
void reFormat(int r, double **arr)
{
  std::string out="";
	int tcount=0;
	int count;
	std::vector<int> counts;
	std::vector<int> pos;
	std::vector<double> value;
	std::cout<<r;
	std::cout<<endl;
	
	
	for (int i = 0; i < r; i++)
		{
			count=0;
			for(int j= 0; j< r;j++)
			{
				if(!(arr[i][j]==0))
					{
					   count++;
					   pos.push_back(j+1);
					   value.push_back(arr[i][j]);
					  
							 
					}
			}
			tcount+=count;
		        counts.push_back(count);
			
		}
	int j=0;
	int offset=0;
	for(int x=0;x<r;x++)
	  {
	    std::cout<<counts[x];
	      std::cout<<" ";
	      for(;j<(counts[x])+offset;j++)
		{
		  std::cout<<pos[j]<<" "<<value[j]<<" ";
		}
		
		  offset+=counts[x];
	      std:: cout<<endl;
	  }
	std::cout<<tcount<<endl;
}
void readArr(int r, double **arr)
{	
	for(int i =0; i < r;i++)
		for(int j= 0;j< r;j++)
				std::cin>>arr[i][j];
}

