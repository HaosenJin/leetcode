package leetcode.DPAndRecurrsive;

import java.util.LinkedList;
import java.util.List;

public class NQueens {
	/**
	 * The n-queens puzzle is the problem of placing n queens on an n¡Án
	 * chessboard such that no two queens attack each other.
	 * 
	 * Given an integer n, return all distinct solutions to the n-queens puzzle.
	 * 
	 * Each solution contains a distinct board configuration of the n-queens'
	 * placement, where 'Q' and '.' both indicate a queen and an empty space
	 * respectively.
	 * 
	 * For example, There exist two distinct solutions to the 4-queens puzzle:
	 * 
	 * [ [".Q..", // Solution 1 "...Q", "Q...", "..Q."],
	 * 
	 * ["..Q.", // Solution 2 "Q...", "...Q", ".Q.."] ]
	 * 
	 * @param n
	 * @return
	 */
	public List<String[]> solveNQueens(int n) {
		List<String[]> result = new LinkedList<String[]>();
		int[] solution = new int[n];
		solve(result, solution, 0);
		return result;
	}

	private void solve(List<String[]> result, int[] solution, int index) {
		if (index == solution.length)
			result.add(generateBoard(solution));
		for (int i = 0; i < solution.length; i++) {
			if (checkColumn(solution, index, i)) {
				solution[index] = i;
				solve(result, solution, index + 1);
			}
		}
	}

	private boolean checkColumn(int[] solution, int index, int column) {
		for (int i = 0; i < index; i++) {
			if (solution[i] == column)
				return false;
			if (Math.abs(solution[i] - column) == index - i)
				return false;
		}
		return true;
	}

	private String[] generateBoard(int[] solution) {
		String[] board = new String[solution.length];
		for (int i = 0; i < board.length; i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < board.length; j++) {
				if (solution[i] == j)
					sb.append("Q");
				else {
					sb.append(".");
				}
			}
			board[i] = sb.toString();
		}
		return board;
	}

	/**
	 * Follow up for N-Queens problem.
	 * 
	 * Now, instead outputting board configurations, return the total number of
	 * distinct solutions.
	 * 
	 * @param n
	 * @return
	 */
	public int totalNQueens(int n) {
		return solveNQueens(n).size();
	}
}
