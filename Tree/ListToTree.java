package leetcode.Tree;

public class ListToTree {
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int x) {
			val = x;
			next = null;
		}
	}

	/**
	 * Given a singly linked list where elements are sorted in ascending order,
	 * convert it to a height balanced BST.
	 * 
	 * @param head
	 * @return
	 */
	public TreeNode sortedListToBST(ListNode head) {
		return convert(head, null);
	}

	private TreeNode convert(ListNode head, ListNode tail) {
		if (head == null || head == tail)
			return null;
		ListNode runner = head;
		ListNode pivot = head;
		while (runner != tail && runner.next != tail) {
			pivot = pivot.next;
			runner = runner.next.next;
		}
		TreeNode node = new TreeNode(pivot.val);
		node.left = convert(head, pivot);
		node.right = convert(pivot.next, tail);
		return node;
	}

	public TreeNode sortedArrayToBST(int[] num) {
		return convert2(num, 0, num.length - 1);
	}

	private TreeNode convert2(int[] num, int start, int end) {
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(num[mid]);
		node.left = convert2(num, start, mid - 1);
		node.right = convert2(num, mid + 1, end);
		return node;
	}
}
