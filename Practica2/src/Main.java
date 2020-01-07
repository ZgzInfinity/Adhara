import java.util.List;

public class Main {

	public static void main(String[] args) {
		String text = "BANANAZ";
		SuffixArray sa = new SuffixArray(text);
		List<Integer> si = sa.getSuffixIndex();
		BurrowsWheeler.bwt2(text);
		System.out.println(BurrowsWheeler.bwt(text, si));
		MoveToFront.m2f("dfacf");

	}

}
