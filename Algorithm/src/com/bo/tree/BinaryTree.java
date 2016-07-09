package com.bo.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T> {

	class Node{
		int value;
		Node left;
		Node right;
		Node(int v){
			this.value = v;
			left = null;
			right = null;
		}
	}
	
	private Node root;
	
	BinaryTree(){
		root = null;
	}
	
	BinaryTree(int[] arr){
		for(int i:arr){
			insert(i);
		}
	}
	
	private void insert(int value){
		root = insert(root, value);
	}
	
	private Node insert(Node node, int value){
		if(node == null){
			node = new Node(value);
		}else{
			if(value <= node.value){
				node.left = insert(node.left, value);
			}else{
				node.right = insert(node.right, value);
			}
		}
		return node;
	}
	
	private void visit(Node node){
		if(node == null){
			return ;
		}
		int value = node.value;
		System.out.println(value);
	}
	
	
	private void preOrder(Node node){
		if(node == null){
			return;
		}
		else{
			visit(node);
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	public void inOrder(Node node){
		if(node == null){
			return ;
		}
		else{
			inOrder(node.left);
			visit(node);
			inOrder(node.right);
		}
	}
	
	public void postOrder(Node node){
		if(node == null){
			return ;
		}
		else{
			postOrder(node.left);
			postOrder(node.right);
			visit(node);
		}
	}
	
	public void postOrderRoot(){
		postOrder(root);
	}
	
	public void inOrderRoot(){
		inOrder(root);
	}
	
	public void preOrderRoot(){
		preOrder(root);
	}
	
	
	
	public void levelTraverse(Node root){
		visit(root);
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		int level = 1;
		
		while(!queue.isEmpty()){
			List<Node> list = new LinkedList<Node>();
			while(!queue.isEmpty()){
				list.add(queue.poll());
			}
			boolean tag = false;
			for(int i=0;i<list.size();i++){
				if(list.get(i).left != null){
					visit(list.get(i).left);
					queue.offer(list.get(i).left);
					tag = true;
				}
				if(list.get(i).right != null){
					visit(list.get(i).right);
					queue.offer(list.get(i).right);
					tag = true;
				}
			}
			if(tag)
				level ++;
		}
		System.out.println("level: " + level);
		
	}
	
	public void level(){
		levelTraverse(root);
	}
	
	public void preOrderStack(Node root){
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node temp = stack.pop();
			visit(temp);
			//stack right first so can visit left first
			if(temp.right != null){
				stack.push(temp.right);
			}
			if(temp.left != null){
				stack.push(temp.left);
			}
			
		}
		
	}
	
	public void inOrderStack(Node root){
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		Node temp = root;
		while(!stack.isEmpty()){
			while(temp != null){
				temp = temp.left;
				stack.push(temp);
			}
			stack.pop();
			if(!stack.isEmpty()){
				temp = stack.pop();
				visit(temp);
				temp = temp.right;
				stack.push(temp);
			}
		}
		while(!stack.isEmpty()){

		}
	}
	
	public void postOrderStack(Node p){
		Node q = p;    
        Stack<Node> stack = new Stack<Node>();    
        while (p != null) {    
            // 左子树入栈    
            for (; p.left != null; p = p.left)    
                stack.push(p);    
            // 当前节点无右子或右子已经输出    
            while (p != null && (p.right == null || p.right == q)) {    
                visit(p);    
                q = p;// 记录上一个已输出节点    
                if (stack.empty())    
                    return;    
                p = stack.pop();    
            }    
            // 处理右子    
            stack.push(p);    
            p = p.right;    
        } 
	}
	
	public void BOS(){
		postOrderStack(root);
	}
	
	public void IOS(){
		inOrderStack(root);
	}
	public void POS(){
		preOrderStack(root);
	}
	
	public Node find(Node node, int x){
		if(node == null){
			return null;
		}
		else if(node.value == x){
			return node;
		}
		else{
			Node temp = find(node.left, x);
			if(temp != null){
				return temp;
			}
			else{
				return find(node.right, x);
			}
		}
	}
	
	public int count(Node node){
		if(node == null){
			return 0;
		}
		else
			return count(node.left) + count(node.right) + 1;
	}
	
	public static void main(String... args){
		int arr[] = {15,6,23,4,7,71,5,50};
		BinaryTree bt = new BinaryTree(arr);
		bt.postOrderRoot();
//		System.out.println("===========");
//		bt.level();
		System.out.println("===========");
		bt.BOS();
	}
}
