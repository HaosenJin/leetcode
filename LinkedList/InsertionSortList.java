package leetcode.LinkedList;

public class InsertionSortList {
	public static ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode j = head;
		ListNode i;
		ListNode temp;
		while (j.next != null) {
			if (head.val > j.next.val) {
				temp = j.next;
				j.next = j.next.next;
				temp.next = head;
				head = temp;
			} else {
				i = head;
				while (i.next.val <= j.next.val && i != j) {
					i = i.next;
				}
				if (i.next.val > j.next.val) {
					temp = j.next;
					j.next = j.next.next;
					temp.next = i.next;
					i.next = temp;
				} else {
					j = j.next;
				}
			}
		}
		return head;
	}
}
