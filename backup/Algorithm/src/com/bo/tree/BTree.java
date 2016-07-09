package com.bo.tree;

import java.util.LinkedList;
import java.util.List;

public class BTree {

	private String[] array = {};
	private static List<Node> nodeList = null;
	
	private static class Node{
		Node left;
		Node right;
		String data;
		Node(String data){
			left = null;
			right = null;
			data = data;
		}
	}
	
	public void creatBTree(){
		nodeList = new LinkedList<Node>();
		
		//all nodes initial
		for(int i=0; i< array.length;i++){
			nodeList.add(new Node(array[i]));
		}
		
		for(int j=0;j<array.length/2 - 1;j++){
			nodeList.get(j).left = nodeList.get(j*2 + 1);
		}
		 int lastParentIndex = array.length / 2 - 1;  
	        // 左孩子  
	        nodeList.get(lastParentIndex).left = nodeList  
	                .get(lastParentIndex * 2 + 1);  
	        // 右孩子,如果数组的长度为奇数才建立右孩子  
	        if (array.length % 2 == 1) {  
	            nodeList.get(lastParentIndex).right = nodeList  
	                    .get(lastParentIndex * 2 + 2);  
	        } 
	}
}
