/*
 *********************************************
 *** Adhara - Algorithm for hard problems ****
 *** Authors: Name - Surname - NIP ***********
 *** Victor Peñasco EStivalez - 741294 *******
 *** Ruben Rodriguez Esteban - 737215 ********
 *** Course: 2019 - 2020 *********************
 *********************************************
 */ 


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MoveToFront {
	
	// Linked list to stored the alphabet characters
	private static LinkedList<String> alphabet = new LinkedList<>(); 


	/**
	 * Fill the list which represents the alphabet
	 */
	private static void createAlphabet() {
		// Alphabet from ASCII code
		for(int i = 0; i <= 255; i++) {
			// Store the the character i
			alphabet.add(Character.toString ((char) i));
		}	
	}
	


	/**
	 * @param text is the content read from the input 
	 * @return the content resulting of applying move to front algorithm to <<text>> input
	 */
	public static int[] moveToFront(char[] text, int n) {
		// Check if the alphabet is empty
		if(alphabet.isEmpty()) {
			// Fills the alphabet
			createAlphabet();
		}
		// Result of the move to front algorithm
		String result2 = "";
		int[] result = new int[n];
		// Iterate through the characters of the input
		for(int i = 0; i < n; i++) {
			// Get the index of the character i
			int idx = alphabet.indexOf(Character.toString(text[i]));
			// Check if the character is stored in the alphabet
			if(idx == -1) {
				// Throws error and finish exectuion
				System.err.println("Invalid character");
				System.exit(1);
			}
			// Store the numeric code of the character read
			result[i] = idx;
			// Move the character in the first position
			alphabet.addFirst(alphabet.remove(idx));
		}
		// Return result
		return result;
	}
	

	
	/**
	 * @param text is the numeric input encoded
	 * @return  the content resulting of applying move to front inverse 
	 * 			algorithm to <<text>> input
	 */
	public static char[] moveToFrontInverse(String text) {
		// Check if the alphabet is empty
		if(alphabet.isEmpty()) {
			// Fills the alphabet
			createAlphabet();
		}
		// Elimination of the spaces and store number codes in a list
		String[] numberList = text.trim().split("\\s+");
		int n = numberList.length;
		char[] result = new char[n];
		// Iteration through the number list
		for(int i = 0; i < n; i++) {
			// Apply the move to front algorithm
			result[i] = alphabet.get(Integer.parseInt(numberList[i])).charAt(0);
			alphabet.addFirst(alphabet.remove(Integer.parseInt(numberList[i])));
		}
		// Returns the result
		return result;
	}
}
