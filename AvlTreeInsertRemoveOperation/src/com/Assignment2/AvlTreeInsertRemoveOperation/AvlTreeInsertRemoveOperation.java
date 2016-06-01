package com.Assignment2.AvlTreeInsertRemoveOperation;

import java.util.Scanner;

/**
 * This class define attributes of tree necessary to
 * perform avl tree operations.
 */
class Node
{
	Node rightNode,leftNode;
	int nodeValue,height;
	
	Node(int nodeValue,int height)
	{
		this.height=height;
		this.nodeValue=nodeValue;
		leftNode=rightNode=null;
	}
}

/**
 *This class is used to generate 3 different arrays, calculate time
 *for insertion and removal of node in tree for each element in array 
 *and call methods for insert , remove node from tree using algorithm stated below :
 *
 *Insert :
 *
 * 1. insert(Node t, int x)
 * 2. if t==null
 * 3. return new Node(x);
 * 4. else
 * 5. if x less than t.element
 * 6. insert x in left subtree
 * 	  if height.leftSubtree - height.rightSubtree >=2
 *    if height.leftSubtree.leftSubtree >= height.leftSubtree.rightSubtree
 *    singleRotateToRight(t)
 *    else
 *    doubleRotateToRight(t) 	
 * 7. if x greater than t.element
 * 8. insert x in right subtree
 * 9. if height.rightSubtree - height.leftSubtree >=2
 *    if height.rightSubtree.rightSubtree >= height.rightSubtree.leftSubtree
 *    singleRotateToLeft(t)
 *    else
 *    doubleRotateToLeft(t)
 * 10.return t;
 * 
 * Remove :
 * 
 * 1. remove(Node t,int x)
 * 2. if t!=null
 * 3. if x less than t.element
 * 4. remove x in left subtree
 * 5. if height.rightSubtree - height.leftSubtree >=2
 *    if height.rightSubtree.rightSubtree >= height.rightSubtree.leftSubtree
 *    singleRotateToLeft(t)
 *    else
 *    doubleRotateToLeft(t)
 * 6. if x greater than t.element
 *    if height.leftSubtree - height.rightSubtree >=2
 * 	  if height.leftSubtree.leftSubtree >= height.leftSubtree.rightSubtree
 *    singleRotateToRight(t)
 *    else
 *    doubleRotateToRight(t)
 * 7. else // if x==t.element
 * 8. if t.leftSubtree!=null && t.rightSubtree!=null
 *    t.element=findMinValue(t.right).element
 *    remove t.element from t.right where x=t.element
 *    else
 * 9. if t.leftSubTree!=null
 *    return t.leftSubtree
 *    else
 * 10.return t.rightSubtree
 */
public class AvlTreeInsertRemoveOperation 
{
	// size declared for 3 array
	static int ascendingOrderArraySize=20000, descendingOrderArraySize=20000, randomOrderArraySize=20000;
	
	/**
	 * This method is main method from where all other methods , i.e array creation and 
	 * insert and remove operation for avl tree are invoked which are using algorithm stated above
	 * @param args
	 */
	public static void main(String args[])
	{
		Node root=null;

		// For 1st Array in Ascending order
		int[] ascendingOrderArray=createAscendingOrderArray();
		Node rootAscendingOrder=insertElementsOfAscendingOrderToTreeAndCalculateTime(root,ascendingOrderArray);
		
		// For 2st Array in descending order
		int[] descendingOrderArray=createDescendingOrderArray();
		Node rootDescendingOrder=insertElementsOfDescendingOrderToTreeAndCalculateTime(root,descendingOrderArray);
		
		// For 3rd Array in random order
		int[] randomOrderArray=createRandomOrderArray();
		Node rootRandomOrder=insertElementsOfRandomOrderToTreeAndCalculateTime(root,randomOrderArray);
		
		//For remove operation
		Scanner sys=new Scanner(System.in);
		System.out.println("Do you want to delete any elements from either of the tree ?(0=Yes ,1=No)");
		int isRemoveOperationRequired=sys.nextInt();
		
		if(isRemoveOperationRequired==0)
		{
			createRemoveElementsFromAscendingOrderTree(ascendingOrderArray,rootAscendingOrder,sys);
			createRemoveElementsFromDescendingOrderTree(descendingOrderArray, rootDescendingOrder, sys);
			createRemoveElementsFromRandomOrderTree(randomOrderArray, rootRandomOrder, sys);
		}
		sys.close();
		System.out.println("Program Ends!");
	}
	
