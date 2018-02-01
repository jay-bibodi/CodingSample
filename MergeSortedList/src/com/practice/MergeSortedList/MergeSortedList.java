package com.practice.MergeSortedList;

import java.util.Scanner;

class ListNode 
{
     int val;
     ListNode next;
     ListNode(int x) 
     { 
    	 val = x; 
     }
}

public class MergeSortedList 
{
	public static void main(String args[])
	{
		Scanner sys = new Scanner(System.in);
		System.out.println("Enter # of elements in list 1: ");
		int numberOfElementsInListOne = sys.nextInt();
		
		ListNode objOne = new ListNode(sys.nextInt());
		objOne.next = null;
		
		ListNode listOne = objOne;
		
		for (int i=1;i<numberOfElementsInListOne;i++)
		{
			objOne.next = new ListNode(sys.nextInt());
			objOne.next.next = null;
			objOne = objOne.next;
		}
		
		System.out.println("Enter # of elements in list 2: ");
		int numberOfElementsInListTwo = sys.nextInt();
		
		ListNode objTwo = new ListNode(sys.nextInt());
		objTwo.next = null;
		
		ListNode listTwo = objTwo;
		
		for (int i=1;i<numberOfElementsInListTwo;i++)
		{
			objTwo.next = new ListNode(sys.nextInt());
			objTwo.next.next = null;
			objTwo = objTwo.next;
		}
		
		sys.close();
		new MergeSortedList().mergeSortedList(listOne,listTwo);
	}

	private ListNode mergeSortedList(ListNode listOne, ListNode listTwo) 
	{
		ListNode newObj = null;
		
		if(listOne.val > listTwo.val)
		{
			newObj = new ListNode(listTwo.val);
			listTwo = listTwo.next;
		}
		else
		{
			newObj = new ListNode(listOne.val);
			listOne = listOne.next;
		}
		
		newObj.next = null;
		
		ListNode newList = newObj;
		
		while(listOne!=null && listTwo!=null)
		{
			if(listOne.val > listTwo.val)
			{
				newObj.next = new ListNode(listTwo.val);
				listTwo = listTwo.next;
			}
			else
			{
				newObj.next = new ListNode(listOne.val);
				listOne = listOne.next;
			}
			
			newObj.next.next = null;
			newObj = newObj.next;
		}
		
		if(listOne!=null)
		{
			while(listOne!=null)
			{
				newObj.next = new ListNode(listOne.val);
				newObj.next.next = null;
				newObj = newObj.next;
				listOne = listOne.next;
			}
		}
		
		if(listTwo!=null)
		{
			while(listTwo!=null)
			{
				newObj.next = new ListNode(listTwo.val);
				newObj.next.next = null;
				newObj = newObj.next;
				listTwo = listTwo.next;
			}
		}
		
		return newList;
	}
}