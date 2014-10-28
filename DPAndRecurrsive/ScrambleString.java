package leetcode.DPAndRecurrsive;

import java.util.Arrays;

public class ScrambleString {
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isScramble(String s1, String s2) {
		if (!needCheck(s1, s2)) {
			return false;
		}
		if (s1.equals(s2))
			return true;
		boolean result = false;
		int length = s1.length();
		for (int i = 1; i < s1.length(); i++) {
			result = isScramble(s1.substring(0, i), s2.substring(0, i))
					&& isScramble(s1.substring(i), s2.substring(i));
			result = result
					|| isScramble(s1.substring(0, i), s2.substring(length - i))
					&& isScramble(s1.substring(i), s2.substring(0, length - i));
			if (result) {
				return result;
			}
		}
		return result;
	}

	private static boolean needCheck(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;
		if (s1.length() == 1 && !s1.equals(s2))
			return false;
		char[] s1Array = s1.toCharArray();
		Arrays.sort(s1Array);
		char[] s2Array = s2.toCharArray();
		Arrays.sort(s2Array);
		return String.valueOf(s1Array).equals(String.valueOf(s2Array));
	}
}
