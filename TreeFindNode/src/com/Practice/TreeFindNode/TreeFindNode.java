package com.Practice.TreeFindNode;

/**
 * @author Bibodi Jay
 * 
 * This class defines nodeValue and two nodes which will be used 
 * while generation of left and right subtree
 */
class Node
{
	Node rightNode,leftNode;
	int nodeValue;
	
	Node(int nodeValue)
	{
		this.nodeValue=nodeValue;
		leftNode=rightNode=null;
	}
}

/**
 * This class is used find node value , maximum node value or minimum node value in given binary tree.
 */
public class TreeFindNode 
{
	//used to set active root and to set and get value of child nodes.
	static Node root;
	
	public static void main(String args[])
	{
		//creates left subtree from root
		TreeFindNode.root = new Node(13);
		TreeFindNode.root.leftNode = new Node(10);
		TreeFindNode.root.leftNode.leftNode = new Node(1);
		TreeFindNode.root.leftNode.rightNode = new Node(12);
		TreeFindNode.root.leftNode.rightNode.leftNode = new Node(11);
        
        //creates right subtree from root
		TreeFindNode.root.rightNode = new Node(15);
		TreeFindNode.root.rightNode.leftNode = new Node(12);
		TreeFindNode.root.rightNode.rightNode = new Node(21);
		TreeFindNode.root.rightNode.rightNode.rightNode = new Node(25);
		TreeFindNode.root.rightNode.rightNode.rightNode.leftNode = new Node(18);
		
		// finds node value from binary tree
		Node searchedNode=findNode(root,12);
		
		if(searchedNode==null)
		{
			System.out.println("Element not present");
		}
		else
		{
			System.out.println("Node Value is "+searchedNode.nodeValue);
		}
		
		// finds minimum value of a particular node in given binary tree
		System.out.println("Minimum Value in tree is "+findMinimumValueOfNode(root).nodeValue);
		
		// finds maximum value of a particular node in given binary tree
		System.out.println("Maximum Value in tree is "+findMaximumValueOfNode(root).nodeValue);		
	}
	
	/**
	 * This method finds the node of value x if present else returns null
	 * @param node , node from which value is to be searched
	 * @param x , value to be searched from binary tree
	 * @return node with value x
	 */
	static Node findNode(Node node,int x)
	{
		if(node==null)
		{
			return null;
		}
		else if(node.nodeValue==x) // if value of node equals value x
		{
			return node;
		}
		else if(x<node.nodeValue)  // if value of x is less than current node than search x in left subtree recursively
		{
			return findNode(node.leftNode, x);
		}
		
		return findNode(node.rightNode, x); // if value of x is greater than current node than search x in right subtree recursively
	}
	
	/**
	 * This method is used to find node with minimum node value in binary search tree
	 * @param node, current node until node with minimum node value is found
	 * @return node
	 */
	static Node findMinimumValueOfNode(Node node)
	{
		if(node==null)
		{
			return null;
		}
		else if(node.leftNode==null) // In BST , minimum node value lies in left subtree, if left subtree is null than return node itself
		{
			return node;
		}
		return findMinimumValueOfNode(node.leftNode); // recursively find minimum node value in left subtree
	}
	
	/**
	 * This method is used to find node with maximum node value in binary search tree
	 * @param node, current node until node with maximum node value is found
	 * @return node
	 */
	static Node findMaximumValueOfNode(Node node)
	{
		if(node==null)
		{
			return null;
		}
		else if(node.rightNode==null) // In BST , maximum  node value lies in right subtree, if right subtree is null than return node itself
		{
			return node;
		}
		return findMaximumValueOfNode(node.rightNode); // recursively find maximum node value in right subtree
	}
}