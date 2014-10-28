package leetcode.StringAndArray;

import java.util.LinkedList;

public class MultiplyStrings {
	/**
	 * Given two numbers represented as strings, return multiplication of the
	 * numbers as a string.
	 * 
	 * Note: The numbers can be arbitrarily large and are non-negative.
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static String multiply(String num1, String num2) {
		if (num1.isEmpty() || num1 == null)
			return num2;
		if (num2.isEmpty() || num2 == null)
			return num1;
		if (num1.length() == 1 && num1.charAt(0) == '0')
			return "0";
		if (num2.length() == 1 && num2.charAt(0) == '0')
			return "0";
		LinkedList<Integer> result = new LinkedList<Integer>();
		for (int i = num2.length() - 1; i >= 0; i--) {
			int position = num2.length() - i - 1;
			int digit = num2.charAt(i) - '0';
			LinkedList<Integer> num = createNum(num1);
			if (digit > 0) {
				LinkedList<Integer> temp = multiply(num, digit);
				appendZero(temp, position);
				result = add(result, temp);
			}
		}
		if (result.size() == 0)
			return "0";
		StringBuffer sb = new StringBuffer();
		for (Integer integer : result) {
			sb.append(integer);
		}
		return sb.toString();
	}

	private static LinkedList<Integer> createNum(String num) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		for (int i = 0; i < num.length(); i++) {
			result.add(num.charAt(i) - '0');
		}
		return result;
	}

	private static void appendZero(LinkedList<Integer> num, int count) {
		while (count > 0) {
			num.add(0);
			count--;
		}
	}

	private static LinkedList<Integer> multiply(LinkedList<Integer> num,
			int digit) {
		int carry = 0;
		int end = num.size() - 1;
		int result = 0;
		while (end >= 0) {
			result = num.get(end) * digit + carry;
			num.set(end, result % 10);
			carry = result / 10;
			end--;
		}
		if (carry > 0)
			num.addFirst(carry);
		return num;
	}

	private static LinkedList<Integer> add(LinkedList<Integer> num1,
			LinkedList<Integer> num2) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		int end1 = num1.size() - 1;
		int end2 = num2.size() - 1;
		int carry = 0;
		int sum = 0;
		while (end1 >= 0 && end2 >= 0) {
			sum = num1.get(end1) + num2.get(end2) + carry;
			result.addFirst(sum % 10);
			carry = sum / 10;
			end1--;
			end2--;
		}

		if (end1 >= 0) {
			while (end1 >= 0) {
				if (carry > 0) {
					sum = num1.get(end1) + carry;
					result.addFirst(sum % 10);
					carry = sum / 10;
				} else {
					result.addFirst(num1.get(end1));
				}
				end1--;
			}
		}

		if (end2 >= 0) {
			while (end2 >= 0) {
				if (carry > 0) {
					sum = num2.get(end2) + carry;
					result.addFirst(sum % 10);
					carry = sum / 10;
				} else {
					result.addFirst(num2.get(end2));
				}
				end2--;
			}
		}

		if (carry > 0)
			result.addFirst(carry);

		return result;
	}
}
