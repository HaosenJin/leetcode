package leetcode.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {
	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return null;
		int result_key = node.label;
		// copy map
		Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
		HashSet<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
		// original queue
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		map.put(node.label, new UndirectedGraphNode(node.label));
		queue.add(node);
		while (!queue.isEmpty()) {
			UndirectedGraphNode source = queue.poll();
			UndirectedGraphNode copy = map.get(source.label);
			visited.add(source);
			for (UndirectedGraphNode n : source.neighbors) {
				if (!map.containsKey(n.label)) {
					map.put(n.label, new UndirectedGraphNode(n.label));
				}
				copy.neighbors.add(map.get(n.label));
				if (!visited.contains(n) && !queue.contains(n)) {
					queue.add(n);
				}
			}
		}
		return map.get(result_key);
	}

	public static void print(UndirectedGraphNode node) {
		Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		HashSet<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
		map.put(node.label, node);
		queue.add(node);
		while (!queue.isEmpty()) {
			UndirectedGraphNode source = queue.poll();
			visited.add(source);
			for (UndirectedGraphNode n : source.neighbors) {
				if (!map.containsKey(n.label)) {
					map.put(n.label, n);
				}

				if (!visited.contains(n)) {
					queue.add(n);
				}
			}
		}
		for (int i = 0; i < map.size(); i++) {
			UndirectedGraphNode n = map.get(i);
			System.out.print(n.label + "->{");
			for (UndirectedGraphNode x : n.neighbors) {
				System.out.print(x.label + ",");
			}
			System.out.print("}");
			System.out.println();
		}
	}
}
