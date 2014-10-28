package leetcode.DPAndRecurrsive;

public class MaximumProductSubarray {
	/**
	 * Find the contiguous subarray within an array (containing at least one
	 * number) which has the largest product.
	 * 
	 * 
	 * 
	 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3]
	 * has the largest product = 6.
	 * 
	 * @param A
	 * @return
	 */
	public static int maxProduct(int[] A) {
		int negative = A[0] < 0 ? A[0] : 0;
		int[] p = new int[A.length];
		p[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			if (A[i] == 0) {
				p[i] = 0;
				negative = 0;
			} else if (A[i] > 0) {
				negative = negative * A[i];
				if (p[i - 1] > 0) {
					p[i] = p[i - 1] * A[i];
				} else {
					p[i] = A[i];
				}
			} else {
				if (negative < 0) {
					p[i] = negative * A[i];
				} else {
					p[i] = A[i];
				}
				if (p[i - 1] > 0)
					negative = A[i] * p[i - 1];
				else
					negative = A[i];
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < p.length; i++) {
			if (p[i] > max) {
				max = p[i];
			}
		}
		return max;
	}
}
