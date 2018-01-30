package com.practice.LongestCommonPrefix;

import java.util.Scanner;

/**
 * Find longest common prefix using divide and conquer
 * TigerOne, TigerTwo, TigThree --> should return tig
 *
 */
public class LongestCommonPrefix 
{
	public static void main(String args[])
	{
		String [] obj = new String[3];
		Scanner sys = new Scanner(System.in);
		
		for(int i=0;i<3;i++)
		{
			obj[i] = sys.next();
		}
		
		sys.close();
		
		System.out.println(new LongestCommonPrefix().findLongestCommonPrefix(obj, 0, obj.length-1));
	}

	private String findLongestCommonPrefix(String[] obj, int start, int end) 
	{
	    if (start == end) 
	    {
	        return obj[start];
	    }
	    else 
	    {
	        int mid = (start + end)/2;
	        String left = findLongestCommonPrefix(obj, start , mid);
	        String right = findLongestCommonPrefix(obj, mid + 1,end);
	        return commonPrefix(left, right);
	   }
	}

	String commonPrefix(String left,String right) 
	{
	    int min = Math.min(left.length(), right.length());       
	    for (int i = 0; i < min; i++) 
	    {
	        if (left.charAt(i) != right.charAt(i))
	        {
	        	return right.substring(0, i);
	        }
	    }
	    return right.substring(0, min);
	}
}