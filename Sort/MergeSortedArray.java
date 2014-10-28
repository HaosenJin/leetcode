package leetcode.Sort;

public class MergeSortedArray {
	/**
	 * Given two sorted integer arrays A and B, merge B into A as one sorted
	 * array.
	 * 
	 * Note: You may assume that A has enough space (size that is greater or
	 * equal to m + n) to hold additional elements from B. The number of
	 * elements initialized in A and B are m and n respectively.
	 * 
	 * @param A
	 * @param m
	 * @param B
	 * @param n
	 */
	public void merge(int A[], int m, int B[], int n) {
		int end = m + n - 1;
		int A_end = m - 1;
		int B_end = n - 1;
		while (B_end >= 0) {
			if (A_end >= 0 && A[A_end] > B[B_end]) {
				A[end--] = A[A_end--];
			} else {
				A[end--] = B[B_end--];
			}
		}
	}
}
