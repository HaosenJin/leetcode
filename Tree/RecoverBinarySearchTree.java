package leetcode.Tree;

public class RecoverBinarySearchTree {
	/**
	 * Two elements of a binary search tree (BST) are swapped by mistake.
	 * 
	 * Recover the tree without changing its structure.
	 * 
	 * Note: A solution using O(n) space is pretty straight forward. Could you
	 * devise a constant space solution? confused what "{1,#,2,3}" means? > read
	 * more on how binary tree is serialized on OJ.
	 * 
	 * @param root
	 */
	public void recoverTree(TreeNode root) {
		if (root == null)
			return;
		inorder(root);
		if (firstErrorNode != null) {
			int temp = firstErrorNode.val;
			firstErrorNode.val = afterErrorNode.val;
			afterErrorNode.val = temp;
		}
	}

	private TreeNode firstErrorNode = null;
	private TreeNode afterErrorNode = null;
	private TreeNode prevNode = null;

	private void inorder(TreeNode node) {
		if (node == null)
			return;
		inorder(node.left);
		if (prevNode != null && prevNode.val > node.val) {
			if (firstErrorNode == null) {
				firstErrorNode = prevNode;
				afterErrorNode = node;
			} else {
				int temp = firstErrorNode.val;
				firstErrorNode.val = node.val;
				node.val = temp;
				firstErrorNode = null;
				return;
			}
		}
		prevNode = node;
		inorder(node.right);
	}
}
