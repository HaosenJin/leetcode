package leetcode.DPAndRecurrsive;

public class MinimumPathSum {
	/**
	 * Given a m x n grid filled with non-negative numbers, find a path from top
	 * left to bottom right which minimizes the sum of all numbers along its
	 * path.
	 * 
	 * Note: You can only move either down or right at any point in time.
	 * 
	 * @param grid
	 * @return
	 */
	public static int minPathSum(int[][] grid) {
		int[][] sum = new int[grid.length][grid[0].length];
		sum[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
		for (int i = grid.length - 2; i >= 0; i--) {
			sum[i][grid[0].length - 1] = sum[i + 1][grid[0].length - 1]
					+ grid[i][grid[0].length - 1];
		}

		for (int i = grid[0].length - 2; i >= 0; i--) {
			sum[grid.length - 1][i] = sum[grid.length - 1][i + 1]
					+ grid[grid.length - 1][i];
		}

		for (int i = grid.length - 2; i >= 0; i--) {
			for (int j = grid[0].length - 2; j >= 0; j--) {
				sum[i][j] = Math.min(sum[i][j + 1], sum[i + 1][j]) + grid[i][j];
			}
		}

		return sum[0][0];
	}
}
