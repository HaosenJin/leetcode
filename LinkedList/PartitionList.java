package leetcode.LinkedList;

public class PartitionList {
	/**
	 * Given a linked list and a value x, partition it such that all nodes less
	 * than x come before nodes greater than or equal to x.
	 * 
	 * You should preserve the original relative order of the nodes in each of
	 * the two partitions.
	 * 
	 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public static ListNode partition(ListNode head, int x) {
		if (head == null)
			return null;
		ListNode p = head;
		ListNode q = null;
		while (p != null) {
			if (p.val >= x) {
				break;
			}
			q = p;
			p = p.next;
		}

		if (p == null)
			return head;
		ListNode r = p;
		while (r.next != null) {
			if (r.next.val < x) {
				ListNode temp = r.next;
				r.next = r.next.next;
				temp.next = p;
				if (q == null) {
					head = temp;
				} else {
					q.next = temp;
				}
				q = temp;
			} else {
				r = r.next;
			}
		}
		return head;
	}
}
