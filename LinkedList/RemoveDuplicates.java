package leetcode.LinkedList;

public class RemoveDuplicates {
	/**
	 * Given a sorted linked list, delete all duplicates such that each element
	 * appear only once.
	 * 
	 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return
	 * 1->2->3.
	 * 
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode current = head;
		ListNode runner = head;
		while (current != null) {
			while (runner.next != null) {
				if (runner.next.val == current.val)
					runner.next = runner.next.next;
				else {
					runner = runner.next;
				}
			}
			current = current.next;
			runner = current;
		}
		return head;
	}

	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers,
	 * leaving only distinct numbers from the original list.
	 * 
	 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given
	 * 1->1->1->2->3, return 2->3.
	 * 
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates2(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode prev_num = null;
		ListNode next_num = head;
		while (next_num != null && next_num.next != null) {
			if (next_num.next.val != next_num.val) {
				prev_num = next_num;
				next_num = next_num.next;
			} else {
				while (next_num.next.next != null
						&& next_num.next.next.val == next_num.next.val) {
					next_num.next.next = next_num.next.next.next;
				}
				if (prev_num == null) {
					head = next_num.next.next;
				} else {
					prev_num.next = next_num.next.next;
				}
				next_num = next_num.next.next;
			}
		}
		return head;
	}
}
