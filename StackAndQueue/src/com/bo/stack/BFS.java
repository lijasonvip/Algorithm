package com.bo.stack;

import java.util.LinkedList;
import java.util.List;

public class BFS {
	
	/**
	 * @param node
	 */
	public void BreadFirstTraverse(Graph g, Node node) {
		TwoStackQueue<Node> q = new TwoStackQueue<Node>();
		if(g.nodes != null && node != null){
			System.out.println(node.value + " " );
			node.isvisited = true;
			q.push(node);
			while(!q.isEmpty()){
				Node outq = q.pop();
				Node adjnovisit;
				while((adjnovisit = getAdjNonVisit(g,outq)) != null){
					adjnovisit.isvisited = true;
					System.out.println(adjnovisit.value + " ");
					q.push(adjnovisit);
				}
			}
		}
		
	}
	
	public Node getAdjNonVisit(Graph g, Node n){
		if(g != null && n != null){
			List<Node> adjs = n.adj;
			for(Node nn:adjs){
				if(nn.isvisited == false)
					return nn;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
}

class Graph{
	List<Node> nodes;
	
	public Graph(){}
	
	public Graph(int[][] arr){
		//nodes
		for (int i = 0; i < arr.length; i++) {
			nodes.add(new Node(i));
		}
		//edges
		for (int i = 0; i < arr.length; i++) {
			Node n = nodes.get(i);
			List<Node> adj = new LinkedList<Node>();
			for (int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] != 0){
					Node ajdnode = nodes.get(j);
					adj.add(ajdnode);
				}
			}
			n.adj = adj;
		}
	}
}

class Node{
	int value;
	boolean isvisited;
	List<Node> adj;
	
	public Node(){}
	
	public Node(int value){
		this.value = value;
		this.isvisited = false;
		this.adj = null;
	}
	
	
}