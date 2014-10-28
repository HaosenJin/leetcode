package leetcode.LinkedList;

public class CopyRandomList {
	public static class RandomListNode {
		public int label;
		public RandomListNode next, random;

		public RandomListNode(int x) {
			this.label = x;
		}
	}

	/**
	 * A linked list is given such that each node contains an additional random
	 * pointer which could point to any node in the list or null.
	 * 
	 * Return a deep copy of the list.
	 * 
	 * @param head
	 * @return
	 */
	public static RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return null;
		
		// copy value
		RandomListNode runner = head;
		RandomListNode temp;
		while (runner != null) {
			temp = new RandomListNode(runner.label);
			temp.next = runner.next;
			runner.next = temp;
			runner = runner.next.next;
		}

		// copy random pointer
		runner = head;
		while (runner != null && runner.next != null) {
			if (runner.random != null) {
				runner.next.random = runner.random.next;
			}
			runner = runner.next.next;
		}

		runner = head;
		RandomListNode result_head = head.next;
		// split new copy from old list and create a new list
		while (runner != null) {
			temp = runner.next;
			runner.next = temp.next;
			runner = runner.next;
			if (temp.next != null)
				temp.next = temp.next.next;

		}
		return result_head;
	}
}
