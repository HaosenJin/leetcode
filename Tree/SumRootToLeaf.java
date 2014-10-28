package leetcode.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SumRootToLeaf {
	/**
	 * 
	 * Given a binary tree containing digits from 0-9 only, each root-to-leaf
	 * path could represent a number.
	 * 
	 * An example is the root-to-leaf path 1->2->3 which represents the number
	 * 123.
	 * 
	 * Find the total sum of all root-to-leaf numbers.
	 * 
	 * For example,
	 * 
	 * --1--------------------------------------------------
	 * --/\------------------------------------------------------------------
	 * -2--3----------------------------------------------------------The
	 * root-to-leaf path 1->2 represents the number 12. The root-to-leaf path
	 * 1->3 represents the number 13.
	 * 
	 * Return the sum = 12 + 13 = 25.
	 * 
	 * @param root
	 * @return
	 */
	public static int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return root.val;
		List<Integer> nums = new LinkedList<Integer>();
		getNumbers(nums, root, new LinkedList<Integer>());
		int sum = 0;
		for (Integer i : nums) {
			sum += i;
		}
		return sum;
	}

	private static void getNumbers(List<Integer> nums, TreeNode node,
			Queue<Integer> digits) {
		digits.add(node.val);
		if (node.left == null && node.right == null) {
			nums.add(convertToInt(digits));
		} else if (node.left != null && node.right == null) {
			getNumbers(nums, node.left, digits);
		} else if (node.left == null && node.right != null) {
			getNumbers(nums, node.right, digits);
		} else {
			Queue<Integer> copy = new LinkedList<Integer>();
			copy.addAll(digits);
			getNumbers(nums, node.left, digits);
			getNumbers(nums, node.right, copy);
		}
	}

	private static int convertToInt(Queue<Integer> digit_queue) {
		int num = 0;
		while (!digit_queue.isEmpty()) {
			num = num * 10 + digit_queue.poll();
		}
		return num;
	}
}
