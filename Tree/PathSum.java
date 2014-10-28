package leetcode.Tree;

import java.util.LinkedList;
import java.util.List;

public class PathSum {
	/**
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf
	 * path such that adding up all the values along the path equals the given
	 * sum.
	 * 
	 * For example: Given the below binary tree and sum = 22,
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public static boolean hasPathSum(TreeNode root, int sum) {
		return hasPathSumHelper(root, sum);
	}

	private static boolean hasPathSumHelper(TreeNode node, int sum) {
		if (node == null)
			return false;
		if (sum == node.val) {
			if (node.left == null && node.right == null)
				return true;
		}
		boolean leftResult = hasPathSumHelper(node.left, sum - node.val);
		boolean rightResult = hasPathSumHelper(node.right, sum - node.val);
		return rightResult || leftResult;
	}

	/**
	 * @param root
	 * @param sum
	 * @return
	 */
	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
		int height = findTreeHeight(root);
		int[] path = new int[height];
		List<List<Integer>> pathList = new LinkedList<List<Integer>>();
		pathSumHelper(root, sum, path, 0, pathList);
		return pathList;
	}

	private static int findTreeHeight(TreeNode node) {
		if (node == null)
			return 0;
		return Math.max(findTreeHeight(node.left), findTreeHeight(node.right)) + 1;
	}

	private static void pathSumHelper(TreeNode node, int sum, int[] path,
			int index, List<List<Integer>> pathList) {
		if (node == null)
			return;
		path[index] = node.val;
		if (sum == node.val) {
			if (node.left == null && node.right == null) {
				List<Integer> list = new LinkedList<Integer>();
				for (int i = 0; i <= index; i++) {
					list.add(path[i]);
				}
				pathList.add(list);
			}
		}
		pathSumHelper(node.left, sum - node.val, path, index + 1, pathList);
		pathSumHelper(node.right, sum - node.val, path, index + 1, pathList);
	}

}
