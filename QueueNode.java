/* Christian Znidarsic
 * Lab 3
 * EN.605.202.86.SP22 Data Structures
 * 
 * The QueueNode class 
 * 
 * 	This class defines a node for a queue data structure.
 * It has fields "key" and "next" which are the two properties of
 * a node. The constructor QueueNode() takes a char as an input
 * and sets it as the .key property. It defines the .next property
 * as null.
 */
public class QueueNode {
	char key;
	QueueNode next;
	
	public QueueNode(char key) {
		this.key = key;
		this.next = null;
	}
	
}
