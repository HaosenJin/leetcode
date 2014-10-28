package leetcode.DPAndRecurrsive;

public class MinimumWindowSubstring {
	/**
	 * Given a string S and a string T, find the minimum window in S which will
	 * contain all the characters in T in complexity O(n).
	 * 
	 * For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
	 * 
	 * Note: If there is no such window in S that covers all characters in T,
	 * return the emtpy string "".
	 * 
	 * If there are multiple such windows, you are guaranteed that there will
	 * always be only one unique minimum window in S.
	 * 
	 * @param S
	 * @param T
	 * @return
	 */

	public static String minWindow(String S, String T) {
		result = "";
		if (S == null || S.isEmpty() || S.length() < T.length())
			return result;
		int[] expected = new int[256];
		int[] appeared = new int[256];
		int appear = 0;
		int window_start = 0;
		char[] s_array = S.toCharArray();
		char[] t_array = T.toCharArray();

		for (int i = 0; i < t_array.length; i++) {
			expected[t_array[i]]++;
		}

		for (int window_end = 0; window_end < s_array.length; window_end++) {
			int index = s_array[window_end];
			if (expected[index] > 0) {
				appeared[index]++;
				if (appeared[index] <= expected[index])
					appear++;
			}

			if (appear == t_array.length) {
				while (expected[s_array[window_start]] == 0
						|| appeared[s_array[window_start]] > expected[s_array[window_start]]) {
					appeared[s_array[window_start]]--;
					window_start++;
				}
				if (result.isEmpty()
						|| window_end - window_start + 1 < result.length()) {
					result = S.substring(window_start, window_end + 1);
				}
			}
		}
		return result;
	}

	private static int start = -1;
	private static int end = 0;
	private static String result;
	private static int[] records;

	public static String minWindow2(String S, String T) {
		result = "";
		if (S == null || S.isEmpty() || S.length() < T.length())
			return result;
		records = new int[T.length()];
		for (int i = 0; i < S.length(); i++) {
			int index = findIndex(T, S.charAt(i));
			if (index > -1) {
				records[index]++;
				if (findWindow(records)) {
					end = i;
					updateStartPointer(S, T);
				}
			}
		}
		return result;
	}

	private static void updateStartPointer(String S, String T) {
		while (findWindow(records) && start < S.length()) {
			start++;
			int index = findIndex(T, S.charAt(start));
			if (index > -1) {
				records[index]--;
				if (records[index] == 0) {
					if (!result.isEmpty() && result.length() > end - start + 1
							|| result.isEmpty())
						result = S.substring(start, end + 1);
					break;
				}
			}
		}
	}

	private static boolean findWindow(int[] records) {
		for (int i = 0; i < records.length; i++) {
			if (records[i] == 0)
				return false;
		}
		return true;
	}

	private static int findIndex(String T, char c) {
		for (int i = 0; i < T.length(); i++) {
			if (T.charAt(i) == c)
				return i;
		}
		return -1;
	}
}
