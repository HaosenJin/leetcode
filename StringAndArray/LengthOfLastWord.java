package leetcode.StringAndArray;

public class LengthOfLastWord {
	/**
	 * Given a string s consists of upper/lower-case alphabets and empty space
	 * characters ' ', return the length of last word in the string.
	 * 
	 * If the last word does not exist, return 0.
	 * 
	 * Note: A word is defined as a character sequence consists of non-space
	 * characters only.
	 * 
	 * For example, Given s = "Hello World", return 5.
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLastWord(String s) {
		s = s.trim();
		if (s.isEmpty() || s.length() == 0 || s == null)
			return 0;
		int start = 0;
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i != 0 && s.charAt(i) != ' ' && s.charAt(i - 1) == ' ')
				start = i;
			if (i != s.length() - 1 && s.charAt(i) != ' '
					&& s.charAt(i + 1) == ' ')
				length = i - start + 1;
		}
		if (s.charAt(s.length() - 1) != ' ')
			length = s.length() - start;
		return length;
	}
}
