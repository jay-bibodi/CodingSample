package com.Assignment2.RedBlackTreeInsertRemoveOperation;

import java.util.Scanner;

/**
 * @author Bibodi Jay
 * 
 * This class define attributes of tree necessary to
 * perform red black tree operations.
 */
class RBNode
{
	int nodeValue; 
	RBNode leftNode,rightNode,parentNode; 
	boolean color; // color of a link from parent to this node
	int N; // nodes in subtree
	
	RBNode(int nodeValue,boolean color,int N)
	{
		this.nodeValue=nodeValue;
		this.color=color;
		this.N=N;
		leftNode=rightNode=parentNode=null;
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
 * 3. return new Node(x,RED);
 * 4. else
 * 5. if x less than t.element
 * 6. insert x in left subtree
 * 7. if x greater than t.element
 * 8. insert x in right subtree
 * 9. if t.rightNode==RED && t.leftNode==BLACK
 *    rotateLeft(t)
 * 10.if t.leftNode==RED && t.leftNode.leftNode==RED
 *    rotateRight(t)
 * 11.if t.leftNode==RED and t.rightNode==RED
 *    flipColor(t)   
 * 12.return t;
 * 
 * Remove :  (if node which we want to remove is red node than this algorithm will work)
 * 
 * 1. remove(Node t,int x)
 * 2. if t!=null
 * 3. if x less than t.element
 * 4. remove x in left subtree
 * 6. if x greater than t.element
 * 	  remove x in right subtree
 * 7. else // if x==t.element
 * 8. if t.leftSubtree!=null && t.rightSubtree!=null
 *    t.element=findMinValue(t.right).element
 *    if t.color==RED
 *    remove t.element from t.right where x=t.element
 *    else
 * 9. if t.color==RED
 * 	  if t.leftSubTree!=null
 *    return t.leftSubtree
 *    else
 * 10.return t.rightSubtree
 */
public class RedBlackTreeInsertRemoveOperation 
{
	// to determine if node is of red or black color , if color value of node in tree is TRUE it means it is RED node else BLACK node
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	// size declared for 3 array
	private static int ascendingOrderArraySize=1000000, descendingOrderArraySize=1000000, randomOrderArraySize=1000000;
	static RBNode rootAscendingOrder,rootDescendingOrder,rootRandomOrder;
	static RBNode parent;
	/**
	 * This method is main method from where all other methods , i.e array creation and 
	 * insert and remove operation for RB tree are invoked which are using algorithm stated above
	 * @param args
	 */
	public static void main(String args[])
	{
		RBNode root=null;

		// For 1st Array in Ascending order
		int[] ascendingOrderArray=createAscendingOrderArray();
		rootAscendingOrder=insertElementsOfAscendingOrderToTreeAndCalculateTime(root,ascendingOrderArray);
		
		// For 2st Array in descending order
		int[] descendingOrderArray=createDescendingOrderArray();
		rootDescendingOrder=insertElementsOfDescendingOrderToTreeAndCalculateTime(root,descendingOrderArray);
		
		// For 3rd Array in random order
		int[] randomOrderArray=createRandomOrderArray();
		rootRandomOrder=insertElementsOfRandomOrderToTreeAndCalculateTime(root,randomOrderArray);
		
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
	private static RBNode insertElementsOfAscendingOrderToTreeAndCalculateTime(RBNode root,int[] ascendingOrderArray)
	{	
		long startTime = System.nanoTime();
		for(int i=0;i<ascendingOrderArray.length;i++)
		{
			root=insertNode(root,ascendingOrderArray[i]);
			root.color=BLACK;
		}
		
		long endTime = System.nanoTime();
		System.out.println("Time taken for ascending order array elements to INSERT in RB  tree  : "+(endTime-startTime));
		
		getParentProperty(root);
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
	private static RBNode insertElementsOfDescendingOrderToTreeAndCalculateTime(RBNode root,int[] descendingOrderArray)
	{
		long startTime=System.nanoTime();
		for(int i=0;i<descendingOrderArraySize;i++)
		{
			root=insertNode(root,descendingOrderArray[i]);
			root.color=BLACK;
		}
		
		long endTime=System.nanoTime();

		System.out.println("Time taken for descending order array elements to INSERT in RB  tree : "+(endTime-startTime));
		getParentProperty(root);
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
	private static RBNode insertElementsOfRandomOrderToTreeAndCalculateTime(RBNode root,int[] randomOrderArray)
	{
		long startTime = System.nanoTime();
		for(int i=0;i<randomOrderArraySize;i++)
		{
			root=insertNode(root,randomOrderArray[i]);
			root.color=BLACK;
		}
		
		long endTime=System.nanoTime();

		System.out.println("Time taken for random order array elements to INSERT in RB  tree     : "+(endTime-startTime));
		getParentProperty(root);
		return root;
	}
	
	/**
     * This method is used to insert array elements into tree based on insertion algorithm of RB Tree
     * @param node, node that roots the tree.
     * @param x, single element of the array which we want to insert in tree.
     * @return node.
     */
	private static RBNode insertNode(RBNode node,int x)
	{
		//each time when node points to null means new node needs to be created
		if(node==null)
		{
			node=new RBNode(x,RED,0);
		}
		else
		{
			// when x is less than node.element than node is present in left subtree
			if(x < node.nodeValue)
			{
				node.leftNode=insertNode(node.leftNode,x);
			}
			// when x is greater than node.element than node is present in left subtree
			else if(x > node.nodeValue)
			{
				node.rightNode=insertNode(node.rightNode,x);
			}
			
			//check black balance condition and rotate appropriately
			if(isRed(node.rightNode) && !isRed(node.leftNode))
			{
				//to perform left rotation against node
				node=rotateLeft(node);
			}
			
			if(isRed(node.leftNode) && isRed(node.leftNode.leftNode))
			{
				//to perform right rotation against node
				node=rotateRight(node);
			}
				
			if(isRed(node.leftNode) && isRed(node.rightNode))
			{
				//to flip color of nodes
				node=flipColor(node);
			}
		}
		
		return node;
	}

	/**
	 * This method is used to parent of each node and set parent node property for each particular node
	 * @param x , root from which parent property will get set.
	 */
	private static void getParentProperty(RBNode x)
	{
		if(x!=null)
		{
			RBNode leftChild=updateparentPropertyForLeftChild(x);
			RBNode rightChild=updateParentPropertyForRightChild(x);
			
			if(leftChild!=null && rightChild!=null)
			{
				getParentProperty(leftChild);
				getParentProperty(rightChild);
			}
		}
	}
	
	/**
	 * This method is used to update parent node property for left child  
	 * @param x , root from which left child parent property will set.
	 * @return leftChild with updated parent property
	 */
	private static RBNode updateparentPropertyForLeftChild(RBNode x)
	{
		if(x.leftNode!=null)
		{
			x.leftNode.parentNode=x;
				return x.leftNode;
		}
		return null;
	}
	
	/**
	 * This method is used to update parent node property for right child  
	 * @param x , root from which right child parent property will set.
	 * @return rightChild with updated parent property
	 */
	private static RBNode updateParentPropertyForRightChild(RBNode x)
	{
		if(x.rightNode!=null)
		{
			x.rightNode.parentNode=x;
				return x.rightNode;
		}
		return null;
	}
	
	/**
     * This method is used to create array and decide if we want to remove all array elements or user input elements
     * @param ascendingOrderArray , array in which elements are sorted in ascending order
     * @param rootAscendingOrder , root obtained from insertion method
     * @param sys , Scanner object initiated in main method
     */
	private static void createRemoveElementsFromAscendingOrderTree(int[] ascendingOrderArray, RBNode rootAscendingOrder,Scanner sys)
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
	private static void createRemoveElementsFromDescendingOrderTree(int[] descendingOrderArray, RBNode rootDescendingOrder,Scanner sys)
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
	private static void createRemoveElementsFromRandomOrderTree(int[] randomOrderArray, RBNode rootRandomOrder,Scanner sys)
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
	private static void removeElementFromAscendingOrderTree(RBNode root,int[] ascendingOrderArray)
	{
		if(root!=null && ascendingOrderArray.length>0)
		{
			long startTime=System.nanoTime();
			for(int i=0;i<ascendingOrderArray.length;i++)
			{
				root=removeNode(root, ascendingOrderArray[i]);
			}
			long endTime=System.nanoTime();

			System.out.println("Time taken for ascending order array elements to REMOVE in Red Black tree  : "+(endTime-startTime));
		}
	}
	
	/**
	 * This method is used to measure time take to perform removal for descending order array element
	 * @param root , root of tree , if tree is empty this will be null else contains root node of tree
	 * @param descendingOrderArray , array of all elements which we need to remove from tree
	 */
	private static void removeElementFromDescendingOrderTree(RBNode root,int[] descendingOrderArray)
	{
		if(root!=null && descendingOrderArray.length>0)
		{
			long startTime=System.nanoTime();
			for(int i=0;i<descendingOrderArraySize;i++)
			{
				root=removeNode(root, descendingOrderArray[i]);
			}
			long endTime=System.nanoTime();
			
			System.out.println("Time taken for descending order array elements to REMOVE in Red Black tree : "+(endTime-startTime));
		}
	}
	
	/**
	 * This method is used to measure time take to perform removal for random order array element
	 * @param root , root of tree , if tree is empty this will be null else contains root node of tree
	 * @param randomOrderArray , array of all elements which we need to remove from tree
	 */
	private static void removeElementFromRandomOrderTree(RBNode root,int[] randomOrderArray)
	{
		if(root!=null && randomOrderArray.length>0)
		{
			long startTime=System.nanoTime();
			for(int i=0;i<randomOrderArraySize;i++)
			{
				root=removeNode(root, randomOrderArray[i]);
			}
			long endTime=System.nanoTime();
			
			System.out.println("Time taken for random order array elements to REMOVE in Red Black tree     : "+(endTime-startTime));
		}
	}
	
	/**
     * This method is used to remove array elements into tree based on removal algorithm of RB Tree
     * @param node, node that is root of that tree.
     * @param x, single element of the array which we want to remove from tree.
     * @return null if node==null else return node
     * 
     * Condition which is Covered : node which we want to remove is of red Color
     */
	private static RBNode removeNode(RBNode node,int x)
	{
		if(node!=null)
		{
			if(x<node.nodeValue)
			{
				node.leftNode=removeNode(node.leftNode, x);
			}
			else if(x>node.nodeValue)
			{
				node.rightNode=removeNode(node.rightNode, x);
			}
			else //x==node.nodeValue
			{
				if(node.leftNode!=null && node.rightNode!=null)
				{
					node.nodeValue=findMinimumValueOfNode(node.rightNode).nodeValue;
					
					if(node.color==true)
					{
						node.rightNode=removeNode(node.rightNode, node.nodeValue);
					}
				}
				else
				{
					if(node.color==true)
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
			}
			return node;
		}
		return null;
	}
	
	/*private static void removeMinimumValueChild(RBNode node)
	{
		if(node!=null)
		{
			if(node.color==false)
			{
				RBNode child=null;
				if(node.leftNode!=null)
				{
					child=node.leftNode;
				}
				else
				{
					child=node.rightNode;
				}
				
				if(child.color) 
				{
	                child.color = false;
	            } 
				else 
				{
	                //otherwise we have double black situation.
	                deleteCase1(child);
	            }
			}
		}
	}
	
	*//**
     * If double black node becomes root then we are done. Turning it into
     * single black node just reduces one black in every path.
     *//*
    private static void deleteCase1(RBNode doubleBlackNode) {
        if(doubleBlackNode.parentNode == null) {
            doubleBlackNode.color=BLACK;
            return;
        }
        deleteCase2(doubleBlackNode);
    }

    *//**
     * If sibling is red and parent and sibling's children are black then rotate it
     * so that sibling becomes black. Double black node is still double black so we need
     * further processing.
     *//*
    private static void deleteCase2(RBNode doubleBlackNode) 
    {
        RBNode siblingNode = findSiblingNode(doubleBlackNode,doubleBlackNode.parentNode.nodeValue);
        
        if(siblingNode.color) 
        {
            if(siblingNode.parentNode.leftNode==siblingNode) 
            {
               rotateRight(siblingNode);
            } 
            else 
            {
                rotateLeft(siblingNode);
            }
            
        }
        deleteCase3(doubleBlackNode);
    }
    
    *//**
     * If sibling, sibling's children and parent are all black then turn sibling into red.
     * This reduces black node for both the paths from parent. Now parent is new double black
     * node which needs further processing by going back to case1.
     *//*
    private static void deleteCase3(RBNode doubleBlackNode) {

        RBNode siblingNode = findSiblingNode(doubleBlackNode,doubleBlackNode.parentNode.nodeValue);

        if(!doubleBlackNode.parentNode.color && !siblingNode.color && !siblingNode.leftNode.color && !siblingNode.rightNode.color) 
        {
            siblingNode.color = RED;
            deleteCase1(doubleBlackNode.parentNode);
        } 
        else 
        {
            deleteCase4(doubleBlackNode);
        }
    }

    *//**
     * If sibling color is black, parent color is red and sibling's children color is black then swap color b/w sibling
     * and parent. This increases one black node on double black node path but does not affect black node count on
     * sibling path. We are done if we hit this situation.
     *//*
    private static void deleteCase4(RBNode doubleBlackNode) 
    {
        RBNode siblingNode = findSiblingNode(doubleBlackNode,doubleBlackNode.parentNode.nodeValue);
        
        if(doubleBlackNode.parentNode.color && !siblingNode.color && !siblingNode.leftNode.color && !siblingNode.rightNode.color) 
        {
            siblingNode.color = RED;
            doubleBlackNode.parentNode.color = BLACK;
            return;
        } 
        else 
        {
            deleteCase5(doubleBlackNode);
        }
    }

    *//**
     * If sibling is black, double black node is left child of its parent, siblings right child is black
     * and sibling's left child is red then do a right rotation at siblings left child and swap colors.
     * This converts it to delete case6. It will also have a mirror case.
     *//*
    private static void deleteCase5(RBNode doubleBlackNode) 
    {
        RBNode siblingNode = findSiblingNode(doubleBlackNode,doubleBlackNode.parentNode.nodeValue);
        
        if(!siblingNode.color) 
        {
            if (doubleBlackNode.parentNode.leftNode==doubleBlackNode && !siblingNode.rightNode.color && siblingNode.leftNode.color) 
            {
                rotateRight(siblingNode.leftNode);
            } 
            else if (doubleBlackNode.parentNode.leftNode!=doubleBlackNode && !siblingNode.leftNode.color && siblingNode.rightNode.color) 
            {
                rotateLeft(siblingNode.rightNode);
            }
        }
        deleteCase6(doubleBlackNode);
    }

    *//**
     * If sibling is black, double black node is left child of its parent, sibling left child is black and sibling's right child is
     * red, sibling takes its parent color, parent color becomes black, sibling's right child becomes black and then do
     * left rotation at sibling without any further change in color. This removes double black and we are done. This
     * also has a mirror condition.
     *//*
    private static void deleteCase6(RBNode doubleBlackNode) 
    {
        RBNode siblingNode = findSiblingNode(doubleBlackNode,doubleBlackNode.parentNode.nodeValue);
        
        siblingNode.color = siblingNode.parentNode.color;
        siblingNode.parentNode.color = BLACK;
        
        if(doubleBlackNode.parentNode.leftNode!=doubleBlackNode) 
        {
            siblingNode.rightNode.color = BLACK;
            //rotateLeft(siblingNode, false);
            rotateLeft(siblingNode);
        } 
        else 
        {
            siblingNode.leftNode.color = BLACK;
            //rotateRight(siblingNode, false);
            rotateRight(siblingNode);
        }
    }
	
    private static RBNode findSiblingNode(RBNode node,int x)
    {
    	if(node==null)
    	{
    		return null;
    	}
    	else if(node.parentNode.nodeValue==x)
    	{
    		return node;
    	}
    	else if(x<node.nodeValue)
		{
			return findSiblingNode(node.leftNode, x);
		}
		
		return findSiblingNode(node.rightNode, x);
    }*/
	
	/**
     * This method is used to find minimum value from tree
     * @param node , node from which minimum value of tree is to be found
     * @return node with minimum value
     */	
	private static RBNode findMinimumValueOfNode(RBNode node)
	{
		if(node==null)
		{
			return null;
		}
		else if(node.leftNode==null)
		{
			node.color=RED;
			return node;
		}
		return findMinimumValueOfNode(node.leftNode);
	}
	
	/**
	 * Determines if node is of red or black color
	 * @param x , node
	 * @return true if node is of red color else return false
	 */
	private static boolean isRed(RBNode x)
	{
		if(x==null)
		{
			return false;
		}
		return x.color==RED;
	}
	
	/**
	 * This method is used to rotate left
	 * @param h , node over which tree is rotated to left side
	 * @return node
	 */
	private static RBNode rotateLeft(RBNode h)
	{
		RBNode x=h.rightNode;
		h.rightNode=x.leftNode;
		x.leftNode=h;
		x.color=h.color;
		h.color=RED;
		x.N=h.N;
		h.N=heightOfTree(h);
		
		return x;
	}
	
	/**
	 * This method is used to rotate right
	 * @param h , node over which tree is rotated to right side
	 * @return node
	 */
	private static RBNode rotateRight(RBNode h)
	{
		RBNode x=h.leftNode;
		h.leftNode=x.rightNode;
		x.rightNode=h;
		x.color=h.color;
		h.color=RED;
		x.N=h.N;
		h.N=heightOfTree(h);
		
		return x;
	}
	
	/**
	 * This method is used to flip color of parent node and its children
	 * @param h , parent node
	 * @return node
	 */
	private static RBNode flipColor(RBNode h)
	{
		h.color=RED;
		h.leftNode.color=BLACK;
		h.rightNode.color=BLACK;
		
		return h;
	}
	
	/**
	 * This method calculates height of left and right subtree recursively.
	 * Also maximum of height from left and right subtree is added to value 1 and returned.
	 * max value is added to 1 because one path from root to left or right subtree(whichever height is maximum)
	 * @param node current active node from which height will be calculated.
	 * @return height of subtree
	 */
	private static int heightOfTree(RBNode node) 
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
}