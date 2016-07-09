package com.bo.graph;

import java.util.Queue;
import java.util.LinkedList;

public class Test {

	public void BFS(int[][] graph, int start){
		boolean[] visited = new boolean[graph.length];
		visited[start] = true;
		Queue<Integer> queue = new LinkedList<Integer>();
		System.out.println(start+1);
		queue.offer(start);
		while(!queue.isEmpty()){
			int temp = queue.poll();
			int v;
			while((v = getAdjUnVisited(temp, graph, visited)) != -1){
				visited[v] = true;
				System.out.println(v+1);
				queue.offer(v);
			}
		}
	}
	
	//get adj but not visited already node
	public int getAdjUnVisited(int v, int matrix[][], boolean[] visited){
		for(int i=0;i<matrix[0].length;i++){
			if(matrix[v][i] != 0 && visited[i] == false){
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String... args){
		Test t = new Test();
		int[][] arr = {{0,3,5,8,0},{3,0,6,4,1},{5,6,0,2,0},{8,4,2,0,10},{0,11,0,10,0}};
		t.BFS(arr,0);
	}
}
