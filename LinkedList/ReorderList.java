package leetcode.LinkedList;

import java.util.Stack;

public class ReorderList {
	/**
	 * 
	 * Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn, reorder it to:
	 * L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­
	 * 
	 * You must do this in-place without altering the nodes' values.
	 * 
	 * For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
	 * 
	 * @param head
	 */
	public static void reorderList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return;
		}
		Stack<ListNode> stack = new Stack<ListNode>();
		ListNode runner = head;
		while (runner != null) {
			stack.push(runner);
			runner = runner.next;
		}
		ListNode temp;
		runner = head;
		while (runner.next != stack.peek() && runner != stack.peek()) {
			temp = stack.pop();
			temp.next = runner.next;
			runner.next = temp;
			runner = runner.next.next;
		}
		stack.pop().next = null;
	}
}
