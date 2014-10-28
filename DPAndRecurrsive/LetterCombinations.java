package leetcode.DPAndRecurrsive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LetterCombinations {
	/**
	 * Given a digit string, return all possible letter combinations that the
	 * number could represent.
	 * 
	 * A mapping of digit to letters (just like on the telephone buttons) is
	 * given below.
	 * 
	 * @param digits
	 * @return
	 */
	public static List<String> letterCombinations(String digits) {
		digits = digits.replace("1", "");
		List<String> result = new LinkedList<String>();
		char[] solution = new char[digits.length()];
		ArrayList<Character[]> matrix = new ArrayList<Character[]>();
		Character[] two = { 'a', 'b', 'c' };
		Character[] three = { 'd', 'e', 'f' };
		Character[] four = { 'g', 'h', 'i' };
		Character[] five = { 'j', 'k', 'l' };
		Character[] six = { 'm', 'n', 'o' };
		Character[] seven = { 'p', 'q', 'r', 's' };
		Character[] eight = { 't', 'u', 'v' };
		Character[] nine = { 'w', 'x', 'y', 'z' };
		matrix.add(two);
		matrix.add(three);
		matrix.add(four);
		matrix.add(five);
		matrix.add(six);
		matrix.add(seven);
		matrix.add(eight);
		matrix.add(nine);
		build(digits, result, solution, 0, matrix);
		return result;
	}

	private static void build(String digits, List<String> result, char[] solution,
			int index, ArrayList<Character[]> matrix) {
		if (index == digits.length()) {
			result.add(String.valueOf(solution));
			return;
		}

		Character[] charList = matrix.get(digits.charAt(index) - '2');
		for (Character c : charList) {
			solution[index] = c;
			build(digits, result, solution, index + 1, matrix);
		}
	}
}
