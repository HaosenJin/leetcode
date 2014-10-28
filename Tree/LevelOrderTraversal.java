package leetcode.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LevelOrderTraversal {
	/**
	 * Given a binary tree, return the bottom-up level order traversal of its
	 * nodes' values. (ie, from left to right, level by level from leaf to
	 * root).
	 * 
	 * For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7
	 * return its bottom-up level order traversal as: [ [15,7], [9,20], [3] ]
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		if (root == null)
			return result;
		List<TreeNode> level = new LinkedList<TreeNode>();
		List<TreeNode> next_level;
		level.add(root);
		while (level.size() > 0) {
			addLevel(result, level);
			next_level = new LinkedList<TreeNode>();
			for (TreeNode node : level) {
				if (node.left != null)
					next_level.add(node.left);
				if (node.right != null)
					next_level.add(node.right);
			}
			level = next_level;
		}
		return result;
	}

	private void addLevel(List<List<Integer>> result, List<TreeNode> level) {
		List<Integer> list = new LinkedList<Integer>();
		for (TreeNode node : level) {
			list.add(node.val);
		}
		result.add(0, list);
	}

	private void addLevel2(List<List<Integer>> result, List<TreeNode> level) {
		List<Integer> list = new LinkedList<Integer>();
		for (TreeNode node : level) {
			list.add(node.val);
		}
		result.add(list);
	}

	/**
	 * iven a binary tree, return the zigzag level order traversal of its nodes'
	 * values. (ie, from left to right, then right to left for the next level
	 * and alternate between).
	 * 
	 * For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7
	 * return its zigzag level order traversal as: [ [3], [20,9], [15,7] ]
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		if (root == null)
			return result;
		List<TreeNode> level = new LinkedList<TreeNode>();
		List<TreeNode> next_level;
		level.add(root);
		boolean rightToLeft = true;
		while (level.size() > 0) {
			rightToLeft = !rightToLeft;
			addLevel3(result, level, rightToLeft);
			next_level = new LinkedList<TreeNode>();
			for (TreeNode node : level) {
				if (node.left != null)
					next_level.add(node.left);
				if (node.right != null)
					next_level.add(node.right);
			}
			level = next_level;
		}
		return result;
	}

	private void addLevel3(List<List<Integer>> result, List<TreeNode> level,
			boolean rightToLeft) {
		List<Integer> list = new LinkedList<Integer>();
		if (rightToLeft) {
			Stack<TreeNode> stack = new Stack<TreeNode>();
			for (TreeNode node : level) {
				stack.push(node);
			}
			while (!stack.isEmpty()) {
				list.add(stack.pop().val);
			}
		} else {
			for (TreeNode node : level) {
				list.add(node.val);
			}
		}
		result.add(list);
	}

}
