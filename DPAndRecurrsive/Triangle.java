package leetcode.DPAndRecurrsive;

import java.util.List;
import java.util.PriorityQueue;

public class Triangle {
	/**
	 * Given a triangle, find the minimum path sum from top to bottom. Each step
	 * you may move to adjacent numbers on the row below.
	 * 
	 * For example, given the following triangle [ [2], [3,4], [6,5,7],
	 * [4,1,8,3] ] The minimum path sum from top to bottom is 11 (i.e., 2 + 3 +
	 * 5 + 1 = 11).
	 * 
	 * @param triangle
	 * @return
	 */

	public static int minimumTotal(List<List<Integer>> triangle) {
		if (triangle.size() == 0 || triangle == null) {
			return 0;
		}

		if (triangle.size() == 1) {
			return triangle.get(0).get(0);
		}
		
		int[] sum = new int[triangle.size()];
		
		List<Integer> lowestLevel = triangle.get(triangle.size() - 1);
		
		for (int i = 0; i < sum.length; i++) {
			sum[i] = lowestLevel.get(i);
		}
		for (int i = triangle.size() - 2; i >= 0; i--) {
			List<Integer> level_i = triangle.get(i);
			for (int j = 0; j <= i; j++) {
				sum[j] = level_i.get(j) + Math.min(sum[j], sum[j + 1]);
			}
		}
		return sum[0];
	}

	public static int minimumTotal2(List<List<Integer>> triangle) {
		if (triangle.size() == 0 || triangle == null) {
			return 0;
		}

		if (triangle.size() == 1) {
			return triangle.get(0).get(0);
		}

		PriorityQueue<Integer> result = new PriorityQueue<Integer>();
		result.add(Integer.MAX_VALUE);
		calculate(result, triangle, 2, 0, triangle.get(0).get(0));
		return result.peek();
	}

	private static void calculate(PriorityQueue<Integer> result,
			List<List<Integer>> triangle, int level, int index, int current_sum) {
		List<Integer> list = triangle.get(level - 1);
		int num1 = list.get(index);
		int num2 = list.get(index + 1);
		if (level == triangle.size()) {
			current_sum = current_sum + Math.min(num1, num2);
			if (current_sum < result.peek()) {
				result.poll();
				result.add(current_sum);
			}
		} else {
			calculate(result, triangle, level + 1, index, current_sum + num1);
			calculate(result, triangle, level + 1, index + 1, current_sum
					+ num2);
		}
	}
}
