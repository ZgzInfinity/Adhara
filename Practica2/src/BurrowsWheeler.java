import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
