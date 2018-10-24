
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

class nz {	    // one instance for every non-zero entry in matrix
public:
  nz() { column = 0; value = 0.0; }
  nz(int col, double val) {	// constructor when given the two items of data
    column = col;
    value = val;
  }

  int get_column() const { return column; } // access to ind class member
  double get_value() const { return value; } // access to val class member
  friend istringstream& operator>>(istringstream& is, nz& base);
  friend ostream& operator<<(ostream &os, const nz& base);

private:
  int column;
  double value;
};

typedef list<nz> sparseRow;
typedef list<sparseRow> sparseMat;

void readMat(sparseMat&, int&, int&);	// read from stdin
void transpMat(sparseMat, sparseMat&, int); // first arg is mat to transpose;
					     //  second is passed by reference
					     //  so this will be the transpose
void printMatrix(sparseMat& rows);

double epsilon = 0.0;

int main(int argc, char *argv[])
{
  string line;
  int maxCol = 0;
  int n, m;
  cin>> n>> m;			// summary info: no. of lines, non-zeros
  getline(cin, line);		// read up to end of first line
  cout<< n<< " "<< m<< endl;		//  echo straight back out

  int nzct;
  sparseMat rows;
  readMat(rows, nzct, maxCol);
  
  if (nzct != m)
  {
    cerr<< "Error: non-zero count ("<< nzct<< ") does not match summary ("<< m<<"); exiting."<< endl;
    exit(-1);
  }

  list<sparseRow> transp;
  transpMat(rows, transp, maxCol);
	printMatrix(transp);
  // now write out
  //    :
}

void readMat(sparseMat& rows, int& nzct, int &maxCol)
{
  nzct = 0;
  string line;
  while (getline(cin, line))	// get next full line of text; NB: text
  {
    istringstream lstream(line) ;
    sparseRow row;
	//nz next;
	int col;
	double val;
	while (lstream >> col >> val)
	{
		row.push_back(nz(col,val));
		nzct++;
		
		if(col>maxCol)
			maxCol = col;
	}
	
	rows.push_back(row);

  }
}

void printMatrix(sparseMat& rows)
{
	list<list <nz> >::iterator i;
	for(i = rows.begin(); i != rows.end(); ++i)
	{
	list<nz> row = *i;
	list<nz>::iterator x;
	std::ostringstream sstream;
	
		for(x=row.begin(); x != row.end(); ++x)
		{
		sstream << x -> get_column() << " " << x -> get_value() << " ";
		 
		}
		cout << sstream.str() << endl;
		sstream.clear();
		sstream.seekp(0);
	}
	
}

void transpMat(sparseMat rows, sparseMat& transp, int maxRowSize)
{

	
	int rowCounter = 0;
	
	sparseMat::iterator rowIterator;
	
	for(unsigned int r = 0; r < maxRowSize; r++)
	{
	int columnPostion = 1;
	sparseRow tempRow;
		for(rowIterator = rows.begin(); rowIterator != rows.end(); ++rowIterator)
		{
				
	 			sparseRow row = *rowIterator;
	 			
	 			sparseRow::iterator columnIterator;
	 			bool found = false;
	 			
	 			for(columnIterator = row.begin(); columnIterator != row.end() && !found; ++columnIterator)
				{
					if(columnIterator->get_column() == rowCounter+1)
					{
						tempRow.push_back(nz(columnPostion,columnIterator->get_value()));
						found = true;
					}
				}
				columnPostion++;
		}
		
		transp.push_back(tempRow);
		rowCounter++;	
	}
}

