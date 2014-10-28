package leetcode.BitManipulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GrayCode {
	/**
	 * The gray code is a binary numeral system where two successive values
	 * differ in only one bit. Given a non-negative integer n representing the
	 * total number of bits in the code, print the sequence of gray code. A gray
	 * code sequence must begin with 0. For example, given n = 2, return
	 * [0,1,3,2]. Its gray code sequence is:
	 * 
	 * 00 - 0
	 * 
	 * 01 - 1
	 * 
	 * 11 - 3
	 * 
	 * 10 - 2
	 * 
	 * Note: For a given n, a gray code sequence is not uniquely defined. For
	 * example, [0,2,3,1] is also a valid gray code sequence according to the
	 * above definition. For now, the judge is able to judge based on one
	 * instance of gray code sequence. Sorry about that.
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> grayCode(int n) {
		List<Integer> result = new LinkedList<Integer>();
		result.add(0);
		for (int i = 0; i < n; i++) {
			int highestBit = 1 << i;
			int len = result.size();
			for (int j = len - 1; j >= 0; j--) {
				result.add(highestBit + result.get(j));
			}
		}
		return result;
	}

	private static boolean reorder(int last, ArrayList<Integer> numbers,
			int index) {
		if (index == numbers.size())
			return true;
		for (int i = index; i < numbers.size(); i++) {
			if (canFollow(last, numbers.get(i))) {
				swap(numbers, index, i);
				if (reorder(numbers.get(index), numbers, index + 1))
					return true;
				swap(numbers, index, i);
			}
		}
		return false;
	}

	private static void swap(ArrayList<Integer> numbers, int i, int j) {
		if (i == j || i < 0 || j > numbers.size())
			return;
		int temp = numbers.get(i);
		numbers.set(i, numbers.get(j));
		numbers.set(j, temp);
	}

	private static boolean canFollow(int a, int b) {

		int temp = a ^ b;
		if ((temp & (temp - 1)) == 0)
			return true;
		return false;
	}
}
