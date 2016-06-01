package com.Practice.TreeInsert;

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
 * @author Bibodi Jay
 * This class is used insert node in binary search tree
 */
public class TreeInsert 
{
	//used to set active root and to set and get value of child nodes.
	static Node root;
	
	public static void main(String args[])
	{
		//creates left subtree from root
		TreeInsert.root = new Node(13);
		TreeInsert.root.leftNode = new Node(10);
		TreeInsert.root.leftNode.leftNode = new Node(1);
		TreeInsert.root.leftNode.rightNode = new Node(12);
		TreeInsert.root.leftNode.rightNode.leftNode = new Node(11);
        
        //creates right subtree from root
		TreeInsert.root.rightNode = new Node(15);
		TreeInsert.root.rightNode.leftNode = new Node(12);
		TreeInsert.root.rightNode.rightNode = new Node(21);
		TreeInsert.root.rightNode.rightNode.rightNode = new Node(25);
		TreeInsert.root.rightNode.rightNode.rightNode.leftNode = new Node(18);
	
		// pre order traversal before value inserted
		System.out.println("PreOrder Traversal Before Insertion : ");
		PreOrder(root);
		System.out.println();
		
		// value of x is inserted in tree
		int x=2;		
		System.out.println("Node inserted is :"+x);
		
		//call to method to insert node in binary search tree 
		insert(root,x);
		
		// preorder traversal after value inserted
		System.out.println("PreOrder Traversal After Insertion : ");
		PreOrder(root);
	}	
	
	/**
	 * This method is used to insert node in binary search tree at appropriate position based on BST definition
	 * @param node, root node of tree
	 * @param x , value of node that is to inserted in tree
	 * @return newly inserted node
	 */
	static Node insert(Node node,int x)
	{
		if(node==null) 								// if root node is null, create a new node directly and return node
		{
			node=new Node(x);
		}
		else if(x<node.nodeValue)					// if x is less than current node value, recursively traverse to left subtree and insert node
		{
			node.leftNode=insert(node.leftNode, x); 
		}
		else if(x>node.nodeValue)					// if x is greater than current node value, recursively traverse to left subtree and insert node
		{
			node.rightNode=insert(node.rightNode, x);
		}
		return node;								// return newly inserted node
	}
	
	/**
	 * This method recursively list all nodeValue in preOrder traversal form
	 * @param node, node from which preOrder traversal is to be listed 
	 */
	static void PreOrder(Node node)
	{
		if(node!=null)
		{
			System.out.print(node.nodeValue+" ");
			PreOrder(node.leftNode);
			PreOrder(node.rightNode);
		}
	}
}