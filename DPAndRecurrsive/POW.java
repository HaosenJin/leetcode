package leetcode.DPAndRecurrsive;

import java.util.HashMap;
import java.util.Map;

public class POW {
	/**
	 * Implement pow(x, n).
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static double pow(double x, int n) {
		if (n == 0)
			return 1;
		int d = Math.abs(n);
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		if (n < 0) {
			return 1 / powHelper(x, d, map);
		} else {
			return powHelper(x, d, map);
		}
	}

	private static double powHelper(double x, int n, Map<Integer, Double> map) {
		if (n == 1)
			return x;
		if (map.containsKey(n))
			return map.get(n);
		double result = 0;
		double last = x;
		int index = 0;
		for (index = 1; index * 2 > 0 && index * 2 <= n; index = index * 2) {
			last = last * last;
			map.put(2 * index, last);
		}
		if (index == n) {
			result = last;
		} else {
			result = last * powHelper(x, n - index, map);
			map.put(n, result);
		}
		return result;
	}
}
