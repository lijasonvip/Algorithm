package com.bo.graph;

import java.util.LinkedList;

public class Vertex {

	public boolean isVisited;
	public LinkedList<Vertex> adj = null;
	int id;
	
	public Vertex(){
		isVisited = false;
	}
	
	public Vertex(int id){
		isVisited = false;
		this.id = id;
	}
	
	public void addAdj(Vertex v){
		if(adj == null)
			adj = new LinkedList<Vertex>();
		adj.add(v);
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public LinkedList<Vertex> getAdj(){
		return adj;
	}
	
	public String toString(){
		return String.valueOf(id);
	}
}
