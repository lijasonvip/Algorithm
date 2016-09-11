package com.bo.dynamic;

import java.util.Scanner;

public class LongestCommonSubsequence {
	// define prefix of a sequence, Xi is sequence of X[1...m]
	// two sequence X[1...m], Y[1...n], Z[1...k] is LCS of X and Y
	// if Xm == Yn then Zk == Xm == Yn and Zk-1 is LCS of Xm-1 and Yn-1
	// else if Xm != Yn, Zk != Xm means Z is LCS of Xm-1 and Y
	// or Xm != Yn, Zk != Yn means Z is LCS of X and Yn-1

	// let c[i,j] denote length of Xi and Yj 's LCS, then
	// c[i,j] = 0, if i==0 or j==0
	// c[i, j] = c[i-1, j-1] + 1, if i,j>0 and Xi == Yj
	// c[i,j] = max(c[i-1,j], c[i,j-1]), if i,j>0 and Xi != Yj
	// b[i, j] to store the optimal solution
	public static int[][][] LCS_Length(String[] X, String[] Y) {
		int m = X.length;
		int n = Y.length;
		
		int[][] c = new int[m+1][n+1];
		int[][] b = new int[m+1][n+1];

		//i == 0 or j == 0, then c[i][j] = 0
		for (int i = 1; i <= m; i++)
			c[i][0] = 0;
		for (int j = 0; j <= n; j++) {
			c[0][j] = 0;
		}
		
		//use 1-3 represent four direction up,left,up-left 
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (X[i-1] == Y[j-1]) {
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = 3;
				} else if(c[i-1][j] >= c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = 1;
				}else{
					c[i][j] = c[i][j-1];
					b[i][j] = 2;
				}
			}
		}
		int[][][] result = new int[2][m][n];
		result[0] = c;
		result[1] =  b;
		
		return result;
	}
	//from b[m,n],follow the arrow to print
	public static void Print_LCS(int[][] b, String[] X, int i, int j){
		if(i==0 || j==0){
			return;
		}
		if(b[i][j] == 3){
			Print_LCS(b,X,i-1,j-1);
			System.out.print(X[i-1]);
		}else if(b[i][j] == 1){
			Print_LCS(b,X,i-1,j);
		}else{
			Print_LCS(b,X,i,j-1);
		}
	}
	
	public static void main(String... args){
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		String[] X = {"A","B","C","B","D","A","B"};
		String[] Y = {"B","D","C","A","B","A"};
		int[][][] result = LCS_Length(X,Y);
		Print_LCS(result[1],X,X.length,Y.length);
	}
}
