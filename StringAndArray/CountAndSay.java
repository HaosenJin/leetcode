package leetcode.StringAndArray;

import java.util.LinkedList;

public class CountAndSay {
	/**
	 * The count-and-say sequence is the sequence of integers beginning as
	 * follows: 1, 11, 21, 1211, 111221, ...
	 * 
	 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is
	 * read off as "one 2, then one 1" or 1211. Given an integer n, generate the
	 * nth sequence.
	 * 
	 * Note: The sequence of integers will be represented as a string.
	 * 
	 * @param n
	 * @return
	 */
	public String countAndSay(int n) {
		if (n == 0)
			return "";
		LinkedList<Integer> sequence = new LinkedList<Integer>();
		sequence.add(1);
		int i = 1;
		while (i < n) {
			int last = sequence.get(0);
			int count = 1;
			int j = 1;
			LinkedList<Integer> newSeq = new LinkedList<Integer>();
			while (j < sequence.size()) {
				if (sequence.get(j) == sequence.get(j - 1)) {
					count++;
				} else if (sequence.get(j) != sequence.get(j - 1)) {
					newSeq.add(count);
					newSeq.add(last);
					last = sequence.get(j);
					count = 1;
				}
				j++;
			}
			newSeq.add(count);
			newSeq.add(last);
			sequence = newSeq;
			i++;
		}

		StringBuffer sb = new StringBuffer();
		for (Integer integer : sequence) {
			sb.append(integer);
		}
		return sb.toString();
	}
}
