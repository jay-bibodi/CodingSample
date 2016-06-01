package com.HomeWork.SwapSubTree;


/**
 * @author Bibodi Jay
 * 
 * This class defines nodeValue and two nodes which will be used 
 * while generation of left and right subtree
 */
class ActiveNode 
{
	int nodeValue;
	ActiveNode leftNode, rightNode;

	ActiveNode(int nodeValue) 
	{
		this.nodeValue = nodeValue;
		leftNode = rightNode = null;
	}
}

/**
 * This class is used to swap subtree of a given node in binary tree
 */
public class SwapSubTree 
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
			//SwapSubTree tree = new SwapSubTree();
			
			//creates left subtree from root
			SwapSubTree.root = new ActiveNode(13);
			SwapSubTree.root.leftNode = new ActiveNode(10);
			SwapSubTree.root.leftNode.leftNode = new ActiveNode(1);
			SwapSubTree.root.leftNode.rightNode = new ActiveNode(12);
			SwapSubTree.root.leftNode.rightNode.leftNode = new ActiveNode(11);
	        
	        //creates right subtree from root
			SwapSubTree.root.rightNode = new ActiveNode(15);
			SwapSubTree.root.rightNode.leftNode = new ActiveNode(12);
	        SwapSubTree.root.rightNode.rightNode = new ActiveNode(21);
	        SwapSubTree.root.rightNode.rightNode.rightNode = new ActiveNode(25);
	        SwapSubTree.root.rightNode.rightNode.rightNode.leftNode = new ActiveNode(18);
	        
	        // pre order traversal before swapping subtrees
	        System.out.println("Preorder traversal of input tree is :");
	        preOrder(root);
	        System.out.println("");
	        
	        // call to method for swapping subtrees
	        swap(root);
	        
	        // pre order traversal after swapping subtrees
	        System.out.println("Preorder traversal of swaped tree is :");
	        preOrder(root);
		}
	
		/**
		 * This method recursively list all nodeValue in preOrder traversal form
		 * @param node, node from which preOrder traversal is to be listed 
		 */
		static void preOrder(ActiveNode node)
		{
			if(node!=null)
			{
				System.out.print(node.nodeValue+" ");
				preOrder(node.leftNode);
				preOrder(node.rightNode);
			}
		}
		
		/**
		 * This method is used to swap 2 subtree for a given node recursively
		 * @param node 
		 */
		static void swap(ActiveNode node)
		{
			if(node!=null)
			{
				ActiveNode temp;
				
				swap(node.leftNode);
				swap(node.rightNode);
				
				temp=node.leftNode;
				node.leftNode=node.rightNode;
				node.rightNode=temp;
			}
		}
}
