package com.Practice.TreeRemoveNode;

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
 * This class is used to remove node from binary search tree
 */
public class TreeRemoveNode 
{
	//used to set active root and to set and get value of child nodes.
	static Node root;
	
	public static void main(String args[])
	{
		//creates left subtree from root
		TreeRemoveNode.root = new Node(5);
		TreeRemoveNode.root.leftNode = new Node(2);
		TreeRemoveNode.root.leftNode.leftNode = new Node(-4);
		TreeRemoveNode.root.leftNode.rightNode = new Node(3);
        
        //creates right subtree from root
		TreeRemoveNode.root.rightNode = new Node(12);
		TreeRemoveNode.root.rightNode.leftNode = new Node(9);
		TreeRemoveNode.root.rightNode.rightNode = new Node(21);
		TreeRemoveNode.root.rightNode.rightNode.leftNode = new Node(19);
		TreeRemoveNode.root.rightNode.rightNode.rightNode = new Node(25);
	
		// pre order traversal before node deleted
		System.out.println("PreOrder Traversal Before Deletion : ");
		PreOrder(root);
		System.out.println();
		
		// value of x is deleted from tree
		int x=12;		
		System.out.println("Node inserted is :"+x);
		
		//call to method to delete node from binary search tree
		removeNode(root,x);
		
		// pre order traversal after node deleted
		System.out.println("PreOrder Traversal After Deletion : ");
		PreOrder(root);
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
	
	/**
	 * This method is used to remove node from binary search tree
	 * @param node , current node which we are working on
	 * @param x , node value which we need to remove
	 * @return replaced Node
	 */
	static Node removeNode(Node node,int x)
	{
		if(node!=null)
		{
			//if value of x is less than current node value , recursively traverse to left subtree  
			if(x<node.nodeValue)
			{
				node.leftNode=removeNode(node.leftNode, x);
			}
			else if(x>node.nodeValue) //if value of x is greater than current node value , recursively traverse to right subtree
			{
				node.rightNode=removeNode(node.rightNode, x);
			}
			else  // when value x equals current node value
			{
				if(node.leftNode!=null && node.rightNode!=null) // when left and right child are not null for node to be deleted
				{
					 // find minimum value from right child subtree and replace with current node so x node will be replaced
					 node.nodeValue=findMinimumValueOfNode(node.rightNode).nodeValue; 
					 
					 // now 2 nodes with same value so remove node in right child subtree
					 node.rightNode=removeNode(node.rightNode,node.nodeValue);
				}
				else // when either one of child is null
				{
					if(node.leftNode!=null) 
					{
						node=node.leftNode;
					}
					else
					{
						node=node.rightNode;
					}
				}
			}
			return node;
		}
		return null;
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
}
