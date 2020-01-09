import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BurrowsWheeler {
	
	public static String bwt2(String text) {
		List<String> rotations = new ArrayList<>();
		int n = text.length();
		// Store first element (no rotation)
		rotations.add(text);
		for(int i = 1; i < n; i++) {
			rotations.add(rotations.get(i - 1).charAt(n - 1) + rotations.get(i - 1).substring(0, n - 1));
		}
		// Sort arrays
		String rotationsArray[] = new String[n];
		rotations.toArray(rotationsArray);
		Arrays.sort(rotationsArray);
		
		// Get last column
		String lastColumn = "";
		for(String s : rotationsArray) {
			lastColumn += s.charAt(n - 1);
		}
		return lastColumn;
		
	}
	
	// https://web.stanford.edu/class/cs262/presentations/lecture5.pdf
	public static String bwt(String text, List<Integer> suffixIndex) {
		String result = "";
		for(Integer idx : suffixIndex) {
			if(idx == 0) {
				result += text.charAt(text.length() - 1);
			}
			else {
				result += text.charAt(idx - 1);
			}
		}
		return result;
	}
	
	public static String bwtInverse(String lastColumn) {
		// Create C vector
		int preC[] = new int[256];
		// Initialize array to 0
		for(int i = 0; i < 256; i++) {
			preC[i] = 0;
		}
		int n = lastColumn.length();
		for (int i = 0; i < n; i++) {
			preC[(int)lastColumn.charAt(i)] += 1;
		}
		int numSmallerChars = 0;
		int aux;
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
		// Reuse preC for constructing indexI array
		// Initialize array to 0
		for(int i = 0; i < 256; i++) {
			preC[i] = 0;
		}
		List<Integer> indexI = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			// Update frequency of vector
			preC[(int)lastColumn.charAt(i)] += 1;
			indexI.add(preC[(int)lastColumn.charAt(i)]);
		}
		// Create LF array (C + indexI)
		List<Integer> LF = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			LF.add(C.get(i) + indexI.get(i) - 1);
		}
		// Reconstruct original text
		String result = "";
		int r = 0;
		char c = lastColumn.charAt(r);
		for(int i = 0; i < n - 1; i++) {		
			result = c + result;
			r = LF.get(r);
			c = lastColumn.charAt(r);
		}
		return result;
	}
}
