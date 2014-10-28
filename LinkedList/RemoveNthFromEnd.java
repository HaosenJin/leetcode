package leetcode.LinkedList;

public class RemoveNthFromEnd {
	/**
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		int length = 0;
		ListNode p = head;
		while (p != null) {
			length++;
			p = p.next;
		}

		if (length < n)
			return head;

		if (length == n)
			return head.next;
		p = head;
		int i = 1;
		while (i < length - n) {
			p = p.next;
			i++;
		}
		p.next = p.next.next;

		return head;
	}
}
