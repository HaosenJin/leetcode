package leetcode.MathRelated;

public class Candy {
	/**
	 * There are N children standing in a line. Each child is assigned a rating
	 * value.
	 * 
	 * You are giving candies to these children subjected to the following
	 * requirements:
	 * 
	 * Each child must have at least one candy. Children with a higher rating
	 * get more candies than their neighbors. What is the minimum candies you
	 * must give?
	 * ------------------------------------------------------------------
	 * ratings: 1 2 4 4 3
	 * -------------------------------------------------------------------
	 * candy: 1 2 3 2 1
	 * 
	 * @param ratings
	 * @return
	 */
	public static int candy(int[] ratings) {
		if (identicalRatings(ratings))
			return ratings.length;
		int[] B = new int[ratings.length];
		boolean[] local_minimum = findLocalMinimum(ratings);

		// from left to right
		for (int i = 0; i < ratings.length; i++) {
			if (local_minimum[i]) {
				B[i] = 1;
			} else if (i > 0 && ratings[i] > ratings[i - 1]) {
				B[i] = B[i - 1] + 1;
			} else if (i > 0 && ratings[i] == ratings[i - 1]) {
				B[i] = 1;
			}
		}

		// from right to left
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (!local_minimum[i]) {
				if (ratings[i] > ratings[i + 1]) {
					B[i] = Math.max(B[i], B[i + 1] + 1);
				}
				if (ratings[i] == ratings[i + 1]) {
					B[i] = Math.max(B[i], 1);
				}
			}
		}

		int result = 0;
		for (int i = 0; i < B.length; i++) {
			result += B[i];
		}
		return result;
	}

	private static boolean identicalRatings(int[] ratings) {
		if (ratings.length == 1)
			return true;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] != ratings[i - 1]) {
				return false;
			}
		}
		return true;
	}

	private static boolean[] findLocalMinimum(int[] ratings) {
		boolean[] local_minimum = new boolean[ratings.length];
		// find local minimum
		for (int i = 0; i < local_minimum.length; i++) {
			if (i == 0 && ratings[i] <= ratings[i + 1]
					|| i == ratings.length - 1 && ratings[i] <= ratings[i - 1]) {
				local_minimum[i] = true;
			} else if (i != 0 && i != ratings.length - 1
					&& ratings[i] <= ratings[i - 1]
					&& ratings[i] <= ratings[i + 1]) {
				local_minimum[i] = true;
			} else {
				local_minimum[i] = false;
			}
		}
		return local_minimum;
	}
}
