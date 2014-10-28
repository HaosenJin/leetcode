package leetcode.StringAndArray;

import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {
	/**
	 * 
	 * @param n
	 * @return
	 */
	public int[][] generateMatrix(int n) {
		int[][] result = new int[n][n];
		if (n % 2 != 0)
			result[n / 2][n / 2] = n * n;
		int number = 1;
		for (int layer = 0; layer < n / 2; layer++) {
			// up
			for (int i = layer; i < n - layer - 1; i++) {
				result[layer][i] = number;
				number++;
			}

			for (int i = layer; i < n - layer - 1; i++) {
				result[i][n - layer - 1] = number;
				number++;
			}

			for (int i = n - layer - 1; i > layer; i--) {
				result[n - layer - 1][i] = number;
				number++;
			}

			for (int i = n - layer - 1; i > layer; i--) {
				result[i][layer] = number;
				number++;
			}

		}
		return result;
	}

	/**
	 * Given a matrix of m x n elements (m rows, n columns), return all elements
	 * of the matrix in spiral order.
	 * 
	 * For example, Given the following matrix:
	 * 
	 * [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] You should return
	 * [1,2,3,6,9,8,7,4,5].
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new LinkedList<Integer>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return result;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		int n = Math.min(row, col);

		int layer = 0;
		for (layer = 0; layer < n / 2; layer++) {
			// up
			for (int i = layer; i < col - layer - 1; i++) {
				result.add(matrix[layer][i]);
			}
			// right
			for (int i = layer; i < row - layer - 1; i++) {
				result.add(matrix[i][col - layer - 1]);
			}
			// bottom
			for (int i = col - layer - 1; i > layer; i--) {
				result.add(matrix[row - layer - 1][i]);
			}
			// left
			for (int i = row - layer - 1; i > layer; i--) {
				result.add(matrix[i][layer]);
			}
		}
		if (n % 2 != 0) {
			if (row == col) {
				result.add(matrix[n / 2][n / 2]);
			} else if (row < col) {
				for (int i = layer; i <= col - layer - 1; i++) {
					result.add(matrix[layer][i]);
				}
			} else {
				for (int i = layer; i <= row - layer - 1; i++) {
					result.add(matrix[i][col - layer - 1]);
				}
			}
		}
		return result;
	}
}
