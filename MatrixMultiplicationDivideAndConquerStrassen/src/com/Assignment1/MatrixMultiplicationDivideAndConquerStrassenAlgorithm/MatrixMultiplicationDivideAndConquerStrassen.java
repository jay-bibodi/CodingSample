package com.Assignment1.MatrixMultiplicationDivideAndConquerStrassenAlgorithm;

import java.util.Scanner;

/**
 * @author Bibodi Jay
 * 
 * This class is used to find product of two matrix using divide and conquer method
 * and using strassen algorithm, which was given as below : 
 * 
 * 	Strassen’s algorithm is based on the following observation:
 *	C11 =P5 + P4 − P2 + P6 C12 =P1 + P2
 *	C21 =P3 + P4 C22 =P1 + P5 − P3 − P7
 *	where
 *		P1 = A11(B12 − B22)
 *		P2 = (A11 + A12)B22
 *		P3 = (A21 + A22)B11
 *		P4 = A22(B21 − B11)
 *		P5 = (A11 + A22)(B11 + B22)
 *		P6 = (A12 − A22)(B21 + B22)
 *		P7 = (A11 − A21)(B11 + B12)
 *	Exercise Verify that C11, . . . ,C22 can be computed as above.
 *	The above formulas can be used to compute A × B recursively as follows:
 *  
 * 	Strassen(A,B)
 *	1. If n = 1 Output A × B
 *	2. Else
 *	3. Compute A11,B11, . . . ,A22,B22 % by computing m = n/2
 *	4. P1   Strassen(A11,B12 − B22)
 *	5. P2   Strassen(A11 + A12,B22)
 *	6. P3   Strassen(A21 + A22,B11)
 *	7. P4   Strassen(A22,B21 − B11)
 *	8. P5   Strassen(A11 + A22,B11 + B22)
 *	9. P6   Strassen(A12 − A22,B21 + B22)
 *	10. P7   Strassen(A11 − A21,B11 + B12)
 *	11. C11   P5 + P4 − P2 + P6
 *	12. C12   P1 + P2
 *	13. C21   P3 + P4
 *	14. C22   P1 + P5 − P3 − P7
 *	15. Output C
 *	16. End If
 */
public class MatrixMultiplicationDivideAndConquerStrassen 
{
	/**
	 * This method is main method from where all other methods , i.e taking user input and 
	 * display matrix product using strassen algorithm are invoked
	 * @param args size of matrix and than elements of two matrices 
	 */
	public static void main(String args[])
	{
		try
		{
			// Scanner is java predefined class used to scan elements entered by user
			Scanner userInput=new Scanner(System.in);	
			System.out.println("Please Enter Size Of Matrix : ");
			
			Integer matrixSize=userInput.nextInt();
			
			userInputForStrassenAlgorithm(userInput, matrixSize);
			
			userInput.close();
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong !\n Please restart the program.");
		}
	}
	
	/**
	 * This method is used to read from userInput and creates two , two dimensional array for size of userInput
	 * Also this method invokes " displaySquareMatrixForValuesEntered " method to display elements in matrix form
	 * @param userInput , scanner class object which scans user input when required.
	 * @param matrixSize ,  size of matrix is entered by user and read using scanner
	 */
	public static void userInputForStrassenAlgorithm(Scanner userInput,Integer matrixSize)
	{
		Integer[][] matrixFirstArray=new Integer[matrixSize][matrixSize];
		Integer[][] matrixSecondArray=new Integer[matrixSize][matrixSize];
		System.out.println();
		System.out.println("Please enter "+(matrixSize*matrixSize)+" elements for 1st matrix !");
		for (int i = 0; i < matrixSize; i++)
		{
			for (int j = 0; j < matrixSize; j++)
            {
            	matrixFirstArray[i][j] = userInput.nextInt();
            }
		}
		
		if(matrixFirstArray!=null && matrixFirstArray.length!=0)
		{
			System.out.println();
			System.out.println("First Matrix :");
			
			//displays matrix which created using " createArrayForBothMatrix "
			displaySquareMatrixForValuesEntered(matrixFirstArray,matrixSize);
		}
		
		System.out.println();
		System.out.println("Please enter "+(matrixSize*matrixSize)+" elements for 2nd matrix !");
		for (int i = 0; i < matrixSize; i++)
		{
			for (int j = 0; j < matrixSize; j++)
            {
            	matrixSecondArray[i][j] = userInput.nextInt();
            }
		}
		
		if(matrixSecondArray!=null && matrixSecondArray.length!=0)
		{
			System.out.println();
			System.out.println("Second Matrix :");
			//displays matrix which created using " createArrayForBothMatrix "
			displaySquareMatrixForValuesEntered(matrixSecondArray,matrixSize);
		}
		
		//if block used to display product of two matrix if both matrix are not null
		if(matrixFirstArray!=null && matrixSecondArray!=null)
		{
			Integer[][] productMatrix=strassen(matrixFirstArray, matrixSecondArray);
			System.out.println("\nProduct of two matrices are : ");
			System.out.println();
	        for (int i = 0; i < matrixSize; i++)
	        {
	            for (int j = 0; j < matrixSize; j++)
	            {
	            	System.out.print(productMatrix[i][j] +"\t");
	            }
	            System.out.println();
	        }
		}
	}
	
