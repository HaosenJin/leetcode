package leetcode.DPAndRecurrsive;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {
	/**
	 * Given a string s and a dictionary of words dict, determine if s can be
	 * segmented into a space-separated sequence of one or more dictionary
	 * words.
	 * 
	 * For example, given s = "leetcode", dict = ["leet", "code"].
	 * 
	 * Return true because "leetcode" can be segmented as "leet code".
	 * 
	 * @param s
	 * @param dict
	 * @return
	 */
	public static boolean wordBreak(String s, Set<String> dict) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		return wordBreakHelper(s, dict, map);
	}

	private static boolean wordBreakHelper(String s, Set<String> dict,
			Map<String, Boolean> map) {
		if (s.isEmpty())
			return true;
		if (map.containsKey(s))
			return map.get(s);
		String subString;
		String remainString;
		for (int i = 0; i < s.length(); i++) {
			subString = s.substring(0, i + 1);
			remainString = s.substring(i + 1);
			if (dict.contains(subString) && wordBreakHelper(remainString, dict,map)) {
				map.put(s, true);
				return true;
			}
		}
		map.put(s, false);
		return false;
	}

	public static class Result {
		public boolean valid;
		public List<String> sentences;

		public Result(boolean valid, List<String> sentences) {
			this.valid = valid;
			this.sentences = sentences;
		}
	}

	/**
	 * 
	 * Given a string s and a dictionary of words dict, add spaces in s to
	 * construct a sentence where each word is a valid dictionary word.
	 * 
	 * Return all such possible sentences.
	 * 
	 * For example, given s = "catsanddog", dict = ["cat", "cats", "and",
	 * "sand", "dog"].
	 * 
	 * A solution is ["cats and dog", "cat sand dog"].
	 * 
	 * @param s
	 * @param dict
	 * @return
	 */
	public static List<String> wordBreak2(String s, Set<String> dict) {
		Map<String, Result> map = new HashMap<String, Result>();
		return wordBreakHelper2(s, dict, map).sentences;
	}

	private static Result wordBreakHelper2(String s, Set<String> dict,
			Map<String, Result> map) {
		if (s.isEmpty()) {
			LinkedList<String> sentences = new LinkedList<String>();
			sentences.add("");
			return new Result(true, sentences);
		}

		if (map.containsKey(s))
			return map.get(s);

		String subString;
		String remainString;
		Result result = new Result(false, new LinkedList<String>());
		for (int i = 0; i < s.length(); i++) {
			subString = s.substring(0, i + 1);
			remainString = s.substring(i + 1);
			Result remaining_result = wordBreakHelper2(remainString, dict, map);
			if (dict.contains(subString) && remaining_result.valid) {
				result.valid = true;
				for (String sentence : remaining_result.sentences) {
					String temp = subString + " " + sentence;
					result.sentences.add(temp.trim());
				}
			}
		}
		map.put(s, result);
		return result;
	}
}
