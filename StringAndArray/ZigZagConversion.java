package leetcode.StringAndArray;

public class ZigZagConversion {
	/**
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given
	 * number of rows like this: (you may want to display this pattern in a
	 * fixed font for better legibility)
	 * 
	 * P A H N A P L S I I G Y I R And then read line by line: "PAHNAPLSIIGYIR"
	 * Write the code that will take a string and make this conversion given a
	 * number of rows:
	 * 
	 * string convert(string text, int nRows); convert("PAYPALISHIRING", 3)
	 * should return "PAHNAPLSIIGYIR".
	 * 
	 * @param s
	 * @param nRows
	 * @return
	 */
	public static String convert(String s, int nRows) {
		if (nRows <= 1)
			return s;
		if (s.length() == 0)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nRows; i++) {
			for (int j = 0, index = i; index < s.length(); j++, index = (nRows - 1)
					* 2 * j + i) {
				sb.append(s.charAt(index));
				if (i > 0 && i < nRows - 1) {
					int k = (nRows - 1) * 2 * j + i + (nRows - 1 - i) * 2;
					if (k < s.length())
						sb.append(s.charAt(k));
				}
			}
		}
		return sb.toString();
	}
}
