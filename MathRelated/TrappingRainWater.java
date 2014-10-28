package leetcode.MathRelated;

import java.util.LinkedList;

import leetcode.Tree.MinimumDepth;

public class TrappingRainWater {
	/**
	 * Given n non-negative integers representing an elevation map where the
	 * width of each bar is 1, compute how much water it is able to trap after
	 * raining.
	 * 
	 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	 * 
	 * @param A
	 * @return
	 */
	public static int trap(int[] A) {
		if (A.length <= 2)
			return 0;
		LinkedList<Integer> localMaximum = new LinkedList<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (i == 0) {
				if (A[i] > A[i + 1]) {
					localMaximum.add(i);
				}
			} else if (i == A.length - 1) {
				if (A[i] > A[i - 1]) {
					localMaximum.add(i);
				}
			} else if (A[i] >= A[i - 1] && A[i] >= A[i + 1]) {
				localMaximum.add(i);
			}
		}

		int result = 0;
		int height = -1;
		for (int i = 0; i < A.length; i++) {
			if (localMaximum.contains(i)) {
				localMaximum.remove((Integer) i);
				if (localMaximum.isEmpty())
					break;
				height = A[localMaximum.get(0)] > A[i] ? A[i] : A[localMaximum
						.get(0)];
			} else if (height == -1) {
				continue;
			} else {
				if (height > A[i])
					result += height - A[i];
			}
		}
		return result;
	}

	public static int trap2(int[] A) {
		if (A.length <= 2)
			return 0;
		int result = 0;
		int[] left = new int[A.length];
		int[] right = new int[A.length];
		left[0] = 0;
		int max = A[0];
		for (int i = 1; i < right.length; i++) {
			left[i] = max;
			if (max < A[i])
				max = A[i];
		}
		right[A.length - 1] = 0;
		max = A[A.length - 1];

		for (int i = A.length - 2; i >= 0; i--) {
			right[i] = max;
			if (max < A[i])
				max = A[i];
			int val = Math.min(left[i], right[i]) - A[i];
			if (val > 0)
				result += val;
		}
		return result;
	}
}
