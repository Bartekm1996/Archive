
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
  nz() { ind = 0; val = 0.0; }
  nz(int i, double v) {	// constructor when given the two items of data
    ind = i;
    val = v;
  }
  int getInd() const { return ind; } // access to ind class member
  double getVal() const { return val; } // access to val class member
  friend istringstream& operator>>(istringstream& is, nz& base);
  friend ostream& operator<<(ostream &os, const nz& base);

private:
  int ind;
  double val;
};

typedef list<nz> sparseRow;
typedef vector<sparseRow> sparseMat;

void readMat(sparseMat&, int&);	// read from stdin
void transpMat(const sparseMat, int ,sparseMat&); // first arg is mat to transpose;
void write(sparseMat&,int);					     //  second 
					     //  so this will be the transpose
double epsilon = 0.0;

int main(int argc, char *argv[])
{
  int colct;
  sparseMat rows;
  readMat(rows, colct);

  vector<sparseRow> transp;
  transpMat(rows, colct, transp);
write(transp,colct);

  // now write out
  //    :
}

void readMat(sparseMat& rows, int& colct)
{
  colct = 0;
  string line;
int col;
double val;
  while (getline(cin, line))	// get next full line of text; NB: text
  {
    istringstream lstream(line) ;
    sparseRow row;
	while (lstream>> col>> val) 
	{	
		nz next(col, val);
		row.push_back(next);
		if(colct<col){
			cout<<"here"<<endl;
		}

	}
		rows.push_back(row);
  }
}

void transpMat(const sparseMat rows, int colct, sparseMat& transp)
{
  for (unsigned int c = 0; c < rows.size(); c++) // might not be square matrix
  {
    sparseRow row;
    transp.push_back(row);	// initialise every row of transpose;
			//   there are colct of them
  }
for(unsigned int p=0;p<rows.size();p++){
	sparseRow temp=rows[p];
	sparseRow::iterator j;
	for(j=temp.begin();j != temp.end();j++){
	sparseRow temp2=transp[j->getInd()-1];
	temp2.push_back(nz(p+1,j->getVal()));
	transp[j->getInd()-1]=temp2;
	}
}
}
void write(sparseMat& transp,int colct){
	
	for(int p=0;p<transp.size();p++)
	{
	sparseRow temp=transp[p];
	sparseRow::iterator j;
	for(j=temp.begin();j != temp.end();j++){
		cout<<j->getInd()<<" "<<j->getVal()<<" ";
	}
	cout<<endl;
	}
}
