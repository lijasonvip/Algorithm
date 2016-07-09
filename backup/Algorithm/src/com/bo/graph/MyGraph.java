package com.bo.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//http://blog.csdn.net/todd911/article/details/9182531
//references graph
public class MyGraph {
	public static void BFS(int[][] g, int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		int[] visited  = new int[g.length];
		
		q.offer(start);
		System.out.print(start+" ");
		visited[start] = 1;
		while(!q.isEmpty()){
			int intq = q.poll();
			
			for(int i=0;i<g[0].length;i++){
				if(g[intq][i] != 0 && visited[i] != 1){
					System.out.print(i+" ");
					visited[i] = 1;
					q.offer(i);
				}
			}
		}
	}
	
	//DFS using adjacent matrix and recursive method
	public static void DFS(int[][] g, int start){
		int[] visit = new int[g.length];
		DFS_Recursive(g, start,visit);
	}
	public static void DFS_Recursive(int[][] g, int start, int[] visit){
		if(visit[start] == 1)
			return;
		
		System.out.print(start + " ");
		visit[start] = 1;
		
		int ints;
		while((ints = getAdjNonVisit(g, start,visit))!=-1){
			DFS_Recursive(g,ints,visit);
		}
		
		
	}
	
	public static int getAdjNonVisit(int[][] g, int v, int[] visit){
		for (int i = 0; i < g[0].length; i++) {
			if(g[v][i] !=0 && visit[i] != 1){
				return i;
			}
		}
		return -1;
	}

	//BFS using adjacent list and queue
	public static void BFS(Graph graph, int start){
		Queue<Vertex> q = new LinkedList<Vertex>();
		Vertex v = graph.vertex[start];
		q.offer(v);
		v.isVisited = true;
		System.out.print(v.id + " ");
		while(!q.isEmpty()){
			Vertex vq = q.poll();
			Vertex vadj;
			while((vadj = getAdjNonVisited(vq)) != null){
				q.offer(vadj);
				System.out.print(vadj.id+" ");
				vadj.isVisited = true;
			}
		}
	}
	
	//DFS using adjacent list and stack
	//remember to reassign visited feature to all false
	public static void DFS(Graph graph, int start){
		clean(graph);
		Stack<Vertex> s = new Stack<Vertex>();
		Vertex v = graph.vertex[start];
		System.out.print(v.id+ " ");
		v.isVisited = true;
		s.push(v);
		while(!s.isEmpty()){
			Vertex top = s.peek();
			Vertex adj;
			if((adj = getAdjNonVisited(top)) != null){
				System.out.print(adj.id + " ");
				adj.isVisited = true;
				s.push(adj);
			}else{
				s.pop();
			}
		}
	}
	
	public static Vertex getAdjNonVisited(Vertex v){
		if(v.getAdj() == null)
			return null;
		
		LinkedList<Vertex> adj = v.getAdj();
		for(Vertex vv:adj){
			if(vv.isVisited == false)
				return vv;
		}
		return null;
	}
	
	public static void clean(Graph g){
		for(Vertex v:g.vertex){
			v.isVisited = false;
		}
	}
	
	public static void main(String... args) {
		int[][] gg = { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0 }, { 0, 1, 0, 0, 0 } };
		//Graph g = new Graph(gg);
		System.out.println("======BFS using matrix========");
		BFS(gg, 0);
		System.out.println();
		System.out.println("======DFS using matrix and recursive========");
		DFS(gg, 0);
		
		Graph graph = new Graph(5,true);
		graph.addVertex(new Vertex(0));
		graph.addVertex(new Vertex(1));
		graph.addVertex(new Vertex(2));
		graph.addVertex(new Vertex(3));
		graph.addVertex(new Vertex(4));
		
		graph.addEdge(0, 3);
		graph.addEdge(0, 4);
		graph.addEdge(3, 1);
		graph.addEdge(3, 2);
		graph.addEdge(4, 1);
		System.out.println();
		System.out.println("=======BFS using adjacent list=======");
		BFS(graph,0);
		System.out.println();
		System.out.println("=======DFS using adjacent list and stack=======");
		DFS(graph, 0);
	}
}

// define a adjacent linkedlist to store graph
// sorry but failed
// search java implement linkedlist adjacent