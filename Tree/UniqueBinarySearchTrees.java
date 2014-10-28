package leetcode.Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Haosen
 * 
 */
public class UniqueBinarySearchTrees {
	/**
	 * Given n, how many structurally unique BST's (binary search trees) that
	 * store values 1...n?
	 * 
	 * For example, Given n = 3, there are a total of 5 unique BST's.
	 * 
	 * @param n
	 * @return
	 */
	public static int numTrees(int n) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		return calculate(n, map);
	}

	private static int calculate(int n, Map<Integer, Integer> map) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (map.containsKey(n))
			return map.get(n);
		int num = 0;
		for (int i = 1; i <= n; i++) {
			int left = calculate(i - 1, map);
			int right = calculate(n - i, map);
			if (left != 0 && right != 0)
				num += left * right;
			if (left == 0)
				num += right;
			if (right == 0)
				num += left;
		}
		map.put(n, num);
		return num;
	}

	/**
	 * Given n, generate all structurally unique BST's (binary search trees)
	 * that store values 1...n.
	 * 
	 * @param n
	 * @return
	 */
	public static List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			List<TreeNode> result = new LinkedList<TreeNode>();
			result.add(null);
			return result;
		}
		if (n == 1) {
			List<TreeNode> result = new LinkedList<TreeNode>();
			result.add(new TreeNode(n));
			return result;
		}
		int[] values = new int[n];
		for (int i = 0; i < values.length; i++) {
			values[i] = i + 1;
		}
		Map<String, List<TreeNode>> map = new HashMap<String, List<TreeNode>>();
		return generateTrees(values, 0, values.length - 1, map);
	}

	private static List<TreeNode> generateTrees(int[] vals, int start, int end,
			Map<String, List<TreeNode>> map) {
		if (start > end)
			return null;
		String key = start + "-" + end;
		if (map.containsKey(key))
			return replicateTreeList(map.get(key));
		if (start == end) {
			List<TreeNode> list = new LinkedList<TreeNode>();
			list.add(new TreeNode(vals[start]));
			map.put(key, list);
			return list;
		}

		List<TreeNode> result = new LinkedList<TreeNode>();
		for (int i = start; i <= end; i++) {
			List<TreeNode> left = generateTrees(vals, start, i - 1, map);
			List<TreeNode> right = generateTrees(vals, i + 1, end, map);
			result.addAll(combine(vals[i], left, right));
		}
		map.put(key, result);
		return result;
	}

	private static List<TreeNode> combine(int val, List<TreeNode> leftSubtrees,
			List<TreeNode> rightSubtrees) {
		List<TreeNode> result = new LinkedList<TreeNode>();
		if (leftSubtrees != null && rightSubtrees != null) {
			for (TreeNode left : leftSubtrees) {
				for (TreeNode right : rightSubtrees) {
					TreeNode root = new TreeNode(val);
					root.left = left;
					root.right = right;
					result.add(root);
				}
			}
		} else if (leftSubtrees != null && rightSubtrees == null) {
			for (TreeNode left : leftSubtrees) {
				TreeNode root = new TreeNode(val);
				root.left = left;
				result.add(root);
			}
		} else {
			for (TreeNode right : rightSubtrees) {
				TreeNode root = new TreeNode(val);
				root.right = right;
				result.add(root);
			}
		}
		return result;
	}

	private static List<TreeNode> replicateTreeList(List<TreeNode> trees) {
		List<TreeNode> copy = new LinkedList<TreeNode>();
		for (TreeNode root : trees) {
			copy.add(replicateTree(root));
		}
		return copy;
	}

	private static TreeNode replicateTree(TreeNode node) {
		if (node == null)
			return null;
		TreeNode copy = new TreeNode(node.val);
		copy.left = replicateTree(node.left);
		copy.right = replicateTree(node.right);
		return copy;
	}

}
