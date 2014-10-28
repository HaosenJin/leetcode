package leetcode.LinkedList;

public class ReverseLinkedList {
	/**
	 * Reverse a linked list from position m to n. Do it in-place and in
	 * one-pass.
	 * 
	 * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	 * 
	 * return 1->4->3->2->5->NULL.
	 * 
	 * Note: Given m, n satisfy the following condition: 1 ¡Ü m ¡Ü n ¡Ü length of
	 * list.
	 * 
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		int position = 1;
		ListNode insertPrev = null;
		ListNode insertPosition;
		ListNode deletePosition;
		insertPosition = head;
		while (position < m) {
			position++;
			insertPrev = insertPosition;
			insertPosition = insertPosition.next;
		}
		deletePosition = insertPosition;
		while (position < n) {
			position++;
			ListNode temp = deletePosition.next;
			deletePosition.next = deletePosition.next.next;
			temp.next = insertPosition;
			if (insertPrev != null)
				insertPrev.next = temp;
			insertPosition = temp;
		}
		if (m == 1)
			return insertPosition;
		return head;
	}

	/**
	 * Given a linked list, reverse the nodes of a linked list k at a time and
	 * return its modified list.
	 * 
	 * If the number of nodes is not a multiple of k then left-out nodes in the
	 * end should remain as it is.
	 * 
	 * You may not alter the values in the nodes, only nodes itself may be
	 * changed.
	 * 
	 * Only constant memory is allowed.
	 * 
	 * For example, Given this linked list: 1->2->3->4->5
	 * 
	 * For k = 2, you should return: 2->1->4->3->5
	 * 
	 * For k = 3, you should return: 3->2->1->4->5
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if (k <= 1 || head == null || head.next == null)
			return head;
		int len = 0;
		ListNode p = head;
		while (p != null) {
			len++;
			p = p.next;
		}

		if (len < k)
			return head;

		ListNode group_tail = head;
		ListNode insert_prev = null;
		ListNode insert = head;
		int end = k;
		while (end <= len) {
			int count = 1;
			while (count < k) {
				ListNode temp = group_tail.next;
				group_tail.next = group_tail.next.next;
				temp.next = insert;
				insert = temp;
				if (insert_prev == null) {
					head = temp;
				} else {
					insert_prev.next = temp;
				}
				count++;
			}
			insert_prev = group_tail;
			group_tail = group_tail.next;
			insert = group_tail;
			end += k;
		}
		return head;
	}

	/**
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * 
	 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * Your algorithm should use only constant space. You may not modify the
	 * values in the list, only nodes itself can be changed.
	 * 
	 * @param head
	 * @return
	 */
	public ListNode swapPairs(ListNode head) {
		ListNode p = head;
		ListNode prev = null;
		while (p != null && p.next != null) {
			ListNode temp = p.next;
			p.next = p.next.next;
			temp.next = p;
			if (prev != null)
				prev.next = temp;
			else
				head = temp;
			prev = p;
			p = p.next;
		}
		return head;
	}
}