	/**
	 * Create array in ascending order from 0 to length of array
	 * @return ascending order array.
	 */
	private static int[] createAscendingOrderArray()
	{
		int[] ascendingOrderArray=new int[ascendingOrderArraySize];
		
		for(int i=0;i<ascendingOrderArraySize;i++)
		{
			ascendingOrderArray[i]=i;
		}
		
		return ascendingOrderArray;
	}
	
	/**
	 * This method is used to measure time take to perform insert for each ascending order array element
	 * @param root , root of tree , if tree is empty this will be null
	 * @param ascendingOrderArray , array of all elements which we need to insert into tree
	 * @return root
	 */
	private static Node insertElementsOfAscendingOrderToTreeAndCalculateTime(Node root,int[] ascendingOrderArray)
	{	
		long startTime = System.nanoTime();
		for(int i=0;i<ascendingOrderArraySize;i++)
		{
			root=insertNode(root,ascendingOrderArray[i]);
		}
		long endTime   = System.nanoTime();
		
		System.out.println("Time taken for ascending order array elements to INSERT in avl tree  : "+(endTime-startTime));
		return root;
	}
	
	/**
	 * This method creates array of descending order from size of descending order array to 0
	 * @return array with random order elements
	 */
	private static int[] createDescendingOrderArray()
	{
		int[] descendingOrderArray=new int[descendingOrderArraySize];
		
		int j=0;
		for(int i=descendingOrderArraySize-1;i>0;i--)
		{
			descendingOrderArray[j]=i;
			j++;
		}
		
		return descendingOrderArray;
	}
	
	/**
	 * This method is used to measure time take to perform insert for each descending order array element
	 * @param root , root of tree , if tree is empty this will be null
	 * @param descendingOrderArray , array of all elements which we need to insert into tree
	 * @return root
	 */
	private static Node insertElementsOfDescendingOrderToTreeAndCalculateTime(Node root,int[] descendingOrderArray)
	{
		long startTime=System.nanoTime();
		for(int i=0;i<descendingOrderArraySize;i++)
		{
			root=insertNode(root,descendingOrderArray[i]);
		}
		long endTime=System.nanoTime();

		System.out.println("Time taken for descending order array elements to INSERT in avl tree : "+(endTime-startTime));
		return root;
	}
	
	/**
	 * This method creates array of random order from 0 to size of random order array
	 * @return array with random order elements
	 */
	private static int[] createRandomOrderArray()
	{
		int[] randomOrderArray=new int[randomOrderArraySize];
		
		for(int i=0;i<randomOrderArraySize;i++)
		{
			randomOrderArray[i]=((int) (Math.random()*(randomOrderArraySize - randomOrderArray[i]))) + randomOrderArray[i];
		}
		
		return randomOrderArray;
	}
	
	/**
	 * This method is used to measure time take to perform insert for each random order array element
	 * @param root , root of tree , if tree is empty this will be null
	 * @param randomOrderArray , array of all elements which we need to insert into tree
	 * @return root
	 */
	private static Node insertElementsOfRandomOrderToTreeAndCalculateTime(Node root,int[] randomOrderArray)
	{
		long startTime = System.nanoTime();
		for(int i=0;i<randomOrderArraySize;i++)
		{
			root=insertNode(root,randomOrderArray[i]);
		}
		long endTime=System.nanoTime();

		System.out.println("Time taken for random order array elements to INSERT in avl tree     : "+(endTime-startTime));
		return root;
	}
	
