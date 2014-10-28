package leetcode.StringAndArray;

public class ReverseInteger {
	/**
	 * Reverse digits of an integer.
	 * 
	 * Example1: x = 123, return 321 Example2: x = -123, return -321
	 * 
	 * @param x
	 * @return
	 */
	public static int reverse(int x) {
		int sign = x < 0 ? -1 : 1;
		long xl = x;
		long result = 0;
		if (xl < 0)
			xl = ~xl + 1;
		long index = 1;
		while (index <= xl) {
			result = result * 10 + (xl % (index*10) - xl % index) / index;
			index *= 10;
		}
		return (int) (result*sign);
	}
}
