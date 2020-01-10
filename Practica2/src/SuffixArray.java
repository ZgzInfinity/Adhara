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

public class SuffixArray {

	// Content of the file read
	private String text;
	// Number of characters of the file
	private int n;
	// List of all suffixes
	private List<Integer> suffixIndex;



	/**
	 * @param text is the content of the input file
	 * @return a SuffixArray instance
	 */
	public SuffixArray(String text) {
		// Assignment of the fields
		this.text = text;
		this.n = text.length();
		this.suffixIndex = new ArrayList<>();
		buildSuffixArray();
	}

	
	
	/**
	 * Fills the list of suffixes with the content of the file
	 */
	public void buildSuffixArray() 
	{ 
		Suffix[] su = new Suffix[n];
		// Store each char in suffix array with its index
		for (int i = 0; i < n; i++) { 
			su[i] = new Suffix(i, text.charAt(i), 0); 
		}
		// Store rank of each char
		for (int i = 0; i < n; i++) {
			if(i + 1 < n) {
				su[i].next = su[i + 1].rank;
			}
			else {
				su[i].next = -1;
			}
		}	

		// First sorting step (according the 1st 2 char of each suffix)
		Arrays.sort(su); 

		// Create a new array
		int[] ind = new int[n]; 
		
		// Sort suffix arrays using first 4 chars
		for (int length = 4; length < 2 * n; length <<= 1) { 
			// Assigning rank and index values to first suffix 
			int rank = 0, prev = su[0].rank; 
			su[0].rank = rank; 
			ind[su[0].index] = 0;
			// Calculate new ranks for each suffix
			for (int i = 1; i < n; i++){ 
				// If first rank and next ranks are same as 
				// that of previous suffix in array, 
				// assign the same new rank to this suffix
				if (su[i].rank == prev && su[i].next == su[i - 1].next) { 
					prev = su[i].rank; 
					su[i].rank = rank; 
				} 
				else { 
					// Otherwise increment rank and assign 
					prev = su[i].rank; 
					su[i].rank = ++rank; 
				} 
				ind[su[i].index] = i; 
			} 
			// Assign next rank to every suffix 
			for (int i = 0; i < n; i++) { 
				int nextP = su[i].index + length / 2; 
				su[i].next = nextP < n ? 
				su[ind[nextP]].rank : -1; 
			}
			// Sort the suffixes according to first k characters 
			Arrays.sort(su); 
		}
		// Store result in suffixIndex list
		for (int i = 0; i < n; i++) {
			suffixIndex.add(su[i].index);
		}
	}


	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the suffixIndex
	 */
	public List<Integer> getSuffixIndex() {
		return suffixIndex;
	}

	/**
	 * @param suffixIndex the suffixIndex to set
	 */
	public void setSuffixIndex(List<Integer> suffixIndex) {
		this.suffixIndex = suffixIndex;
	}
}
