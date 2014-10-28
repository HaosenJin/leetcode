package leetcode.Sort;

public class Search2DMatrix {
	/**
	 * Write an efficient algorithm that searches for a value in an m x n
	 * matrix. This matrix has the following properties:
	 * 
	 * Integers in each row are sorted from left to right. The first integer of
	 * each row is greater than the last integer of the previous row. For
	 * example,
	 * 
	 * Consider the following matrix:
	 * 
	 * [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] Given target = 3,
	 * return true.
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int start = 0;
		int end = matrix.length * matrix[0].length - 1;
		int mid;
		int i;
		int j;
		while (start < end) {
			mid = (start + end) / 2;
			i = mid / matrix[0].length;
			j = mid % matrix[0].length;
			if (matrix[i][j] == target)
				return true;
			else if (matrix[i][j] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false;
	}
}
