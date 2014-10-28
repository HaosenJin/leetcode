package leetcode.DPAndRecurrsive;

public class WordSearch {
	/**
	 * Given a 2D board and a word, find if the word exists in the grid.
	 * 
	 * The word can be constructed from letters of sequentially adjacent cell,
	 * where "adjacent" cells are those horizontally or vertically neighboring.
	 * The same letter cell may not be used more than once.
	 * 
	 * For example, Given board =
	 * 
	 * [ ["ABCE"], ["SFCS"], ["ADEE"] ] word = "ABCCED", -> returns true, word =
	 * "SEE", -> returns true, word = "ABCB", -> returns false.
	 * 
	 * @param board
	 * @param word
	 * @return
	 */
	public static boolean exist(char[][] board, String word) {
		if (word == null || word.isEmpty())
			return true;
		if (board == null || board.length == 0 || board[0].length == 0)
			return false;
		char[] wordChars = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == wordChars[0])
					if (wordSearchHelper(board, wordChars, 0, i, j))
						return true;
			}
		}
		return false;
	}

	private static boolean wordSearchHelper(char[][] board, char[] word,
			int word_index, int row, int col) {
		if (word_index == word.length)
			return true;
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
			return false;
		if (board[row][col] != word[word_index])
			return false;
		board[row][col] = '\0';
		boolean result = wordSearchHelper(board, word, word_index + 1, row + 1,
				col)
				|| wordSearchHelper(board, word, word_index + 1, row - 1, col)
				|| wordSearchHelper(board, word, word_index + 1, row, col + 1)
				|| wordSearchHelper(board, word, word_index + 1, row, col - 1);
		if (!result)
			board[row][col] = word[word_index];
		return result;
	}
	

}
