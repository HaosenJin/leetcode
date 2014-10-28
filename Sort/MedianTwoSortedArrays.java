package leetcode.Sort;

public class MedianTwoSortedArrays {
	/**
	 * There are two sorted arrays A and B of size m and n respectively. Find
	 * the median of the two sorted arrays. The overall run time complexity
	 * should be O(log (m+n)).
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static double findMedianSortedArrays(int A[], int B[]) {
		if (A == null || A.length == 0) {
			if (B.length % 2 != 0) {
				return B[B.length / 2];
			} else {
				return (B[B.length / 2 - 1] + B[B.length / 2]) / (double) 2;
			}
		}

		if (B == null || B.length == 0) {
			if (A.length % 2 != 0) {
				return A[A.length / 2];
			} else {
				return (A[A.length / 2 - 1] + A[A.length / 2]) / (double) 2;
			}
		}

		if ((A.length + B.length) % 2 != 0) {
			return findKth(A, 0, A.length - 1, B, 0, B.length - 1,
					(A.length + B.length) / 2 + 1);
		} else {
			int n1 = findKth(A, 0, A.length - 1, B, 0, B.length - 1,
					(A.length + B.length) / 2);
			int n2 = findKth(A, 0, A.length - 1, B, 0, B.length - 1,
					(A.length + B.length) / 2 + 1);
			return (n1 + n2) / (double) 2;
		}
	}

	private static int findKth(int A[], int a_start, int a_end, int B[],
			int b_start, int b_end, int k) {
		if (a_start > a_end) {
			return B[b_start + k - 1];
		}
		if (b_start > b_end) {
			return A[a_start + k - 1];
		}
			
		int amid = (a_start + a_end) / 2;
		int bmid = (b_start + b_end) / 2;
		int half_a = amid - a_start + 1;
		int half_b = bmid - b_start + 1;

		
		if (A[amid] > B[bmid]) {
			if (half_a + half_b <= k) {
				return findKth(A, a_start, a_end, B, bmid + 1, b_end, k
						- half_b);
			} else {
				return findKth(A, a_start, amid - 1, B, b_start, b_end, k);
			}
		} else {
			if (half_a + half_b <= k) {
				return findKth(A, amid + 1, a_end, B, b_start, b_end, k
						- half_a);
			} else {
				return findKth(A, a_start, a_end, B, b_start, bmid - 1, k);
			}
		}
	}
}