	/**
	 * This method is used to display elements in perfect square matrix form
	 * @param matrixArray
	 * @param matrixSize
	 */
	private static void displaySquareMatrixForValuesEntered(Integer[][] matrixArray,Integer matrixSize)
	{
		System.out.println();
		for (Integer i = 0; i < matrixSize; i++) 
		{
		    for (Integer j = 0; j < matrixSize; j++) 
		    {
		        System.out.print(matrixArray[i][j] + "\t");
		    }
		    System.out.print("\n");
		}
	}
	
	/**
	 * This method is used to implement strassen algorithm as stated above.
	 * A recurvise call is made and submatrix of smaller size are used to compute final outcome.
	 * @param matrixFirst , integer array 
	 * @param matrixSecond , integer array
	 * @return two dimensional array of integer.
	 */
	public static Integer[][] strassen(Integer matrixFirst[][],Integer matrixSecond[][])
	{
		Integer size = matrixFirst.length;
		Integer[][] resultArray=new Integer[size][size];
		Integer[][] firstNewMatrix=null,secondNewMatrix=null,resultNewMatrix=null;
		
		int newSize=2;
		if(!isPowerOf2(size) && size!=1)  //Line 1 , comment is used as label which can used in time complexity.
		{
			while(newSize<size)
			{
				newSize *=2;
			}
			
			firstNewMatrix=getNewMatrixForOddNumberSizeOfMatrix(newSize,matrixFirst); // Line 2
			secondNewMatrix=getNewMatrixForOddNumberSizeOfMatrix(newSize,matrixSecond);  
		}
		
		if(firstNewMatrix!=null && secondNewMatrix!=null)
		{
			matrixFirst=firstNewMatrix;
			matrixSecond=secondNewMatrix;
			resultNewMatrix=new Integer[newSize][newSize];
			resultArray=null;
			resultArray=resultNewMatrix;
			size=newSize;
		}
		
		if(size==1)
		{
			resultArray[0][0] = matrixFirst[0][0] * matrixSecond[0][0];
		}
		else
		{
			Integer [][] A11 = new Integer[size/2][size/2];
			Integer [][] A12 = new Integer[size/2][size/2];
			Integer [][] A21 = new Integer[size/2][size/2];
			Integer [][] A22 = new Integer[size/2][size/2];

			Integer [][] B11 = new Integer[size/2][size/2];
			Integer [][] B12 = new Integer[size/2][size/2];
			Integer [][] B21 = new Integer[size/2][size/2];
			Integer [][] B22 = new Integer[size/2][size/2];

			//divide main matrix into submatrix
			division(matrixFirst, A11, 0 , 0); //Line 3
			division(matrixFirst, A12, 0 , size/2);
			division(matrixFirst, A21, size/2, 0);
			division(matrixFirst, A22, size/2, size/2);

			division(matrixSecond, B11, 0 , 0);
			division(matrixSecond, B12, 0 , size/2);
			division(matrixSecond, B21, size/2, 0);
			division(matrixSecond, B22, size/2, size/2);
			
			//recursive call made to same function to compute P1,P2...P7 required as per strassen algorithm. Also addition and subtraction of two submatrix
			Integer [][] P1 = strassen(A11, subtraction(B12, B22));
			Integer [][] P2 = strassen(addition(A11, A12), B22);
			Integer [][] P3 = strassen(addition(A21, A22), B11);
			Integer [][] P4 = strassen(A22, subtraction(B21, B11));
			Integer [][] P5 = strassen(addition(A11, A22), addition(B11, B22));
			Integer [][] P6 = strassen(subtraction(A12, A22), addition(B21, B22));
			Integer [][] P7 = strassen(subtraction(A11,A21), addition(B11, B12));
			
			//computes c1...c4 by addition and subtraction of P(i) term as per algorithm 
			Integer [][] C11 = addition(subtraction(addition(P5, P4), P2), P6);
			Integer [][] C12 = addition(P1, P2);
			Integer [][] C21 = addition(P3, P4);
			Integer [][] C22 = subtraction(subtraction(addition(P1, P5), P3), P7);

			merge(C11, resultArray, 0 , 0);
			merge(C12, resultArray, 0 , size/2);
			merge(C21, resultArray, size/2, 0);
			merge(C22, resultArray, size/2, size/2);
		}
		return resultArray;
	}
	
