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
		// Assigment of the fields
		this.text = text;
		this.n = text.length();
		this.suffixIndex = new ArrayList<>();
		buildSuffixArray();
	}

	/**
	 * Fills the list of sufixes with the content of the file
	 */
	public void buildSuffixArray() {
		// Initialize suffix index list
		for (int i = 0; i < n; i++) {
			// Add the new suffix i
			suffixIndex.add(i);
		}
		// Order the suffixes list using quicksort algorithm
		sort(0, text.length() - 1);
	}



	/**
	 * 
	 * @param left is the upper bound of the search
	 * @param right is the lower bound of the search
	 */
	public void sort(int left, int right) {
		// Choose the first element as pivot
		int pivot = suffixIndex.get(left);
		// Establish the lower and upper bounds for searching
		int i = left;
		int j = right;
		int aux;
		// While the searchings dont intersect
		while (i < j) {
			// Search an element greater than pivot
			while (text.substring(suffixIndex.get(i), n).compareTo(text.substring(pivot, n)) <= 0 && i < j) {
				i++;
			}
			// Search an element lower than pivot
			while (text.substring(suffixIndex.get(j), n).compareTo(text.substring(pivot, n)) > 0) {
				j--;
			}
			// If there is not intersection
			if (i < j) {
				// Swap elements
				aux = suffixIndex.get(i);
				suffixIndex.set(i, suffixIndex.get(j));
				suffixIndex.set(j, aux);
			}
		}
		// Ubicate the pivot in order to have lower elements in its left and
		// greater elements in its right
		suffixIndex.set(left, suffixIndex.get(j));
		suffixIndex.set(j, pivot);
		if (left < j - 1) {
			// Order left sublist 
			sort(left, j - 1);
		}
		if (j + 1 < right) {
			// Order right sublist
			sort(j + 1, right);
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
