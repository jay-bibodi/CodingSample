package com.MaxSubSequence.DivideAndConquer;

import java.util.Scanner;

// N^2 Solution.
/*int maxSubSequence(int a[])
{
	int maxSum = 0;
	for(int i = 0;i<a.length;i++)
	{
		for(int j=0;j<a.length;j++)
		{
			int sum = 0;
			for(int k = i;k<=j;k++)
			{
				sum +=a[i];
			}
			
			if(sum>maxSum)
			{
				maxSum = sum;
			}
		}
	}
	return maxSum;
}*/




/**
 * @author Bibodi Jay
 * 
 * This class is used to find Maximum Sub Sequence Sum for integers using divide and conquer
 */
public class MaxSubSequenceDivideAndConquer 
{	
	public static void main(String args[])
	{
		Scanner sys=new Scanner(System.in);
		
		// user input for array size 
		System.out.println("Please enter size of Array for Max Subsequence");
		int arraySize=sys.nextInt();
		
		Integer[] intArray=new Integer[arraySize];
		
		// user input for array elements
		System.out.println("Please enter "+arraySize+" elements in array to find max sub sequence among them");
		for(int i=0;i<intArray.length;i++)
		{
			intArray[i]=sys.nextInt();
		}
		
		sys.close();
		
		// call to method which calculates sum of maximum subsequence 
		Integer sum=maxSubsequenceSum(intArray,0,intArray.length-1);
		System.out.println("Maximum Subsequence Sum for array is : "+sum);
	}
	
	/**
	 * This method is used to calculate sum of maximum subsequence integers entered by user recursively
	 * @param a, array of elements entered by user
	 * @param startValue , start index of current call
	 * @param endValue , end index of current call
	 * @return sum of maximum subsequence
	 */
	private static Integer maxSubsequenceSum(Integer[] a, Integer startValue, Integer endValue) 
	{
		// initialize sum of elements from center to left , center to right as 0 and also sum variable which will keep track of current sum
        Integer  leftCenterSum = 0, rightCenterSum = 0,sum = 0;

        // if start and endex index are same there is only 1 element present so return that element
        if (startValue==endValue) 
        { 
            return a[startValue]; 
        }

        //find center of array, divide part
        Integer center = (startValue + endValue)/2;
        
        // recursively sum all elements left from center
        Integer maxLeftSum = maxSubsequenceSum(a, startValue, center);
        
        // recursively sum all elements right from center
        Integer maxRightSum = maxSubsequenceSum(a, center + 1, endValue);

        // find max sum of elements from center to start value
        for (int i = center; i >= startValue; i--) 
        {
        	// add all elements to sum
            sum += a[i];
            // if sum is greater than leftCenterSum than assign sum to leftCenterSum else continue
            if (sum > leftCenterSum) 
            {
                leftCenterSum = sum;
            }
        }
        
        sum = 0;
        
        for (int i = center + 1; i <= endValue; i++) 
        {
        	// add all elements to sum
            sum += a[i];
            // if sum is greater than rightCenterSum than assign sum to rightCenterSum else continue
            if (sum > rightCenterSum) 
            {
                rightCenterSum = sum;
            }
        }
        
        // return maxValue out of 3 variables which will give us max subsequence sum 
        return maxValue(maxLeftSum, maxRightSum, (leftCenterSum + rightCenterSum));
    }

	/**
	 * 
	 * @param maxLeftSum
	 * @param maxRightSum
	 * @param maxCenterSum
	 * @return
	 */
    private static Integer maxValue(Integer maxLeftSum, Integer maxRightSum, Integer maxCenterSum) 
    {
        return maxLeftSum > maxRightSum ? (maxLeftSum > maxCenterSum ? maxLeftSum : maxCenterSum) : (maxRightSum > maxCenterSum ? maxRightSum : maxCenterSum);
    }
}