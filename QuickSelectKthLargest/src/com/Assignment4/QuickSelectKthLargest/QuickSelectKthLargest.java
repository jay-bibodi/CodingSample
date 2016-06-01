package com.Assignment4.QuickSelectKthLargest;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Bibodi Jay
 * 
 * This class finds Kth Largest Element from array using quickSelect algorithm
 * 
 * Algorithm Steps :
 * 
 * 1.Create Random integer array for finding Kth Largest element from it.
 * 2.Take input from user to find Kth largest element from array.  
 *   To find kth largest subtract kth value from array length and add 1 to it as array will be sorted in ascending order.
 * 3.Measure starting time for finding Kth largest element using quickSelect
 * 4.Call quickSelect(array,startIndex,endIndex,KthIndex)
 * 		1.if KthIndex is less than 1 or KthIndex is greater than array length return null
 * 	 	2.if startIndex equals endIndex than only element present in array and return that element
 * 		3.find center index and find median by calling method named median(array,startIndex,endIndex,centerIndex) and return pivot index
 * 		  	1.if element at start index is greater than element at center index, swap both elements
 * 		  	2.if element at start index is greater than element at end index , swap both elements
 * 		  	3.if element at center index is greater than element at end index , swap both elements.
 * 		  	4. swap element at center index and  and element at endIndex-1 and return endIndex-1.	 	
 * 		4.Call method named partition(array,startIndex,endIndex,pivotIdex) and return partition index
 * 			1.swap element at pivotIndex and endIndex element, replace pivotIndex=endIndex
 * 			2.Initialize i=startIndex and j=endIndex-1
 * 			3.check if i is less than j
 * 			4. if element at i is less than element at pivotIndex , increment value i.
 * 			5.Continue step 4.4.4 till element at i is greater than element at pivot index
 * 			6. if element at j is greater than element at pivotIndex , decrement value j.
 * 			7.Continue step 4.4.6 till element at j is less than element at pivot index 
 * 			8. if value of i is less than value of j , swap elements at i and j and increment value of i and decrement value of j
 * 			9. Continue step 4.4.3 till value of i is less than j.
 * 			10. swap elements at pivotIndex and i so pivot comes at its original position and return i
 * 		5.calculate size left by subtracting startIndex from partition index and adding 1 to it.
 * 		6.if sizeLeft==kthIndex , return array[partitionIndex]
 * 		7.if sizeLeft > kthIndex , return a recursive call to quickSelect method as quickSelect(array,startIndex,partitionIndex-1,k)
 * 		8.else return a recursive call to quickSelect method as quickSelect(array,pivotIndex+1,endIndex,kthIndex-sizeLeft)
 * 5.repeat step 4 for all array elements
 * 6.Measure ending time for finding Kth largest element using quickSelect
 * 7.Display element at kthIndex which is KthLargest element in array.			    
 */
public class QuickSelectKthLargest 
{
	// Declare size of array
	private static Integer randomOrderArraySize=2000000;
		
	/**
	* Main driver class where program execution starts and call different function to execute task
	* @param args
	*/
	public static void main(String[] args) 
	{
		System.out.println("Finding Kth Largest using quickSelect for Random array generated of "+randomOrderArraySize+" Size");
		
		// Defining and generating random order array and measuring heapSize
		Integer[] heapArray=new Integer[randomOrderArraySize];
		Random random=new Random();
		for(Integer i=0;i<heapArray.length;i++)
		{
			heapArray[i]=random.nextInt(randomOrderArraySize);
		}
	        
		Scanner sys=new Scanner(System.in);
		System.out.print("Enter kth value for finding largest term in array :");
		Integer userInput=sys.nextInt();
			
		sys.close();
			
		Integer kthTermIndex = heapArray.length-userInput+1;
		Integer kthLargest = -1; 
	        
	    // Records starting time for building and sorting heap
	    long startTime=System.nanoTime();
	    
	    // scans all the array elements and sort it for a particular kth index
	    kthLargest=quickselect(heapArray,0,heapArray.length-1,kthTermIndex);
	     	
	    // Records ending time for building and sorting heap
	    long endTime=System.nanoTime();
	        		
	    if (kthLargest != null) 
	    {
	    	// displays kth largest element and displays total running time of the program.
	    	System.out.println(userInput+"th largest element in random array generated is " + kthLargest);
	        System.out.println("Time required to perform kth largest value from array is :"+(endTime-startTime));
	    } 
	    else 
	    {
	        System.out.println("Value entered for k is either less than 1 or greater than array size");
	    }
	}

