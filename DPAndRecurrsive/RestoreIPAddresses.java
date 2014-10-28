package leetcode.DPAndRecurrsive;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RestoreIPAddresses {
	/**
	 * Given a string containing only digits, restore it by returning all
	 * possible valid IP address combinations.
	 * 
	 * For example: Given "25525511135",
	 * 
	 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
	 * 
	 * @param s
	 * @return
	 */
	public static List<String> restoreIpAddresses(String s) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		return restoreIpAddresses(s, 4, map);
	}

	private static boolean isValid(String s) {
		char[] charArray = s.toCharArray();
		return Integer.valueOf(s) < 256
				&& (charArray[0] != '0' || charArray.length == 1)
				&& charArray.length <= 3;
	}

	private static List<String> restoreIpAddresses(String s, int segNum,
			Map<String, List<String>> map) {
		List<String> result = new LinkedList<String>();
		if (s.length() < segNum || s.length() > segNum * 3 || s.isEmpty())
			return result;
		
		String key = s + "-" + segNum;
		if (map.containsKey(key))
			return map.get(key);

		if (segNum == 1) {
			if (isValid(s)) {
				result.add(s);
				map.put(key, result);
			}
			return result;
		}

		for (int i = 1; i <= 3 && i < s.length(); i++) {
			String current = s.substring(0, i);
			String remaining = s.substring(i);
			if (isValid(current)) {
				List<String> followAddresses = restoreIpAddresses(remaining,
						segNum - 1, map);
				if (followAddresses != null && followAddresses.size() > 0)
					for (String followAddress : followAddresses) {
						result.add(current + "." + followAddress);
					}
			}
		}
		map.put(key, result);
		return result;
	}
}
