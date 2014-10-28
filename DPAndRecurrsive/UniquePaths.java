package leetcode.DPAndRecurrsive;

import leetcode.Tree.SumRootToLeaf;

public class UniquePaths {
	/**
	 * A robot is located at the top-left corner of a m x n grid (marked 'Start'
	 * in the diagram below).
	 * 
	 * The robot can only move either down or right at any point in time. The
	 * robot is trying to reach the bottom-right corner of the grid (marked
	 * 'Finish' in the diagram below).
	 * 
	 * How many possible unique paths are there?
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static int uniquePaths(int m, int n) {
		double result = 1.0;
		for (int i = 1; i < n; i++) {
			result = result * ((m - 1 + i) / (double) i);
		}

		return (int) (result + 0.1);
	}

	/**
	 * Follow up for "Unique Paths":
	 * 
	 * Now consider if some obstacles are added to the grids. How many unique
	 * paths would there be?
	 * 
	 * An obstacle and empty space is marked as 1 and 0 respectively in the
	 * grid.
	 * 
	 * For example, There is one obstacle in the middle of a 3x3 grid as
	 * illustrated below.
	 * 
	 * [ [0,0,0], [0,1,0], [0,0,0] ] The total number of unique paths is 2.
	 * 
	 * Note: m and n will be at most 100.
	 * 
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int row = obstacleGrid.length;
		int col = obstacleGrid[0].length;
		if(obstacleGrid[row-1][col-1]==1)
			return 0;
		int[][] num = new int[row][col];
		num[row - 1][col - 1] = 1;
		for (int i = row - 2; i >= 0; i--) {
			if (obstacleGrid[i][col - 1] == 1)
				num[i][col - 1] = 0;
			else
				num[i][col - 1] = num[i + 1][col - 1];
		}

		for (int j = col - 2; j >= 0; j--) {
			if (obstacleGrid[row - 1][j] == 1)
				num[row - 1][j] = 0;
			else
				num[row - 1][j] = num[row - 1][j + 1];
		}

		for (int i = row - 2; i >= 0; i--) {
			for (int j = col - 2; j >= 0; j--) {
				if (obstacleGrid[i][j] == 1)
					num[i][j] = 0;
				else {
					num[i][j] = num[i][j + 1] + num[i + 1][j];
				}
			}
		}
		return num[0][0];
	}
}
