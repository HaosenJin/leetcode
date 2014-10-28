package leetcode.StringAndArray;

public class RotateMatrix {
	/**
	 * You are given an n x n 2D matrix representing an image.
	 * 
	 * Rotate the image by 90 degrees (clockwise).
	 * 
	 * Follow up: Could you do this in-place?
	 * 
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		for (int layer = 0; layer < matrix.length / 2; layer++) {
			for (int i = layer; i < matrix.length - layer - 1; i++) {
				int distance = i - layer;
				int temp = matrix[layer][i];
				// left to up
				matrix[layer][i] = matrix[matrix.length - layer - 1 - distance][layer];
				// bottom to left
				matrix[matrix.length - layer - 1 - distance][layer] = matrix[matrix.length
						- layer - 1][matrix.length - layer - 1 - distance];
				// right to bottom
				matrix[matrix.length - layer - 1][matrix.length - layer - 1
						- distance] = matrix[layer + distance][matrix.length
						- layer - 1];
				// up to right
				matrix[layer + distance][matrix.length - layer - 1] = temp;
			}
		}
	}
}
