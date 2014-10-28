package leetcode.DPAndRecurrsive;

public class MaximalRectangle {
	/**
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest
	 * rectangle containing all ones and return its area.
	 * 
	 * @param matrix
	 * @return
	 */
	public static int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int[][] sum = preCompute(matrix);
		for (int height = matrix.length; height > 0; height--) {
			for (int width = matrix[0].length; width > 0; width--) {
				if (maximalRectangle(sum, height, width) > 0)
					return height * width;
			}
		}
		return 0;
	}

	private static int maximalRectangle(int[][] sum, int height, int width) {
		for (int i = 0; i < sum.length - height + 1; i++) {
			for (int j = 0; j < sum[0].length - width + 1; j++) {
				if (isRectangle(sum, i, i + height - 1, j, j + width - 1))
					return 1;
			}
		}
		return -1;
	}

	private static boolean isRectangle(int[][] sum, int i1, int i2, int j1,
			int j2) {
		int expected = (i2 - i1 + 1) * (j2 - j1 + 1);

		int actual = 0;
		if (i1 == 0 && j1 == 0) {
			actual = sum[i2][j2];
		} else if (i1 == 0 && j1 != 0) {
			actual = sum[i2][j2] - sum[i2][j1 - 1];
		} else if (i1 != 0 && j1 == 0) {
			actual = sum[i2][j2] - sum[i1 - 1][j2];
		} else {
			actual = sum[i2][j2] - sum[i2][j1 - 1] - sum[i1 - 1][j2]
					+ sum[i1 - 1][j1 - 1];
		}
		if (expected == actual)
			return true;
		return false;
	}

	private static int[][] preCompute(char[][] matrix) {
		int[][] sum = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == '1')
					sum[i][j] = 1;
				else
					sum[i][j] = 0;
				if (i == 0) {
					if (j - 1 >= 0)
						sum[i][j] += sum[i][j - 1];
				} else if (j == 0) {
					if (i - 1 >= 0)
						sum[i][j] += sum[i - 1][j];
				} else {
					sum[i][j] = sum[i - 1][j] + sum[i][j - 1]
							- sum[i - 1][j - 1];
				}
			}
		}
		return sum;
	}

	public static int maximalRectangle2(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int max = 0;
		int[] p = new int[matrix[0].length];
		for (int i1 = 0; i1 < matrix.length; i1++) {
			clear(p);
			for (int i2 = i1; i2 < matrix.length; i2++) {
				for (int j = 0; j < matrix[0].length; j++) {
					p[j] += matrix[i2][j] == '1' ? 1 : 0;			
				}
				int current_max = findMax(p, i2 - i1 + 1);
				if (current_max > max)
					max = current_max;
			}
		}
		return max;
	}

	private static void clear(int[] p) {
		for (int i = 0; i < p.length; i++) {
			p[i] = 0;
		}
	}

	private static int findMax(int[] p, int height) {
		int max = 0;
		int sum = 0;
		for (int i = 0; i < p.length; i++) {
			if (p[i] < height)
				sum = 0;
			else {
				sum = sum + height;
				if (sum > max)
					max = sum;
			}
		}
		return max;	
	}
	
	
}
