package leetcode.BitManipulation;

public class SingleNumber {
	/**
	 * Given an array of integers, every element appears twice except for one.
	 * Find that single one.
	 * 
	 * Note: Your algorithm should have a linear runtime complexity. Could you
	 * implement it without using extra memory?
	 * 
	 * @param A
	 * @return
	 */
	public int singleNumber(int[] A) {
		int temp = A[0];
		for (int i = 1; i < A.length; i++) {
			temp = temp ^ A[i];
		}
		return temp;
	}

	/**
	 * 
	 * Given an array of integers, every element appears three times except for
	 * one. Find that single one.
	 * 
	 * Note: Your algorithm should have a linear runtime complexity. Could you
	 * implement it without using extra memory?
	 * 
	 * @param A
	 * @return
	 */
	public int singleNumber2(int[] A) {

		int result = 0;
		for (int i = 0; i < 32; i++) {
			int count = 0;
			for (int j = 0; j < A.length; j++) {
				if (((A[j] >> i) & 1) == 1) {
					count++;
				}
			}
			result |= ((count % 3) << i);
		}
		return result;
	}
}
