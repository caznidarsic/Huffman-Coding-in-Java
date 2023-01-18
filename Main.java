/* Christian Znidarsic
 * Lab 3
 * EN.605.202.86.SP22 Data Structures
 * 
 * The Main Class.
 * 
 * 	Main is the driver class of this project. It contains a primary loop
 * that iterates until the desired input has been entered by the user. Within
 * that loop, the user is asked for a Frequency Table file, and whether they 
 * would like to use it to encode or decode. The user selects, and enters
 * either a clear text file or encoded file respectively. */


import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException {

		HuffmanTree hufTree = new HuffmanTree();
		boolean hufCreated = false;
		boolean endProgram = false;
		boolean choiceMade = false;
		
		String choice = "";

		
		/*a while loop to keep iterating until the user has entered all valid information
		 * required by the program.*/
		while (!endProgram) {
			try {
				
				//only run this block of code before a Huffman tree has been created. After that, skip it.
				if(!hufCreated) {
					//get name of input file from user
					Scanner keyboard = new Scanner(System.in);
					
					
//					Create Huffman Tree #################################################################
	
					/*because we are getting input from the console, the user
					must specify the input file path in addition to the name*/
					System.out.println("Please enter a file containing a valid frequency table: ");
					
					String frequencyTable = keyboard.nextLine();
					
					//create a File object using the input file name from user
					File inputFile = new File(frequencyTable);
					
					FileReader fr = new FileReader(inputFile);
					
					//create bufferedReader object
					BufferedReader br = new BufferedReader(fr);
					
					/* use the Validation class to decide whether the file input by the user represents a
					valid frequency table.*/
					if (!Validation.validateFreqTable(br)) {
						
						//if it is not a valid frequency table, throw an exception.
						throw new InvalidFrequencyTableException();
						
					}
					
					
					
					//pass the queue generated in Validation into the getHuffTree method
					hufTree.getHuffTree(Validation.validatedFreqTable);
						
					//initialize a new hashmap to store letter-code pairs.
					HashMap<Character, String> map = new HashMap<>();
					
					/*run getCodes in hufTree to generate the codes for each letter. The hashmap containing
					this info is stored in hufTree.codes */
					hufTree.getCodes(hufTree.root, "", map);		
					
					
					System.out.println("Huffman tree generated successfully.");
//					#####################################################################################
					
					
					hufCreated = true;
				}
	
				
				//if the user has already entered a valid choice (E or D), skip this block of code
				if (!choiceMade) {
					Scanner keyboard = new Scanner(System.in);
						
						
//					Encode or Decode ####################################################################
					System.out.println("To decode, enter D. To encode, enter E. ");
					
					//get a choice from the user
					choice = keyboard.next();
					
				}
					
				
				//if the user enters E, they intend to encode a file. 
				if (choice.equals("E")) {
					choiceMade = true;
					
					Scanner keyboard = new Scanner(System.in);
					
					System.out.println("Please enter a valid Clear Text input file name: ");
						
					String clearFile = keyboard.nextLine();
						
					File inputFile = new File(clearFile);
						
					FileReader fr = new FileReader(inputFile);
							
					BufferedReader br = new BufferedReader(fr);
					
					
					if (!Validation.validateClearText(br)) {
						
						System.out.println("WARNING: This Clear Text file contains non-letters. Non-letters will be ignored. ");
						
					}
					
					/*
					 * Here is an optional exception. It is commented out but can be uncommented and used
					 * if restrictions on input Clear Text files need to be changed.
					 */
//					else {
//						
//						throw new InvalidClearTextException();
//						
//					}
						
					System.out.println("Please enter an output file name: ");
					
					String outputFileName = keyboard.nextLine();
					
					PrintWriter outputFile = new PrintWriter (outputFileName);
					
					//call the encode method and pass in the queue generated in Validation
					hufTree.encode(Validation.validatedClearText, outputFile);
					
					
					
				}
				
				//if the user enters D, then they intend to decode a file. 
				else if (choice.equals("D")) {
					choiceMade = true;
					
					Scanner keyboard = new Scanner(System.in);
					
					System.out.println("Please enter a valid Encoded Text input file name: ");
						
					String encodedFile = keyboard.nextLine();
						
					File inputFile = new File(encodedFile);
						
					FileReader fr = new FileReader(inputFile);
							
					BufferedReader br = new BufferedReader(fr);
					
					
					//if the encoded file is valid, then go ahead and decode it.
					if (Validation.validateEncoded(br)) {
						
						System.out.println("Please enter an output file name: ");
						
						String outputFileName = keyboard.nextLine();
						
						PrintWriter outputFile = new PrintWriter (outputFileName);
						
						//call the decode method in hufTree and pass in the queue generated in Validation
						hufTree.decode(Validation.validatedEncodedText, outputFile);
						
					}
					//if the encoded file is not valid, then throw an exception.
					else {
						
						throw new InvalidEncodedException();
						
					}
				}
				//if the user entered a choice that was neither E nor D, throw an exception.
				else {
					
					throw new InvalidChoiceException();
					
				}
					

				
				
					endProgram = true;
					
			
			} catch (FileNotFoundException f) {
				
				System.out.println("Input file not found.");
				
			} catch (NumberFormatException n) {
				
				System.out.println("Number format exception.");
				
			} catch (InvalidChoiceException i) {
				
				System.out.println("Invalid Choice. ");
				
			} catch (InvalidFrequencyTableException f) {
				
				System.out.println("That file was not a valid Frequency Table. ");
			
				/*this catch statement is also commented out. It corresponds with the InvalidClearTextException 
				 * that is commented out above. Again, this can be edited if the definition of a valid clear
				 * text file needs to be updated.
				 */
//			} 
//			catch (InvalidClearTextException c) {
//				
//				System.out.println("That file was not a valid Clear Text File. ");
				
			} catch (InvalidEncodedException e) {
				
				System.out.println("That file was not a valid Encoded Text File. Encoded files may only contain the characters '0', '1', ' ', and '\\n'. ");
				
			}
				
		}
		
	}
	
}