#include <fstream>
#include <iostream>

using std::cin;
using std::cout;
using std::cerr;
using std::endl;
using std::ofstream;
#include <vector>
#include <sstream>
#include <math.h>
#include <stdlib.h>

//void readArr(int r, double **arr);
void countArr(double p);

int main(int argc, char *argv[])
{
	double epsilon=0;
	double **A;
	if (argc > 1 && std::string(argv[1]) == "-e"){
	epsilon = fabs(strtod(argv[2], 0));
  	countArr(epsilon);
	}
	else
	countArr(epsilon);
	
}

void countArr(double p)
{
  std::string line;
  while (getline(cin, line))
    {
	
	int count=0;
      std::istringstream lstream(line);
      double val;
      // peel off values in this line, one at a time
      while (lstream>> val)
	{
	count++;
	if(!(val==0))
	  {
		if (p==0)
	   	std:: cout<<count<<" "<<val<<" ";
		else if (val>p || val<-p )
		{
		std:: cout<<count<<" "<<val<<" ";
		}	
			 
	}
	   
	  // check if val is 0, etc.
	}
	std:: cout<<endl;
    }

}

