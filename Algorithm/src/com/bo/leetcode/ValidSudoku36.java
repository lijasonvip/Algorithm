package com.bo.leetcode;

public class ValidSudoku36 {

	//判断数独是否合法
	
	public static boolean isValidSudoku(char[][] board){
		//9x9
		if (board == null || board.length != 9 || board[0].length != 9) {
			return false;
		}
		
		//定义三个标记数组 记下i数字是否出现
		boolean row[][] = new boolean[9][9];
		//
		boolean col[][] = new boolean[9][9];
		//
		boolean box[][][] = new boolean[3][3][9];
		
		for(int r=0; r<board.length;r++){
			for(int c=0;c<board[0].length;c++){
				char ch = board[r][c];
				if (ch == '.') {
					continue;
				}
				else if (ch >= '1' && ch <= '9') {
					int num = ch -'0' - 1;  //因为下标是从0开始的-1
					if (row[r][num]) {
						return false;
					}
					row[r][num] = true;
					if(col[c][num])
						return false;
					col[c][num] = true;
					if (box[r/3][c/3][num]) {
						return false;
					}
					box[r/3][c/3][num] = true;
				}else {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isValidSudoku2(char[][] board) {
	    int [] vset = new int [9];
	    int [] hset = new int [9];
	    int [] bckt = new int [9];
	    int idx = 0;
	    for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	            if (board[i][j] != '.') {
	                idx = 1 << (board[i][j] - '0') ;
	                if ((hset[i] & idx) > 0 ||
	                    (vset[j] & idx) > 0 ||
	                    (bckt[(i / 3) * 3 + j / 3] & idx) > 0) return false;
	                hset[i] |= idx;
	                vset[j] |= idx;
	                bckt[(i / 3) * 3 + j / 3] |= idx;
	            }
	        }
	    }
	    return true;
	}
}
