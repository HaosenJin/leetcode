package leetcode.StringAndArray;

import java.util.LinkedList;

public class LongestCommonPrefix {
	/**
	 * Write a function to find the longest common prefix string amongst an
	 * array of strings.
	 * 
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		int i = 0;
		LinkedList<Character> prefix = new LinkedList<Character>();
		while (true) {
			Character c = null;
			int j = 0;
			for (; j < strs.length; j++) {
				if (i >= strs[j].length())
					break;
				if (c == null)
					c = strs[j].charAt(i);
				if (c != strs[j].charAt(i))
					break;
			}
			if (j != strs.length)
				break;
			else {
				prefix.add(c);
				i++;
			}
		}
		StringBuffer sb = new StringBuffer();
		for (Character c : prefix) {
			sb.append(c);
		}
		return sb.toString();
	}
}
