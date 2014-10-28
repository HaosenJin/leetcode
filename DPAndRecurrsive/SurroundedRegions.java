package leetcode.DPAndRecurrsive;

public class SurroundedRegions {
	/**
	 * Given a 2D board containing 'X' and 'O', capture all regions surrounded
	 * by 'X'.
	 * 
	 * A region is captured by flipping all 'O's into 'X's in that surrounded
	 * region.
	 * 
	 * For example, -----------------------------------------------------------
	 * X X X X ----------------------------------------------------------------
	 * X O O X------------------------------------------------------------------
	 * X X O X------------------------------------------------------------------
	 * X O X X---------------------------------------------------------------
	 * 
	 * After running your function, the board should be:
	 * 
	 * X X X X-----------------------------------------------------------------
	 * X X X X------------------------------------------------------------------
	 * X X X X-----------------------------------------------------------------
	 * X O X X-----------------------------------------------------------------
	 * 
	 * @param board
	 */
	public static void solve(char[][] board) {
		if (board == null || board.length == 0)
			return;
		boolean[][] needFlip = preCompute(board);
		for (int i = 0; i < needFlip.length; i++) {
			for (int j = 0; j < needFlip[0].length; j++) {
				if (needFlip[i][j])
					board[i][j] = 'X';
			}
		}
	}

	private static boolean[][] preCompute(char[][] board) {
		int row_len = board.length;
		int col_len = board[0].length;
		boolean[][] needFlip = new boolean[board.length][board[0].length];
		for (int i = 0; i < row_len; i++) {
			for (int j = 0; j < col_len; j++) {
				if (board[i][j] == 'X' || onTheEdge(row_len, col_len, i, j)) {
					needFlip[i][j] = false;
				} else {
					needFlip[i][j] = true;
				}
			}
		}

		for (int i = 0; i < row_len; i++) {
			if (board[i][0] == 'O') {
				preComputeHelper(needFlip, i, 1);
			}

			if (board[i][col_len - 1] == 'O') {
				preComputeHelper(needFlip, i, col_len - 2);
			}
		}

		for (int j = 0; j < col_len; j++) {
			if (board[0][j] == 'O') {
				preComputeHelper(needFlip, 1, j);
			}

			if (board[row_len - 1][j] == 'O') {
				preComputeHelper(needFlip, row_len - 2, j);
			}
		}

		return needFlip;
	}

	private static void preComputeHelper(boolean[][] needFlip, int i, int j) {
		if (i < 0 || j < 0 || i >= needFlip.length || j >= needFlip[0].length
				|| onTheEdge(needFlip.length, needFlip[0].length, i, j))
			return;
		if (needFlip[i][j] == true) {
			needFlip[i][j] = false;
			preComputeHelper(needFlip, i + 1, j);
			preComputeHelper(needFlip, i - 1, j);
			preComputeHelper(needFlip, i, j + 1);
			preComputeHelper(needFlip, i, j - 1);
		}
	}

	public static void solve2(char[][] board) {
		if (board == null || board.length == 0)
			return;
		int row_len = board.length;
		int col_len = board[0].length;

		for (int i = 0; i < row_len; i++) {
			if (board[i][0] == 'O') {
				preComputeHelper2(board, i, 1);
			}

			if (board[i][col_len - 1] == 'O') {
				preComputeHelper2(board, i, col_len - 2);
			}
		}

		for (int j = 0; j < col_len; j++) {
			if (board[0][j] == 'O') {
				preComputeHelper2(board, 1, j);
			}

			if (board[row_len - 1][j] == 'O') {
				preComputeHelper2(board, row_len - 2, j);
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!onTheEdge(row_len, col_len, i, j)) {
					if (board[i][j] == 'O')
						board[i][j] = 'X';
					if (board[i][j] == 'Z')
						board[i][j] = 'O';
				}
			}
		}
	}

	private static void preComputeHelper2(char[][] board, int i, int j) {
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
				|| onTheEdge(board.length, board[0].length, i, j))
			return;
		if (board[i][j] == 'O') {
			board[i][j] = 'Z';
			preComputeHelper2(board, i + 1, j);
			preComputeHelper2(board, i - 1, j);
			preComputeHelper2(board, i, j + 1);
			preComputeHelper2(board, i, j - 1);
		}
	}

	private static boolean onTheEdge(int row_len, int col_len, int i, int j) {
		return i == 0 || i == row_len - 1 || j == 0 || j == col_len - 1;
	}
}
