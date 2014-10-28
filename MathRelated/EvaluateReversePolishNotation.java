package leetcode.MathRelated;

import java.util.Stack;

public class EvaluateReversePolishNotation {

	/**
	 * Evaluate the value of an arithmetic expression in Reverse Polish
	 * Notation.
	 * 
	 * Valid operators are +, -, *, /. Each operand may be an integer or another
	 * expression.
	 * 
	 * Some examples: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	 * ["4","13","5", "/", "+"] -> (4 + (13 / 5)) -> 6
	 * 
	 * @param tokens
	 * @return
	 */
	public static int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < tokens.length; i++) {
			if (!isOperator(tokens[i])) {
				stack.push(convertToInt(tokens[i]));
			} else {
				int num2 = stack.pop();
				int num1 = stack.pop();
				stack.push(compute(num1, num2, tokens[i]));
			}
		}
		return stack.pop();
	}

	private static boolean isOperator(String token) {
		int code = opcode(token);
		return code >= 0 && code <= 3;
	}

	private static int opcode(String token) {
		String opString = "+-*/";
		return opString.indexOf(token);
	}

	private static int convertToInt(String token) {
		token = token.trim();
		String numString;
		int sign = 1;
		
		if (token.charAt(0) == '+' || token.charAt(0) == '-') {
			numString = token.substring(1);
		} else {
			numString = token;
		}
		
		if (token.charAt(0) == '-') {
			sign = -1;
		}
		
		int num = 0;
		for (int i = 0; i < numString.length(); i++) {
			num = num * 10 + numString.charAt(i) - '0';
		}
		return sign * num;
	}

	private static int compute(int num1, int num2, String token) {
		switch (opcode(token)) {
		case 0:
			return num1 + num2;
		case 1:
			return num1 - num2;
		case 2:
			if (num1 == 0 || num2 == 0)
				return 0;
			return num1 * num2;
		case 3:
			if (num1 == 0)
				return 0;
			return num1 / num2;
		default:
			return 0;
		}
	}
}
