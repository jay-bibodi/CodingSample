package com.practice.MergeSort;

import java.util.Random;
import java.util.Scanner;

/**
  *	This class sorts the array of integer in ascending order using merge sort algorithm
  *
  * if 
  * 
  * start of array and end of array are equal
  * return data at start.
  *
  * else 
  *
  * Use Divide strategy
  * recursively sort left half of array from center to start of array  
  * recursively sort right half of array from center+1 to end of array
  *
  * merge left sorted and right sorted array using conquer strategy.
  */
public class MergeSort 
{
	public static void main(String args[])
	{
		Scanner sys = new Scanner(System.in);
		
		// Take user input for size of array.
		System.out.println("Enter value for array which will be randomly generated : ");
		int arrayLength = sys.nextInt();
		sys.close();
		
		int[] array=new int[arrayLength];
		
		// Create random array elements
		Random random=new Random();
		
		for(int i=0;i<array.length;i++)
		{
			array[i]=random.nextInt(arrayLength);
		}
		
		// call method invokeMergeSort to sort array elements.
		array = invokeMergeSort(array,0,array.length-1);
		
		// print sorted array.
		for(int i = 0;i < array.length; i++)
		{
			System.out.print(array[i]+" ");
		}
	}

	/**
	 * This method is used to divide array based on start and end index and invoke merge method for sorting.
	 * @param(array) integer array which needs to be sorted.
	 * @param(start) start position of the array.
	 * @param(end) end position of the array.
	 * return sorted array.
	 */
	 public static int[] invokeMergeSort(int[] array, int start, int end)
	 {
		 // find the center of array.
		 int center = (start+end)/2;
		 
		 // if only one element is present in array.
	     if (start==end)
	     {	
	    	 int[] data = new int [1];
	         data[0]=array[center];
	         return data;
	     }
	     else // if more than one element is present in array.
	     {
			 // recursively sort left half of array.
	    	 int[] leftHalfSorted = invokeMergeSort(array, start, center);
			 
			 // recursively sort right half of array.
	    	 int[] rightHalfSorted = invokeMergeSort(array, center+1, end);
			 
			 // invoke and return merge method sorted array which will merge left and right half.
	    	 return merge(leftHalfSorted, rightHalfSorted);
	     }
	 }

	 /**
	  * This method is used to merge the elements of leftHalfSorted array and rightHalfSorted array.
	  * @param(leftHalfSorted) array with elements from left half of the array which will be sorted.
	  * @param(rightHalfSorted) array with elements from right half of the array which will be sorted.
	  * return merged array i.e sorted array of both combined left and right halves.
	  */
	 public static int[] merge(int[] leftHalfSorted, int[] rightHalfSorted)
	 {
		 // initialize a new array of size equal to size of sum of leftHalfSorted array and rightHalfSorted array.
		 int[] SortedArray = new int[leftHalfSorted.length+rightHalfSorted.length];
	     
		 // track leftIndex of the array.	
		 int leftIndex = 0;
		 
		 // track rightIndex of the array.
		 int rightIndex = 0;
		 
		 // track countIndex for the total variable being sorted and stored in array initialized above in this method.
		 int countIndex = 0;
	        
		 // continue to sort the array until leftIndex is less than leftHalfSorted's array length and rightIndex is less than rightHalfSorted's array length.	
		 while (leftIndex < leftHalfSorted.length && rightIndex < rightHalfSorted.length)
		 {
			 /* if element in leftHalfSorted array at leftIndex is less than or equal to element in rightHalfSorted array at rightIndex add element at leftIndex in sorted array and increase countIndex and leftIndex, Less than or equal too is used so that duplicates can be handled. */ 
			 if (leftHalfSorted[leftIndex] <= rightHalfSorted[rightIndex])
			 {
				 SortedArray[countIndex]=leftHalfSorted[leftIndex];
				 countIndex++;
				 leftIndex++;
			 }
			 else
			 {
				 SortedArray[countIndex]=rightHalfSorted[rightIndex];
				 countIndex++;
				 rightIndex++;
			 }
		 }
	     
		 // if while loop terminates and still elements are present in leftHalfSorted array, just add elements to sorted array and increase the countIndex and leftIndex.		 
		 if (leftIndex!=leftHalfSorted.length)
		 {
			 while(leftIndex<leftHalfSorted.length)
			 {
				 SortedArray[countIndex]=leftHalfSorted[leftIndex];
				 countIndex++;
				 leftIndex++;
			 }
		 }
	     
		 // if while loop terminates and still elements are present in rightHalfSorted array, just add elements to sorted array and increase the countIndex and rightIndex.	
		 if (rightIndex!=rightHalfSorted.length)
		 {
			 while(rightIndex<rightHalfSorted.length)
			 {
				 SortedArray[countIndex]=rightHalfSorted[rightIndex];
				 countIndex++;
				 rightIndex++;
			 }
		 }
		 
		 // return sorted merged array.
		 return SortedArray;
	 }
}