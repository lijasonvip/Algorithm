package com.bo.tree;

public class Node {
	
	public int idata;
	public String sdata;
	public Node left;
	public Node right;
	
	public Node(int id, String sd){
		this.idata = id;
		this.sdata = sd;
		this.left = null;
		this.right = null;
	}
	
	public Node(int id){
		this.idata = id;
		this.left = null;
		this.right = null;
	}
	
	public Node(String sd){
		this.sdata = sd;
		this.left = null;
		this.right = null;
	}
	
	public void displayNode(){
		System.out.println(idata);
	}
}
