package leetcode.DPAndRecurrsive;

public class EditDistance {
	/**
	 * Given two words word1 and word2, find the minimum number of steps
	 * required to convert word1 to word2. (each operation is counted as 1
	 * step.)
	 * 
	 * You have the following 3 operations permitted on a word:
	 * 
	 * a) Insert a character b) Delete a character c) Replace a character
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	public int minDistance(String word1, String word2) {
		int[][] min = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 1; i < min.length; i++) {
			min[i][0] = i;
		}
		for (int j = 1; j < min[0].length; j++) {
			min[0][j] = j;
		}
		for (int i = 1; i < min.length; i++) {
			for (int j = 1; j < min[0].length; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					min[i][j] = min[i - 1][j - 1];
				} else {
					min[i][j] = Math.min(min[i - 1][j],
							Math.min(min[i - 1][j - 1], min[i][j - 1])) + 1;
				}
			}
		}
		return min[word1.length()][word2.length()];
	}
}
