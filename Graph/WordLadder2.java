package leetcode.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WordLadder2 {
	public ArrayList<ArrayList<String>> findLadders(String start, String end,
			HashSet<String> dict) {

		// construct graph
		HashMap<String, HashSet<String>> graph = new HashMap<String, HashSet<String>>();
		dict.add(start);
		dict.add(end);
		for (String key : dict) {
			HashSet<String> neighbours = new HashSet<String>();
			char[] chars = key.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				char c = chars[i];
				for (char replace = 'a'; replace <= 'z'; replace++) {
					if (replace == c)
						continue;
					chars[i] = replace;
					String temp = new String(chars);
					if (dict.contains(temp)) {
						neighbours.add(temp);
					}
					chars[i] = c;
				}
			}
			graph.put(key, neighbours);
		}

		return BFS(dict, graph, start, end);
	}

	public ArrayList<ArrayList<String>> BFS(HashSet<String> dict,
			HashMap<String, HashSet<String>> graph, String start, String end) {
		ArrayList<String> level = new ArrayList<String>();
		level.add(start);
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		HashMap<String, ArrayList<String>> predecessors = new HashMap<String, ArrayList<String>>();
		dict.remove(start);
		predecessors.put(start, new ArrayList<String>());

		while (!level.isEmpty()) {
			ArrayList<String> nextLevel = new ArrayList<String>();
			for (String s : level) {
				HashSet<String> children = graph.get(s);

				for (String child : children) {
					if (!dict.contains(child))
						continue;

					if (!nextLevel.contains(child))
						nextLevel.add(child);
					if (predecessors.containsKey(child)) {
						predecessors.get(child).add(s);
					} else {
						ArrayList<String> list = new ArrayList<String>();
						list.add(s);
						predecessors.put(child, list);
					}
				}

			}

			level = nextLevel;
			dict.removeAll(level);

			if (level.contains(end)) {

				buildPaths(result, predecessors, new ArrayList<String>(), end,
						start);
				return result;

			}
		}

		return result;
	}

	public void buildPaths(ArrayList<ArrayList<String>> result,
			HashMap<String, ArrayList<String>> predecessors,
			ArrayList<String> path, String current, String start) {
		path.add(0, current);
		if (current.equals(start)) {
			result.add(path);
			return;
		}
		for (String s : predecessors.get(current)) {
			ArrayList<String> nextPath = new ArrayList<String>(path);
			buildPaths(result, predecessors, nextPath, s, start);
		}
	}
}
