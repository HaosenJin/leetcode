package leetcode.Tree;

public class MinimumDepth {
	/**
	 * Given a binary tree, find its minimum depth.
	 * 
	 * The minimum depth is the number of nodes along the shortest path from the
	 * root node down to the nearest leaf node.
	 * 
	 * @param root
	 * @return
	 */
	public static int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left != null && root.right != null)
			return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
		else if (root.left == null && root.right == null)
			return 1;
		else if (root.left == null) {
			return minDepth(root.right) + 1;
		} else {
			return minDepth(root.left) + 1;
		}
	}
}
