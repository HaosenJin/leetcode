package leetcode.DPAndRecurrsive;

public class ClimbingStairs {
	/**
	 * You are climbing a stair case. It takes n steps to reach to the top.
	 * 
	 * Each time you can either climb 1 or 2 steps. In how many distinct ways
	 * can you climb to the top?
	 * 
	 * @param n
	 * @return
	 */
	public static int climbStairs(int n) {
		int[] map = new int[n + 1];
		return climbStairs(n, map);
	}

	private static int climbStairs(int n, int[] map) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		if (map[n] > 0)
			return map[n];
		map[n] = climbStairs(n - 1, map) + climbStairs(n - 2, map);
		return map[n];
	}
}
