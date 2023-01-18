/* Christian Znidarsic
 * Lab 3
 * EN.605.202.86.SP22 Data Structures
 * 
 * The TreeNode class.
 * 
 * 	This class defines a TreeNode and its attributes. Each node has a key (
 * an array of chars), a frequency, a left node pointer, a right node pointer,
 *  and a charArrayLength, which stores the length of the key array.
 * */

public class TreeNode {
	/*the key of each node must be stored in an array of chars to enable index access.
	This becomes extremely useful when sorting the array alphabetically so that 
	key[0] returns the character that is earliest in the alphabet. This is used
	in the alphabetical node tie-breaker when building the heap, and when combining
	nodes into supernodes.*/
	char[] key = new char[40];
	int frequency;
	TreeNode left = null;
	TreeNode right = null;
	public int charArrayLen = 0;
	
	//default constructor
	public TreeNode() {
		
	}
	
	//other constructor
	public TreeNode(char k, int f) {
		this.key[0] = k;
		this.frequency = f;
		
		charArrayLen += 1;
	}
	
	
	/* 
	 * This method takes two nodes and combines their key arrays, sorting them alphabetically
	 * and assigning them to the key array of the current object.
	 */
	public void combineKeys(TreeNode node1, TreeNode node2) {
		
		//merge key1[] and key2[]
		for (int i = 0; i < node1.charArrayLen; i++) {
			key[i] = node1.key[i];
		}
		for (int i = 0; i < node2.charArrayLen; i++) {
			key[i + node1.charArrayLen] = node2.key[i];
		}
		
		//update char array length counter
		charArrayLen += node1.charArrayLen + node2.charArrayLen;
		
		//sort all elements
		char temp;
		int i = 0;
		while (i <= charArrayLen) {
			int j = i + 1;
			while (j <= charArrayLen) {
				if (key[j] < key[i] && Character.isLetter(key[j])) {
					
					temp = key[i];
					key[i] = key[j];
					key[j] = temp;
					
				}
				j += 1;
			}
			i += 1;
		}
		
	}
	
}