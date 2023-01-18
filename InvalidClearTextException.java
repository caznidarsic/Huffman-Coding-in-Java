/* Christian Znidarsic
 * Lab 3
 * EN.605.202.86.SP22 Data Structures
 * 
 * The InvalidClearTextException
 * 
 * 	Exception that is thrown when the user enters an invalid clear text
 * file.
 * */

public class InvalidClearTextException extends Exception{

	public InvalidClearTextException() {
		
		super("Error: Invalid File Entered.");
		
	}
	
}
