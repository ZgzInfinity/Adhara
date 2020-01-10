/*
 *********************************************
 *** Adhara - Algorithm for hard problems ****
 *** Authors: Name - Surname - NIP ***********
 *** Victor Peñasco EStivalez - 741294 *******
 *** Ruben Rodriguez Esteban - 737215 ********
 *** Course: 2019 - 2020 *********************
 *********************************************
 */ 


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BurrowsWheeler {
	

	// https://web.stanford.edu/class/cs262/presentations/lecture5.pdf

	/**
	 * @param text is the content read from the input file
	 * @param suffixIndex is the list of suffixes
	 * @return the burrows wheeler transform applied to the input text
	 */
	public static String bwt(String text, List<Integer> suffixIndex) {
		// Final result
		String result = "";
		// Iterate through suffixes index list
		for(Integer idx : suffixIndex) {
			if(idx == 0) {
				// Get last char 
				result += text.charAt(text.length() - 1);
			}
			else {
				// Get left char
				result += text.charAt(idx - 1);
			}
		}
		// Return result
		return result;
	}
	


	/**
	 * @param lastColumn is the last column of the bwt matrix
	 * @return the inverse of the burrows wheeler transformed
	 */
	public static String bwtInverse(String lastColumn) {
		// Create C vector
		int preC[] = new int[256];
		// Create a list which contains ith occurrence of character j
		List<Integer> indexI = new ArrayList<>();
		// Initialize array to 0
		for(int i = 0; i < 256; i++) {
			preC[i] = 0;
		}
		int n = lastColumn.length();
		// Get the frequency of each character
		for (int i = 0; i < n; i++) {
			preC[(int)lastColumn.charAt(i)] += 1;
			// Storing the jth occurrence of the character i
			indexI.add(preC[(int)lastColumn.charAt(i)]);
		}
		int numSmallerChars = 0;
		int aux;
		// Count how many characters are smaller than character i
		for(int i = 1; i < 256; i++) {
			aux = preC[i];
			preC[i] = numSmallerChars;
			numSmallerChars +=  aux;
		}
		List<Integer> C = new ArrayList<>();
		// Create C array
		for (int i = 0; i < n; i++) {
			C.add(preC[(int)lastColumn.charAt(i)]);
		}
		// Create LF array (C + indexI)
		List<Integer> LF = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			LF.add(C.get(i) + indexI.get(i) - 1);
		}
		// Final result
		String result = "";
		int r = 0;
		char c = lastColumn.charAt(r);
		// Reconstruct original text
		for(int i = 0; i < n - 1; i++) {		
			result = c + result;
			r = LF.get(r);
			c = lastColumn.charAt(r);
		}
		// Return result
		return result;
	}
}
