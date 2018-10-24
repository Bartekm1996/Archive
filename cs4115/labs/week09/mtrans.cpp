#include <iostream>
#include <string>
#include <sstream>
#include <list>
#include <vector>

using std::cin;
using std::cout;
using std::cerr;
using std::endl;
using std::string;
using std::list;
using std::vector;
using std::istringstream;
using std::ostream;

#include <math.h>
#include <stdlib.h>

class nz {	 
public:
  nz() { column = 0; value = 0.0; }
  nz(int col, double val) {	
	value = val;    
	column = col;
    
  }

  int get_column() const 
  {
   return column; 
  } 
  double get_value() const { 
  return value; 
  } 

private:
	double value;
  	int column;
  
};
typedef list<nz> sparseRow;
typedef list<sparseRow> sparseMatrix;
void readIn(sparseMat rows);
void transpose(sparseMatrix A,&sparseMatrix B);
int main(int argc, char *argv[])
{
	/*double epsilon=0;
	double **A;
	if (argc > 1 && std::string(argv[1]) == "-e"){
	epsilon = fabs(strtod(argv[2], 0));
  	countArr(epsilon);
	}
	else
	countArr(epsilon);*/
	sparseMatrix rows;
	readIn(rows);
	transpose(rows,&row);
	write(rows);
	
	
}
void readIn(sparseMatrix rows){
std::string line;
	while (getline(cin, line))
    {
		lstream(line);
		list<nz> row;
		int col;
		double val;
		
	}
	
}
void transpose(sparseMatrix A, &sparsemat B){

}
