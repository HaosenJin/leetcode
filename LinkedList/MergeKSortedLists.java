package leetcode.LinkedList;

import java.util.LinkedList;
import java.util.List;

public class MergeKSortedLists {
	public ListNode mergeKLists(List<ListNode> lists) {
		if (lists.size() == 0 || lists == null)
			return null;
		if (lists.size() == 1)
			return lists.get(0);
		while (lists.size() >= 2) {
			List<ListNode> newList = new LinkedList<ListNode>();
			int i = 0;
			while (i < lists.size() - 1) {
				newList.add(mergeLists(lists.get(i), lists.get(i + 1)));
				i += 2;
			}

			if (i == lists.size() - 1) {
				newList.add(lists.get(i));
			}
			lists = newList;
		}
		return lists.get(0);
	}

	private ListNode mergeLists(ListNode list1, ListNode list2) {
		if (list1 == null)
			return list2;
		if (list2 == null)
			return list1;
		ListNode p1 = list1;
		ListNode p2 = list2;
		ListNode head = null;
		ListNode tail = null;
		while (p1 != null && p2 != null) {
			ListNode temp;
			if (p1.val < p2.val) {
				temp = p1;
				p1 = p1.next;
			} else {
				temp = p2;
				p2 = p2.next;
			}
			if (head == null) {
				head = temp;
				tail = head;
			} else {
				tail.next = temp;
				tail = tail.next;
			}
		}
		if (p1 != null)
			tail.next = p1;
		if (p2 != null)
			tail.next = p2;
		return head;
	}
}
