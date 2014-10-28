package leetcode.StringAndArray;

public class ReverseWords {
	/*
	 * Given an input string, reverse the string word by word. For example,
	 * Given s = "the sky is blue", return "blue is sky the". click to show
	 * clarification.
	 * 
	 * Clarification: What constitutes a word? A sequence of non-space
	 * characters constitutes a word. Could the input string contain leading or
	 * trailing spaces? Yes. However, your reversed string should not contain
	 * leading or trailing spaces. How about multiple spaces between two words?
	 * Reduce them to a single space in the reversed string.
	 */
	/**
	 * @param s
	 * @return
	 */
	public static String reverseWords(String s) {
		s = preprocess(s);
		if (s.isEmpty())
			return "";
		char[] charArray = s.toCharArray();
		reverse(charArray, 0, charArray.length - 1);
		int start = 0;
		int end = 0;
		for (int i = 0; i < charArray.length; i++) {

			if (i == 0) {
				start = i;
			} else if (charArray[i] != ' ' && charArray[i - 1] == ' ') {
				start = i;
			}

			if (i == charArray.length - 1) {
				end = i;
				reverse(charArray, start, end);
			} else if (charArray[i] != ' ' && charArray[i + 1] == ' ') {
				end = i;
				reverse(charArray, start, end);
			}
		}
		StringBuffer sb = new StringBuffer();
		return sb.append(charArray).toString();
	}

	private static void reverse(char[] array, int start, int end) {
		while (start < end) {
			char temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
	}

	private static String preprocess(String s) {
		String[] words = s.split(" ");
		StringBuffer sb = new StringBuffer();
		for (String string : words) {
			if (!string.isEmpty())
				sb.append(string + " ");
		}
		return sb.toString().trim();
	}
}
