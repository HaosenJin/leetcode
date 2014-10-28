package leetcode.DPAndRecurrsive;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PalindromePartitioning {
	/**
	 * 
	 * Given a string s, partition s such that every substring of the partition
	 * is a palindrome.
	 * 
	 * Return all possible palindrome partitioning of s.
	 * 
	 * For example, given s = "aab", Return
	 * 
	 * [ ["aa","b"], ["a","a","b"] ]
	 * 
	 * @param s
	 * @return
	 */
	public static List<List<String>> partition(String s) {
		if (s == null)
			return null;

		String[][] subStringPalindrome = preCompute(s);
		Map<Integer, List<List<String>>> partitionMap = new HashMap<Integer, List<List<String>>>();

		for (int i = 0; i < s.length(); i++) {
			List<List<String>> partitions = new LinkedList<List<String>>();
			if (subStringPalindrome[0][i] != null) {
				LinkedList<String> partition = new LinkedList<String>();
				partition.add(subStringPalindrome[0][i]);
				partitions.add(partition);
			}

			for (int j = 0; j < i; j++) {
				if (subStringPalindrome[j + 1][i] == null) {
					continue;
				}
				List<List<String>> prev_partitions = partitionMap.get(j);
				for (List<String> linkedList : prev_partitions) {
					List<String> partition = new LinkedList<String>();
					partition.addAll(linkedList);
					partition.add(subStringPalindrome[j + 1][i]);
					partitions.add(partition);
				}
			}
			partitionMap.put(i, partitions);
		}
		return partitionMap.get(s.length() - 1);
	}

	private static String[][] preCompute(String s) {
		String[][] result = new String[s.length()][s.length()];
		for (int i = 0; i < result.length; i++) {
			result[i][i] = s.substring(i, i + 1);
			if (i + 1 < s.length()) {
				if (s.substring(i, i + 1).equals(s.substring(i + 1, i + 2)))
					result[i][i + 1] = s.substring(i, i + 2);
				else
					result[i][i + 1] = null;
			}
		}

		for (int l = 3; l <= result.length; l++) {
			for (int i = 0; i < result.length - l + 1; i++) {
				int j = i + l - 1;
				if (result[i + 1][j - 1] == null
						|| !result[i][i].equals(result[j][j])) {
					result[i][j] = null;
				} else {
					result[i][j] = result[i][i] + result[i + 1][j - 1]
							+ result[j][j];
				}
			}
		}
		return result;
	}

	/**
	 * Given a string s, partition s such that every substring of the partition
	 * is a palindrome.
	 * 
	 * Return the minimum cuts needed for a palindrome partitioning of s.
	 * 
	 * For example, given s = "aab", Return 1 since the palindrome partitioning
	 * ["aa","b"] could be produced using 1 cut.
	 * 
	 * @param s
	 * @return
	 */
	public static int minCut(String s) {

		if (s == null || s.length() == 1)
			return 0;
		boolean[][] isPalindrome = isPalindrome(s);
		int[] f = new int[s.length()];

		for (int i = 1; i < f.length; i++) {
			if (isPalindrome[0][i]) {
				f[i] = 0;
				continue;
			}
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (isPalindrome[j + 1][i] && f[j] + 1 < min) {
					min = f[j] + 1;
				}
			}
			f[i] = min;
		}
		return f[s.length() - 1];
	}

	private static boolean[][] isPalindrome(String s) {
		boolean[][] result = new boolean[s.length()][s.length()];
		char[] c = s.toCharArray();
		for (int i = 0; i < result.length; i++) {
			result[i][i] = true;
			if (i + 1 < result.length) {
				result[i][i + 1] = c[i] == c[i + 1];
			}
		}
		for (int l = 3; l <= result.length; l++) {
			for (int i = 0; i < result.length - l + 1; i++) {
				int j = i + l - 1;
				if (c[i] == c[j] && result[i + 1][j - 1])
					result[i][j] = true;
				else
					result[i][j] = false;
			}
		}
		return result;
	}
}
