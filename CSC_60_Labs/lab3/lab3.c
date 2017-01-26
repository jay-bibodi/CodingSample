/*-------------------------------------------------*/
/* Bibodi Jay Nikhil                               */
/* Lab 3                                           */
/* Figure the perimeter and area of a polygon      */
/* surrounded by a circle                          */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define INPUT_FILE_NAME  ("lab3.dat")
#define OUTPUT_FILE_NAME ("lab3.txt")

int main(void)
{
	double radius,sides,perimeter,area;

	FILE *FILEIN;
	FILE *answer_file;
	
	FILEIN = fopen(INPUT_FILE_NAME,"r");
	if(FILEIN != NULL)
	{
		answer_file = fopen(OUTPUT_FILE_NAME,"w");
		if(answer_file == NULL)
		{
			printf("Error on opening the output file\n");
			return EXIT_FAILURE;	
		}

		/* All these fprintf statements use the file pointer                          	
		 of answer_file.  Feel free to use your own variable
        	 or the one I used.  You may delete this comment. */
                                                                                                 
            	fprintf(answer_file, "\nBibodi Jay Nikhil.  Lab 3.\n\n");
                fprintf(answer_file, "            Number      Perimeter      Area Of  \n");
                fprintf(answer_file, " Radius    Of Sides    Of Polygon      Polygon  \n");
                fprintf(answer_file, "--------   --------   ------------   ----------- \n");	
		while((fscanf(FILEIN,"%lf %lf",&radius,&sides))==2)
		{
			perimeter = 2 * sides * radius * (sin(M_PI/sides));

			area = 0.5 * sides * radius * radius * (sin((2*M_PI)/sides));
						
               		fprintf(answer_file," %5.2f       %5.2f     %10.4f    %10.4f \n",radius,sides,perimeter,area);	
		}

		fclose(FILEIN);
		fclose(answer_file);				
	}
	else
	{
		printf("Error on opening the data file\n");
		return EXIT_FAILURE;
	}
}
/*------------------------------------------------------------------*/
