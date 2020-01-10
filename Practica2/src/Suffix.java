/*
 *********************************************
 *** Adhara - Algorithm for hard problems ****
 *** Authors: Name - Surname - NIP ***********
 *** Victor Peñasco EStivalez - 741294 *******
 *** Ruben Rodriguez Esteban - 737215 ********
 *** Course: 2019 - 2020 *********************
 *********************************************
 */ 


public class Suffix implements Comparable<Suffix> {
	// Index of the suffix inside the text
	int index;
	// Rank of the suffix
	int rank;
	// Next rank of the suffix
	int next; 

	/**
	 * 
	 * @param ind
	 * @param r
	 * @param nr
	 * @return Suffix object instance
	 */
	public Suffix(int ind, int r, int nr) { 
		index = ind; 
		rank = r; 
		next = nr; 
	} 
	
	/**
	 * @param s suffix
	 * @return comparison value between ranks.
	 * 		   0 if equals, < 0 if lower, > 0 if greater.
	 */
	public int compareTo(Suffix s) { 
		if (rank != s.rank) {
			return Integer.compare(rank, s.rank); 
		}
		return Integer.compare(next, s.next); 
	} 
} 