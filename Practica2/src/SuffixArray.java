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
import java.util.List;

public class SuffixArray {

	// Content of the file read
	private String text;
	// Number of characters of the file
	private int n;
	// List of all suffixes
	private int[] suffixIndex;



	/**
	 * @param text is the content of the input file
	 * @return a SuffixArray instance
	 */
	public SuffixArray(String text) {
		// Assignment of the fields
		this.text = text;
		this.n = text.length();
		int[] sa = new int[n + 3];
		for(int i = 0; i < n; i++) {
			sa[i] = (int) text.charAt(i);
		}
		this.suffixIndex = Arrays.copyOfRange(Skew.buildSuffixArray(sa, 0, n), 0, n);	
		// this.suffixIndex = Arrays.asList(Arrays.stream(Arrays.copyOfRange(Skew.buildSuffixArray(sa, 0, n), 0, n)).boxed().toArray(Integer[]::new));	
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
	public int[] getSuffixIndex() {
		return suffixIndex;
	}

	/**
	 * @param suffixIndex the suffixIndex to set
	 */
	public void setSuffixIndex(int[] suffixIndex) {
		this.suffixIndex = suffixIndex;
	}
}
