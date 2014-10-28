package leetcode.Sort;

public class SearchInsertPosition {
	/**
	 * Given a sorted array and a target value, return the index if the target
	 * is found. If not, return the index where it would be if it were inserted
	 * in order.
	 * 
	 * You may assume no duplicates in the array.
	 * 
	 * Here are few examples. [1,3,5,6], 5 ¡ú 2 [1,3,5,6], 2 ¡ú 1 [1,3,5,6], 7 ¡ú 4
	 * [1,3,5,6], 0 ¡ú 0
	 * 
	 * @param A
	 * @param target
	 * @return
	 */
	public static int searchInsert(int[] A, int target) {
		return searchInsert(A, target, 0, A.length - 1);
	}

	private static int searchInsert(int[] A, int target, int start, int end) {
		if (start > end)
			return start;
		int mid = (start + end) / 2;

		if (A[mid] == target) {
			return mid;
		} else if (A[mid] > target) {
			if (mid - 1 >= 0 && A[mid - 1] < target) {
				return mid;
			} else {
				return searchInsert(A, target, start, mid - 1);
			}
		} else {
			if (mid + 1 < A.length && A[mid + 1] >= target) {
				return mid + 1;
			} else {
				return searchInsert(A, target, mid + 1, end);
			}
		}
	}
}