	/**
	 * Recursively determines element at kth index from the array.
	 * @param heapArray, random order array given as input , array gets sorted as recursive call is made
	 * @param startIndex, indicates starting point of array index for a current call to the method
	 * @param endIndex, indicates ending point of array index for a current call to the method
	 * @param k, value for which largest element is found
	 * @return largest value for kth index
	 */
	 public static Integer quickselect(Integer[] heapArray, Integer startIndex, Integer endIndex, Integer k) 
	 {
	     // returns null if requested largest element is less than 1 or greater than array size
	     if (k < 1 || k > heapArray.length) 
	     {
	         return null;
	     }

	     // returns the only element present in array
	     if (startIndex == endIndex) 
	     {
	         return heapArray[startIndex];
	     }

	     Integer centerIndex = (startIndex + endIndex) / 2;
	        
	     // find median of startIndex, endIndex and centerIndex element
	     int pivotIndex = median(heapArray, startIndex, endIndex, centerIndex);
	        
	     // Partition of array into two halves
	     pivotIndex = partition(heapArray, startIndex, endIndex,pivotIndex);
	     int sizeLeft = pivotIndex - startIndex + 1;

	     // Perform comparisons and recurse in binary search / quicksort fashion
	     if (sizeLeft == k) 
	     {
	         return heapArray[pivotIndex];
	     } 
	     else if (sizeLeft > k) 
	     {
	    	// sort left side of pivot
	         return quickselect(heapArray, startIndex, pivotIndex - 1, k);
	     } 
	     else 
	     {
	    	// sort right side of pivot
	         return quickselect(heapArray, pivotIndex + 1, endIndex, k - sizeLeft);
	     }
	 }

	 /**
	  * Computes the median of the first value, middle value, and last value
	  * of a list.  Also rearranges the first, middle, and last values of the
	  * list to be in sorted order.
	  * @param heapArray, array of all elements
	  * @param startIndex, start index of current subarray or main array
	  * @param endIndex,end index of current subarray or main array
	  * @param centerIndex, center index of the current subarray or main array
	  * @return index of median value
	  */
	  public static Integer median(Integer[] heapArray, Integer startIndex, Integer endIndex,Integer centerIndex) 
	  {
	     if (heapArray[startIndex] > heapArray[centerIndex]) 
	     {
	          swap(heapArray, startIndex, centerIndex);
	     }

	     if (heapArray[startIndex] > heapArray[endIndex]) 
	     {
	          swap(heapArray, startIndex, endIndex);
	     }

	     if (heapArray[centerIndex] > heapArray[endIndex]) 
	     {
	          swap(heapArray, centerIndex, endIndex);
	     }

	     swap(heapArray, centerIndex, endIndex - 1);
	     return endIndex - 1;
	  }
	    
	 /**
	  * Swaps two elements in a array based on start and end index.
	  * @param heapArray, array.
	  * @param startIndex, index of element which needs to be swapped with 2nd element
	  * @param endIndex, index of element which needs to be swapped with 1st element
	  */
	  public static void swap(Integer[] heapArray, Integer startIndex, Integer endIndex) 
	  {
	      Integer temp = heapArray[startIndex];
	      heapArray[startIndex] = heapArray[endIndex];
	      heapArray[endIndex] = temp;
	  }

	/**
	 * This method is used to do partition of array , where all values lower than pivot reside on left side and all values
	 * greater than pivot reside on right side.
	 * @param heapArray, array for which pivot is set and lower values are sorted on left side and greater on right side
	 * @param startIndex, start index of current subarray or main array
	 * @param endIndex,end index of current subarray or main array
	 * @param pivotIndex, used to get pivot element and compare each element with pivot element to decide position of each element
	 * @return value of new pivot index
	 */
	private static int partition(Integer[] heapArray, Integer startIndex, Integer endIndex,Integer pivotIndex) 
	{
		//get pivot element
	    int pivotValue = heapArray[pivotIndex];
	          
	    // move pivot to end
	    swap(heapArray, pivotIndex, endIndex); 
	    pivotIndex=endIndex;
	    // put i= starting of array and j= end-1 because end == pivot
	    int i=startIndex,j=endIndex-1;
	    // move i and j until i is less than j
	    while(i<j)
	    {
	    	//if element at i is less than pivot element than element at i is at proper sub array
	    	while(heapArray[i] < pivotValue)
	    	{
	    		i++;
	    	}
	    	//if element at j is greater than pivot element than element at j is at proper sub array
	    	while(heapArray[j] > pivotValue)
	    	{
	    		j--;
	    	}
	    	// when i is less than j and element at i greater than pivot element and element at j is less than pivot element , just swap values at i and j and continue to check
	    	if(i<j)
	    	{
	    		swap(heapArray,i,j);
	    		i++;
	    		j--;
	    	}
	    }
		
	    // swap pivot back to its original position
	    swap(heapArray, pivotIndex,i);
	    return i;
	}  	
}