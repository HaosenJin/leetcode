package leetcode.DPAndRecurrsive;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Combinations {
	/**
	 * Given two integers n and k, return all possible combinations of k numbers
	 * out of 1 ... n.
	 * 
	 * For example, If n = 4 and k = 2, a solution is:
	 * 
	 * [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> combinations = new LinkedList<List<Integer>>();
		Stack<Integer> stack = new Stack<Integer>();
		return combine(n, k, 1, combinations, stack);
	}

	@SuppressWarnings("unchecked")
	private static List<List<Integer>> combine(int n, int k, int level,
			List<List<Integer>> combinations, Stack<Integer> stack) {
		if (k == 0 || n == 0) {
			combinations.add(new LinkedList<Integer>());
			return combinations;
		}
		
		if (stack.size() == k) {	
			combinations.add((Stack<Integer>)stack.clone());
		}

		for (int i = level; i <= n; i++) {
			stack.push(i);
			combine(n, k, i + 1, combinations, stack);
			stack.pop();
		}
		return combinations;
	}
}
