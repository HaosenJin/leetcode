package leetcode.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class WordLadder {

	/**
	 * Given two words (start and end), and a dictionary, find all shortest
	 * transformation sequence(s) from start to end, such that:
	 * 
	 * Only one letter can be changed at a time Each intermediate word must
	 * exist in the dictionary For example,
	 * 
	 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
	 * Return [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"]
	 * ]
	 * 
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */

	public static List<List<String>> findLadders2(String start, String end,
			Set<String> dict) {
		Map<Integer, List<List<String>>> map = new TreeMap<Integer, List<List<String>>>();
		ArrayList<String> path = new ArrayList<String>();
		HashSet<String> visited = new HashSet<String>();
		path.add(start);
		visited.add(start);
		DFS_Visit(start, end, path, visited, 1, map, dict);
		Iterator<Integer> iterator = map.keySet().iterator();
		if (iterator.hasNext())
			return map.get(iterator.next());
		else
			return null;
	}

	private static void DFS_Visit(String start, String end,
			ArrayList<String> path, HashSet<String> visited, int distance,
			Map<Integer, List<List<String>>> map, Set<String> dict) {

		List<String> wordList = buildOneEditWordList(start);
		for (String word : wordList) {
			if (word.equals(end)) {
				List<List<String>> lists;
				if (!map.containsKey(distance + 1)) {
					lists = new LinkedList<List<String>>();
					map.put(distance + 1, lists);
				}
				lists = map.get(distance + 1);
				List<String> partial = new LinkedList<String>();
				partial.addAll(path.subList(0, distance));
				partial.add(end);
				if (!contains(lists, partial)) {
					lists.add(partial);
				}
			} else if (dict.contains(word)) {
				if (!visited.contains(word)) {
					visited.add(word);
					if (path.size() <= distance) {
						path.add(word);
					} else {
						path.set(distance, word);
					}
					DFS_Visit(word, end, path, visited, distance + 1, map, dict);
				}
			}
		}
		visited.remove(start);
	}

	private static boolean contains(List<List<String>> lists, List<String> list) {
		for (List<String> l : lists) {
			if (l.containsAll(list))
				return true;
		}
		return false;
	}

	/**
	 * Given two words (start and end), and a dictionary, find the length of
	 * shortest transformation sequence from start to end, such that:
	 * 
	 * Only one letter can be changed at a time Each intermediate word must
	 * exist in the dictionary For example,
	 * 
	 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
	 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" ->
	 * "cog", return its length 5.
	 * 
	 * Note: Return 0 if there is no such transformation sequence. All words
	 * have the same length. All words contain only lowercase alphabetic
	 * characters.
	 * 
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public static int ladderLength(String start, String end, Set<String> dict) {
		int distance = 0;
		Map<String, String> backTrackMap = new HashMap<String, String>();
		Set<String> visited = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		while (!queue.isEmpty()) {
			String current = queue.poll();
			List<String> wordList = buildOneEditWordList(current);
			for (String word : wordList) {
				if (word.equals(end)) {
					int count = 2;
					String s = current;
					while (!s.equals(start)) {
						s = backTrackMap.get(s);
						count++;
					}
					if (distance == 0 || distance > count)
						distance = count;
				}
				if (dict.contains(word)) {
					if (!visited.contains(word)) {
						visited.add(word);
						queue.add(word);
						backTrackMap.put(word, current);
						dict.remove(word);
					}
				}
			}
		}
		return distance;
	}

	private static List<String> buildOneEditWordList(String source) {
		List<String> wordList = new LinkedList<String>();
		for (int i = 0; i < source.length(); i++) {
			char[] c_str = source.toCharArray();

			for (char c = 'a'; c <= 'z'; c++) {
				if (source.charAt(i) != c) {
					c_str[i] = c;
					wordList.add(String.valueOf(c_str));
				}
			}
		}
		return wordList;
	}

	public static int ladderLength2(String start, String end, Set<String> dict) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Queue<String> queue = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		List<String> worList = buildOneEditWordList2(end, dict);
		dict.removeAll(worList);
		for (String word : worList) {
			map.put(word, 1);
			visited.add(word);
		}
		queue.addAll(worList);
		while (!queue.isEmpty()) {
			String current = queue.poll();
			if (difference(start, current) == 1)
				return map.get(current) + 2;
			worList = buildOneEditWordList2(current, dict);
			dict.removeAll(worList);
			for (String word : worList) {
				if (!visited.contains(word)) {
					map.put(word, map.get(current) + 1);
					visited.add(word);
					queue.add(word);
				} else {
					if (map.get(word) > map.get(current) + 1)
						map.put(word, map.get(current) + 1);
				}
			}
		}
		return 0;
	}

	private static List<String> buildOneEditWordList2(String source,
			Set<String> dict) {
		List<String> wordList = new LinkedList<String>();
		for (String w : dict) {
			if (difference(w, source) == 1)
				wordList.add(w);
		}
		return wordList;
	}

	private static int difference(String word1, String word2) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < word1.length(); i++) {
			char c = word1.charAt(i);
			if (!map.containsKey(c)) {
				map.put(c, 0);
			}
			map.put(c, map.get(c) + 1);
		}

		int difference = 0;
		for (int i = 0; i < word2.length(); i++) {
			char c = word2.charAt(i);
			if (!map.containsKey(c)) {
				difference++;
			} else {
				map.put(c, map.get(c) - 1);
				if (map.get(c) == 0)
					map.remove(c);
			}
		}
		return difference;
	}
}
