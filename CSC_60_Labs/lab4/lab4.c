#include <stdio.h>
#include <stdlib.h>

int partial_sum(int x[],int npts);

int main(void)
{
	int a[] = {-6,3,4,1,8,20,16,7};
	int *ptr = &a[2];

	double x[] = {5.2,6.1,4.9,-8.5,4.6,3.7};
	double *doubleptr = &x[0];
	
	printf("%f \n",*doubleptr);

	printf("%f \n",*doubleptr+3);

	printf("%f \n",*(doubleptr+3));

	printf("%f \n",(*doubleptr+*(doubleptr+5)));

	printf("%f \n",*(doubleptr+2)-1);

	printf("%f \n",x[3]-*doubleptr);

	printf("%f \n",(*doubleptr+x[5]+*(doubleptr+1)+x[2]));

	printf("%f \n",*x);

	printf("%f \n",*x+*doubleptr);

	printf("%f \n",x[2]-*doubleptr+3);
	
	printf("%i \n",partial_sum(ptr,2));
	
	printf("%i \n",partial_sum(ptr+1,3));
	
	printf("%i \n",partial_sum(a,8));

	printf("%i \n",partial_sum(a,4));
	
	printf("%i \n",partial_sum(ptr,a[1]));

	printf("%i \n",partial_sum(&a[3],2));
}
/*	printf("\nLab 1 \n\n");
	printf("Hi, Bibodi Jay Nikhil\n\n");
	printf("The best preparation for tomorrow is doing your best today. \n\n");
	return EXIT_SUCCESS; */

	int partial_sum(int x[],int npts)
	{
		int k, sum = 0;

		for(k=0;k<npts;k++)
			sum+=x[k];

		return sum;
	}

