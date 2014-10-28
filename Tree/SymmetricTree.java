package leetcode.Tree;

import java.util.LinkedList;
import java.util.List;

public class SymmetricTree {
	public static boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		List<TreeNode> level = new LinkedList<TreeNode>();
		List<TreeNode> next_level;
		level.add(root);
		while (level.size() > 0) {
			if (!isSymmetric(level))
				return false;
			next_level = new LinkedList<TreeNode>();
			for (TreeNode node : level) {
				if (node.left != null)
					next_level.add(node.left);
				if (node.right != null)
					next_level.add(node.right);
			}
			level = next_level;
		}
		return true;
	}

	private static boolean isSymmetric(List<TreeNode> level) {
		if (level.size() % 2 != 0 && level.size() != 1)
			return false;
		if (level.size() == 1) {
			TreeNode node = level.get(0);
			if (node.left != null && node.right != null
					&& node.left.val != node.right.val)
				return false;
			if (node.left == null && node.right != null || node.left != null
					&& node.right == null)
				return false;
		} else {
			int start = 0;
			int end = level.size() - 1;
			while (start < end) {
				if (!isSymmetric(level.get(start), level.get(end)))
					return false;
				start++;
				end--;
			}
		}
		return true;
	}

	private static boolean isSymmetric(TreeNode node1, TreeNode node2) {
		if (node1.left != null && node2.right != null) {
			if (node1.left.val != node2.right.val)
				return false;
		} else if (node1.left == null && node2.right != null
				|| node1.left != null && node2.right == null) {
			return false;
		}
		if (node1.right != null && node2.left != null) {
			if (node1.right.val != node2.left.val)
				return false;
		} else if (node1.right == null && node2.left != null
				|| node1.right != null && node2.left == null) {
			return false;
		}
		return true;
	}
}
