package leetcode.DPAndRecurrsive;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PermutationSequence {

	/**
	 * Given a collection of numbers, return all possible permutations.
	 * 
	 * For example, [1,2,3] have the following permutations: [1,2,3], [1,3,2],
	 * [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	 * 
	 * @param num
	 * @return
	 */
	public List<List<Integer>> permute(int[] num) {
		return permuteHelper(num, 0);
	}

	private List<List<Integer>> permuteHelper(int[] num, int level) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		if (level == num.length) {
			result.add(new LinkedList<Integer>());
			return result;
		}

		int current = num[level];
		List<List<Integer>> sub_result = permuteHelper(num, level + 1);
		for (List<Integer> list : sub_result) {
			for (int position = 0; position <= list.size(); position++) {
				int k = 0;
				List<Integer> perm = new LinkedList<Integer>();
				for (int i = 0; i <= list.size(); i++) {
					if (i == position) {
						perm.add(current);
					} else {
						perm.add(list.get(k));
						k++;
					}
				}
				result.add(perm);
			}
		}
		return result;
	}

	public static List<List<Integer>> permuteUnique(int[] num) {
		int[] visited = new int[num.length];
		Arrays.sort(num);
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		Stack<Integer> perm = new Stack<Integer>();
		permuteHelper2(result, num, visited, perm, 0);
		return result;
	}

	@SuppressWarnings("unchecked")
	private static void permuteHelper2(List<List<Integer>> result, int[] num,
			int[] visited, Stack<Integer> perm, int level) {
		if (level >= num.length) {
			result.add((Stack<Integer>) perm.clone());
		}
		for (int i = 0; i < num.length; i++) {
			if (visited[i] == 0) {
				if (i > 0 && num[i] == num[i - 1] && visited[i - 1] == 0)
					continue;
				perm.push(num[i]);
				visited[i] = 1;
				permuteHelper2(result, num, visited, perm, level + 1);
				perm.pop();
				visited[i] = 0;
			}
		}
	}

	/**
	 * The set [1,2,3,¡­,n] contains a total of n! unique permutations.
	 * 
	 * By listing and labeling all of the permutations in order, We get the
	 * following sequence (ie, for n = 3):
	 * 
	 * "123" "132" "213" "231" "312" "321" Given n and k, return the kth
	 * permutation sequence.
	 * 
	 * Note: Given n will be between 1 and 9 inclusive.
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public String getPermutation(int n, int k) {
		int[] nums = new int[n];
		int perms = 1;
		for (int i = 0; i < n; i++) {
			nums[i] = i + 1;
			perms = perms * (i + 1);
		}
		k--;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			perms = perms / (n - i);
			int choose = k / perms;
			sb.append(nums[choose]);
			for (int j = choose; j < n - i; j++) {
				nums[j] = nums[j + 1];
			}
			k = k % perms;
		}
		return sb.toString();
	}

	/**
	 * Implement next permutation, which rearranges numbers into the
	 * lexicographically next greater permutation of numbers.
	 * 
	 * If such arrangement is not possible, it must rearrange it as the lowest
	 * possible order (ie, sorted in ascending order).
	 * 
	 * The replacement must be in-place, do not allocate extra memory.
	 * 
	 * Here are some examples. Inputs are in the left-hand column and its
	 * corresponding outputs are in the right-hand column.
	 * 
	 * 1,2,3 ¡ú 1,3,2
	 * 
	 * 3,2,1 ¡ú 1,2,3
	 * 
	 * 1,1,5 ¡ú 1,5,1
	 * 
	 * @param num
	 */
	public static void nextPermutation(int[] num) {
		int[] order = createOrder(num);
		double perm = 1;
		for (int i = 0; i < order.length; i++) {
			perm = perm * (i + 1);
		}

		// calculate current permutation's position in the permutation sequence
		double current = 0;
		double temp = perm;
		for (int i = 0; i < num.length; i++) {
			int n = getOrder(order, num[i]);
			temp = temp / (num.length - i);
			current += n * temp;
		}

		// generate next permutation
		double next = current + 1;
		if (next == perm)
			next = 0;
		order = createOrder(num);
		temp = perm;
		for (int i = 0; i < num.length; i++) {
			temp = temp / (num.length - i);
			int choose = (int) (next / temp);
			next = next % temp;
			num[i] = order[choose];
			for (int j = choose; j < order.length - 1; j++) {
				order[j] = order[j + 1];
			}
		}
	}

	private static int[] createOrder(int[] num) {
		int[] order = new int[num.length];
		for (int i = 0; i < num.length; i++) {
			order[i] = num[i];
		}
		Arrays.sort(order);
		return order;
	}

	private static int getOrder(int[] order, int target) {
		int position = 0;
		for (int i = 0; i < order.length; i++) {
			if (order[i] == target) {
				position = i;
				break;
			}
		}
		for (int i = position; i < order.length - 1; i++) {
			order[i] = order[i + 1];
		}
		return position;
	}

	public static void nextPermutation2(int[] num) {
		int pivot = -1;
		for (int i = num.length - 1; i >= 0; i--) {
			if (i < num.length - 1 && num[i] < num[i + 1]) {
				pivot = i;
				break;
			}
		}
		if (pivot == -1)
			reverseArray(num, 0, num.length - 1);
		else {
			int swap_position = -1;
			for (int i = num.length - 1; i > pivot; i--) {
				if (num[i] > num[pivot]) {
					swap_position = i;
					break;
				}
			}

			int temp = num[pivot];
			num[pivot] = num[swap_position];
			num[swap_position] = temp;
			reverseArray(num, pivot + 1, num.length - 1);
		}
	}

	private static void reverseArray(int[] num, int start, int end) {
		if (start < 0 || end >= num.length || start >= end)
			return;
		while (start < end) {
			int temp = num[start];
			num[start] = num[end];
			num[end] = temp;
			start++;
			end--;
		}
	}

}
