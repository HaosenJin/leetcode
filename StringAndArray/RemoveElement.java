package leetcode.StringAndArray;

public class RemoveElement {
	/**
	 * Given an array and a value, remove all instances of that value in place
	 * and return the new length.
	 * 
	 * The order of elements can be changed. It doesn't matter what you leave
	 * beyond the new length.
	 * 
	 * @param A
	 * @param elem
	 * @return
	 */
	public static int removeElement(int[] A, int elem) {

		int length = A.length;
		for (int i = 0; i < length; i++) {
			if (A[i] == elem) {
				moveAhead(A, i);
				i--;
				length--;
			}
		}
		return length;
	}

	private static void moveAhead(int[] A, int k) {
		for (int i = k; i < A.length - 1; i++) {
			A[i] = A[i + 1];
		}
	}
}
