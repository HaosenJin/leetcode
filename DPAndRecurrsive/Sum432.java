package leetcode.DPAndRecurrsive;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Sum432 {
	/**
	 * Given an array S of n integers, are there elements a, b, c, and d in S
	 * such that a + b + c + d = target? Find all unique quadruplets in the
	 * array which gives the sum of target.
	 * 
	 * Note: Elements in a quadruplet (a,b,c,d) must be in non-descending order.
	 * (ie, a ¡Ü b ¡Ü c ¡Ü d) The solution set must not contain duplicate
	 * quadruplets. For example, given array S = {1 0 -1 0 -2 2}, and target =
	 * 0.
	 * 
	 * A solution set is: (-1, 0, 0, 1) (-2, -1, 1, 2) (-2, 0, 0, 2)
	 * 
	 * @param num
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> fourSum(int[] num, int target) {
		int[] solution = new int[4];
		Arrays.sort(num);
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		calculate(result, solution, 0, num, 0, target, 0);
		return result;
	}

	private static void calculate(List<List<Integer>> result, int[] solution,
			int s_index, int[] num, int n_index, int target, int sum) {
		if (s_index == solution.length) {
			if (sum == target) {
				List<Integer> list = new LinkedList<Integer>();
				for (int i : solution) {
					list.add(i);
				}
				result.add(list);
			}
			return;
		}
		for (int i = n_index; i < num.length; i++) {
			if (sum + num[i] > target)
				break;
			solution[s_index] = num[i];
			calculate(result, solution, s_index + 1, num, i + 1, target, sum
					+ num[i]);
			while (i + 1 < num.length && num[i] == num[i + 1]) {
				i++;
			}
		}
	}

	public static List<List<Integer>> fourSum2(int[] num, int target) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		Arrays.sort(num);
		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				int k = j + 1;
				int n = num.length - 1;
				while (k < n) {
					if (k > j + 1 && num[k] == num[k - 1]) {
						k++;
						continue;
					}
					int sum = num[i] + num[j] + num[k] + num[n];
					if (sum == target) {
						List<Integer> solution = new LinkedList<Integer>();
						solution.add(num[i]);
						solution.add(num[j]);
						solution.add(num[k]);
						solution.add(num[n]);
						result.add(solution);
						k++;
						n--;
					} else if (sum < target) {
						k++;
					} else {
						n--;
					}
				}
				while (j + 1 < num.length && num[j] == num[j + 1]) {
					j++;
				}
			}
			while (i + 1 < num.length && num[i] == num[i + 1]) {
				i++;
			}
		}
		return result;
	}

	/**
	 * Given an array of integers, find two numbers such that they add up to a
	 * specific target number.
	 * 
	 * The function twoSum should return indices of the two numbers such that
	 * they add up to the target, where index1 must be less than index2. Please
	 * note that your returned answers (both index1 and index2) are not
	 * zero-based.
	 * 
	 * You may assume that each input would have exactly one solution.
	 * 
	 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
	 * 
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] numbers, int target) {
		int[] copy = new int[numbers.length];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = numbers[i];
		}
		Arrays.sort(copy);
		int[] result = new int[2];
		int start = 0;
		int end = numbers.length - 1;
		while (start < end) {
			int sum = copy[start] + copy[end];
			if (sum == target) {
				result[0] = copy[start];
				result[1] = copy[end];
				break;
			} else if (sum < target) {
				start++;
			} else {
				end--;
			}
		}
		int index1 = -1;
		int index2 = -1;
		for (int i = 0; i < numbers.length; i++) {
			if (result[0] != result[1]) {
				if (numbers[i] == result[0]) {
					index1 = i + 1;
				}
				if (numbers[i] == result[1]) {
					index2 = i + 1;
				}
			} else {
				if (numbers[i] == result[0]) {
					if (index1 == -1) {
						index1 = i + 1;
					} else {
						index2 = i + 1;
					}
				}
			}
		}
		result[0] = Math.min(index1, index2);
		result[1] = Math.max(index1, index2);
		return result;
	}

	/**
	 * Given an array S of n integers, are there elements a, b, c in S such that
	 * a + b + c = 0? Find all unique triplets in the array which gives the sum
	 * of zero.
	 * 
	 * Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie,
	 * a ¡Ü b ¡Ü c) The solution set must not contain duplicate triplets. For
	 * example, given array S = {-1 0 1 2 -1 -4},
	 * 
	 * A solution set is: (-1, 0, 1) (-1, -1, 2)
	 * 
	 * @param num
	 * @return
	 */
	public static List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		Arrays.sort(num);
		for (int i = 0; i < num.length; i++) {
			int j = i + 1;
			int k = num.length - 1;
			while (j < k) {
				if (j > i + 1 && num[j] == num[j - 1]) {
					j++;
					continue;
				}
				int sum = num[i] + num[j] + num[k];
				if (sum == 0) {
					List<Integer> solution = new LinkedList<Integer>();
					solution.add(num[i]);
					solution.add(num[j]);
					solution.add(num[k]);
					result.add(solution);
					j++;
					k--;
				} else if (sum < 0) {
					j++;

				} else {
					k--;
				}
			}
			while (i + 1 < num.length && num[i] == num[i + 1])
				i++;
		}
		return result;
	}

	/**
	 * Given an array S of n integers, find three integers in S such that the
	 * sum is closest to a given number, target. Return the sum of the three
	 * integers. You may assume that each input would have exactly one solution.
	 * 
	 * For example, given array S = {-1 2 1 -4}, and target = 1.
	 * 
	 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	 * 
	 * @param num
	 * @param target
	 * @return
	 */
	public static int threeSumClosest(int[] num, int target) {
		Integer closest = null;
		Arrays.sort(num);
		for (int i = 0; i < num.length; i++) {
			int j = i + 1;
			int k = num.length - 1;
			while (j < k) {
				if (j > i + 1 && num[j] == num[j - 1]) {
					j++;
					continue;
				}
				int sum = num[i] + num[j] + num[k];
				if (closest == null
						|| Math.abs((sum - target)) < Math
								.abs((closest - target))) {
					closest = sum;
				}
				if (sum < target) {
					j++;
				} else {
					k--;
				}
			}
			while (i + 1 < num.length && num[i] == num[i + 1])
				i++;
		}
		return closest;
	}

	/**
	 * Given n non-negative integers a1, a2, ..., an, where each represents a
	 * point at coordinate (i, ai). n vertical lines are drawn such that the two
	 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which
	 * together with x-axis forms a container, such that the container contains
	 * the most water.
	 * 
	 * Note: You may not slant the container.
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int start = 0;
		int end = height.length - 1;
		Integer max = null;
		while (start < end) {
			int area = Math.min(height[start], height[end]) * (end - start);
			if (max == null || area > max)
				max = area;
			if (height[start] < height[end])
				start++;
			else {
				end--;
			}
		}
		return max;
	}
}
