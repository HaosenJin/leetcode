package leetcode.LinkedList;

public class AddTwoNumbers {
	/**
	 * You are given two linked lists representing two non-negative numbers. The
	 * digits are stored in reverse order and each of their nodes contain a
	 * single digit. Add the two numbers and return it as a linked list.
	 * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		int carry = 0;
		int sum = 0;
		ListNode head = null;
		ListNode tail = null;
		while (l1 != null && l2 != null) {
			sum = l1.val + l2.val + carry;
			ListNode node = new ListNode(sum % 10);
			if (head == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				tail = tail.next;
			}
			carry = sum / 10;
			l1 = l1.next;
			l2 = l2.next;
		}

		if (l1 != null) {
			while (l1 != null) {
				sum = l1.val + carry;
				ListNode node = new ListNode(sum % 10);
				tail.next = node;
				tail = tail.next;
				carry = sum / 10;
				l1 = l1.next;
			}
		}

		if (l2 != null) {
			while (l2 != null) {
				sum = l2.val + carry;
				ListNode node = new ListNode(sum % 10);
				tail.next = node;
				tail = tail.next;
				carry = sum / 10;
				l2 = l2.next;
			}
		}

		if (carry != 0) {
			ListNode node = new ListNode(carry);
			tail.next = node;
			tail = tail.next;
		}
		return head;
	}
}
