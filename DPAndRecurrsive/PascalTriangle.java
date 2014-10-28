package leetcode.DPAndRecurrsive;

import java.util.LinkedList;
import java.util.List;

public class PascalTriangle {
	/**
	 * Given an index k, return the kth row of the Pascal's triangle.
	 * 
	 * For example, given k = 3, Return [1,3,3,1].
	 * 
	 * Note: Could you optimize your algorithm to use only O(k) extra space?
	 * 
	 * @param rowIndex
	 * @return
	 */
	public static List<Integer> getRow(int rowIndex) {
		List<Integer> result = new LinkedList<Integer>();
		if (rowIndex == 0) {
			result.add(1);
			return result;
		}

		if (rowIndex == 1) {
			result.add(1);
			result.add(1);
			return result;
		}

		int[] current_level = new int[rowIndex + 1];
		int[] up_level = new int[rowIndex + 1];
		up_level[0] = 1;
		up_level[1] = 1;
		for (int i = 2; i <= rowIndex; i++) {
			current_level[0] = 1;
			current_level[i] = 1;
			for (int j = 1; j < i; j++) {
				current_level[j] = up_level[j - 1] + up_level[j];
			}
			copyArray(current_level, up_level);
		}

		for (int i = 0; i < up_level.length; i++) {
			result.add(up_level[i]);
		}
		return result;
	}

	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		List<Integer> level = new LinkedList<Integer>();
		if (numRows == 0)
			return result;
		level.add(1);
		result.add(level);
		if (numRows == 1) {
			return result;
		}

		List<Integer> level2 = new LinkedList<Integer>();
		level2.add(1);
		level2.add(1);
		result.add(level2);
		if (numRows == 2) {
			return result;
		}

		int[] current_level = new int[numRows];
		int[] up_level = new int[numRows];
		up_level[0] = 1;
		up_level[1] = 1;
		for (int i = 2; i < numRows; i++) {
			current_level[0] = 1;
			current_level[i] = 1;
			for (int j = 1; j < i; j++) {
				current_level[j] = up_level[j - 1] + up_level[j];
			}
			copyArray(current_level, up_level);
			addRow(result, current_level, i);
		}
		return result;
	}

	private static void addRow(List<List<Integer>> result, int[] row,
			int rowIndex) {
		List<Integer> level = new LinkedList<Integer>();
		for (int i = 0; i <= rowIndex; i++) {
			level.add(row[i]);
		}
		result.add(level);
	}

	private static void copyArray(int[] origin, int[] copy) {
		for (int i = 0; i < copy.length; i++) {
			copy[i] = origin[i];
		}
	}

}
