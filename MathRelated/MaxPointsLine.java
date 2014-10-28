package leetcode.MathRelated;

public class MaxPointsLine {
	public static class Point {
		public int x;
		public int y;

		public Point() {
			x = 0;
			y = 0;
		}

		public Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	/**
	 * Given n points on a 2D plane, find the maximum number of points that lie
	 * on the same straight line.
	 * 
	 * [(0,0),(0,0)]
	 * 
	 * [(0,0),(1,0)]
	 * 
	 * @param points
	 * @return
	 */
	public static int maxPoints(Point[] points) {
		if (points.length <= 2)
			return points.length;
		int max = 0;
		Double epsilon = 0.00001;
		for (int i = 0; i < points.length; i++) {
			Point a = points[i];
			if (countIdenticalPoint(points, a) > max)
				max = countIdenticalPoint(points, a);
			for (int j = i + 1; j < points.length; j++) {
				Point b = points[j];
				if (equals(a, b))
					continue;
				int count = countIdenticalPoint(points, a)
						+ countIdenticalPoint(points, b);
				double slope = 0;
				boolean vertical = false;

				if (a.x == b.x) {
					vertical = true;
				} else {
					slope = (a.y - b.y) / (double) (a.x - b.x);
				}

				for (int k = j + 1; k < points.length; k++) {
					Point current_point = points[k];
					if (!equals(a, current_point) && !equals(b, current_point)) {
						if (vertical) {
							if (current_point.x == a.x) {
								count++;
							}
						} else {
							double current_slope = (a.y - current_point.y)
									/ (double) (a.x - current_point.x);
							if (Math.abs(slope - current_slope) < epsilon) {
								count++;
							}
						}
					}
				}
				if (count > max)
					max = count;
			}
		}
		return max;
	}

	private static int countIdenticalPoint(Point[] points, Point p) {
		int count = 0;
		for (Point point : points) {
			if (point.x == p.x && point.y == p.y) {
				count++;
			}
		}
		return count;
	}

	private static boolean equals(Point p1, Point p2) {
		return p1.x == p2.x && p1.y == p2.y;
	}

}
