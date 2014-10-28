package leetcode.StringAndArray;

public class PlusOne {
	/**
	 * Given a non-negative number represented as an array of digits, plus one
	 * to the number.
	 * 
	 * The digits are stored such that the most significant digit is at the head
	 * of the list.
	 * 
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		int carry = 1;
		int sum;
		for (int i = digits.length - 1; i >= 0; i--) {
			sum = digits[i] + carry;
			digits[i] = sum % 10;
			carry = sum / 10;
		}

		if (carry == 1) {
			int[] result = new int[digits.length + 1];
			result[0] = 1;
			for (int i = 0; i < digits.length; i++) {
				result[i + 1] = digits[i];				
			}
			return result;
		}
		return digits;
	}
}
