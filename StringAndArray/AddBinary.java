package leetcode.StringAndArray;

import java.util.Stack;

public class AddBinary {
	/**
	 * Given two binary strings, return their sum (also a binary string).
	 * 
	 * For example, a = "11" b = "1" Return "100".
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String addBinary(String a, String b) {
		int index_a = a.length() - 1;
		int index_b = b.length() - 1;
		int carry = 0;
		Stack<Integer> stack = new Stack<Integer>();
		int num1;
		int num2;
		int sum;
		while (index_a >= 0 && index_b >= 0) {
			num1 = a.charAt(index_a) == '0' ? 0 : 1;
			num2 = b.charAt(index_b) == '0' ? 0 : 1;
			sum = num1 + num2 + carry;
			stack.push(sum % 2);
			carry = sum / 2;
			index_a--;
			index_b--;
		}

		if (index_a >= 0) {
			while (index_a >= 0) {
				num1 = a.charAt(index_a) == '0' ? 0 : 1;
				if (carry == 0) {
					stack.push(num1);
				} else {
					sum = num1 + carry;
					stack.push(sum % 2);
					carry = sum / 2;
				}
				index_a--;
			}
		}

		if (index_b >= 0) {
			while (index_b >= 0) {
				num2 = b.charAt(index_b) == '0' ? 0 : 1;
				if (carry == 0) {
					stack.push(num2);
				} else {
					sum = num2 + carry;
					stack.push(sum % 2);
					carry = sum / 2;
				}
				index_b--;
			}
		}

		if (carry == 1)
			stack.push(1);

		StringBuffer sb = new StringBuffer();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
}
