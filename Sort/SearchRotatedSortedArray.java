package leetcode.Sort;

public class SearchRotatedSortedArray {
	/**
	 * Follow up for "Search in Rotated Sorted Array": What if duplicates are
	 * allowed?
	 * 
	 * Would this affect the run-time complexity? How and why?
	 * 
	 * Write a function to determine if a given target is in the array.
	 * 
	 * @param A
	 * @param target
	 * @return
	 */
	public static int search(int[] A, int target) {
		int rotatedPosition = findRotatedPosition(A, 0, A.length - 1);
		return rotatedPosition;
		/*
		 * if (rotatedPosition > 0) { if (target >= A[rotatedPosition] && target
		 * <= A[A.length - 1]) return binarySearch(A, rotatedPosition, A.length
		 * - 1, target); else return binarySearch(A, 0, rotatedPosition,
		 * target); } else { return binarySearch(A, 0, A.length - 1, target); }
		 */
	}

	private int search(int[] A, int target, int start, int end) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (A[mid] == target)
			return mid;
		if (A[mid] > A[start]) {
			if (target >= A[start] && target <= A[mid]) {
				return search(A, target, start, mid - 1);
			} else {
				return search(A, target, mid + 1, end);
			}
		} else if (A[mid] < A[end]) {
			if (target >= A[mid] && target <= A[end]) {
				return search(A, target, mid + 1, end);
			} else {
				return search(A, target, start, mid - 1);
			}
		} else {
			int result = search(A, target, start, mid - 1);
			if (result == -1) {
				result = search(A, target, mid + 1, end);
			}
			return result;
		}
	}

	private static int findRotatedPosition(int[] A, int start, int end) {
		if (start < 0 || end >= A.length || start > end)
			return -1;
		int mid = (start + end) / 2;
		if (mid - 1 >= 0 && A[mid] < A[mid - 1])
			return mid;
		if (mid + 1 < A.length && A[mid + 1] < A[mid])
			return mid + 1;
		if (A[mid] < A[start])
			return findRotatedPosition(A, start, mid - 1);
		else if (A[mid] > A[end]) {
			return findRotatedPosition(A, mid + 1, end);
		} else {
			int result = findRotatedPosition(A, start, mid - 1);
			if (result == -1)
				result = findRotatedPosition(A, mid + 1, end);
			return result;
		}
	}

	private static boolean binarySearch(int[] A, int start, int end, int target) {
		if (start < 0 || end >= A.length || start > end)
			return false;
		int mid = (start + end) / 2;
		if (A[mid] == target)
			return true;
		else if (A[mid] > target)
			return binarySearch(A, start, mid - 1, target);
		else {
			return binarySearch(A, mid + 1, end, target);
		}
	}
}
