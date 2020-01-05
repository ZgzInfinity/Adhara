import java.util.List;

public class Main {

	public static void main(String[] args) {
		SuffixArray sa = new SuffixArray("dabdac");
		List<Integer> si = sa.getSuffixIndex();
		for(Integer element : si) {
			System.out.println(element + 1);
		}

	}

}
