package leetcode.LinkedList;

public class RotateList {
	/**
	 * Given a list, rotate the list to the right by k places, where k is
	 * non-negative.
	 * 
	 * For example: Given 1->2->3->4->5->NULL and k = 2, return
	 * 4->5->1->2->3->NULL.
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode rotateRight(ListNode head, int n) {
		if (head == null || n == 0 || head.next == null)
			return head;
		ListNode p = head;
		ListNode q = head;
		int length = 0;
		while (p != null) {
			length++;
			p = p.next;
		}
		int shift = n % length;

		p = head;
		while (shift > 0) {
			p = p.next;
			shift--;
		}

		while (p.next != null) {
			q = q.next;
			p = p.next;
		}

		p.next = head;
		head = q.next;
		q.next = null;
		return head;
	}
}
