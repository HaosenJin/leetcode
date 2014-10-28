package leetcode.MathRelated;

public class Divide {
	/**
	 * Divide two integers without using multiplication, division and mod
	 * operator.
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static int divide(int dividend, int divisor) {
		if (dividend == 0)
			return 0;
		if (divisor == 1)
			return dividend;
		if (divisor == -1)
			return ~dividend + 1;

		long divisor_abs = Math.abs((long) divisor);
		long dividend_abs = Math.abs((long) dividend);
		if (dividend_abs < divisor_abs)
			return 0;
		if (dividend_abs == divisor_abs) {
			if (divisor < 0 && dividend > 0 || divisor > 0 && dividend < 0) {
				return -1;
			} else {
				return 1;
			}
		}
		int result = 0;
		int step = 0;

		while (divisor_abs <= dividend_abs) {
			divisor_abs = divisor_abs << 1;
			step++;
		}

		divisor_abs = divisor_abs >> 1;
		step--;
		while (divisor_abs >= Math.abs(divisor)) {
			if (dividend_abs >= divisor_abs) {
				dividend_abs = dividend_abs - divisor_abs;
				result += 1 << step;		
			}
			step--;
			divisor_abs = divisor_abs >> 1;
		}

		if (divisor < 0 && dividend > 0 || divisor > 0 && dividend < 0) {
			return ~result + 1;
		}

		return result;
	}
}