	/**
     * This method checks if input number is power of 2.
     * If number of 1-bit count in binary representation of a number is
     * 1, then the number is power of 2.
     * @param n 
     * @return true if input number is power of 2.
     */
    private static boolean isPowerOf2(int n) 
    {
    	if (n <= 0) 
    	{
    		return false;
    	}
    	return Integer.bitCount(n) == 1;
    }
	
	/**
	 * This method is used to pad zero for odd matrix size
	 * @param newSize , defines new matrix size for padding zero's
	 * @param matrix , decribes old matrix
	 * @return new array of integer or new 2D matrix with padded zero's
	 */
	public static Integer[][] getNewMatrixForOddNumberSizeOfMatrix(Integer newSize,Integer[][] matrix)
	{
		Integer[][] newMatrix=new Integer[newSize][newSize];
		
		for(int i=0;i<newMatrix.length;i++)
		{
			for(int j=0;j<newMatrix.length;j++)
			{
				if(j>matrix.length-1 || i>matrix.length-1)
				{
					newMatrix[i][j]=0;
				}
				else
				{
					newMatrix[i][j]=matrix[i][j];
				}
			}
		}
		
		return newMatrix;
	}
	
	/**
	 * This method is used to creat submatrix of original or input matrix
	 * @param userInputMatrix , input matrix 
	 * @param submatrix , passed by reference and will update elements in submatrix
	 * @param startIndex , describe from which value of i and j , input matrix should be divided and store in submatrix
	 * @param endIndex , describe to which value of m and n , input matrix should be divided
	 */
	public static void division(Integer[][] userInputMatrix, Integer[][] submatrix, Integer startIndex, Integer endIndex)
	{
		for(Integer i = 0, j=startIndex; i<submatrix.length; i++, j++)
		{
			for(Integer m = 0, n=endIndex; m<submatrix.length; m++, n++)
			{
				submatrix[i][m] = userInputMatrix[j][n];
			}
		}	
	}
	
	/**
	 * This method is used to add elements of two matrix
	 * @param firstMatrix
	 * @param secondMatrix
	 * @return new 2D integer array of added values
	 */
	public static Integer [][] addition(Integer [][] firstMatrix, Integer [][] secondMatrix)
	{
		Integer size = firstMatrix.length;

		Integer [][] resultArray = new Integer[size][size];

		for(Integer i=0; i<size; i++)
		{
			for(Integer j=0; j<size; j++)
			{
				resultArray[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
			}
		}

		return resultArray;
	}

	/**
	 * This method is used to subtract elements of two matrix
	 * @param firstMatrix
	 * @param secondMatrix
	 * @return new 2D integer array of added values
	 */
	public static Integer [][] subtraction(Integer [][] firstMatrix, Integer [][] secondMatrix)
	{
		Integer size = firstMatrix.length;

		Integer [][] resultArray = new Integer[size][size];

		for(Integer i=0; i<size; i++)
		{
			for(Integer j=0; j<size; j++)
			{
				resultArray[i][j] = firstMatrix[i][j] - secondMatrix[i][j];
			}
		}

		return resultArray;
	}
	
	/**
	 * This method is used to merge c(i) terms where 0<i<5
	 * @param generatedCSubArray, 2D array passed which was generated in strassen method.
	 * @param finalResultArray , passed by reference , array is passed and values in same are updated
	 * @param startIndex , endIndex : it describes where a particular value should be inserted in finalResultArray
	 */
	public static void merge(Integer[][] generatedCSubArray, Integer[][] finalResultArray, Integer startIndex, Integer endIndex)
	{
		for(Integer i = 0, j=startIndex; i<generatedCSubArray.length; i++, j++)
		{
			for(Integer m = 0, n=endIndex; m<generatedCSubArray.length; m++, n++)
			{
				finalResultArray[j][n] = generatedCSubArray[i][m];
			}
		}
	}
}