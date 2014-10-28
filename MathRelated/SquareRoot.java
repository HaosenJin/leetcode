package leetcode.MathRelated;

public class SquareRoot {
	/**
	 * Implement int sqrt(int x).
	 * 
	 * Compute and return the square root of x.
	 * 
	 * @param x
	 * @return
	 */
	public int sqrt(int x) {
		double g = x;
		while (Math.abs(g * g - x) > 0.000001) {
			g = (g + x / g) / 2;
		}
		return (int) g;
	}
}
