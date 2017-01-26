package com.practice.LongestCommonSubString;

import java.util.Scanner;

/**
 * @author Bibodi Jay
 * 
 * This class finds length of longest common substring from the strings entered by user. 
 * Alphabets entered will be case sensitive
 */
public class LongestCommonSubString 
{
	public static void main(String args[])
	{
		Scanner sys=new Scanner(System.in);
		
		//user input for 1st string
		System.out.println("Please enter First String");
		String firstString=sys.next();
		
		//user input for second string
		System.out.println("Please enter Second String");
		String secondString=sys.next();
		
		//call to method that calculates length of longest common substring
		calculateLongestCommonSubstring(firstString,secondString);
		
		//call to method that calculates length of longest common subsequence
		getLongestCommonSubsequence(firstString,secondString);
		
		sys.close();
	}

	/**
	 * This method is used to find length of longest common substring
	 * @param firstString , user input 1st string
	 * @param secondString, user input 2nd string
	 */
	private static void calculateLongestCommonSubstring(String firstString,String secondString) 
	{
		//initial length of common substring
		int lengthOfCommonSubString=0;

		// convert 1st string to char array
		char[] firstArray=firstString.toCharArray();
		
		//convert 2nd string to char array
		char[] secondArray=secondString.toCharArray();
		
		//take 2 dimensional array to record 1 if alphabets from both char array matches
		int[][] subStringCharArray=new int[firstArray.length+1][secondArray.length+1];
		
		//for loop will compare alphabets in both char array until all the elements are compared
		for(int i=0;i<firstArray.length;i++)
		{
			for(int j=0;j<secondArray.length;j++)
			{
				// if both alphabets are equal
				if(firstArray[i] == secondArray[j])
				{
					// if i or j is 0 than do not look for value 1 in previous position in 2 dimensional array
					if(i==0 || j==0)
					{
						subStringCharArray[i][j] = 1;
					}
					else 
					{
						subStringCharArray[i][j] = 1 + subStringCharArray[i-1][j-1];
					}
					
					//update length of common substring
					lengthOfCommonSubString = lengthOfCommonSubString > subStringCharArray[i][j] ? lengthOfCommonSubString : subStringCharArray[i][j];
				}
				else // when alphabets are not equal
				{
					subStringCharArray[i][j] = 0;
				}
			}
		}
		System.out.println("Longest Common Substring length is : "+lengthOfCommonSubString);
	}
	
	private static void getLongestCommonSubsequence(String a, String b)
	{
		// length of string a	
		int m = a.length();
		
		// length of string b
		int n = b.length();
		
		//take 2 dimensional array to record 1 if alphabets from both char array matches
		int[][] dp = new int[m+1][n+1];
	 
		for(int i=0; i<=m; i++)
		{
			for(int j=0; j<=n; j++)
			{
				// if i or j is 0 than put 0 at that position in two dimensional array
				if(i==0 || j==0)
				{
					dp[i][j]=0;
				}
				else if(a.charAt(i-1)==b.charAt(j-1))  // if previous character in string a and string b are equal than 1 to two dimensional matrix and add valueat i-1 and j-1
				{
					dp[i][j] = 1 + dp[i-1][j-1];
				}
				else  // when previous character are not equal in string a and b and i and j are not 0.
				{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);   
				}
			}
		}
		System.out.println("Length of Longest Common Sub-Sequence is : "+dp[m][n]);
	}
}