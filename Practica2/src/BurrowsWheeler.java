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
	public static char[] bwt(String text, int[] suffixIndex) {
		// Final result
		int n = text.length();
		char[] result = new char[n];
		// Iterate through suffixes index list
		for(int i = 0; i < n; i++) {
			if(suffixIndex[i] == 0) {
				// Get last char 
				result[i] += text.charAt(text.length() - 1);
				
			}
			else {
				// Get left char
				result[i] += text.charAt(suffixIndex[i] - 1);
			}
		}
		// Return result
		return result;
	}
	


	/**
	 * @param lastColumn is the last column of the bwt matrix
	 * @return the inverse of the burrows wheeler transformed
	 */
	public static char[] bwtInverse(char[] lastColumn, int n) {
		// Create C vector
		int preC[] = new int[256];
		// Create a list which contains ith occurrence of character j
		// List<Integer> indexI = new ArrayList<>();
		int[] indexI = new int[n];
		// Initialize array to 0
		for(int i = 0; i < 256; i++) {
			preC[i] = 0;
		}
		// Get the frequency of each character
		for (int i = 0; i < n; i++) {
			preC[(int)lastColumn[i]] += 1;
			// Storing the jth occurrence of the character i
			indexI[i] = preC[(int)lastColumn[i]];
		}
		int numSmallerChars = 0;
		int aux;
		// Count how many characters are smaller than character i
		for(int i = 1; i < 256; i++) {
			aux = preC[i];
			preC[i] = numSmallerChars;
			numSmallerChars +=  aux;
		}
		// List<Integer> C = new ArrayList<>();
		int[] C = new int[n];
		// Create C array
		for (int i = 0; i < n; i++) {
			C[i] = preC[(int)lastColumn[i]];
		}
		// Create LF array (C + indexI)
		// List<Integer> LF = new ArrayList<>();
		int[] LF = new int[n];
		for (int i = 0; i < n; i++) {
			LF[i] = C[i] + indexI[i] - 1;
		}
		// Final result
		char[] result = new char[n - 1];
		int r = 0;
		char c = lastColumn[r];
		// Reconstruct original text
		for(int i = 0, j = n - 2; i < n - 1; i++, j--) {		
			result[j] = c;
			r = LF[r];
			c = lastColumn[r];
		}
		// Return result
		return result;
	}
}
