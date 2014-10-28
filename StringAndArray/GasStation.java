package leetcode.StringAndArray;

public class GasStation {
	/**
	 * There are N gas stations along a circular route, where the amount of gas
	 * at station i is gas[i].
	 * 
	 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
	 * travel from station i to its next station (i+1). You begin the journey
	 * with an empty tank at one of the gas stations.
	 * 
	 * Return the starting gas station's index if you can travel around the
	 * circuit once, otherwise return -1.
	 * 
	 * Note: The solution is guaranteed to be unique.
	 * 
	 * @param gas
	 * @param cost
	 * @return
	 */
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		int length = gas.length;
		int[] p = new int[gas.length];
		int sum = 0;
		for (int i = 0; i < length; i++) {
			p[i] = gas[i] - cost[i];
			sum = sum + p[i];
		}

		if (sum < 0) {
			return -1;
		}
		int i = maxSubarraySumIndex(p);
		if (i < 0)
			return -1;
		if (canCompleteCircuitHelper(gas, cost, i))
			return i;
		else
			return -1;
	}

	private static int maxSubarraySumIndex(int[] p) {
		int index = 0;
		int sum = 0;
		int max = Integer.MIN_VALUE;
		int max_index = -1;
		for (int i = 0; i < p.length * 2; i++) {
			sum = sum + p[i % p.length];
			if (sum < 0) {
				sum = 0;
				index = (i + 1) % p.length;
			}
			if (sum > max) {
				max = sum;
				max_index = index;
			}
		}
		return max_index;
	}

	private static boolean canCompleteCircuitHelper(int[] gas, int[] cost, int i) {
		int remaining_gas = 0;
		for (int j = 0, k = i; j < cost.length; j++, k = (k + 1) % gas.length) {
			remaining_gas = remaining_gas + gas[k] - cost[k];
			if (remaining_gas < 0) {
				return false;
			}
		}
		return true;
	}
}
