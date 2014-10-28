package leetcode.DPAndRecurrsive;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses {
	/**
	 * Given n pairs of parentheses, write a function to generate all
	 * combinations of well-formed parentheses.
	 * 
	 * For example, given n = 3, a solution set is:
	 * 
	 * "((()))", "(()())", "(())()", "()(())", "()()()"
	 * 
	 * @param n
	 * @return
	 */
	public static List<String> generateParenthesis(int n) {
		List<String> result = new LinkedList<String>();
		char[] solution = new char[n * 2];
		generateParenthesis(result, solution, 0, n, n);
		return result;
	}

	private static void generateParenthesis(List<String> result,
			char[] solution, int index, int left, int right) {
		if (left < 0 || right < 0 || right < left)
			return;
		if (index == solution.length) {
			result.add(String.valueOf(solution));
		} else {
			if (left >= 0) {
				solution[index] = '(';
				generateParenthesis(result, solution, index + 1, left - 1,
						right);
			}
			if (right > left) {
				solution[index] = ')';
				generateParenthesis(result, solution, index + 1, left,
						right - 1);
			}
		}
	}

	public static List<String> generateParenthesis2(int n) {
		HashSet<String> parenthesis = generate(n);
		List<String> result = new LinkedList<String>();
		result.addAll(parenthesis);
		return result;
	}

	private static HashSet<String> generate(int n) {
		HashSet<String> result = new HashSet<String>();
		if (n == 0) {
			result.add("");
			return result;
		}
		HashSet<String> old = generate(n - 1);
		for (String s : old) {
			result.add("()" + s);
			result.add(s + "()");
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(') {
					result.add(s.substring(0, i + 1) + "()"
							+ s.substring(i + 1));
				}
			}
		}
		return result;
	}
}
