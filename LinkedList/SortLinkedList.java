package leetcode.LinkedList;

import java.util.Random;

public class SortLinkedList {
	
	public static class Result {
		public ListNode pivot;
		public ListNode head;

		public Result(ListNode p, ListNode h) {
			pivot = p;
			head = h;
		}
	}

	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null || !needSort(head)) {
			return head;
		}

		ListNode leftHead = null;
		ListNode rightHead = null;
		Result r = randomPartition(head);

		ListNode pivot = r.pivot;
		ListNode runner = r.head;
		ListNode temp;

		while (runner != null) {
			temp = runner.next;
			if (runner.val > pivot.val) {
				runner.next = rightHead;
				rightHead = runner;
			} else {
				runner.next = leftHead;
				leftHead = runner;
			}
			runner = temp;
		}

		leftHead = sortList(leftHead);
		rightHead = sortList(rightHead);

		if (leftHead == null) {
			pivot.next = rightHead;
			return pivot;
		} else {
			runner = leftHead;
			while (runner.next != null) {
				runner = runner.next;
			}
			runner.next = pivot;
			pivot.next = rightHead;
			return leftHead;
		}
	}

	private static Result randomPartition(ListNode head) {
		int length = 0;
		ListNode runner = head;
		ListNode pivot;
		while (runner != null) {
			length++;
			runner = runner.next;
		}
		Random r = new Random();
		int position = r.nextInt(length);
		if (position == 0) {
			pivot = head;
			head = head.next;
			pivot.next = null;
		} else {
			position--;
			runner = head;
			while (position > 0) {
				position--;
				runner = runner.next;
			}
			pivot = runner.next;
			runner.next = runner.next.next;
			pivot.next = null;
		}
		return new Result(pivot, head);
	}

	private static boolean needSort(ListNode head) {
		while (head != null && head.next != null) {
			if (head.val > head.next.val)
				return true;
			head = head.next;
		}
		return false;
	}
}
