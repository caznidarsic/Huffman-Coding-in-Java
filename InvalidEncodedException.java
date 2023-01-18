/* Christian Znidarsic
 * Lab 3
 * EN.605.202.86.SP22 Data Structures
 * 
 * The InvalidEncodedException class.
 * 
 * 	Exception that is thrown when the user enters an invalid Encoded
 * text file.
 * */

public class InvalidEncodedException extends Exception{

	public InvalidEncodedException() {
		
		super("Error: Invalid Encoded Text File Entered.");
		
	}
	
}
