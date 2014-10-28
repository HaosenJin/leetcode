package leetcode.BitManipulation;

import java.util.LinkedList;

public class FirstMissingPositive {
	/**
	 * Given an unsorted integer array, find the first missing positive integer.
	 * 
	 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
	 * 
	 * @param A
	 * @return
	 */
	public static int firstMissingPositive(int[] A) {
		for (int i = 0; i < A.length; i++) {
			while (A[i] != i + 1) {
				if (A[i] <= 0 || A[i] > A.length || A[i] == A[A[i] - 1])
					break;
				int temp = A[i];
				A[i] = A[temp - 1];
				A[temp - 1] = temp;
			}
		}

		for (int i = 0; i < A.length; i++) {
			if (A[i] != i + 1)
				return i + 1;
		}
		return A.length + 1;
	}

	public static int firstMissingPositive2(int[] A) {
		boolean hasZero = false;
		LinkedList<Integer> nums = new LinkedList<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0) {
				nums.add(A[i]);
				hasZero = true;
			}
			if (A[i] > 0) {
				nums.add(A[i]);
			}
		}
		if (!hasZero)
			nums.add(0);
		return findMissingHelper(nums, 0);
	}

	private static int findMissingHelper(LinkedList<Integer> nums, int index) {
		if (index == 31)
			return 0;
		LinkedList<Integer> ones = new LinkedList<Integer>();
		LinkedList<Integer> zeros = new LinkedList<Integer>();
		for (Integer integer : nums) {
			if (getBit(integer, index))
				ones.add(integer);
			else {
				zeros.add(integer);
			}
		}
		int result = 0;
		if (zeros.size() <= ones.size()) {
			result = findMissingHelper(zeros, index + 1) | (0 << index);
		} else {
			result = findMissingHelper(ones, index + 1) | (1 << index);
		}
		return result;
	}

	private static boolean getBit(int n, int position) {
		return ((n >> position) & 1) == 1;
	}
}
