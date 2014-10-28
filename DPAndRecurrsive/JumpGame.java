package leetcode.DPAndRecurrsive;

public class JumpGame {
	/**
	 * Given an array of non-negative integers, you are initially positioned at
	 * the first index of the array.
	 * 
	 * Each element in the array represents your maximum jump length at that
	 * position.
	 * 
	 * Determine if you are able to reach the last index.
	 * 
	 * For example: A = [2,3,1,1,4], return true.
	 * 
	 * A = [3,2,1,0,4], return false.
	 * 
	 * @param A
	 * @return
	 */
	public static boolean canJump(int[] A) {
		int[] jump = new int[A.length];
		jump[0] = 0;
		for (int i = 1; i < A.length; i++) {
			jump[i] = Math.max(A[i - 1], jump[i - 1]) - 1;
			if (jump[i] < 0)
				return false;
		}
		return jump[A.length - 1] >= 0;
	}

	public static boolean canJump3(int[] A) {
		int maxCover = 0;
		for (int start = 0; start <= maxCover && start < A.length; start++) {
			if (A[start] + start > maxCover)
				maxCover = A[start] + start;
			if (maxCover >= A.length - 1)
				return true;
		}
		return false;
	}

	public static boolean canJump2(int[] A) {
		int[] map = new int[A.length];
		for (int i = 0; i < map.length; i++) {
			map[i] = -1;
		}
		return canJump2(A, 0, map);
	}

	private static boolean canJump2(int[] A, int index, int[] map) {
		if (index >= A.length - 1)
			return true;
		if (map[index] == 1)
			return true;
		if (map[index] == 0)
			return false;
		for (int i = index + 1; i <= index + A[index]; i++) {
			if (canJump2(A, i, map)) {
				map[index] = 1;
				return true;
			}
		}
		map[index] = 0;
		return false;
	}

	/**
	 * Given an array of non-negative integers, you are initially positioned at
	 * the first index of the array.
	 * 
	 * Each element in the array represents your maximum jump length at that
	 * position.
	 * 
	 * Your goal is to reach the last index in the minimum number of jumps.
	 * 
	 * For example: Given array A = [2,3,1,1,4]
	 * 
	 * The minimum number of jumps to reach the last index is 2. (Jump 1 step
	 * from index 0 to 1, then 3 steps to the last index.)
	 * 
	 * @param A
	 * @return
	 */
	public int jump(int[] A) {
		int[] jump = new int[A.length];
		jump[0] = 0;
		for (int i = 1; i < jump.length; i++) {
			jump[i] = Integer.MAX_VALUE;
		}
		for (int i = 1; i < jump.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (A[j] + j >= i) {
					if (jump[j] + 1 < jump[i])
						jump[i] = jump[j] + 1;
				}
			}
		}
		return jump[jump.length - 1];
	}

	public int jump2(int[] A) {
		if (A == null || A.length == 0 || A.length == 1) {
			return 0;
		}
		int count = 0;
		int end = 0;
		int start = 0;
		while (end < A.length) {
			int cover = 0;
			count++;
			for (int i = start; i <= end; i++) {
				if (A[i] + i > cover) {
					cover = A[i] + i;
				}
				if (cover >= A.length - 1)
					return count;
			}
			start = end + 1;
			end = cover;
		}
		return count;
	}
}
