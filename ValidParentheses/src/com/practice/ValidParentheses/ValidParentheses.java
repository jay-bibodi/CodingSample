package com.practice.ValidParentheses;

import java.util.Scanner;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 *
 */
public class ValidParentheses 
{
	public static void main(String args[])
	{
		Scanner sys = new Scanner(System.in);
		System.out.println("Enter string:");
		String obj = sys.nextLine();
		sys.close();
		char charObj[] = obj.toCharArray();
		
		System.out.println(new ValidParentheses().isValidParentheses(charObj));
	}

	private boolean isValidParentheses(char[] charObj) 
	{
		Stack<Character> stackObj = new Stack<>();
		for(int i=0;i<charObj.length;i++)
		{
			switch(charObj[i])
			{
				case '(':
					stackObj.push(')');
					break;
				case '{':
					stackObj.push('}');
					break;
				case '[':
					stackObj.push(']');
					break;
				default :
					if(!stackObj.peek().equals(charObj[i]))
						return false;
					else
						stackObj.pop();
			}
		}
		return true;
	}
}