package leetcode.Tree;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumPathSum {
	/**
	 * Given a binary tree, find the maximum path sum.
	 * 
	 * The path may start and end at any node in the tree.
	 * 
	 * For example: Given the below binary tree,
	 * 
	 * 1 / \ 2 3 Return 6.
	 * 
	 * @param root
	 * @return
	 */
	public static int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		};
		PriorityQueue<Integer> maxSumHeap = new PriorityQueue<Integer>(11,
				comparator);
		maxSumToNode(maxSumHeap, root);
		return maxSumHeap.peek();
	}

	private static int maxSumToNode(PriorityQueue<Integer> heap, TreeNode node) {
		if (node == null)
			return 0;
		int leftMax = maxSumToNode(heap, node.left);
		int rightMax = maxSumToNode(heap, node.right);
		if (leftMax < 0)
			leftMax = 0;
		if (rightMax < 0)
			rightMax = 0;
		heap.add(leftMax + rightMax + node.val);
		return node.val + (leftMax > rightMax ? leftMax : rightMax);
	}
}
