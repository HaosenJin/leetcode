package leetcode.StringAndArray;

import java.util.Stack;

public class WildcardMatching {
	/**
	 * implement wildcard pattern matching with support for '?' and '*'.
	 * 
	 * '?' Matches any single character. '*' Matches any sequence of characters
	 * (including the empty sequence).
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * The function prototype should be: bool isMatch(const char *s, const char
	 * *p)
	 * 
	 * Some examples:
	 * 
	 * isMatch("aa","a") ¡ú false
	 * 
	 * isMatch("aa","aa") ¡ú true
	 * 
	 * isMatch("aaa","aa") ¡ú false
	 * 
	 * isMatch("aa", "*") ¡ú true
	 * 
	 * isMatch("aa", "a*")¡ú true
	 * 
	 * isMatch("ab", "?*") ¡ú true
	 * 
	 * isMatch("aab", "c*a*b") ¡ú false
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isMatch(String s, String p) {
		boolean[][] result = new boolean[s.length() + 1][p.length() + 1];
		char[] s_chars = s.toCharArray();
		char[] p_chars = p.toCharArray();
		result[0][0] = true;
		for (int i = 1; i <= s_chars.length; i++) {
			if (p_chars[i - 1] == '*') {
				result[0][i] = result[0][i - 1];
			} else {
				result[0][i] = false;
			}
		}
		for (int i = 1; i <= s_chars.length; i++) {
			result[i][0] = false;
		}
		for (int i = 1; i <= s_chars.length; i++) {
			for (int j = 1; j <= p_chars.length; j++) {
				if (s_chars[i - 1] == p_chars[j - 1] || p_chars[j - 1] == '?') {
					result[i][j] = result[i - 1][j - 1];
				} else if (p_chars[j - 1] == '*') {
					if (result[i][j - 1]) {
						result[i][j] = true;
					} else if (j - 1 > i) {
						result[i][j] = false;
					} else {
						result[i][j] = result[j - 1][j - 1];
					}
				} else {
					result[i][j] = false;
				}
			}
		}
		return result[s.length()][p.length()];
	}

	public static boolean isMatch2(String s, String p) {
		boolean hasStar = false;
		char[] s_chars = s.toCharArray();
		char[] p_chars = p.toCharArray();
		int str = 0;
		int ptr = 0;
		int i, j;
		for (i = str, j = ptr; i < s_chars.length; i++, j++) {
			if (j == p_chars.length) {
				if (!hasStar) {
					return false;
				}
				str++;
				i = str - 1;
				j = ptr - 1;
			} else {
				switch (p_chars[j]) {
				case '?':
					break;
				case '*':
					hasStar = true;
					str = i;
					ptr = j;
					while (ptr < p_chars.length && p_chars[ptr] == '*') {
						ptr++;
					}
					if (ptr == p_chars.length)
						return true;
					i = str - 1;
					j = ptr - 1;
					break;
				default:
					if (s_chars[i] != p_chars[j]) {
						if (!hasStar) {
							return false;
						}
						str++;
						i = str - 1;
						j = ptr - 1;
					}
					break;
				}
			}
		}
		while (j < p_chars.length && p_chars[j] == '*')
			j++;
		return j == p_chars.length;
	}

	public static boolean isMatch3(String s, String p) {
		if (s.isEmpty() || s == null) {
			int k = 0;
			while (k < p.length() && p.charAt(k) == '*')
				k++;
			return k == p.length();
		}

		if (p.isEmpty() || p == null) {
			return s == null || s.isEmpty();
		}

		int s_ptr = 0;
		int p_ptr = 0;
		boolean hasStar = false;
		int i = s_ptr;
		int j = p_ptr;
		while (i < s.length()) {
			if (j == p.length()) {
				if (!hasStar) {
					return false;
				}
				s_ptr++;
				i = s_ptr;
				j = p_ptr;
				continue;
			}

			if (s.charAt(i) != p.charAt(j) && p.charAt(j) != '?') {
				if (p.charAt(j) == '*') {
					while (j < p.length() && p.charAt(j) == '*')
						j++;
					if (j == p.length())
						return true;
					s_ptr = i;
					p_ptr = j;
					hasStar = true;
				} else if (hasStar) {
					s_ptr++;
					i = s_ptr;
					j = p_ptr;
				} else {
					return false;
				}
			} else {
				i++;
				j++;
			}

		}
		while (j < p.length() && p.charAt(j) == '*')
			j++;
		return j == p.length();
	}

	/**
	 * '.' Matches any single character. '*' Matches zero or more of the
	 * preceding element.
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * The function prototype should be: bool isMatch(const char *s, const char
	 * *p)
	 * 
	 * Some examples:
	 * 
	 * isMatch("aa","a") ¡ú false
	 * 
	 * isMatch("aa","aa") ¡ú true
	 * 
	 * isMatch("aaa","aa") ¡ú false
	 * 
	 * isMatch("aa", "a*") ¡ú true
	 * 
	 * isMatch("aa", ".*") ¡ú true
	 * 
	 * isMatch("ab", ".*") ¡ú true
	 * 
	 * isMatch("aab", "c*a*b") ¡ú true
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isMatch4(String s, String p) {
		if (p.isEmpty() || p == null) {
			return s == null || s.isEmpty();
		}
		Stack<Integer> s_ptr = new Stack<Integer>();
		Stack<Integer> p_ptr = new Stack<Integer>();
		s_ptr.push(0);
		p_ptr.push(0);
		int i = 0;
		int j = 0;
		while (!s_ptr.isEmpty() && !p_ptr.isEmpty()) {
			i = s_ptr.pop();
			j = p_ptr.pop();
			while (i < s.length() && j < p.length()) {
				if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
					if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
						s_ptr.push(i + 1);
						p_ptr.push(j);
					}
					j = j + 2;
				} else {
					if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
						i++;
						j++;
					} else {
						break;
					}
				}
			}
			if (i == s.length())
				break;
		}
		if (i < s.length())
			return false;
		while (j + 1 < p.length() && p.charAt(j + 1) == '*') {
			j = j + 2;
		}
		return j == p.length();
	}
}
