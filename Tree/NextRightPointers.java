package leetcode.Tree;

public class NextRightPointers {
	/**
	 * Populate each next pointer to point to its next right node. If there is
	 * no next right node, the next pointer should be set to NULL.
	 * 
	 * Initially, all next pointers are set to NULL.
	 * 
	 * Note:
	 * 
	 * You may only use constant extra space. You may assume that it is a
	 * perfect binary tree (ie, all leaves are at the same level, and every
	 * parent has two children).
	 * 
	 * For example, Given the following perfect binary tree,
	 * 
	 * ------1
	 * 
	 * -----/-\
	 * 
	 * ----2---3
	 * 
	 * ---/ \--/ \
	 * 
	 * --4--5-6--7
	 * 
	 * After calling your function, the tree should look like:
	 * 
	 * ------1->NULL
	 * 
	 * -----/-\
	 * 
	 * ----2->3->NULL
	 * 
	 * ---/ \--/ \
	 * 
	 * --4->5->6->7->NULL
	 * 
	 * @param root
	 */
	public void connect(TreeLinkNode root) {
		if (root == null)
			return;
		if (root.left == null && root.right == null)
			return;
		root.left.next = root.right;
		if (root.next != null) {
			root.right.next = root.next.left;
		}
		connect(root.left);
		connect(root.right);
	}

	/**
	 * Follow up for problem "Populating Next Right Pointers in Each Node".
	 * 
	 * What if the given tree could be any binary tree? Would your previous
	 * solution still work?
	 * 
	 * @param root
	 */
	public static void connect2(TreeLinkNode root) {
		TreeLinkNode levelHead = root;
		while (levelHead != null) {
			levelHead = connectLevel(levelHead);
		}
	}

	private static TreeLinkNode connectLevel(TreeLinkNode LevelHead) {
		if (LevelHead == null)
			return null;
		TreeLinkNode current = LevelHead;
		TreeLinkNode nextLevelHead = null;
		while (current != null) {
			if (current.left == null && current.right == null) {
				current = current.next;
				continue;
			}
			if (current.left != null && current.right != null) {
				current.left.next = current.right;
			}
			if (nextLevelHead == null) {
				nextLevelHead = current.left != null ? current.left
						: current.right;
			}
			connectCousinHelper(current, current.right != null ? current.right
					: current.left);
			current = current.next;
		}
		return nextLevelHead;
	}

	private static void connectCousinHelper(TreeLinkNode parent,
			TreeLinkNode child) {
		TreeLinkNode next = parent.next;
		while (next != null && next.left == null && next.right == null) {
			next = next.next;
		}
		if (next != null) {
			child.next = next.left == null ? next.right : next.left;
		}
	}
}
