package leetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class FlattenBinaryTree {
	/**
	 * @param root
	 */
	public void flatten(TreeNode root) {
		if (root == null)
			return;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		preorderTraversal(root, queue);
		TreeNode prev_node;
		while (!queue.isEmpty()) {
			prev_node = queue.poll();
			prev_node.left = null;
			prev_node.right = queue.isEmpty() ? null : queue.peek();
		}
	}

	private void preorderTraversal(TreeNode node, Queue<TreeNode> queue) {
		if (node != null) {
			queue.add(node);
			preorderTraversal(node.left, queue);
			preorderTraversal(node.right, queue);
		}
	}

}
