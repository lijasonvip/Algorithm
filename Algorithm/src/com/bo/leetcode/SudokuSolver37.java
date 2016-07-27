package com.bo.leetcode;

public class SudokuSolver37 {

	/**
	 * 58.8%
	 */
	public static void solveSudoku(char[][] board) {
		boolean res = solve(board, 0);
		if(!res)
			throw new IllegalArgumentException();
	}
	
	public static boolean solve(char[][] board, int index){
		if (index == 81) {
			return true;
		}
		int i = index / 9, j= index % 9;  //row and col
		if (board[i][j] != '.') {
			return solve(board, index+1);
		}
		for(char v = '1'; v <= '9'; v++){
			board[i][j] = v;
			if (isValid(board, i, j) && solve(board, index+1)) {
				return true;
			}
		}
		board[i][j] = '.';
		return false;
	}
	
	public static boolean isValid(char[][] board, int i, int j){
		int row, col;
		//check col
		for(col=0;col<9;col++){
			if (board[i][col] == '.' || col == j) {
				continue;
			}
			if (board[i][col] == board[i][j]) {
				return false;
			}
		}
		//check row
		for(row=0;row < 9;row ++ ){
			if (board[row][j] == '.' || row == i) {
				continue;
			}
			if(board[row][j] == board[i][j]){
				return false;
			}
		}
		//check box
		int start = (i/3)*3, end = (j/3)*3;
		for(row=start;row<start+3;row++){
			for(col=end;col<end+3;col++){
				if (board[row][col] == '.' || (row == i && col == j)) {
					continue;
				}
				if (board[row][col] == board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	

}
