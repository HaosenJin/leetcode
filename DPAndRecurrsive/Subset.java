package leetcode.DPAndRecurrsive;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Subset {
	/**
	 * Given a collection of integers that might contain duplicates, S, return
	 * all possible subsets.
	 * 
	 * Note: Elements in a subset must be in non-descending order. The solution
	 * set must not contain duplicate subsets. For example, If S = [1,2,2], a
	 * solution is:
	 * 
	 * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
	 * 
	 * @param num
	 * @return
	 */
	public static List<List<Integer>> subsetsWithDup(int[] num) {
		Arrays.sort(num);
		return getSubsets(num, 0);
	}

	private static List<List<Integer>> getSubsets(int[] num, int index) {
		List<List<Integer>> subsets = new LinkedList<List<Integer>>();
		if (index == num.length) {
			subsets.add(new LinkedList<Integer>());
			return subsets;
		}
		List<List<Integer>> oldSubsets = getSubsets(num, index + 1);

		for (List<Integer> oldSubset : oldSubsets) {
			List<Integer> newSubset = new LinkedList<Integer>();
			newSubset.addAll(oldSubset);
			subsets.add(newSubset);
		}

		for (List<Integer> list : oldSubsets) {
			List<Integer> newSubset = new LinkedList<Integer>();
			newSubset.add(num[index]);
			newSubset.addAll(list);
			if (!contains(subsets, newSubset)) {
				subsets.add(newSubset);
			}
		}
		return subsets;
	}

	private static boolean contains(List<List<Integer>> subsets,
			List<Integer> subset) {
		for (List<Integer> sub : subsets) {
			if (sub.equals(subset))
				return true;
		}
		return false;
	}

	/**
	 * Given a set of distinct integers, S, return all possible subsets.
	 * 
	 * Note: Elements in a subset must be in non-descending order. The solution
	 * set must not contain duplicate subsets.
	 * 
	 * @param S
	 * @return
	 */
	public List<List<Integer>> subsets(int[] S) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		Stack<Integer> subset = new Stack<Integer>();
		generate(result, subset, S, 0);
		return result;
	}

	@SuppressWarnings("unchecked")
	private void generate(List<List<Integer>> result, Stack<Integer> subset,
			int[] S, int index) {
		for (int i = index; i < S.length; i++) {
			subset.push(S[i]);
			result.add((Stack<Integer>) subset.clone());
			generate(result, subset, S, i + 1);
			subset.pop();
		}
	}

	public List<List<Integer>> subsetsUnique(int[] S) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		Stack<Integer> subset = new Stack<Integer>();
		Arrays.sort(S);
		generateUnique(result, subset, S, 0);
		return result;
	}

	@SuppressWarnings("unchecked")
	private void generateUnique(List<List<Integer>> result,
			Stack<Integer> subset, int[] S, int index) {
		for (int i = index; i < S.length; i++) {
			subset.push(S[i]);
			result.add((Stack<Integer>) subset.clone());
			generate(result, subset, S, i + 1);
			subset.pop();
			while (i < S.length && S[i + 1] == S[i]) {
				i++;
			}
		}
	}
}
