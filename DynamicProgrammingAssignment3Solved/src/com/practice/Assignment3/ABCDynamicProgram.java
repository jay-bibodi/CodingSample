package com.practice.Assignment3;

/**
 * @author Bibodi Jay
 * 
 * This class is used to find total number of words that can be formed with alphabets
 * A,B and C such that A and C are never together i.e AC is not possible and CA is not possible
 */
public class ABCDynamicProgram 
{
	public static void main(String[] args) 
	{
		// 8 is length of string formed using 3 alphabets a,b,c
		numberOfWords(8);
	}
	
	/**
	 * This methods counts and prints total words formed using restriction stated above in class declaration
	 * @param n, length of the string
	 */
	static void numberOfWords(int n) 
	{

		int an[] = new int[n];
		int bn[] = new int[n];
		int cn[] = new int[n];

		// initial condition, if length of string is 1 than only 3 words can be formed using 3 alphabets
		an[0] = 1;
		bn[0] = 1;
		cn[0] = 1;

		if (n == 1) 
		{
			System.out.println("The number of words is: " + 3);
		}
		else // when length of string is greater than 1
		{

			//adding number of possible words for each alphabets that start with a , b and c respectively considering AC or CA are never together
			for (int i = 1; i < n; i++) 
			{
				an[i] = an[i - 1] + bn[i - 1];
				bn[i] = an[i - 1] + bn[i - 1] + cn[i - 1];
				cn[i] = bn[i - 1] + cn[i - 1];
			}
			
			//total words formed
			int result = an[n - 1] + bn[n - 1] + cn[n - 1];

			System.out.println("The number of words that can be formed is: "+ result);
		}
	}
}