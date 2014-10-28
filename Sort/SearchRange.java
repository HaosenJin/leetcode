package leetcode.Sort;

public class SearchRange {
	/**
	 * Given a sorted array of integers, find the starting and ending position
	 * of a given target value.
	 * 
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 * 
	 * If the target is not found in the array, return [-1, -1].
	 * 
	 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
	 * 
	 * @param A
	 * @param target
	 * @return
	 */
	public static int[] searchRange(int[] A, int target) {
		int[] range = new int[2];
		searchHead(A, range, target, 0, A.length - 1);
		if (range[0] == -1) {
			range[1] = -1;
			return range;
		} else {
			searchTail(A, range, target, 0, A.length - 1);
			return range;
		}
	}

	private static void searchHead(int[] A, int[] range, int target, int start,
			int end) {
		if (start > end) {
			range[0] = -1;
			return;
		}
		int mid = (start + end) / 2;
		if (A[mid] == target) {
			if (mid - 1 >= 0 && A[mid - 1] == A[mid])
				searchHead(A, range, target, start, mid - 1);
			else
				range[0] = mid;
		} else if (A[mid] > target) {
			searchHead(A, range, target, start, mid - 1);
		} else {
			searchHead(A, range, target, mid + 1, end);
		}
	}

	private static void searchTail(int[] A, int[] range, int target, int start,
			int end) {
		if (start > end) {
			range[1] = -1;
			return;
		}
		int mid = (start + end) / 2;
		if (A[mid] == target) {
			if (mid + 1 < A.length && A[mid + 1] == A[mid])
				searchHead(A, range, target, mid + 1, end);
			else
				range[1] = mid;
		} else if (A[mid] > target) {
			searchTail(A, range, target, start, mid - 1);
		} else {
			searchTail(A, range, target, mid + 1, end);
		}
	}

}
