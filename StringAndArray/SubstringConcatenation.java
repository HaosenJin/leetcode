package leetcode.StringAndArray;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SubstringConcatenation {
	/**
	 * You are given a string, S, and a list of words, L, that are all of the
	 * same length. Find all starting indices of substring(s) in S that is a
	 * concatenation of each word in L exactly once and without any intervening
	 * characters.
	 * 
	 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
	 * 
	 * You should return the indices: [0,9]. (order does not matter).
	 * 
	 * @param S
	 * @param L
	 * @return
	 */
	public List<Integer> findSubstring(String S, String[] L) {
		int[] visited = new int[L.length];
		List<Integer> result = new LinkedList<Integer>();
		List<String> dic = new LinkedList<String>();
		for (String word : L) {
			dic.add(word);
		}
		for (int i = 0; i < L.length; i++) {
			if (findSubstringHelper(S, L, visited))
				result.add(i);
		}
		return result;
	}

	private boolean findSubstringHelper(String S, String[] L, int[] visited) {
		if (findAllWords(visited))
			return true;
		if (S.isEmpty() || S == null)
			return false;
		boolean result = false;
		for (int i = 1; i < S.length(); i++) {
			String current = S.substring(0, i);
			String remaining = S.substring(i);
			if (contains(L, current, visited)) {
				result = findSubstringHelper(remaining, L, visited);
				unmark(L, current, visited);
				if (result)
					return result;
			}
		}
		return result;
	}

	private boolean findAllWords(int[] visited) {
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == 0)
				return false;
		}
		return true;
	}

	private boolean contains(String[] L, String word, int[] visited) {
		for (int i = 0; i < L.length; i++) {
			if (L[i].equals(word)) {
				visited[i] = 1;
				return true;
			}
		}
		return false;
	}

	private void unmark(String[] L, String word, int[] visited) {
		for (int i = 0; i < L.length; i++) {
			if (L[i].equals(word)) {
				visited[i] = 0;
				break;
			}
		}
	}

	public List<Integer> findSubstring2(String S, String[] L) {
		List<Integer> result = new LinkedList<Integer>();
		Map<String, Integer> expect = new HashMap<String, Integer>();
		for (String word : L) {
			if (!expect.containsKey(word)) {
				expect.put(word, 0);
			}
			expect.put(word, expect.get(word) + 1);
		}

		Map<String, Integer> actual = new HashMap<String, Integer>();

		int word_len = L[0].length();
		int word_num = L.length;

		for (int i = 0; i < S.length() - word_len * word_num + 1; i++) {
			int j = 0;
			actual.clear();
			while (j < word_num) {
				String word = S.substring(i + j * word_len, i + (j + 1)
						* word_len);
				if (expect.containsKey(word)) {
					if (!actual.containsKey(word)) {
						actual.put(word, 0);
					}
					actual.put(word, actual.get(word) + 1);
					if (actual.get(word) > expect.get(word))
						break;
				} else {
					break;
				}
				j++;
			}

			if (j == word_num)
				result.add(i);
		}
		return result;
	}
}
