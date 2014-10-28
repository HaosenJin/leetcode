package leetcode.LinkedList;

public class LinkedListCycle {
	/**
	 * Given a linked list, determine if it has a cycle in it.
	 * 
	 * Follow up: Can you solve it without using extra space?
	 * 
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
		ListNode p = head;
		ListNode q = head;
		while (q != null && q.next != null) {
			p = p.next;
			q = q.next.next;
			if (p == q)
				return true;
		}
		return false;
	}

	/**
	 * Given a linked list, return the node where the cycle begins. If there is
	 * no cycle, return null.
	 * 
	 * Follow up: Can you solve it without using extra space?
	 * 
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {
		ListNode p = head;
		ListNode q = head;
		while (q != null && q.next != null) {
			p = p.next;
			q = q.next.next;
			if (p == q)
				break;
		}

		if (q == null || q.next == null)
			return null;
		p = head;
		while (p != q) {
			p = p.next;
			q = q.next;
		}
		return p;
	}
}
