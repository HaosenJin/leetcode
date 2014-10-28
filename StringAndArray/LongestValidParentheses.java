package leetcode.StringAndArray;

import java.util.Stack;

public class LongestValidParentheses {
	/**
	 * Given a string containing just the characters '(' and ')', find the
	 * length of the longest valid (well-formed) parentheses substring.
	 * 
	 * For "(()", the longest valid parentheses substring is "()", which has
	 * length = 2.
	 * 
	 * Another example is ")()())", where the longest valid parentheses
	 * substring is "()()", which has length = 4.
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int max = 0;
		int last_index = -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				if (stack.isEmpty()) {
					last_index = i;
					continue;
				} else {
					stack.pop();
					int length = i
							- (stack.isEmpty() ? last_index : stack.peek());
					if (length > max)
						max = length;
				}
			}
		}
		return max;
	}

	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and
	 * ']', determine if the input string is valid.
	 * 
	 * The brackets must close in the correct order, "()" and "()[]{}" are all
	 * valid but "(]" and "([)]" are not.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
				stack.push(s.charAt(i));
			} else {
				if (s.charAt(i) == ')') {
					if (stack.isEmpty() || stack.peek() != '(') {
						return false;
					}
				} else if (s.charAt(i) == ']') {
					if (stack.isEmpty() || stack.peek() != '[') {
						return false;
					}
				} else {
					if (stack.isEmpty() || stack.peek() != '{') {
						return false;
					}
				}
				stack.pop();
			}
		}
		if (stack.isEmpty())
			return true;
		else {
			return false;
		}
	}
}
