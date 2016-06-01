package com.Practice.TreeTraversal;

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
 * This class is used display 3 traversal types for binary search tree i.e inOrder, postOrder and preOrder traversal
 */
public class TreeTraversal 
{
	//used to set active root and to set and get value of child nodes.
	static Node root;
	
	public static void main(String args[])
	{
		//creates left subtree from root
		TreeTraversal.root = new Node(13);
		TreeTraversal.root.leftNode = new Node(10);
		TreeTraversal.root.leftNode.leftNode = new Node(1);
		TreeTraversal.root.leftNode.rightNode = new Node(12);
		TreeTraversal.root.leftNode.rightNode.leftNode = new Node(11);
        
        //creates right subtree from root
		TreeTraversal.root.rightNode = new Node(15);
		TreeTraversal.root.rightNode.leftNode = new Node(12);
		TreeTraversal.root.rightNode.rightNode = new Node(21);
		TreeTraversal.root.rightNode.rightNode.rightNode = new Node(25);
		TreeTraversal.root.rightNode.rightNode.rightNode.leftNode = new Node(18);

		// call to method for Inorder traversal of binary search tree
		System.out.println("Inorder Traversal For Tree :");
		InOrder(root);
		System.out.println();
		System.out.println();
		
		// call to method for Postorder traversal of binary search tree
		System.out.println("PostOrder Traversal For Tree :");
		PostOrder(root);
		System.out.println();
		System.out.println();
		
		// call to method for Preorder traversal of binary search tree
		System.out.println("PreOrder Traversal For Tree :");
		PreOrder(root);
	}
	
	/**
	 * This method recursively list all nodeValue in InOrder traversal form
	 * @param node, node from which InOrder traversal is to be listed 
	 */
	static void InOrder(Node node)
	{
		if(node!=null)
		{
			InOrder(node.leftNode);
			System.out.print(node.nodeValue+" ");
			InOrder(node.rightNode);
		}
	}
	
	/**
	 * This method recursively list all nodeValue in postOrder traversal form
	 * @param node, node from which postOrder traversal is to be listed 
	 */
	static void PostOrder(Node node)
	{
		if(node!=null)
		{
			PostOrder(node.leftNode);
			PostOrder(node.rightNode);
			System.out.print(node.nodeValue+" ");
		}
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