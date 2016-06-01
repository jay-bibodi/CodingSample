package com.practice.MaxSubSequence;

import java.util.Scanner;

/**
 * @author Bibodi Jay
 * 
 * This class is used to find Maximum Sub Sequence Sum for integers using dynamic programming
 */
public class DynamicProgrammingMaxSubSequence 
{
	public static void main(String args[])
	{
		Scanner sys=new Scanner(System.in);
		
		//user input for array size 
		System.out.println("Please enter size of Array for Max Subsequence");
		int arraySize=sys.nextInt();
		
		int[] A=new int[arraySize];
		
		// user input for array elements
		System.out.println("Please enter "+arraySize+" elements in array to find max sub sequence among them");
		for(int i=0;i<A.length;i++)
		{
			A[i]=sys.nextInt();
		}
		
		//call to method which calculates sum of maximum subsequence
		calculateMaxSubSequence(A);
		sys.close();
	}

	/**
	 * This method is used to calculate sum of maximum subsequence integers entered by user
	 * @param a , array of elements enetered by user
	 */
	private static void calculateMaxSubSequence(int[] a) 
	{
		if(a!=null)
		{
			// initially let sum be 1st element in array 
			int maxSubSequenceSum=a[0];
			int sum=maxSubSequenceSum;
			
			//scan entire array
			for(int i=1;i<a.length;i++)
			{
				// if sum is greater than 0 which means a positive integer element is added so sum would be definitely greater than previous sum 
				if(sum>0)
				{
					// add current element to sum
					sum=sum+a[i];
				}
				else
				{
					// if sum is less than zero , make sum as current element in array
					sum=a[i];
				}
				
				// if sum is greater than maxSubsequenceSum than make maxSubsequenceSum equals current sum
				if(sum>maxSubSequenceSum)
				{
					maxSubSequenceSum=sum;
				}
			}
			System.out.println("Maximum Sub Sequence Sum is "+ maxSubSequenceSum);
		}
	}
}