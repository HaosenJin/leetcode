package leetcode.DPAndRecurrsive;

import java.util.LinkedList;
import java.util.List;

public class TextJustification {
	/**
	 * Given an array of words and a length L, format the text such that each
	 * line has exactly L characters and is fully (left and right) justified.
	 * 
	 * You should pack your words in a greedy approach; that is, pack as many
	 * words as you can in each line. Pad extra spaces ' ' when necessary so
	 * that each line has exactly L characters.
	 * 
	 * Extra spaces between words should be distributed as evenly as possible.
	 * If the number of spaces on a line do not divide evenly between words, the
	 * empty slots on the left will be assigned more spaces than the slots on
	 * the right.
	 * 
	 * For the last line of text, it should be left justified and no extra space
	 * is inserted between words.
	 * 
	 * For example, words: ["This", "is", "an", "example", "of", "text",
	 * "justification."] L: 16.
	 * 
	 * Return the formatted lines as:
	 * 
	 * [ "This    is    an",
	 * 
	 * "example  of text",
	 * 
	 * "justification.  " ]
	 * 
	 * Note: Each word is guaranteed not to exceed L in length.
	 * 
	 * Corner Cases: A line other than the last line might contain only one
	 * word. What should you do in this case? In this case, that line should be
	 * left-justified.
	 * 
	 * @param words
	 * @param L
	 * @return
	 */
	public static List<String> fullJustify(String[] words, int L) {
		List<String> result = new LinkedList<String>();
		int i = 0;
		StringBuffer sb;
		while (i < words.length) {
			sb = new StringBuffer();
			int j = i;
			while (j < words.length && length(words, i, j) < L) {
				j++;
			}
			// last line
			if (j == words.length) {		
				for (int k = i; k < j; k++) {
					sb.append(words[k]);
					if (k != j - 1)
						sb.append(" ");
				}
				while (sb.length() < L)
					sb.append(" ");
				result.add(sb.toString());
				break;
			} else if (length(words, i, j) == L) {
				// exactly fit in a line
				for (int k = i; k <= j; k++) {
					sb.append(words[k]);
					if (k != j)
						sb.append(" ");
				}
				result.add(sb.toString());
				i = j + 1;
			} else {
				result.add(buildLine(words, i, j - 1, L));
				i = j;
			}
		}
		return result;
	}

	private static String buildLine(String[] words, int start, int end, int L) {
		StringBuffer sb = new StringBuffer();
		int words_len = 0;
		for (int i = start; i <= end; i++) {
			words_len = words_len + words[i].length();
		}
		int space_num = end - start;
		int space_len = L - words_len;
		if (space_num == 0) {
			// one word only
			sb.append(words[start]);
			while (sb.length() < L)
				sb.append(" ");
		} else {
			int ordinarySpace = space_len / space_num;
			int longSpace = space_len % space_num;
			for (int i = start; i <= end; i++) {
				sb.append(words[i]);
				if (i != end) {
					for (int j = 0; j < ordinarySpace; j++) {
						sb.append(" ");
					}
				}
				if (i - start < longSpace)
					sb.append(" ");
			}
		}
		return sb.toString();
	}

	private static int length(String[] words, int start, int end) {
		int len = 0;
		for (int i = start; i <= end; i++) {
			len = len + words[i].length() + 1;
		}
		len--;
		return len;
	}
}
