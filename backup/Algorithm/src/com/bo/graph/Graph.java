package com.bo.graph;

public class Graph {
	public Vertex[] vertex;
	public boolean direct;
	public static int v_index;
	
	public Graph(int num, boolean flag){
		direct = flag;
		vertex = new Vertex[num];
	}
	
	public void addVertex(Vertex v){
		vertex[v_index++] = v;
	}
	
	public void addEdge(int from, int to){
		//vertex[from].setId(from);
		vertex[from].addAdj(vertex[to]);
		if(!direct){
			//vertex[to].setId(to);
			vertex[to].addAdj(vertex[from]);
		}
	}
}