	/**
     * This method is used to insert array elements into tree based on insertion algorithm of AVL Tree
     * @param node, node that roots the tree.
     * @param x, single element of the array which we want to insert in tree.
     * @return node.
     */
    private static Node insertNode(Node node,int x)
    {
    	//each time when node points to null means new node needs to be created
        if(node == null)
        {
        	node = new Node(x,0);
        }
        // when x is less than node.element than node is present in left subtree
        else if(x < node.nodeValue)
        {
        	node.leftNode = insertNode(node.leftNode,x);
        	
            //checks if balance condition is met for avl tree
        	if(heightOfTree(node.leftNode) - heightOfTree(node.rightNode)==2)
            {
            	if(heightOfTree(node.leftNode.leftNode) >= heightOfTree(node.leftNode.rightNode))
            	{
            		//performs right rotation against node
            		node = SingleRotateToRight(node);
            	}
                else
                {
                	//performs left rotation and right rotation against node
                	node = doubleRotateToRight(node);
                }
            }
        }
        // when x is greater than node.element than node is present in left subtree
        else if(x > node.nodeValue)
        {
        	node.rightNode = insertNode(node.rightNode, x);
        	
        	//checks if balance condition is met for avl tree
            if(heightOfTree(node.rightNode) - heightOfTree(node.leftNode )==2)
            {
            	if(heightOfTree(node.rightNode.rightNode) >= heightOfTree(node.rightNode.leftNode))
            	{
            		//performs left rotation against node
            		node = SingleRotateToLeft(node);
            	}
                else
                {
                	//performs right rotation and left rotation against node
                	node = doubleRotateToLeft(node);
                }
            }
        }
        return node;
    }
	
    /**
     * This method is used to create array and decide if we want to remove all array elements or user input elements
     * @param ascendingOrderArray , array in which elements are sorted in ascending order
     * @param rootAscendingOrder , root obtained from insertion method
     * @param sys , Scanner object initiated in main method
     */
	private static void createRemoveElementsFromAscendingOrderTree(int[] ascendingOrderArray, Node rootAscendingOrder,Scanner sys)
	{
		System.out.println("Remove all nodes of tree created from ASCENDING order array elements ?(0=Yes, 1=No)");
		int yesOrNoValue=sys.nextInt();
		
		//based on user input elements are removed from tree
		if(yesOrNoValue==0)
		{
			removeElementFromAscendingOrderTree(rootAscendingOrder,ascendingOrderArray);
		}
		else if(yesOrNoValue==1)
		{
			System.out.println("How many elements you want to remove ?");
			int removeElements=sys.nextInt();
			if(removeElements>0)
			{
				int[] elementToBeRemoved=new int[removeElements];
				
				System.out.println("Please enter node value between 1 and "+ascendingOrderArraySize+" to delete node from tree created using ascending order array elements!");
				
				//creates new array with specific elements which we want to remove from tree
				for(int i=0;i<removeElements;i++)
				{
					elementToBeRemoved[i]=sys.nextInt();
				}
				
				removeElementFromAscendingOrderTree(rootAscendingOrder,elementToBeRemoved);
			}
			else
			{
				System.out.println("You entered wrong value!");
			}
			
		}
		else
		{
			System.out.println("You entered wrong value!");
		}
	}
	
	/**
     * This method is used to create array and decide if we want to remove all array elements or user input elements
     * @param descendingOrderArray , array in which elements are sorted in descending order
     * @param rootDescendingOrder , root obtained from insertion method
     * @param sys , Scanner object initiated in main method
     */
	private static void createRemoveElementsFromDescendingOrderTree(int[] descendingOrderArray, Node rootDescendingOrder,Scanner sys)
	{
		System.out.println("Remove all nodes from Tree created from DESCENDING order array elements ?(0=Yes, 1=No)");
		int yesOrNoValue=sys.nextInt();
		
		//based on user input elements are removed from tree
		if(yesOrNoValue==0)
		{
			removeElementFromDescendingOrderTree(rootDescendingOrder, descendingOrderArray);
		}
		else if(yesOrNoValue==1)
		{
			System.out.println("How many elements you want to remove ?");
			int removeElements=sys.nextInt();
			if(removeElements>0)
			{
				int[] elementToBeRemoved=new int[removeElements];
				
				System.out.println("Please enter node value between 1 and "+descendingOrderArraySize+" to delete node from tree created using descending order array elements!");
				
				//creates new array with specific elements which we want to remove from tree
				for(int i=0;i<removeElements;i++)
				{
					elementToBeRemoved[i]=sys.nextInt();
				}
				
				removeElementFromAscendingOrderTree(rootDescendingOrder,elementToBeRemoved);
			}
			else
			{
				System.out.println("You entered wrong value!");
			}
			
		}
		else
		{
			System.out.println("You entered wrong value!");
		}
	}
	
