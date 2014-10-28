package leetcode.StringAndArray;

import java.util.HashMap;
import java.util.Map;

public class LongestWithoutRepeating {
	/**
	 * Given a string, find the length of the longest substring without
	 * repeating characters. For example, the longest substring without
	 * repeating letters for "abcabcbb" is "abc", which the length is 3. For
	 * "bbbbb" the longest substring is "b", with the length of 1.
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s) {
		int max = 0;
		int count = 0;
		int i = 0;
		int[] map = new int[256];
		while (i < s.length()) {
			char c = s.charAt(i);
			if (map[c] == 0) {
				map[c] = i + 1;
				count++;
				i++;
			} else {
				if (count > max)
					max = count;
				count = 0;
				i = map[c];
				map = new int[256];
			}
		}
		if (count > max)
			max = count;
		return max;
	}
}
