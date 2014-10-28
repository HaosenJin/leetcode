package leetcode.StringAndArray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Anagrams {
	/**
	 * Given an array of strings, return all groups of strings that are
	 * anagrams.
	 * 
	 * Note: All inputs will be in lower-case.
	 * 
	 * @param strs
	 * @return
	 */
	public List<String> anagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String string : strs) {
			char[] charArray = string.toCharArray();
			Arrays.sort(charArray);
			String key = String.valueOf(charArray);
			if (!map.containsKey(key)) {
				map.put(key, new LinkedList<String>());
			}
			map.get(key).add(string);
		}

		List<String> result = new LinkedList<String>();
		for (String key : map.keySet()) {
			if (map.get(key).size() > 1)
				result.addAll(map.get(key));
		}
		return result;
	}
}
