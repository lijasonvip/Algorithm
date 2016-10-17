package com.bo.harbin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectedZone {

	public static int countZones(int[][] matrix){
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] flag = new int[row][col];
		
		return 0;
	}
	
	public static void bfs(int[][] matrix, int start){
		ArrayList<Integer> res = new ArrayList<>();
		int[] visited = new int[matrix.length];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = 1;
		while(!queue.isEmpty()){
			
		}
	}
	
}


