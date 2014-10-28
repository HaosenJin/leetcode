package leetcode.StringAndArray;

public class ValidPalindrome {
	/**
	 * Given a string, determine if it is a palindrome, considering only
	 * alphanumeric characters and ignoring cases.
	 * 
	 * For example, "A man, a plan, a canal: Panama" is a palindrome.
	 * "race a car" is not a palindrome.
	 * 
	 * Note: Have you consider that the string might be empty? This is a good
	 * question to ask during an interview.
	 * 
	 * For the purpose of this problem, we define empty string as valid
	 * palindrome.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome(String s) {
		if (s == null || s.trim().isEmpty())
			return true;
		if (s.length() == 1) {
			return true;
		}
		char[] charArray = s.toLowerCase().toCharArray();
		int start = 0;
		int end = charArray.length - 1;
		while (start < end) {
			while (start < charArray.length
					&& !isAlphanumeric(charArray[start]))
				start++;
			while (end >= 0 && !isAlphanumeric(charArray[end]))
				end--;

			if (start >= charArray.length || end < 0)
				return true;

			if (charArray[start] != charArray[end])
				return false;
			start++;
			end--;
		}
		return true;
	}

	private static boolean isAlphanumeric(char c) {
		return c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c >= 'A'
				&& c <= 'Z';
	}

	/**
	 * Determine whether an integer is a palindrome. Do this without extra
	 * space.
	 * 
	 * click to show spoilers.
	 * 
	 * Some hints: Could negative integers be palindromes? (ie, -1)
	 * 
	 * If you are thinking of converting the integer to string, note the
	 * restriction of using extra space.
	 * 
	 * You could also try reversing an integer. However, if you have solved the
	 * problem "Reverse Integer", you know that the reversed integer might
	 * overflow. How would you handle such case?
	 * 
	 * There is a more generic way of solving this problem.
	 * 
	 * @param x
	 * @return
	 */
	public static boolean isPalindrome(int x) {
		long xl = x;
		if (xl < 0) {
			return false;
		}
		long high = 1;
		while (high <= xl)
			high *= 10;
		high /= 10;
		if (high == 1)
			return true;
		long low = 1;
		while (low < high) {
			int highDigit = (int) (xl % (high * 10) / high);
			int lowDigit = (int) ((xl % (low * 10) - xl % (low)) / low);
			if (highDigit != lowDigit)
				return false;
			low *= 10;
			high /= 10;
		}
		return true;
	}

	/**
	 * Given a string S, find the longest palindromic substring in S. You may
	 * assume that the maximum length of S is 1000, and there exists one unique
	 * longest palindromic substring.
	 * 
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		if (s == null)
			return "";
		if (s.length() == 1)
			return s;
		boolean[][] isPalindrome = new boolean[s.length()][s.length()];
		for (int i = 0; i < isPalindrome.length; i++) {
			isPalindrome[i][i] = true;
			if (i + 1 < isPalindrome.length) {
				if (s.charAt(i) == s.charAt(i + 1)) {
					isPalindrome[i][i + 1] = true;
				}
			}
		}

		for (int l = 3; l <= s.length(); l++) {
			for (int i = 0; i < s.length() - l + 1; i++) {
				int j = i + l - 1;
				if (s.charAt(i) == s.charAt(j) && isPalindrome[i + 1][j - 1])
					isPalindrome[i][j] = true;
				else
					isPalindrome[i][j] = false;
			}
		}
		int start = 0;
		int end = 0;
		for (int i = 0; i < isPalindrome.length; i++) {
			for (int j = i; j < isPalindrome.length; j++) {
				if (isPalindrome[i][j]) {
					if (j - i > end - start) {
						start = i;
						end = j;
					}
				}
			}
		}
		return s.substring(start, end + 1);
	}
}
