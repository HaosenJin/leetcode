package leetcode.DPAndRecurrsive;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LongestConsecutiveSequence {
	/**
	 * Given an unsorted array of integers, find the length of the longest
	 * consecutive elements sequence.
	 * 
	 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive
	 * elements sequence is [1, 2, 3, 4]. Return its length: 4.
	 * 
	 * Your algorithm should run in O(n) complexity.
	 * 
	 * @param num
	 * @return
	 */
	public static int longestConsecutive(int[] num) {
		if (num == null)
			return 0;
		if (num.length == 1)
			return 1;
		Map<Integer, LinkedList<Integer>> head = new HashMap<Integer, LinkedList<Integer>>();
		Map<Integer, LinkedList<Integer>> tail = new HashMap<Integer, LinkedList<Integer>>();
		for (int i = 0; i < num.length; i++) {
			int n = num[i];
			if (head.containsKey(n) || tail.containsKey(n))
				continue;
			if (head.containsKey(n + 1) && tail.containsKey(n - 1)) {
				merge(head, tail, n);
			} else if (head.containsKey(n + 1)) {
				preAppend(head, n);
			} else if (tail.containsKey(n - 1)) {
				append(tail, n);
			} else {
				LinkedList<Integer> list = new LinkedList<Integer>();
				list.add(n);
				head.put(n, list);
				tail.put(n, list);
			}
		}
		int max = Integer.MIN_VALUE;
		for (LinkedList<Integer> list : head.values()) {
			if (list.size() > max)
				max = list.size();
		}
		return max;
	}

	private static void merge(Map<Integer, LinkedList<Integer>> head,
			Map<Integer, LinkedList<Integer>> tail, int n) {
		LinkedList<Integer> pre_list = tail.get(n - 1);
		LinkedList<Integer> post_list = head.get(n + 1);
		pre_list.add(n);
		pre_list.addAll(post_list);
		tail.remove(n - 1);
		head.remove(n + 1);
		tail.put(pre_list.getLast(), pre_list);
	}

	private static void preAppend(Map<Integer, LinkedList<Integer>> head, int n) {
		LinkedList<Integer> list = head.get(n + 1);
		list.addFirst(n);
		head.remove(n + 1);
		head.put(n, list);
	}

	private static void append(Map<Integer, LinkedList<Integer>> tail, int n) {
		LinkedList<Integer> list = tail.get(n - 1);
		list.add(n);
		tail.remove(n - 1);
		tail.put(n, list);
	}
}
