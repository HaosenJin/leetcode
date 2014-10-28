package leetcode.StringAndArray;

public class RemoveDuplicates {

	/**
	 * Given a sorted array, remove the duplicates in place such that each
	 * element appear only once and return the new length.
	 * 
	 * Do not allocate extra space for another array, you must do this in place
	 * with constant memory.
	 * 
	 * For example, Given input array A = [1,1,2],
	 * 
	 * Your function should return length = 2, and A is now [1,2].
	 * 
	 * @param A
	 * @return
	 */
	public int removeDuplicates(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		if (A.length == 1)
			return 1;
		int position = 1;
		for (int i = 1; i < A.length; i++) {
			if (A[i] != A[i - 1]) {
				put(A, i, position);
				position++;
			}
		}
		return position;
	}

	/**
	 * Follow up for "Remove Duplicates": What if duplicates are allowed at most
	 * twice?
	 * 
	 * For example, Given sorted array A = [1,1,1,2,2,3],
	 * 
	 * Your function should return length = 5, and A is now [1,1,2,2,3].
	 * 
	 * @param A
	 * @return
	 */
	public static int removeDuplicates2(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		if (A.length == 1)
			return 1;
		int position = 1;
		int count = 1;
		for (int i = 1; i < A.length; i++) {
			if (A[i] == A[i - 1]) {
				count++;
			} else {
				count = 1;
			}
			if (count <= 2) {
				put(A, i, position);
				position++;
			}
		}
		return position;
	}

	private static void put(int[] A, int from, int to) {
		if (from == to)
			return;
		A[to] = A[from];
	}
}
