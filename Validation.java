/* Christian Znidarsic
 * Lab 3
 * EN.605.202.86.SP22 Data Structures
 * 
 * The Validation class.
 * 
 * 	The Validation class is used to determine if files input by the user are
 * valid or not. It has three methods, one for each file type the user may enter.
 * They are validateFreqTable() for validating frequency tables, validateClearText() 
 * for validating Clear Text files, and validateEncoded, for validating files containing
 * encoded text in the form of 1s and 0s.
 * 
 * 	All three of these methods take a BufferedReader object as input, and use it
 * to read all characters in the input, and store them in a queue. If the input file
 * is valid, then the resulting Queue is passed to other methods that read it and 
 * either encode or decode the contents. Or, in the case of the Frequency Table,
 * the contents of the resulting queue are used to create a Huffman Tree.
 * */

import java.io.*;

public class Validation {
	static Queue validatedFreqTable = new Queue();
	static Queue validatedClearText = new Queue();
	static Queue validatedEncodedText = new Queue();
	
	
	/*
	 * Takes BufferedReader object of input file as input. If the contents of the
	 * file do not follow the format: "A ... 123", where A is a single letter and 
	 * 123 is any length of integer (with the ellipses representing any characters
	 * in between), then false is returned. If the input file follows the format,
	 * true is returned to indicate a valid Frequency Table file.
	 */
	public static boolean validateFreqTable(BufferedReader input) throws IOException {
		
		int c = 0;
		boolean letterRead = false;
		
		//iterate through whole .txt file
		while((c = input.read()) != -1) {
			
			char character = (char) c;
			
			//enqueue all chars in validatedFreqTable
			validatedFreqTable.enqueue(character);
			
			/*if the character is a letter, switch letterRead to on. 
			 * If a second letter is read down the line before an integer
			 * and a newline, the format is invalid. */
			if (Character.isLetter(character)) {
				if (!letterRead) {
					letterRead = true;
				}
				else {
					//empty the validatedFreqTable to allow for multiple attempts.
					while (!validatedFreqTable.isEmpty()) {
						validatedFreqTable.dequeue();
					}
					return false;
				}
				
			}
			//if the character is a digit, and a letter has not yet been read, not valid.
			else if (Character.isDigit(character)) {
				if (!letterRead) {
					while (!validatedFreqTable.isEmpty()) {
						validatedFreqTable.dequeue();
					}
					return false;
				}
			}
			else if (character == '\n') {
				//reset letterRead boolean
				letterRead = false;
			}
			
		}
		
		//return true if the entire file has been parsed without any errors
		return true;
	}
	
	
	/*
	 * Takes BufferedReader object as input. If the cleartext file contains non-letters,
	 * then false is returned. 
	 */
	public static boolean validateClearText(BufferedReader input) throws IOException {
		boolean hasFalse = false;
		int c = 0;
		
		while((c = input.read()) != -1) {
			
			char character = (char) c;
			
			validatedClearText.enqueue(character);
			
			if (!Character.isLetter(character) && !(character == '\n') && !(character == ' ')) {
				hasFalse = true;
			}
		}
		
		if (hasFalse) {
			return false;
		}
		return true;
	}
	
	
	
	/*
	 * If input contains any characters that are not 0, 1, \s or \n, then it is an 
	 * invalid encoded file. Return false.
	 */
	public static boolean validateEncoded(BufferedReader input) throws IOException {
		int c = 0;
		
		while((c = input.read()) != -1) {
			
			char character = (char) c;
			
			validatedEncodedText.enqueue(character);
			
			if (!(character == '0') && !(character == '1') && !(character == ' ') && !(character == '\n')) {
				while (!validatedEncodedText.isEmpty()) {
					validatedEncodedText.dequeue();
				}
				return false;
			}
		}

		return true;
	}
	
}
