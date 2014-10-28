package leetcode.DPAndRecurrsive;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CombinationSum {
	/**
	 * Given a set of candidate numbers (C) and a target number (T), find all
	 * unique combinations in C where the candidate numbers sums to T.
	 * 
	 * The same repeated number may be chosen from C unlimited number of times.
	 * 
	 * Note: All numbers (including target) will be positive integers. Elements
	 * in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie,
	 * a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak). The solution set must not contain duplicate
	 * combinations. For example, given candidate set 2,3,6,7 and target 7, A
	 * solution set is: [7] [2, 2, 3]
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSum(int[] candidates,
			int target) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		Stack<Integer> solution = new Stack<Integer>();
		Arrays.sort(candidates);
		combinationSum(candidates, target, result, solution, 0);
		return result;
	}

	@SuppressWarnings("unchecked")
	private static void combinationSum(int[] candidates, int target,
			List<List<Integer>> result, Stack<Integer> solution, int level) {
		if (target == 0) {
			result.add((Stack<Integer>) solution.clone());
		} else if (target < 0) {
			return;
		} else {
			for (int i = level; i < candidates.length; i++) {
				solution.add(candidates[i]);
				combinationSum(candidates, target - candidates[i], result,
						solution, i);
				solution.pop();
			}
		}
	}

	/**
	 * Given a collection of candidate numbers (C) and a target number (T), find
	 * all unique combinations in C where the candidate numbers sums to T.
	 * 
	 * Each number in C may only be used once in the combination.
	 * 
	 * Note: All numbers (including target) will be positive integers. Elements
	 * in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie,
	 * a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak). The solution set must not contain duplicate
	 * combinations. For example, given candidate set 10,1,2,7,6,1,5 and target
	 * 8, A solution set is: [1, 7] [1, 2, 5] [2, 6] [1, 1, 6]
	 * 
	 * @param num
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSum2(int[] num, int target) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		Stack<Integer> solution = new Stack<Integer>();
		Arrays.sort(num);
		combinationSum2(num, target, result, solution, 0);
		return result;
	}

	@SuppressWarnings("unchecked")
	private static void combinationSum2(int[] candidates, int target,
			List<List<Integer>> result, Stack<Integer> solution, int level) {
		if (target == 0) {
			result.add((Stack<Integer>) solution.clone());
		} else if (target < 0) {
			return;
		} else {
			for (int i = level; i < candidates.length; i++) {
				solution.add(candidates[i]);
				combinationSum2(candidates, target - candidates[i], result,
						solution, i + 1);
				solution.pop();
				while (i + 1 < candidates.length
						&& candidates[i] == candidates[i + 1])
					i++;
			}
		}
	}

}
