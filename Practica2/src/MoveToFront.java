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
	public static String moveToFront(String text) {
		// Check if the alphabet is empty
		if(alphabet.isEmpty()) {
			// Fills the alphabet
			createAlphabet();
		}
		// Result of the move to front algorithm
		String result = "";
		int n = text.length();
		// Iterate through the characters of the input
		for(int i = 0; i < n; i++) {
			// Get the index of the character i
			int idx = alphabet.indexOf(Character.toString(text.charAt(i)));
			// Check if the character is stored in the alphabet
			if(idx == -1) {
				// Throws error and finish exectuion
				System.err.println("Invalid character");
				System.exit(1);
			}
			// Store the numeric code of the character read
			result += idx + " ";
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
	public static String moveToFrontInverse(String text) {
		// Check if the alphabet is empty
		if(alphabet.isEmpty()) {
			// Fills the alphabet
			createAlphabet();
		}
		String result = "";
		// Elimination of the spaces and store number codes in a list
		List<String> numberList = Arrays.asList(text.trim().split(" "));
		// Itetation through the number list
		for(String number : numberList) {
			// Apply the move to front algorithm
			result += alphabet.get(Integer.parseInt(number));
			alphabet.addFirst(alphabet.remove(Integer.parseInt(number)));
		}
		// Returns the result
		return result;
	}
}
