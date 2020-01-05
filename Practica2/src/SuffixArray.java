import java.util.ArrayList;
import java.util.List;

public class SuffixArray {

	private String text;
	private int n;
	private List<Integer> suffixIndex;

	/**
	 * @param text
	 * @param suffixIndex
	 */
	public SuffixArray(String text) {
		this.text = text;
		this.n = text.length();
		this.suffixIndex = new ArrayList<>();
		buildSuffixArray();
	}

	/**
	 * 
	 */
	public void buildSuffixArray() {
		for (int i = 0; i < n; i++) {
			suffixIndex.add(i);
		}
		sort(0, text.length() - 1);

	}

	public void sort(int left, int right) {
		int pivot = suffixIndex.get(left);
		int i = left;
		int j = right;
		int aux;
		while (i < j) {
			while (text.substring(suffixIndex.get(i), n).compareTo(text.substring(pivot, n)) <= 0 && i < j) {
				i++;
			}
			while (text.substring(suffixIndex.get(j), n).compareTo(text.substring(pivot, n)) > 0) {
				j--;
			}
			if (i < j) {
				aux = suffixIndex.get(i);
				suffixIndex.set(i, suffixIndex.get(j));
				suffixIndex.set(j, aux);
			}
		}
		suffixIndex.set(left, suffixIndex.get(j));
		suffixIndex.set(j, pivot);
		if (left < j - 1) {
			sort(left, j - 1);
		}
		if (j + 1 < right) {
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
