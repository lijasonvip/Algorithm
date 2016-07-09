package com.bo.offer;

public class CircleMatrix {

	public static void PrintMatrixClockwisely(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		if (matrix == null || rows <= 0 || cols <= 0)
			return;
		int start = 0;
		while (2 * start < rows && 2 * start < cols) {
			PrintMatrixInCircle(matrix, start, rows, cols);
			start++;
		}
	}

	public static void PrintMatrixInCircle(int[][] matrix, int start, int rows, int cols) {
		int endX = cols - 1 - start;
		int endY = rows - 1 - start;

		// left to right
		for (int j = start; j <= endX; j++) {
			System.out.print(matrix[start][j] + " ");
		}

		// top to down
		if (start < endY) {
			for (int i = start + 1; i <= endY; i++) {
				System.out.print(matrix[i][endX] + " ");
			}
		}

		// right to left
		if (endX > start && endY > start) {
			for (int j = endX - 1; j >= start; j--) {
				System.out.print(matrix[endY][j] + " ");
			}
		}

		// down to top
		if (start < endX && start < endY - 1) {
			for(int i=endY-1;i>=start+1;i--){
				System.out.print(matrix[i][start]+" ");
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		PrintMatrixClockwisely(matrix);
	}
}
