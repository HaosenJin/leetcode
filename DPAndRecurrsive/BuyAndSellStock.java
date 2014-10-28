package leetcode.DPAndRecurrsive;

public class BuyAndSellStock {

	private static int[] preCompute(int[] prices) {
		int[] differ = new int[prices.length - 1];

		for (int i = 1; i < prices.length; i++) {
			differ[i - 1] = prices[i] - prices[i - 1];
		}

		// drop head negative values
		for (int i = 0; i < differ.length; i++) {
			if (differ[i] < 0)
				differ[i] = 0;
			if (differ[i] > 0)
				break;
		}

		// drop tail negative values
		for (int i = differ.length - 1; i >= 0; i--) {
			if (differ[i] < 0)
				differ[i] = 0;
			if (differ[i] > 0)
				break;
		}
		return differ;
	}

	/**
	 * Say you have an array for which the ith element is the price of a given
	 * stock on day i.
	 * 
	 * If you were only permitted to complete at most one transaction (ie, buy
	 * one and sell one share of the stock), design an algorithm to find the
	 * maximum profit.
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		if (prices.length == 0 || prices == null)
			return 0;
		int[] differ = preCompute(prices);
		int max = 0;
		int runner = 0;
		for (int i = 0; i < differ.length; i++) {
			runner += differ[i];
			if (runner > max)
				max = runner;
			if (runner < 0)
				runner = 0;
		}
		return max;
	}

	/**
	 * Say you have an array for which the ith element is the price of a given
	 * stock on day i.
	 * 
	 * Design an algorithm to find the maximum profit. You may complete as many
	 * transactions as you like (ie, buy one and sell one share of the stock
	 * multiple times). However, you may not engage in multiple transactions at
	 * the same time (ie, you must sell the stock before you buy again).
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit2(int[] prices) {
		if (prices.length == 0 || prices == null)
			return 0;
		int[] differ = preCompute(prices);
		int profit = 0;
		for (int i = 0; i < differ.length; i++) {
			if (differ[i] > 0)
				profit += differ[i];
		}
		return profit;
	}

	/**
	 * Say you have an array for which the ith element is the price of a given
	 * stock on day i.
	 * 
	 * Design an algorithm to find the maximum profit. You may complete at most
	 * two transactions.
	 * 
	 * Note: You may not engage in multiple transactions at the same time (ie,
	 * you must sell the stock before you buy again).
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit3(int[] prices) {
		if (prices.length == 0 || prices == null)
			return 0;
		int[] differ = preCompute(prices);
		int max = 0;
		int start = 0;
		int end = 0;
		int current_start = 0;
		int runner = 0;
		for (int i = 0; i < differ.length; i++) {
			runner += differ[i];
			if (runner < 0) {
				runner = 0;
				current_start = i + 1;
			}
			if (runner > max) {
				max = runner;
				end = i;
				start = current_start;
			}
		}

		int m1 = findMax(differ, 0, start - 1);
		int m2 = findMax(differ, end + 1, differ.length - 1);
		int m3 = Math.abs(findMin(differ, start, end));
		return max + Math.max(m1, Math.max(m2, m3));
	}

	private static int findMax(int[] differ, int start, int end) {
		if (start < 0 || end >= differ.length || start > end)
			return 0;
		int max = 0;
		int runner = 0;
		for (int i = start; i <= end; i++) {
			runner += differ[i];
			if (runner > max)
				max = runner;
			if (runner < 0)
				runner = 0;
		}
		return max;
	}

	private static int findMin(int[] differ, int start, int end) {
		if (start < 0 || end >= differ.length || start > end)
			return 0;
		int min = 0;
		int runner = 0;
		for (int i = start; i <= end; i++) {
			runner += differ[i];
			if (runner < min)
				min = runner;
			if (runner > 0)
				runner = 0;
		}
		return min;
	}
}
