package leetcode.StringAndArray;

public class ImplementStrStr {
	/**
	 * Implement strStr().
	 * 
	 * Returns a pointer to the first occurrence of needle in haystack, or null
	 * if needle is not part of haystack.
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public String strStr(String haystack, String needle) {
		int first = -1;
		for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
			if (haystack.subSequence(i, i + needle.length()).equals(needle)) {
				first = i;
				break;
			}
		}
		if (first == -1)
			return null;
		return haystack.substring(first);
	}
}
