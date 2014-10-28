package leetcode.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordLadder3 {
	public List<List<String>> findLadders(String start, String end,
			Set<String> dict) {
		if (dict == null)
			return null;
		HashMap<String, HashSet<String>> graph = new HashMap<String, HashSet<String>>();
		dict.add(start);
		dict.add(end);
		for (String node : dict) {
			HashSet<String> children = new HashSet<String>();
			char[] c_str = node.toCharArray();
			for (int i = 0; i < node.length(); i++) {
				char c = c_str[i];
				for (char replace = 'a'; replace <= 'z'; replace++) {
					if (c == replace)
						continue;
					c_str[i] = replace;
					String child = String.valueOf(c_str);
					if (dict.contains(child)) {
						children.add(child);
					}
				}
				c_str[i] = c;
			}
			graph.put(node, children);
		}
		return BFS(dict, graph, start, end);
	}

	private List<List<String>> BFS(Set<String> dict,
			HashMap<String, HashSet<String>> graph, String start, String end) {
		List<List<String>> result = new LinkedList<List<String>>();
		HashSet<String> level = new HashSet<String>();
		level.add(start);
		dict.remove(start);
		HashMap<String, HashSet<String>> predecessors = new HashMap<String, HashSet<String>>();
		predecessors.put(start, new HashSet<String>());
		while (!level.isEmpty()) {
			HashSet<String> nextLevel = new HashSet<String>();
			for (String word : level) {
				for (String child : graph.get(word)) {
					if (dict.contains(child)) {
						nextLevel.add(child);
						if (!predecessors.containsKey(child)) {
							predecessors.put(child, new HashSet<String>());
						}
						predecessors.get(child).add(word);
					}
				}
			}
			level = nextLevel;
			dict.removeAll(level);
			if (level.contains(end)) {
				buildPath(predecessors, result, new ArrayList<String>(), end,
						start);
			}
		}

		return result;
	}

	private void buildPath(HashMap<String, HashSet<String>> predecessors,
			List<List<String>> result, ArrayList<String> path, String current,
			String start) {
		path.add(0, current);
		if (current.equals(start)) {
			result.add(path);
			return;
		}

		for (String predecessor : predecessors.get(current)) {
			ArrayList<String> copy = new ArrayList<String>(path);
			buildPath(predecessors, result, copy, predecessor, start);
		}
	}

}
