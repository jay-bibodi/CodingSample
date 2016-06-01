package com.HomeWork.BinarySearchTree;

/**
 * @author Bibodi Jay
 * 
 * This class defines nodeValue and two nodes which will be used 
 * while generation of left and right subtree
 */
class ActiveNode 
{
	Integer nodeValue;
	ActiveNode leftNode, rightNode;

	ActiveNode(Integer nodeValue) 
	{
		this.nodeValue = nodeValue;
		leftNode = rightNode = null;
	}
}

/** 
 * This class is used to find Height and Longest path values of nodes 
 * in binary tree.
 */
public class BinarySearchTreeHeightAndLongestPath
{
	//used to set active root and to set and get value of child nodes. 
	static ActiveNode root;

	/**
	 * Main method
	 * Root and its child nodes are created here and method which calculates height of tree is invoked from here.
	 * @param args
	 */
	public static void main(String args[])
	{
		//BinarySearchTreeHeightAndLongestPath tree = new BinarySearchTreeHeightAndLongestPath();
		
		//creates left subtree from root
        BinarySearchTreeHeightAndLongestPath.root = new ActiveNode(13);
        BinarySearchTreeHeightAndLongestPath.root.leftNode = new ActiveNode(10);
        BinarySearchTreeHeightAndLongestPath.root.leftNode.leftNode = new ActiveNode(1);
        BinarySearchTreeHeightAndLongestPath.root.leftNode.rightNode = new ActiveNode(12);
        BinarySearchTreeHeightAndLongestPath.root.leftNode.rightNode.leftNode = new ActiveNode(11);
        
        //creates right subtree from root
        BinarySearchTreeHeightAndLongestPath.root.rightNode = new ActiveNode(15);
        BinarySearchTreeHeightAndLongestPath.root.rightNode.leftNode = new ActiveNode(14);
        BinarySearchTreeHeightAndLongestPath.root.rightNode.rightNode = new ActiveNode(17);
        BinarySearchTreeHeightAndLongestPath.root.rightNode.rightNode.rightNode = new ActiveNode(21);
        BinarySearchTreeHeightAndLongestPath.root.rightNode.rightNode.rightNode.leftNode = new ActiveNode(18);
       
        //method invoked and result is displayed for height of the tree
        System.out.println("Height of tree is : " + heightOfTree(root));
	}
	
	/**
	 * This method calculates height of left and right subtree recursively.
	 * Also maximum of height from left and right subtree is added to value 1 and returned.
	 * max value is added to 1 because one path from root to left or right subtree(whichever height is maximum)
	 * @param node current active node from which height will be calculated.
	 * @return height of subtree
	 */
	static Integer heightOfTree(ActiveNode node) 
	{
        if (node == null) 
        {
            return -1;
        } 
        else 
        {
            int heightOfLeftSubTree = heightOfTree(node.leftNode);
            int heightOfRightSubTree = heightOfTree(node.rightNode);
            return (heightOfLeftSubTree > heightOfRightSubTree? heightOfLeftSubTree+1 : heightOfRightSubTree+1);
        }
    }
}