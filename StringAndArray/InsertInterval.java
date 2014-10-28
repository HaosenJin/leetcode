package leetcode.StringAndArray;

import java.util.List;

public class InsertInterval {
	public static class Interval {
		public int start;
		public int end;

		public Interval() {
			start = 0;
			end = 0;
		}

		public Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	/**
	 * Given a set of non-overlapping intervals, insert a new interval into the
	 * intervals (merge if necessary).
	 * 
	 * You may assume that the intervals were initially sorted according to
	 * their start times.
	 * 
	 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as
	 * [1,5],[6,9].
	 * 
	 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9]
	 * in as [1,2],[3,10],[12,16].
	 * 
	 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	 * 
	 * @param intervals
	 * @param newInterval
	 * @return
	 */
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if (intervals == null || !needInsert(intervals, newInterval)
				|| newInterval == null)
			return intervals;
		if (intervals.isEmpty()) {
			intervals.add(newInterval);
			return intervals;
		}
		Interval start = null;
		Interval end = null;
		int insertAfter = -1;
		int endIndex = -1;
		for (int i = 0; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			if (current.end <= newInterval.end
					&& current.start >= newInterval.start) {
				intervals.remove(i);
				i--;
				continue;
			} else if (current.end >= newInterval.start
					&& current.start < newInterval.start) {
				start = current;
			} else if (current.start <= newInterval.end
					&& current.end > newInterval.end) {
				end = current;
				endIndex = i;
			} else if (current.end < newInterval.start) {
				insertAfter = i;
			}
		}
		if (start == null && end == null) {
			intervals.add(insertAfter + 1, newInterval);
		} else if (start != null && end == null) {
			start.end = newInterval.end;
		} else if (start == null && end != null) {
			end.start = newInterval.start;
		} else {
			start.end = end.end;
			intervals.remove(endIndex);
		}
		return intervals;
	}

	private boolean needInsert(List<Interval> intervals, Interval newInterval) {
		for (int i = 0; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			if (current.start <= newInterval.start
					&& current.end >= newInterval.end)
				return false;
		}
		return true;
	}

	/**
	 * Given a collection of intervals, merge all overlapping intervals.
	 * 
	 * For example, Given [1,3],[2,6],[8,10],[15,18], return
	 * [1,6],[8,10],[15,18].
	 * 
	 * @param intervals
	 * @return
	 */
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null)
			return null;
		for (int i = 0; i < intervals.size(); i++) {
			for (int j = i + 1; j < intervals.size(); j++) {
				if (i + 1 < intervals.size()) {
					Interval current = intervals.get(i);
					Interval next = intervals.get(j);
					if (current.start <= next.start && current.end >= next.end) {
						next.start = current.start;
						next.end = current.end;
						intervals.remove(i);
						i--;
						break;
					} else if (next.start <= current.start
							&& next.end >= current.end) {
						intervals.remove(i);
						i--;
						break;
					} else if (current.end >= next.start
							&& next.start > current.start) {
						next.start = current.start;
						intervals.remove(i);
						i--;
						break;
					} else if (next.end >= current.start
							&& current.start > next.start) {
						next.end = current.end;
						intervals.remove(i);
						i--;
						break;
					}
				}
			}
		}
		return intervals;
	}
}
