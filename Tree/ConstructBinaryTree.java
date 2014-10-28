package leetcode.Tree;

public class ConstructBinaryTree {
	/**
	 * Given inorder and postorder traversal of a tree, construct the binary
	 * tree.
	 * 
	 * Note: You may assume that duplicates do not exist in the tree.
	 * 
	 * @param inorder
	 * @param postorder
	 * @return
	 */
	public static TreeNode buildTree(int[] inorder, int[] postorder) {
		return buildHelper(inorder, 0, inorder.length - 1, postorder, 0,
				postorder.length - 1);
	}

	private static TreeNode buildHelper(int[] inorder, int inorder_start,
			int inorder_end, int[] postorder, int postorder_start,
			int postorder_end) {
		if (inorder_start > inorder_end || postorder_start > postorder_end)
			return null;
		if (inorder_end == inorder_start)
			return new TreeNode(inorder[inorder_start]);
		TreeNode node = new TreeNode(postorder[postorder_end]);
		int pivot = -1;
		for (int i = inorder_start; i <= inorder_end; i++) {
			if (inorder[i] == postorder[postorder_end]) {
				pivot = i - inorder_start;
				break;
			}
		}
		node.left = buildHelper(inorder, inorder_start, inorder_start + pivot
				- 1, postorder, postorder_start, postorder_start + pivot - 1);
		node.right = buildHelper(inorder, inorder_start + pivot + 1,
				inorder_end, postorder, postorder_start + pivot,
				postorder_end - 1);
		return node;
	}

	/**
	 * Given preorder and inorder traversal of a tree, construct the binary
	 * tree.
	 * 
	 * Note: You may assume that duplicates do not exist in the tree.
	 * 
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public static TreeNode buildTree2(int[] preorder, int[] inorder) {
		return buildHelper2(inorder, 0, inorder.length - 1, preorder, 0,
				preorder.length - 1);
	}

	private static TreeNode buildHelper2(int[] inorder, int inorder_start,
			int inorder_end, int[] preorder, int preorder_start,
			int preorder_end) {
		if (inorder_start > inorder_end || preorder_start > preorder_end)
			return null;
		if (inorder_end == inorder_start)
			return new TreeNode(inorder[inorder_start]);
		TreeNode node = new TreeNode(preorder[preorder_start]);
		int pivot = -1;
		for (int i = inorder_start; i <= inorder_end; i++) {
			if (inorder[i] == preorder[preorder_start]) {
				pivot = i - inorder_start;
				break;
			}
		}
		node.left = buildHelper2(inorder, inorder_start, inorder_start + pivot
				- 1, preorder, preorder_start + 1, preorder_start + pivot);
		node.right = buildHelper2(inorder, inorder_start + pivot + 1,
				inorder_end, preorder, preorder_start + pivot + 1, preorder_end);
		return node;
	}
}
