/* Christian Znidarsic
 * Lab 3
 * EN.605.202.86.SP22 Data Structures
 * 
 * The InvalidChoiceException class
 * 
 * 	Exception that is thrown when the user enters an invalid choice
 * between E and D.
 * */

public class InvalidChoiceException extends Exception{

	public InvalidChoiceException() {
		
		super("Error: Negative value entered.");
		
	}
	
}
