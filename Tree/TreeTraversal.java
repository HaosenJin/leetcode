package leetcode.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeTraversal {
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new LinkedList<Integer>();
		if (root != null) {
			result.addAll(postorderTraversal(root.left));
			result.addAll(postorderTraversal(root.right));
			result.add(root.val);
		}
		return result;
	}

	public static List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new LinkedList<Integer>();
		if (root != null) {
			result.add(root.val);
			result.addAll(preorderTraversal(root.left));
			result.addAll(preorderTraversal(root.right));

		}
		return result;
	}

	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new LinkedList<Integer>();		
		if (root != null) {
			Stack<TreeNode> stack = new Stack<TreeNode>();
			TreeNode node = root;
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			while (!stack.isEmpty()) {
				node = stack.pop();
				result.add(node.val);
				node = node.right;
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
		}
		return result;
	}
}
