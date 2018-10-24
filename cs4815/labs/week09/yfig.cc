#include <GL/glut.h>
#include <stdlib.h>
#include <math.h>
#if !defined(GLUT_WHEEL_UP)
#  define GLUT_WHEEL_UP   3
#  define GLUT_WHEEL_DOWN 4
#endif
#define QUIT 1
#define Increase1 2
#define Decrease1 5
#define Increase2 10
#define Decrease2 11
#define Increase3 12
#define Decrease3 13
#define RED 6
#define GREEN 7
#define BLUE 8
#define WHITE 9

/*  Set initial size of the display window.  */
GLsizei winWidth = 600, winHeight = 600;  

/*  Set size of world-coordinate clipping window.  */
GLfloat red=0.0,blue=0.0,green=1.0, Thickness=2;
GLfloat xwcMin = -60.0, xwcMax = 60.0;
GLfloat ywcMin = -60.0, ywcMax = 60.0;
float clickX, clickY ,clickX1, clickY1,clickX2, clickY2;
float X=0.0,Y=0.0;
float Zoom=1,movex=0,movey=0;
GLint width,height; 
bool check1= false, Some=false, mb=false;
int Choice=0,count=1;
GLdouble wx, wy, wz; 
double posx=0.0,posy=0.0;
class wcPt3D {
   public:
      GLfloat x, y, x1,y1;
};
wcPt3D drawpt [3] = { {0.0, 0.0, 0.0,0.0}, {0.0, 0.0, 0.0,0.0},{0.0, 0.0, 0.0,0.0}};
void DisplayCallback(void)
{
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
	gluOrtho2D (height*Zoom, height*Zoom, height*Zoom, height*Zoom);

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
void add(float x1,float y1,float x2,float y2)
{
	drawpt[Choice].x=x1;
	drawpt[Choice].y=y1;
	drawpt[Choice].x1=x2;
	drawpt[Choice].y1=y2;
	check1=false;	
}
void Draw(void)
{
  glMatrixMode (GL_PROJECTION);
   glLoadIdentity ( );
		glLineWidth(Thickness);
		glColor3f(red, green,blue);	// set line colour to blue
			glBegin(GL_LINE_STRIP);
	    		glVertex2f(drawpt[1].x-posx,drawpt[1].y-posy);
			glVertex2f(drawpt[1].x1-posx,drawpt[1].y1-posy);
			glEnd();
		
			glBegin(GL_LINE_STRIP);
	    		glVertex2f(drawpt[0].x -posx,drawpt[0].y-posy);
			glVertex2f(drawpt[0].x -posx,drawpt[0].y1-posy);
			glVertex2f(drawpt[0].x1-posx,drawpt[0].y1-posy);
			glVertex2f(drawpt[0].x1-posx,drawpt[0].y-posy);
			glVertex2f(drawpt[0].x-posx,drawpt[0].y-posy);
			glEnd();
			float radius=(sqrt(pow(((drawpt[2].x)-drawpt[2].x1),2)+pow(((drawpt[2].y)-drawpt[2].y1),2)));
			glBegin(GL_LINE_LOOP);
			for(int i = 0; i <= 100;i++) { 
			glVertex2f(
			    (drawpt[2].x -posx)+ (radius * cos(i *  2.0f * 3.141592 / 100)), 
			    (drawpt[2].y -posy)+ (radius * sin(i * 2.0f * 3.141592 / 100)));
			}
	glEnd();
 		
}
void MouseCallback(int button, int state, int x, int y)
{
	if((button == (GLUT_MIDDLE_BUTTON))&& (state == GLUT_DOWN))
	{		GetOGLPos(x,y);
			clickX2= wx;
			clickY2= wy;
			mb=true;
			}
	else mb=false;
	if((button == GLUT_LEFT_BUTTON) && (state == GLUT_DOWN))
		if(check1)
			if(count==2)
			{
				GetOGLPos(x,y);
				clickX1= wx;
				clickY1= wy;
				count=1;

					Some=true;
			}
			else
			{
			GetOGLPos(x,y);
			clickX= wx;
			clickY= wy;
			count++;
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

void line(void)
{	
	glLineWidth(1.0);
	
	 glColor3f(0.0, 0.0, 1.0);	// set line colour to blue
  	glBegin(GL_LINE_STRIP);
    glVertex2f(0.0,height*Zoom);
		glVertex2f(0.0,0.0);
		glVertex2f(0.0,-(height*Zoom));
		glVertex2f(0.0,0.0);
		glVertex2f(-(height*Zoom),0.0);
		glVertex2f(height*Zoom,0.0);
		glEnd();
	
}

void MotionCallback(int x, int y)
{
	if(mb){
	GetOGLPos(x,y);
	movex=(clickX2-wx)/20;
	movey=(clickY2-wy)/20;
	posx+= movex;
	posy+= movey;
		}

}

void init (void)
{
   /*  Set color of display window to white.  */
	glClearColor (1.0, 1.0, 1.0, 0.0);
	glutMouseFunc(MouseCallback);
	glutMotionFunc(MotionCallback);
}
void displayFcn (void)
{	 gluLookAt(posx, posy, 0, posx, posy,-1, 0, 1, 0);
   glClear (GL_COLOR_BUFFER_BIT);   //  Clear display window.
		line();
		if(Some){
			add(clickX, clickY ,clickX1, clickY1);}
		Draw();
   glutSwapBuffers();
}

void winReshapeFcn (GLint newWidth, GLint newHeight)
{
 
 /*  Maintain an aspect ratio of 1.0.  */
	if(newWidth>newHeight)
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
   gluOrtho2D (height*Zoom, height*Zoom, height*Zoom, height*Zoom);

   glutPostRedisplay();
}
void processMenuEvents(int option) 
{

	switch (option) 
    {
			case QUIT :  exit(0); break;
			case Increase1 : Thickness=3.0;  break;
			case Decrease1 : Thickness=1.5; break;
			case Decrease2 : Thickness=2.0; break;
			case Increase3 : Thickness=4.0;  break;
			case Decrease3 : Thickness=1; break;
			case RED : red = 1.0; green = 0.0; blue = 0.0; break;
			case GREEN : red = 0.0; green = 1.0; blue = 0.0; break;
			case BLUE : red = 0.0; green = 0.0; blue = 1.0; break;
			case WHITE : red = 1.0; green = 1.0; blue = 1.0; break;
	}
}
void createGLUTMenus() 
{
 
	int menu,subThick,subcolour;
    subcolour =glutCreateMenu(processMenuEvents);
	glutAddMenuEntry("Red",RED);
	glutAddMenuEntry("Blue",BLUE);
	glutAddMenuEntry("Green",GREEN);

    subThick = glutCreateMenu(processMenuEvents);
		glutAddMenuEntry("4px",Increase3);	
		glutAddMenuEntry("3px",Increase1);		
		glutAddMenuEntry("Defualt",Decrease2);    
		glutAddMenuEntry("1.5px",Decrease1);
    		glutAddMenuEntry("1px",Decrease3);
    
	menu = glutCreateMenu(processMenuEvents);
	glutAddSubMenu("Thickness",subThick);
  glutAddSubMenu("Pen-colour",subcolour);
  glutAddMenuEntry("quit",QUIT);
	glutAttachMenu(GLUT_RIGHT_BUTTON);
}
void Check(unsigned char key, int x, int y)
{
  if (key == 'l' || key == 'L'){
    		check1=true;
		Some=false; 
		Choice=1;}
  else if(key == 'c' || key =='C'){
		check1=true;
		Some=false;  	
		Choice=2;}
	else if(key == 'r' || key =='R'){
		check1=true;
		Some=false;  
		Choice=0;}

	displayFcn(); 
	
}
int main (int argc, char** argv)
{
   glutInit (&argc, argv);
   glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB);
   glutInitWindowPosition (50, 50);
   glutInitWindowSize (winWidth, winHeight);
   glutCreateWindow ("yfig");
   init ( );
   glutDisplayFunc (displayFcn);
   glutReshapeFunc (winReshapeFcn);
	glutKeyboardFunc(Check);
		 createGLUTMenus();
   glutMainLoop ( );
}
