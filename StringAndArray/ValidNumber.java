package leetcode.StringAndArray;

public class ValidNumber {
	/**
	 * Validate if a given string is numeric.
	 * 
	 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" => false
	 * "2e10" => true Note: It is intended for the problem statement to be
	 * ambiguous. You should gather all requirements up front before
	 * implementing one.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumber(String s) {
		s = s.trim().toLowerCase();
		if (s.length() == 0)
			return false;
		if (!checkOther(s))
			return false;
		if (!checkPoint(s))
			return false;
		if (!checkSign(s))
			return false;
		if (!checkE(s))
			return false;
		// if (!checkOther(s) || !checkPoint(s) || !checkSign(s) || !checkE(s))
		// return false;
		return true;
	}

	private static int[] count = new int[256];

	private static boolean checkSign(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-') {
				if (i != 0
						&& (s.charAt(i - 1) != 'e'
								|| s.charAt(0) != s.charAt(i)
								&& count[s.charAt(i)] > 0 || s.charAt(0) == s
								.charAt(i) && count[s.charAt(i)] > 1)) {
					return false;
				} else {
					count[s.charAt(i)]++;
				}
			}
		}
		return true;
	}

	private static boolean checkE(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'e') {
				if (i == s.length() - 1
						|| count['e'] > 0
						|| i == 0
						|| i - 1 >= 0
						&& (!isDigit(s.charAt(i - 1)) && !(s.charAt(i - 1) == '.' && leftHasNumber(
								s, i - 1))) || i + 1 == s.length() - 1
						&& !isDigit(s.charAt(i + 1)) || i + 1 < s.length() - 1
						&& !isDigit(s.charAt(i + 1))
						&& !isSign(s.charAt(i + 1))) {
					return false;
				} else {
					count['e']++;
				}
			}
		}
		return true;
	}

	private static boolean checkOther(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!isDigit(s.charAt(i)) && s.charAt(i) != '.'
					&& s.charAt(i) != '+' && s.charAt(i) != '-'
					&& s.charAt(i) != 'e')
				return false;
		}
		return true;
	}

	private static boolean checkPoint(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '.') {
				if (count['.'] > 0
						|| leftHasE(s, i)
						|| i - 1 >= 0
						&& (!isDigit(s.charAt(i - 1)) && !isSign(s
								.charAt(i - 1)))
						|| i + 1 < s.length()
						&& (!isDigit(s.charAt(i + 1)) && s.charAt(i + 1) != 'e')
						|| i == s.length() - 1 && !leftHasNumber(s, i))
					return false;
				else {
					count['.']++;
				}
			}
		}
		return true;
	}

	private static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	private static boolean leftHasNumber(String s, int i) {
		for (int j = 0; j < i; j++) {
			if (s.charAt(j) >= '0' && s.charAt(j) <= '9')
				return true;
		}
		return false;
	}

	private static boolean isSign(char c) {
		return c == '+' || c == '-';
	}

	private static boolean leftHasE(String s, int i) {
		for (int j = 0; j < i; j++) {
			if (s.charAt(j) == 'e')
				return true;
		}
		return false;
	}

}
