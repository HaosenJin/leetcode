package leetcode.DPAndRecurrsive;

public class InterleavingString {
	/**
	 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and
	 * s2.
	 * 
	 * For example, Given: s1 = "aabcc", s2 = "dbbca",
	 * 
	 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return
	 * false.
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public static boolean isInterleave(String s1, String s2, String s3) {
		if (s1 == null || s2 == null || s3 == null) {
			return false;
		}
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		boolean[][] interleave = new boolean[s1.length() + 1][s2.length() + 1];
		interleave[0][0] = true;
		for (int i = 1; i < interleave.length; i++) {
			if (s1.substring(0, i).equals(s3.substring(0, i)))
				interleave[i][0] = true;
		}
		for (int i = 1; i < interleave[0].length; i++) {
			if (s2.substring(0, i).equals(s3.substring(0, i)))
				interleave[0][i] = true;
		}
		for (int i = 1; i < interleave.length; i++) {
			for (int j = 1; j < interleave[0].length; j++) {
				if (interleave[i - 1][j]
						&& s1.charAt(i - 1) == s3.charAt(i + j - 1)
						|| interleave[i][j - 1]
						&& s2.charAt(j - 1) == s3.charAt(i + j - 1))
					interleave[i][j] = true;
			}
		}
		return interleave[s1.length()][s2.length()];
	}
}
