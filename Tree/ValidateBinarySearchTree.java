package leetcode.Tree;

public class ValidateBinarySearchTree {
	/**
	 * Given a binary tree, determine if it is a valid binary search tree (BST).
	 * 
	 * Assume a BST is defined as follows:
	 * 
	 * The left subtree of a node contains only nodes with keys less than the
	 * node's key. The right subtree of a node contains only nodes with keys
	 * greater than the node's key. Both the left and right subtrees must also
	 * be binary search trees.
	 * 
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		return validateBST(root, null, null);
	}

	private boolean validateBST(TreeNode node, Integer low, Integer high) {
		if (node == null)
			return true;
		if (low != null && node.val <= low)
			return false;
		if (high != null && node.val >= high)
			return false;
		return validateBST(node.left, low, node.val)
				&& validateBST(node.right, node.val, high);
	}
}
