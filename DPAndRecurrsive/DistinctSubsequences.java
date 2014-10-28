package leetcode.DPAndRecurrsive;

public class DistinctSubsequences {
	/**
	 * Given a string S and a string T, count the number of distinct
	 * subsequences of T in S.
	 * 
	 * A subsequence of a string is a new string which is formed from the
	 * original string by deleting some (can be none) of the characters without
	 * disturbing the relative positions of the remaining characters. (ie, "ACE"
	 * is a subsequence of "ABCDE" while "AEC" is not).
	 * 
	 * Here is an example: S = "rabbbit", T = "rabbit"
	 * 
	 * Return 3.
	 * 
	 * @param S
	 * @param T
	 * @return
	 */
	public static int numDistinct(String S, String T) {
		if (S == null || S.length() < T.length())
			return 0;
		int[][] f = new int[S.length() + 1][T.length() + 1];
		for (int i = 0; i < f.length; i++) {
			f[i][0] = 1;
		}
		for (int j = 1; j < f[0].length; j++) {
			f[0][j] = 0;
		}

		for (int i = 1; i < f.length; i++) {
			for (int j = 1; j <= i && j < f[0].length; j++) {
				f[i][j] += f[i - 1][j];
				if (S.charAt(i - 1) == T.charAt(j - 1))
					f[i][j] += f[i - 1][j - 1];
			}
		}
		return f[S.length()][T.length()];
	}
}
