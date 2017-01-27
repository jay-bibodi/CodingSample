package com.csc135.TowerOfHanoi;

public class TowerOfHanoi 
{
	public static void main(String args[])
	{
		int totalDisk=5;
		towerOfHanoi(totalDisk,'A','B','C');
	}
	
	public static void towerOfHanoi(int totalDisk, char source, char extra,char destination)
	{
		if(totalDisk==1)
		{
			System.out.println("Disk 1 from "+source+" to destination "+destination);
		}
		else
		{
			towerOfHanoi(totalDisk-1,source,destination,extra);
			System.out.println("Disk "+totalDisk+" from "+source+" to destination "+destination);
			towerOfHanoi(totalDisk-1,extra,source,destination);
		}
	}	
}	