	/**
     * This method is used to create array and decide if we want to remove all array elements or user input elements
     * @param randomOrderArray , array in which elements are sorted in random order
     * @param rootRandomOrder , root obtained from insertion method
     * @param sys , Scanner object initiated in main method
     */
	private static void createRemoveElementsFromRandomOrderTree(int[] randomOrderArray, Node rootRandomOrder,Scanner sys)
	{
		System.out.println("Remove all nodes from Tree created from Random order array elements ?(0=Yes, 1=No)");
		int yesOrNoValue=sys.nextInt();
		
		//based on user input elements are removed from tree
		if(yesOrNoValue==0)
		{
			removeElementFromRandomOrderTree(rootRandomOrder, randomOrderArray);
		}
		else if(yesOrNoValue==1)
		{
			System.out.println("How many elements you want to remove ?");
			int removeElements=sys.nextInt();
			if(removeElements>0)
			{
				int[] elementToBeRemoved=new int[removeElements];
				
				System.out.println("Please enter node value between 1 and "+randomOrderArraySize+" to delete node from tree created using descending order array elements!");
				
				//creates new array with specific elements which we want to remove from tree
				for(int i=0;i<removeElements;i++)
				{
					elementToBeRemoved[i]=sys.nextInt();
				}
				
				removeElementFromRandomOrderTree(rootRandomOrder,elementToBeRemoved);
			}
			else
			{
				System.out.println("You entered wrong value!");
			}
			
		}
		else
		{
			System.out.println("You entered wrong value!");
		}
	}
	
	/**
	 * This method is used to measure time take to perform removal for ascending order array element
	 * @param root , root of tree , if tree is empty this will be null else contains root node of tree
	 * @param ascendingOrderArray , array of all elements which we need to remove from tree
	 */
	private static void removeElementFromAscendingOrderTree(Node root,int[] ascendingOrderArray)
	{
		if(root!=null && ascendingOrderArray.length>0)
		{
			long startTime=System.nanoTime();
			for(int i=0;i<ascendingOrderArray.length;i++)
			{
				root=removeNode(root, ascendingOrderArray[i]);
			}
			long endTime=System.nanoTime();

			System.out.println("Time taken for ascending order array elements to REMOVE in avl tree  : "+(endTime-startTime));
		}
	}
	
	/**
	 * This method is used to measure time take to perform removal for descending order array element
	 * @param root , root of tree , if tree is empty this will be null else contains root node of tree
	 * @param descendingOrderArray , array of all elements which we need to remove from tree
	 */
	private static void removeElementFromDescendingOrderTree(Node root,int[] descendingOrderArray)
	{
		if(root!=null && descendingOrderArray.length>0)
		{
			long startTime=System.nanoTime();
			for(int i=0;i<descendingOrderArray.length;i++)
			{
				root=removeNode(root, descendingOrderArray[i]);
			}
			long endTime=System.nanoTime();
			
			System.out.println("Time taken for descending order array elements to REMOVE in avl tree : "+(endTime-startTime));
		}
	}
	
	/**
	 * This method is used to measure time take to perform removal for random order array element
	 * @param root , root of tree , if tree is empty this will be null else contains root node of tree
	 * @param randomOrderArray , array of all elements which we need to remove from tree
	 */
	private static void removeElementFromRandomOrderTree(Node root,int[] randomOrderArray)
	{
		if(root!=null && randomOrderArray.length>0)
		{
			long startTime=System.nanoTime();
			for(int i=0;i<randomOrderArray.length;i++)
			{
				root=removeNode(root, randomOrderArray[i]);
			}
			long endTime=System.nanoTime();
			
			System.out.println("Time taken for random order array elements to REMOVE in avl tree     : "+(endTime-startTime));
		}
	}
    
