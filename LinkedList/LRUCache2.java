package leetcode.LinkedList;

import java.util.HashMap;

/**
 * Runtime Error Message: Line 60: java.lang.NullPointerException Last executed
 * input: 1,[set(2,1),get(1)]
 * 
 * @author Haosen
 * 
 */
public class LRUCache2 {
	public static class LRUNode {
		public int key;
		public int val;
		LRUNode prev, next;

		public LRUNode(int v, int k) {
			this.key = k;
			this.val = v;
			prev = null;
			next = null;
		}
	}

	private int capacity;
	private LRUNode head, tail;
	private HashMap<Integer, LRUNode> map;

	public LRUCache2(int c) {
		capacity = c;
		map = new HashMap<Integer, LRUNode>();
		head = null;
		tail = null;
	}

	public int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		}
		LRUNode node = map.get(key);
		moveToHead(node);
		return node.val;
	}

	public void set(int key, int value) {
		if (map.containsKey(key)) {
			LRUNode oldNode = map.get(key);
			if (oldNode.val != value) {
				oldNode.val = value;
			}
			moveToHead(oldNode);
		} else {
			LRUNode node = new LRUNode(value, key);
			if (map.size() == capacity) {
				removeTail();
			}
			map.put(key, node);
			preAppendNode(node);
		}
	}

	private void moveToHead(LRUNode node) {
		if (node != head) {
			if (node == tail) {
				tail = tail.prev;
				tail.next = null;
			} else {
				node.prev.next = node.next;
				node.next.prev = node.prev;
			}
			head.prev = node;
			node.next = head;
			node.prev = null;
			head = node;
		}
	}

	private void preAppendNode(LRUNode node) {
		if (head == null && tail == null) {
			head = node;
			tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
	}

	private void removeTail() {
		map.remove(tail.key);
		if (map.size() == 0) {
			head = null;
			tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
	}
}
