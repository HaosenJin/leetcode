package leetcode.MathRelated;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Histogram {
	/**
	 * Given n non-negative integers representing the histogram's bar height
	 * where the width of each bar is 1, find the area of largest rectangle in
	 * the histogram.
	 * 
	 * @param height
	 * @return
	 */
	public static int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0)
			return 0;
		if (height.length == 1)
			return height[0];
		Map<Integer, Integer> left = new HashMap<Integer, Integer>();
		Map<Integer, Integer> right = new HashMap<Integer, Integer>();

		// left
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
				left.put(stack.pop(), stack.isEmpty() ? -1 : stack.peek());
			}
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			left.put(stack.pop(), stack.isEmpty() ? -1 : stack.peek());
		}

		// right
		for (int i = height.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
				right.put(stack.pop(), stack.isEmpty() ? height.length - 1
						: stack.peek() - 1);
			}
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			right.put(stack.pop(),
					stack.isEmpty() ? height.length - 1 : stack.peek() - 1);
		}
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			int currentArea = height[i] * (right.get(i) - left.get(i));
			if (currentArea > maxArea)
				maxArea = currentArea;
		}
		return maxArea;
	}
}