	/**
     * This method is used to remove array elements into tree based on removal algorithm of AVL Tree
     * @param node, node that is root of that tree.
     * @param x, single element of the array which we want to remove from tree.
     * @return null if node==null else return node
     */
    private static Node removeNode(Node node,int x)
	{
		if(node!=null)
		{
			// when x is less than node.element than node is present in left subtree
			if(x<node.nodeValue)
			{
				node.leftNode=removeNode(node.leftNode, x);
				
				//checks if balance condition is met for avl tree
				if(heightOfTree(node.rightNode) - heightOfTree(node.leftNode)==2)
				{
					if(heightOfTree(node.rightNode.rightNode) >= heightOfTree(node.rightNode.leftNode))
	            	{
						//performs left rotation against node
	            		node = SingleRotateToLeft(node);
	            	}
	                else
	                {
	                	//performs right rotation and left rotation against node
	                	node = doubleRotateToLeft(node);
	                }
				}
			}
			//when x is greater than node.element than node is present in right subtree
			else if(x>node.nodeValue)
			{
				node.rightNode=removeNode(node.rightNode, x);
				if(heightOfTree(node.leftNode) - heightOfTree(node.rightNode)==2)
				{
					if(heightOfTree(node.leftNode.leftNode) >= heightOfTree(node.leftNode.rightNode))
	            	{
						//performs right rotation against node
	            		node = SingleRotateToRight(node);
	            	}
	                else
	                {
	                	//performs left rotation and right rotation against node
	                	node = doubleRotateToRight(node);
	                }
				}
			}
			else  // x==node.nodeValue
			{
				// if two of child are not null
				if(node.leftNode!=null && node.rightNode!=null)
				{
					 // find Min value from right subtree and replace with node to be removed
					 node.nodeValue=findMinimumValueOfNode(node.rightNode).nodeValue;
					 
					 //remove original node from right subtree to avoid duplicate
					 node.rightNode=removeNode(node.rightNode,node.nodeValue);
				}
				else
				{
					// if only one child is present
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
     * This method is used to find minimum value from tree
     * @param node , node from which minimum value of tree is to be found
     * @return node with minimum value
     */
	private static Node findMinimumValueOfNode(Node node)
	{
		if(node==null)
		{
			return null;
		}
		else if(node.leftNode==null)
		{
			return node;
		}
		return findMinimumValueOfNode(node.leftNode);
	}
    
    /**
	 * This method calculates height of left and right subtree recursively.
	 * Also maximum of height from left and right subtree is added to value 1 and returned.
	 * max value is added to 1 because one path from root to left or right subtree(whichever height is maximum)
	 * @param node current active node from which height will be calculated.
	 * @return height of subtree
	 */
	private static int heightOfTree(Node node) 
	{
        if (node == null) 
        {
            return 0;
        } 
        else 
        {
            int heightOfLeftSubTree = heightOfTree(node.leftNode);
            int heightOfRightSubTree = heightOfTree(node.rightNode);
            return (heightOfLeftSubTree > heightOfRightSubTree? heightOfLeftSubTree+1 : heightOfRightSubTree+1);
        }
    }
	
	/**
	 * This method is used to perform left rotation which is used to balance avl tree 
	 * @param k2 , which violates balance condition
	 * @return node after rotated to left
	 */
	private static Node SingleRotateToLeft(Node k2)
	{
		if(k2!=null)
		{
			Node k1=k2.rightNode;
			k2.rightNode=k1.leftNode;
			k1.leftNode=k2;
			
			k2.height=heightOfTree(k2);
			k1.height=heightOfTree(k1);
			
			return k1;
		}
		return null;
	}
	
	/**
	 * This method is used to perform right rotation which is used to balance avl tree 
	 * @param k2 , which violates balance condition
	 * @return node after rotated to right
	 */
	private static Node SingleRotateToRight(Node k2)
	{
		if(k2!=null)
		{
			Node k1=k2.leftNode;
			k2.leftNode=k1.rightNode;
			k1.rightNode=k2;
			
			k2.height=heightOfTree(k2);
			k1.height=heightOfTree(k1);
			
			return k1;
		}
		return null;
	}
	
	/**
	 * This method is used to perform right and left rotation which is used to balance avl tree 
	 * @param k3 , which violates balance condition
	 * @return node
	 */
	private static Node doubleRotateToLeft(Node k3)
	{
		if(k3!=null)
		{
			k3.rightNode=SingleRotateToRight(k3.rightNode);
			return SingleRotateToLeft(k3);
		}
		return null;
	}
	
	/**
	 * This method is used to perform left and right rotation which is used to balance avl tree 
	 * @param k3 , which violates balance condition
	 * @return node
	 */
	private static Node doubleRotateToRight(Node k3)
	{
		if(k3!=null)
		{
			k3.leftNode=SingleRotateToLeft(k3.leftNode);
			return SingleRotateToRight(k3);
		}
		return null;
	}
}