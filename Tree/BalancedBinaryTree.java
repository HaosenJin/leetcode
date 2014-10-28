package leetcode.Tree;

public class BalancedBinaryTree {
	/**
	 * Given a binary tree, determine if it is height-balanced.
	 * 
	 * For this problem, a height-balanced binary tree is defined as a binary
	 * tree in which the depth of the two subtrees of every node never differ by
	 * more than 1.
	 * 
	 * @param root
	 * @return
	 */
	public static boolean isBalanced(TreeNode root) {
		if (height(root) == -1)
			return false;
		return true;
	}

	private static int height(TreeNode node) {
		if (node == null)
			return 0;
		int left = height(node.left);
		if (left == -1)
			return -1;
		int right = height(node.right);
		if (right == -1)
			return -1;

		if (Math.abs(left - right) > 1)
			return -1;
		return Math.max(left, right) + 1;
	}
}
