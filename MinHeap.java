/* Christian Znidarsic
 * Lab 3
 * EN.605.202.86.SP22 Data Structures
 * 
 * The MinHeap class.
 * 
 * 	The MinHeap class defines a minHeap and its methods. This is a special min heap 
 * that contains TreeNode objects. It sorts not only based on integer value, but also
 * based on the complexity and alphabetic order of the nodes. The secret sauce is in 
 * the hasPriority() method, which determines which node has priority. This method is 
 * then used in insertNode and percolateDown to order the min heap. In other words,
 * the definition of "min" in this min heap is not only based on smallest integer value,
 * but also other characteristics of the nodes inside.
 * */

public class MinHeap {
	
	private int maxArrayLength = 100;
	private TreeNode[] heapArray = new TreeNode[maxArrayLength];
	private int heapSize = 0;
	
	//default constructor
	public MinHeap() {
		TreeNode node = new TreeNode();
		node.frequency = -1;
		heapArray[0] = node;
	}
	
	
	//method to get left child
	private int leftChild(int index) {
		return index*2;
	}
	
	//method to get right child
	private int rightChild(int index) {
		return index*2 + 1;
	}
	
	//method to get parent
	private int parent(int index) {
		return index / 2;
	}
	
	
	//insert a node at the bottom of the tree and percolate up
	public void insertNode(TreeNode node) {
		
		//make sure array bounds are not exceeded
		if (heapSize >= maxArrayLength) {
			return;
		}
		else {
			
			heapSize += 1;
			heapArray[heapSize] = node;
			int curIndex = heapSize;
			
			//percolate up. Uses hasPriority() to determine when to swap nodes.
			while(!hasPriority(heapArray[parent(curIndex)], heapArray[curIndex])) {
				
				//swap the parent with the current node
				swapNodes(parent(curIndex), curIndex);
				
				if (heapArray[parent(curIndex)].frequency != -1) {
					curIndex = parent(curIndex);
				}
			}
		}
	}
	
	//swaps the nodes at the indices parentIndex and childIndex
	public void swapNodes(int parentIndex, int childIndex) {
		TreeNode temp = heapArray[parentIndex];
		
		heapArray[parentIndex] = heapArray[childIndex];
		heapArray[childIndex] = temp;
		
	}
	
	/*
	 * The recursive percolateDown method recursively compares the root node to it's left and right 
	 * children to see if one of them has priority over it. If so, they are swapped and percolateDown
	 * is called again for the node that was swapped down.
	 */
	private void percolateDown(int index) {
		
		if (!isLeaf(index)) {
			
			//if one of the children of current node has priority over it...
			if (hasPriority(heapArray[leftChild(index)], heapArray[index]) || hasPriority(heapArray[rightChild(index)], heapArray[index])) {
				//if left child of current node has priority over right child
				if (hasPriority(heapArray[leftChild(index)], heapArray[rightChild(index)])) {
					//then swap current node with left child
					swapNodes(index, leftChild(index));
					//recursively call percolateDown for the new leftChild
					percolateDown(leftChild(index));
				}
				else {
					swapNodes(index, rightChild(index));
					percolateDown(rightChild(index));
				}
			}
		}
	}
	
	
	//determine if node at index is a leaf node
	private boolean isLeaf(int index) {
		if (rightChild(index) > heapSize) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//removes the root of the minHeap and returns it.
	public TreeNode removeRoot() {
		TreeNode root = heapArray[1];
		
		//if the heap is not empty, then replace root node with last node in tree and percolate it down.
		if (heapSize > 0) {
			heapArray[1] = heapArray[heapSize];
			percolateDown(1);
			
			heapSize -= 1;
		}
		return root;
	}
	
	
	//determines if the MinHeap has only one node. Valuable to external methods.
	public boolean hasOneNode() {
		if (heapSize == 1) {
			return true;
		}
		return false;
	}
	
	
	/*the secret sauce. Takes node frequency, complexity and alphabetical order into account
	to determine which node has priority over the other when building the min heap. */
	private boolean hasPriority(TreeNode node1, TreeNode node2) {
		
		int node1Freq = node1.frequency;
		int node2Freq = node2.frequency;

		//first test if one of the nodes has a greater frequency. Node with smaller frequency has priority.
		if (node1Freq < node2Freq) {
			return true;
		}
		else if (node2Freq < node1Freq) {
			return false;
		}
		
		/*if priorities are equal, first tiebreaker is if one node is complex and other is not. In that case,
		the node that is not complex has priority. */
		else if (node1Freq == node2Freq) {
			/*if both are simple or both are complex, last tiebreaker is alphabetical. Lower alphabetically
			has priority. */
			if ((isComplex(node1) && isComplex(node2)) || (!isComplex(node1) && !isComplex(node2))) {
				if (node1.key[0] < node2.key[0]) {
					return true;
				}
				else if (node2.key[0] < node1.key[0]) {
					return false;
				}
			}
			//if node1 is not complex, node2 must be complex, and node1 has priority.
			else if (!isComplex(node1)) {
				return true;
			}
		}
		//else, node2 has priority, meaning we return false.
		return false;
	}
	
	
	
	
	//determines if a node is complex or not
	private boolean isComplex(TreeNode node) {
		if (node.charArrayLen > 1) {
			return true;
		}
		return false;
	}
	
	
	
}
