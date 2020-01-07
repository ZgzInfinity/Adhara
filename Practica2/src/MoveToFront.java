import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MoveToFront {
	
	private static LinkedList<String> alphabet = new LinkedList<>(); 

	private static void createAlphabet() {
		// Alphabet from ASCII code
		for(int i = 0; i <= 255; i++) {
			alphabet.add(Character.toString ((char) i));
		}	
	}
	
	public static List<Integer> m2f(String text) {
		if(alphabet.isEmpty()) {
			createAlphabet();
		}
		List<Integer> result = new ArrayList<>();
		int n = text.length();
		for(int i = 0; i < n; i++) {
			int idx = alphabet.indexOf(Character.toString(text.charAt(i)));
			if(idx == -1) {
				System.err.println("Invalid character");
				System.exit(1);
			}
			result.add(idx);
			alphabet.addFirst(alphabet.remove(idx));
		}
		return result;
	}
}
