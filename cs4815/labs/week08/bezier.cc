#include <GL/glut.h>
#include <stdlib.h>
#include <math.h>
#if !defined(GLUT_WHEEL_UP)
#  define GLUT_WHEEL_UP   3
#  define GLUT_WHEEL_DOWN 4
#endif

/*  Set initial size of the display window.  */
GLsizei winWidth = 600, winHeight = 600;  

/*  Set size of world-coordinate clipping window.  */

GLfloat xwcMin = -60.0, xwcMax = 60.0;
GLfloat ywcMin = -60.0, ywcMax = 60.0;
int clickX, clickY,Poin=5;
float X=0.0,Y=0.0;
float Zoom=1;
GLint width,height; 
bool leftButton = false,nearpoint=false;
GLdouble wx, wy, wz; 
class wcPt3D {
   public:
      GLfloat x, y, z;
};
GLint nCtrlPts = 6;
wcPt3D ctrlPts [6] = { {-40.0, -40.0, 0.0}, {-10.0, 200.0, 0.0},{10.0, -200.0, 0.0}, {40.0, 40.0, 0.0}, {-60.0, 200.0, 0.0},{-60.0,40.0, 0.0}};
void ComputeWorldClipsize(void)
{
	for (int k = 0; k < nCtrlPts; k++) {
		if (ctrlPts[k].x< xwcMin){
			xwcMin=ctrlPts[k].x;
			}
		else if (ctrlPts[k].x> xwcMax){
			xwcMax=ctrlPts[k].x;
			}
		if (ctrlPts[k].y< ywcMin){
			ywcMin=ctrlPts[k].y;
			}
		else if (ctrlPts[k].y> ywcMax){
			ywcMax=ctrlPts[k].y;
			}
   }
}
void DisplayCallback(void)
{
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
	gluOrtho2D (xwcMin*Zoom, xwcMax*Zoom, ywcMin*Zoom, ywcMax*Zoom);

   glutPostRedisplay();
}
void GetOGLPos(int x, int y)
{
GLint viewport[4];
   	GLdouble mvmatrix[16], projmatrix[16];
   	GLint realy;  /*  OpenGL y coordinate position  */
            glGetIntegerv (GL_VIEWPORT, viewport);
            glGetDoublev (GL_MODELVIEW_MATRIX, mvmatrix);
            glGetDoublev (GL_PROJECTION_MATRIX, projmatrix);
/*  note viewport[3] is height of window in pixels  */
            realy = viewport[3] - (GLint) y - 1;
            gluUnProject ((GLdouble) x, (GLdouble) realy, 0.0,
               mvmatrix, projmatrix, viewport, &wx, &wy, &wz);
}
void MouseCallback(int button, int state, int x, int y)
{
nearpoint=false;
  leftButton = ((button == GLUT_LEFT_BUTTON) && (state == GLUT_DOWN));
	GetOGLPos(x,y);
	clickX= wx;
	clickY= wy;
	for(int k= 0; k< nCtrlPts;k++)
	{
		if(sqrt(pow(((wx)-ctrlPts[k].x),2)+pow(((wy)-ctrlPts[k].y),2))<=(10*Zoom))
		{
			nearpoint= true;
			Poin=k;
			k=nCtrlPts;
		}
		
		
	}
	if((button == (GLUT_WHEEL_DOWN))&& (glutGetModifiers()&GLUT_ACTIVE_CTRL))
	{
		Zoom=Zoom*1.2;
		DisplayCallback();
	}
	if((button == (GLUT_WHEEL_UP))&& (glutGetModifiers()&GLUT_ACTIVE_CTRL))
	{
		Zoom=Zoom/1.2;
		DisplayCallback();
	}
	
  glutPostRedisplay();
}
void ControlPointline(void)
{
	 glColor3f(0.0, 0.0, 1.0);	// set line colour to blue
  glBegin(GL_LINE_STRIP);
    for (int k = 0; k< nCtrlPts; k++)
      glVertex2f(ctrlPts[k].x,ctrlPts[k].y);
  glEnd();

}

