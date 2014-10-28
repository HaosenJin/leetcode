package leetcode.DPAndRecurrsive;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {
	/**
	 * A message containing letters from A-Z is being encoded to numbers using
	 * the following mapping:
	 * 
	 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing
	 * digits, determine the total number of ways to decode it.
	 * 
	 * For example, Given encoded message "12", it could be decoded as "AB" (1
	 * 2) or "L" (12).
	 * 
	 * The number of ways decoding "12" is 2.
	 * 
	 * @param s
	 * @return
	 */
	public static int numDecodings(String s) {
		if (s.length() == 0)
			return 0;
		if (s.length() == 1) {
			if (s.equals("0"))
				return 0;
			return 1;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		return numDecodings(s, 0, s.length() - 1, map);
	}

	private static int numDecodings(String s, Map<String, Integer> map) {
		if (s.length() == 0 || s.length() == 1)
			return 1;
		if (s.charAt(0) == '0')
			return 0;
		if (map.containsKey(s))
			return map.get(s);
		int result = 0;
		result = result + numDecodings(s.substring(1));
		if (Integer.valueOf(s.substring(0, 2)) < 27) {
			result = result + numDecodings(s.substring(2));
		}
		map.put(s, result);
		return result;
	}

	private static int numDecodings(String s, int start, int end,
			Map<String, Integer> map) {
		if (start > end || start < 0 || end >= s.length())
			return 1;

		if (s.charAt(start) == '0') {
			return 0;
		}

		if (start == end) {
			if (s.charAt(start) == '0')
				return 0;
			return 1;
		}

		String key = s.substring(start, end + 1);
		if (map.containsKey(key))
			return map.get(key);
		int result = 0;
		int mid = (start + end) / 2;
		if (s.charAt(mid) != '0') {
			int left = numDecodings(s, start, mid - 1, map);
			int right = numDecodings(s, mid + 1, end, map);
			result += left * right;
			if (mid + 1 <= end && isValidToken(s.substring(mid, mid + 2))) {
				result = result + numDecodings(s, mid + 2, end, map) * left;
			}
		}

		if (mid - 1 >= start && isValidToken(s.substring(mid - 1, mid + 1))) {
			result = result + numDecodings(s, start, mid - 2, map)
					* numDecodings(s, mid + 1, end, map);
		}
		map.put(key, result);
		return result;
	}

	private static boolean isValidToken(String s) {
		return s.length() > 0 && s.length() <= 2 && s.charAt(0) != '0'
				&& Integer.valueOf(s) < 27;
	}
}
