package leetcode.LinkedList;

public class MergeTwoSortedLists {
	/**
	 * Merge two sorted linked lists and return it as a new list. The new list
	 * should be made by splicing together the nodes of the first two lists.
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1==null)
			return l2;
		if(l2==null)
			return l1;
		ListNode head = null;
		ListNode tail = null;
		ListNode temp = null;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				temp = l1.next;
				if (head == null) {
					head = l1;
					tail = head;
				} else {
					tail.next = l1;
					tail = tail.next;
				}
				l1 = temp;
			} else {
				temp = l2.next;
				if (head == null) {
					head = l2;
					tail = head;
				} else {
					tail.next = l2;
					tail = tail.next;
				}
				l2 = temp;
			}
		}
		
		if(l1!=null)
			tail.next = l1;
		if(l2!=null)
			tail.next = l2;
		return head;
	}
}
