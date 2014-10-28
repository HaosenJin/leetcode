package leetcode.DPAndRecurrsive;

import java.util.LinkedList;

public class Sudoku {
	/**
	 * Write a program to solve a Sudoku puzzle by filling the empty cells.
	 * 
	 * Empty cells are indicated by the character '.'.
	 * 
	 * You may assume that there will be only one unique solution.
	 * 
	 * @param board
	 */
	public static void solveSudoku(char[][] board) {
		solveSudokuHelper(board, 0, 0);
	}

	private static boolean solveSudokuHelper(char[][] board, int i, int j) {
		if (board[i][j] != '.') {
			if (i == board.length - 1 && j == board[0].length - 1)// solve
				return true;
			else if (j < board[0].length - 1)
				return solveSudokuHelper(board, i, j + 1);
			else
				return solveSudokuHelper(board, i + 1, 0);
		} else {
			for (char k = '1'; k <= '9'; k++) {
				board[i][j] = k;
				if (isValidSudoku(board, i, j)) {
					if (i == board.length - 1 && j == board[0].length - 1)// solve
						return true;
					else if (j < board[0].length - 1) {
						if (solveSudokuHelper(board, i, j + 1))
							return true;
					} else {
						if (solveSudokuHelper(board, i + 1, 0))
							return true;
					}
				}
			}
			board[i][j] = '.';
			return false;
		}
	}

	private static LinkedList<Character> availableCharacters(char[][] board,
			int i, int j) {
		LinkedList<Character> result = new LinkedList<Character>();
		for (char k = '1'; k <= '9'; k++) {
			result.add(k);
		}

		for (int k = 0; k < board[0].length; k++) {
			if (result.contains(board[i][k]))
				result.remove((Character) board[i][k]);
		}

		for (int k = 0; k < board.length; k++) {
			if (result.contains(board[k][j]))
				result.remove((Character) board[k][j]);
		}
		return result;
	}

	/**
	 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
	 * 
	 * The Sudoku board could be partially filled, where empty cells are filled
	 * with the character '.'.
	 * 
	 * A valid Sudoku board (partially filled) is not necessarily solvable. Only
	 * the filled cells need to be validated.
	 * 
	 * @param board
	 * @return
	 */
	public static boolean isValidSudoku(char[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!isValidSudoku(board, i, j))
					return false;
			}
		}
		return true;
	}

	private static boolean isValidSudoku(char[][] board, int x, int y) {
		if (board[x][y] == '.')
			return true;
		for (int i = 0; i < board.length; i++) {
			if (i != x && board[i][y] == board[x][y])
				return false;
		}

		for (int j = 0; j < board[0].length; j++) {
			if (j != y && board[x][y] == board[x][j])
				return false;
		}

		for (int i = x / 3 * 3; i < x / 3 * 3 + 3; i++) {
			for (int j = y / 3 * 3; j < y / 3 * 3 + 3; j++) {
				if (x != i && y != j && board[i][j] == board[x][y])
					return false;
			}
		}
		return true;
	}

}