void MotionCallback(int x, int y)
{
	
	if (leftButton)
	{
	if (nearpoint)
	{
		GetOGLPos(x,y);
		ctrlPts[Poin].x=ctrlPts[Poin].x+((clickX-wx)/-20);
		ctrlPts[Poin].y=ctrlPts[Poin].y+((clickY-wy)/-20);
		ControlPointline();
		glutPostRedisplay();
	}
	else
	{
		    for (int k = 0; k< nCtrlPts; k++)
	{
		ctrlPts[k].x=ctrlPts[k].x+(clickX-wx)/20;
		ctrlPts[k].y=ctrlPts[k].y+(clickY-wy)/-20;
	}
		glutPostRedisplay();
	}
	}
}

void init (void)
{
   /*  Set color of display window to white.  */
	glClearColor (1.0, 1.0, 1.0, 0.0);
	ComputeWorldClipsize();
	glutMouseFunc(MouseCallback);
	glutMotionFunc(MotionCallback);

}

void plotPoint (wcPt3D bezCurvePt)
{
		ControlPointline();
		glColor3f(1.0, 0.0, 0.0);
    glBegin (GL_POINTS);
        glVertex2f (bezCurvePt.x+X, bezCurvePt.y+Y);
		glutIdleFunc(glutPostRedisplay);
    glEnd ( );
	glFlush();
}

/*  Compute binomial coefficients C for given value of n.  */
void binomialCoeffs (GLint n, GLint * C)
{
   GLint k, j;

   for (k = 0;  k <= n;  k++) {
      /*  Compute n!/(k!(n - k)!).  */
      C [k] = 1;
      for (j = n;  j >= k + 1;  j--)
        C [k] *= j;
      for (j = n - k;  j >= 2;  j--)
        C [k] /= j;
   }
}

void computeBezPt (GLfloat t, wcPt3D * bezPt,GLint * C)
{
   GLint k, n = nCtrlPts - 1;
   GLfloat bezBlendFcn;

   bezPt->x = bezPt->y = bezPt->z = 0.0;

   /*  Compute blending functions and blend control points. */
   for (k = 0; k < nCtrlPts; k++) {
      bezBlendFcn = C [k] * pow (t, k) * pow (1 - t, n - k);
      bezPt->x += ctrlPts[k].x * bezBlendFcn;
      bezPt->y += ctrlPts[k].y * bezBlendFcn;
      bezPt->z += ctrlPts[k].z * bezBlendFcn;
   }
}

void bezier (GLint nBezCurvePts)
{
   wcPt3D bezCurvePt;
   GLfloat t;
   GLint *C;

   /*  Allocate space for binomial coefficients  */
   C = new GLint [nCtrlPts];

   binomialCoeffs (nCtrlPts - 1, C);
   for (int i = 0;  i <= nBezCurvePts;  i++) {
      t = GLfloat (i) / GLfloat (nBezCurvePts);
      computeBezPt (t, &bezCurvePt, C);
      plotPoint (bezCurvePt);
   }
   delete [ ] C;
}

void displayFcn (void)
{
   glClear (GL_COLOR_BUFFER_BIT);   //  Clear display window.
		
   /*  Set example number of control points and number of
    *  curve positions to be plotted along the Bezier curve.
    */
	GLint nBezCurvePts = 1000;
	glPointSize (4);
   glColor3f (1.0, 0.0, 0.0);      //  Set point color to red.

   bezier (nBezCurvePts);
   glutSwapBuffers();
}

void winReshapeFcn (GLint newWidth, GLint newHeight)
{

 /*  Maintain an aspect ratio of 1.0.  */
	if(newWidth<newHeight)
	{
   		glViewport (0, 0, newWidth, newWidth);
		height=newWidth;
	}
	else{
		glViewport (0, 0, newHeight, newHeight);
		height=newHeight;
		}
   glMatrixMode (GL_PROJECTION);
   glLoadIdentity ( );
   gluOrtho2D (xwcMin*Zoom, xwcMax*Zoom, ywcMin*Zoom, ywcMax*Zoom);

   glutPostRedisplay();
}

int main (int argc, char** argv)
{
   glutInit (&argc, argv);
   glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB);
   glutInitWindowPosition (50, 50);
   glutInitWindowSize (winWidth, winHeight);
   glutCreateWindow ("Bezier Curve");
   init ( );
   glutDisplayFunc (displayFcn);
   glutReshapeFunc (winReshapeFcn);

   glutMainLoop ( );
}
