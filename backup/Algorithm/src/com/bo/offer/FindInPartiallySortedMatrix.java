package com.bo.offer;

public class FindInPartiallySortedMatrix {

	//matrix is partially sorted
	//right is not smaller than left
	//down is not smaller than up in a matrix
	/**
	 * @param matrix
	 * @param key
	 * return if key was found in matrix
	 */
	public boolean find(int[][] matrix, int key){
		boolean found = false;
		int row = matrix.length;
		int col = matrix[0].length;
		if(matrix != null && row > 0 && col > 0){
			int j = col -1;
			int i = 0;
			while(j>=0 && i<row){
				if(matrix[i][j] == key){
					//return i + " " + j;
					found = true;
					break;
					
				}else if(matrix[i][j] > key)
					j--;
				else{
					i++;
				}
			}
			
		}
		return found;
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
}
