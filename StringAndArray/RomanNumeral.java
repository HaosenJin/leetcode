package leetcode.StringAndArray;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RomanNumeral {
	/**
	 * Given a roman numeral, convert it to an integer.
	 * 
	 * Input is guaranteed to be within the range from 1 to 3999.
	 * 
	 * @param s
	 * @return
	 */
	public static int romanToInt(String s) {
		int[] vals = new int[256];
		vals['I'] = 1;
		vals['V'] = 5;
		vals['X'] = 10;
		vals['L'] = 50;
		vals['C'] = 100;
		vals['D'] = 500;
		vals['M'] = 1000;
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i + 1 < s.length() && vals[s.charAt(i)] < vals[s.charAt(i + 1)]) {
				result -= vals[s.charAt(i)];
			} else {
				result += vals[s.charAt(i)];
			}
		}
		return result;
	}

	/**
	 * Given an integer, convert it to a roman numeral.
	 * 
	 * Input is guaranteed to be within the range from 1 to 3999.
	 * 
	 * @param num
	 * @return
	 */
	public static String intToRoman(int num) {
		Map<Integer, Character> map = new TreeMap<Integer, Character>();
		map.put(1000, 'M');
		map.put(500, 'D');
		map.put(100, 'C');
		map.put(50, 'L');
		map.put(10, 'X');
		map.put(5, 'V');
		map.put(1, 'I');
		int[] keys = { 1000, 500, 100, 50, 10, 5, 1 };
		StringBuffer sb = new StringBuffer();
		while (num > 0) {
			for (int i = 0; i < keys.length; i++) {
				int key = keys[i];
				int n = num / key;

				if (n == 9 && i > 0) {
					sb.append(map.get(keys[i]));
					sb.append(map.get(keys[i - 2]));
					num = num % key;
				} else if (n == 4) {
					sb.append(map.get(keys[i]));
					sb.append(map.get(keys[i - 1]));
					num = num % key;
				} else {
					if (i + 1 < keys.length && (num % key) / keys[i + 1] < 4
							|| i + 1 == keys.length) {
						while (n > 0) {
							sb.append(map.get(keys[i]));
							n--;
						}
						num = num % key;
					}
				}
			}
		}
		return sb.toString();
	}
}
