
#include <GL/glut.h>
#include <stdlib.h>
#include <math.h>
#define QUIT 1
#define BAR 2
#define PIE 3
#define LINE 4
int Choice=1;
float Zoom=1;
class scrPt {

public:
    GLint x, y;
};

const GLdouble twoPi = 6.283185;

void circleMidpoint (scrPt , GLint ); // fn. defined in circle.cc

GLsizei winWidth = 700, winHeight = 800;    // Initial display window size.
GLint xRaster = 25, yRaster = 150; // Initial raster position

GLubyte label[36] = { 'J', 'a', 'n',   'F', 'e', 'b',   'M', 'a', 'r',
		      'A', 'p', 'r',   'M', 'a', 'y',   'J', 'u', 'n',
		      'J', 'u', 'l',   'A', 'u', 'g',   'S', 'e', 'p',
		      'O', 'c', 't',   'N', 'o', 'v',   'D', 'e', 'c' };

GLint dataValue[12] = { 420, 342, 324, 310, 262, 185,
			190, 196, 217, 240, 213, 438 };

void init (void)
{
  glClearColor (1.0, 1.0, 1.0, 1.0);

  glMatrixMode (GL_PROJECTION);
  gluOrtho2D (0.0, (GLdouble)winWidth, 0.0, (GLdouble)winHeight);
}

void barChart (void)
{
  GLint month, k;

  glColor3f (1.0, 0.0, 0.0);           //  Set bar color to red.
  for (k = 0; k < 12; k++)
    glRecti (20 + k*50, 165, 40 + k*50, dataValue [k]);

  glColor3f (0.0, 0.0, 0.0);          //  Set text color to black.
  xRaster = 20;                       //  Display chart labels.
  for (month = 0; month < 12; month++) {
    glRasterPos2i (xRaster, yRaster);
    for (k = 3*month; k < 3*month + 3; k++)
      glutBitmapCharacter (GLUT_BITMAP_HELVETICA_12, label [k]);
    xRaster += 50;
  }
}

void lineChart (void)
{
  GLint x = 30;

  xRaster = 25;	yRaster = 150;

  glClear(GL_COLOR_BUFFER_BIT);	// clear display window

  glColor3f(0.0, 0.0, 1.0);	// set line colour to blue
  glBegin(GL_LINE_STRIP);
    for (int k = 0; k < 12; k++)
      glVertex2i(x + k*50, dataValue[k]);
  glEnd();

  glColor3f(1.0, 0.0, 0.0);	// Set marker colour to red
  for (int k = 0; k < 12; k++)
  {
    glRasterPos2i(xRaster + k*50, dataValue[k]-4);
    glutBitmapCharacter(GLUT_BITMAP_9_BY_15, '*');
  }

  glColor3f(0.0, 0.0, 0.0);	// black
  xRaster = 20;
  for (int m = 0; m < 12; m++)
  {
    glRasterPos2i(xRaster, yRaster);
    for (int k = 3*m; k < 3*m+3; k++)
      glutBitmapCharacter(GLUT_BITMAP_HELVETICA_12, label[k]);
    xRaster += 50;
  }
  glFlush();
}

void pieChart (void)
{
    scrPt circCtr, piePt;
    GLint radius = winWidth / 4;               // Circle radius.

    GLdouble sliceAngle, previousSliceAngle = 0.0;

    GLint k, nSlices = 12;                     // Number of Slices. 
    GLfloat dataValues[12] = {10.0, 7.0, 13.0, 5.0, 13.0, 14.0,
                               3.0, 16, 5.0, 3.0, 17.0, 8.0};
    GLfloat dataSum = 0.0;
 
    circCtr.x = winWidth / 2;                  // Circle center position.
    circCtr.y = winHeight / 2;
    circleMidpoint (circCtr, radius);  // Call midpoint circle-plot routine.

    for (k = 0; k < nSlices; k++)
        dataSum += dataValues[k];

    for (k = 0; k < nSlices; k++) {
        sliceAngle = twoPi * dataValues[k] / dataSum + previousSliceAngle;
        piePt.x = (GLint) (circCtr.x + radius * cos (sliceAngle));
        piePt.y = (GLint) (circCtr.y + radius * sin (sliceAngle));
        glBegin (GL_LINES);
            glVertex2i (circCtr.x, circCtr.y);
            glVertex2i (piePt.x, piePt.y);
        glEnd ( );
        previousSliceAngle = sliceAngle;
    }
}

void displayFcn (void)
{
    glClear (GL_COLOR_BUFFER_BIT);   //  Clear display window.

    glColor3f (0.0, 0.0, 1.0);       //  Set circle color to blue.

    
    if(Choice==1)
    {
		pieChart ( );
	}
	else if(Choice==-1)
	{   
    	barChart ( );
	}
	else
	{
		lineChart();
	}
    glutSwapBuffers ( );
}

void winReshapeFcn (int newWidth, int newHeight)
{
    glMatrixMode (GL_PROJECTION);
    glLoadIdentity ( );
	gluOrtho2D (0.0*Zoom, GLdouble (newWidth)*Zoom, 0.0*Zoom, GLdouble (newHeight)*Zoom);
	
    /*  Reset display-window size parameters.  */
    winWidth = newWidth;
    winHeight = newHeight;  
	glViewport(0, 0, winWidth, winHeight);
    glutPostRedisplay();
}

void processMenuEvents(int option) 
{

	switch (option) 
    {
		case QUIT :  exit(0); break;
        case PIE : Choice=1; displayFcn(); break;
        case BAR : Choice=-1;displayFcn(); break;
	case LINE : Choice=0;displayFcn(); break;	
	}
}
void createGLUTMenus() 
{

	int menu;
    menu =glutCreateMenu(processMenuEvents);
	glutAddMenuEntry("Pie-Chart",PIE);
	glutAddMenuEntry("Bar-Chart",BAR);
	glutAddMenuEntry("Line-Chart",LINE);
	glutAddMenuEntry("Quit",QUIT);
	glutAttachMenu(GLUT_RIGHT_BUTTON);
}
void check(unsigned char key, int x, int y)
{
	if (key == 'q' || key == 'Q')
    	exit(0);
	if(key == 'b' || key == 'B')
  		Choice=-1;
	if (key == 'p' || key == 'P')
		Choice=1;
	if (key == 'l' || key == 'L')
		Choice=0;
	if (key == 'z')
	{
		Zoom= Zoom/2;
		winReshapeFcn(winWidth,winHeight);
	}
 	if (key == 'Z')
	{
		Zoom= Zoom*2;
		winReshapeFcn(winWidth,winHeight);
	}
 	else


		displayFcn(); 

	
}
int main (int argc, char** argv)
{
    glutInit (&argc, argv);
    glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
    glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowPosition (100, 100);
    glutInitWindowSize (winWidth, winHeight);
    glutCreateWindow ("Charts");

    init ( );
    glutDisplayFunc (displayFcn);
    glutReshapeFunc (winReshapeFcn);
	createGLUTMenus();
	glutKeyboardFunc(check);
    glutMainLoop ( );
}