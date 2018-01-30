package com.practice.RomanToInteger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Converts Roman number to Integer
 * https://leetcode.com/problems/roman-to-integer/description/
 * 
 * I placed before V or X indicates one less, so four is IV (one less than 5) and 9 is IX (one less than 10).
 * 
 * X placed before L or C indicates ten less, so forty is XL (10 less than 50) and 90 is XC 
 * (ten less than a hundred).
 * 
 * C placed before D or M indicates a hundred less, so four hundred is CD (a hundred less than five hundred) 
 * and nine hundred is CM (a hundred less than a thousand)
*/
public class RomanToInteger 
{
	public static void main(String args[])
	{
		Scanner sys = new Scanner(System.in);
		String romanNum = sys.nextLine();
		sys.close();
		
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X',10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		
		new RomanToInteger().evaluateInteger(map,romanNum);
	}

	private void evaluateInteger(Map<Character, Integer> map,String romanNum) 
	{
		int num = 0;
		for(int i=0;i<romanNum.length();i++)
		{
			if(i+1 < romanNum.length() && map.get(romanNum.charAt(i)) < map.get(romanNum.charAt(i+1)))
			{
				num = num + (map.get(romanNum.charAt(i+1)) - map.get(romanNum.charAt(i)));
				i++;
			}
			else
			{
				num = num+map.get(romanNum.charAt(i));
			}
		}
		System.out.println(num);
	}
}