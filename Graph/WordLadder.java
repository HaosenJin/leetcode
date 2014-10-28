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
					return count;
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
		return 0;
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
}
