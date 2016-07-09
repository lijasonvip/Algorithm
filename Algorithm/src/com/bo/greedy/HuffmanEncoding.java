package com.bo.greedy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//using variable length coding to get better compressing rate.
//idea is assign high frequency char short code and low frequency long code.
public class HuffmanEncoding {
	// definition of prefix code:no code is prefix of other code

	public static MyQueue Huffman(List<Node> C){
		MyQueue q = new MyQueue();
		q.nodes = C;
		for (int i = 0; i < C.size()-1; i++) {
			Node n = new Node();
			n.left = q.extrat_min();
			n.right = q.extrat_min();
			n.freq = n.left.freq + n.right.freq;
			q.insert(n);
		}
		return q;
	}
	
	public static void main(String... args){
		List<Node> C = new LinkedList<Node>();
		Node a = new Node("a",45);
		Node b = new Node("b",13);
		Node c = new Node("c",12);
		Node d = new Node("d",16);
		Node e = new Node("e",9);
		Node f = new Node("f",5);
		C.add(a);
		C.add(b);
		C.add(c);
		C.add(e);
		C.add(e);
		C.add(f);
		MyQueue q = Huffman(C);
		
	}
}

class MyQueue {
	List<Node> nodes = new LinkedList<Node>();

	public Node extrat_min(){
		if(nodes.size()>1){
			Node min = nodes.get(0);
			int index = 0;
			for (int i = 0; i < nodes.size(); i++) {
				if(nodes.get(i).freq < min.freq){
					min = nodes.get(i);
					index = i;
				}
			}
			nodes.remove(index);
			return min;
		}else if(nodes.size() == 1){
			nodes.remove(0);
			return nodes.get(0);
		}
			
		else
			return null;
	}
	
	public void insert(Node node){
		nodes.add(node);
	}
	
	public void print(){
		Node n = nodes.get(nodes.size()-1);
		Recursive(n);
	}
	public void Recursive(Node n){
		//print wait a moment
	
	}
	public boolean isleaf(Node n){
		if(n.left == null && n.right == null)
			return true;
		else
			return false;
	}
}

class Node {
	String ch;
	int freq;
	Node left;
	Node right;

	public Node(){}
	public Node(String ch, int freq){
		this.ch = ch;
		this.left = null;
		this.right = null;
		this.freq = freq;
	}
	public Node(String ch, int freq, Node left, Node right){
		this.ch = ch;
		this.left = left;
		this.right = right;
		this.freq = freq;
	}
}