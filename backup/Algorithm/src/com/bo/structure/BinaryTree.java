package com.bo.structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

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
	
	public BinaryTree(int[] arr){
		for(int i:arr){
			insert(i);
		}
	}
	
	public void insert(int i){
		root = insert(root, i);
	}
	
	public Node insert(Node node, int i){
		if(node == null){
			node = new Node(i);
		}else if(i <= node.value){
			node.left = insert(node.left, i);
		}else{
			node.right = insert(node.right, i);
		}
		
		return node;
	}
	
	public void visit(Node p){
		System.out.println(p.value);
	}
	
	public void pre(Node p){
		if(p != null){
			visit(p);
			pre(p.left);
			pre(p.right);
		}
	}
	
	public void ppp(){
		pre(root);
	}
	
	public void preOrder(Node p){
		Stack<Node> stack = new Stack<Node>();
		if(p != null){
			stack.push(p);
			while(!stack.isEmpty()){
				Node t = stack.pop();
				visit(t);
				if(p.right != null){
					stack.push(p.right);
				}
				if(p.left != null){
					stack.push(p.left);
				}
			}
		}
	}
	
	//first visit then push into stack
	//much more easier to understand
	public void preOrder2(Node p){
		Stack<Node> stack = new Stack<Node>();
		Node node = p;
		while(node != null || !stack.isEmpty()){
			while(node != null){
				visit(node);
				stack.push(node);
				node = node.left;
			}
			if(!stack.isEmpty()){
				node = stack.pop();
				node = node.right;
			}
		}
		
	}
	
	public void inOrder(Node p){
		Stack<Node> stack = new Stack<Node>();
		while(p != null){
			while(p != null){
				if(p.right != null)
					stack.push(p.right);
				stack.push(p);
				p = p.left;
			}
			p = stack.pop();
			while(!stack.isEmpty() && p.right == null){
				visit(p);
				p = stack.pop();
			}
			visit(p);
			if(!stack.isEmpty())
				p = stack.pop();
			else
				p = null;
				
		}
	}
	
	public void inOrder2(Node p){
		Stack<Node> stack = new Stack<Node>();
		Node node = p;
		while(node != null || !stack.isEmpty()){
			while(node != null){
				stack.push(node);
				node = node.left;
			}
			if(!stack.isEmpty()){
				node = stack.pop();
				visit(node);
				node = node.right;
						
			}
		}
	}
	
	public void IOS(){
		inOrder(root);
	}
	
	public void postOrder(Node p){
		Node q = p;
		Stack<Node> stack = new Stack<Node>();
		while(p != null){
			//stack in left tree
			for(; p.left != null; p = p.left){
				stack.push(p);
			}
			//current node have no right children or right children have been visit
			while(p != null && (p.right == null || p.right == q)){
				visit(p);
				q = p;
				if(stack.isEmpty())
					return;
				p = stack.pop();
			}
			stack.push(p);
			p = p.right;
		}
	}
	
	//double stack
	public void postOrder2(Node p){
		Stack<Node> leftstack = new Stack<Node>();
		Stack<Node> rightstack = new Stack<Node>();
		Node node = p, right;
		do{
			while(node != null){
				right = node.right;
				leftstack.push(node);
				rightstack.push(right);
				node = node.left;
			}
			node = leftstack.pop();
			right = rightstack.pop();
			if(right == null){
				visit(node);
			}else{
				leftstack.push(node);
				rightstack.push(null);
			}
			node = right;
			
		}while(!leftstack.isEmpty() || !rightstack.isEmpty());
	}
	
	//single stack
	public void postOrder3(Node p){
		Stack<Node> stack = new Stack<Node>();    
        Node node = p, prev = p;    
        while (node != null || stack.size() > 0) {    
            while (node != null) {    
                stack.push(node);    
                node = node.left;    
            }    
            if (stack.size() > 0) {    
                Node temp = stack.peek().right;    
                if (temp == null || temp == prev) {    
                    node = stack.pop();    
                    visit(node);    
                    prev = node;    
                    node = null;    
                } else {    
                    node = temp;    
                }    
            }    
    
        }    
	}
	
	//double stack 2
	public void postOrder4(Node p){
		Stack<Node> stack = new Stack<Node>();    
        Stack<Node> temp = new Stack<Node>();    
        Node node = p;    
        while (node != null || stack.size() > 0) {    
            while (node != null) {    
                temp.push(node);    
                stack.push(node);    
                node = node.right;    
            }    
            if (stack.size() > 0) {    
                node = stack.pop();    
                node = node.left;    
            }    
        }    
        while (temp.size() > 0) {//把插入序列都插入到了temp。  
            node = temp.pop();    
            visit(node);    
        }   
	}
	
	public void reverseLeftAndRight(Node p){
		if(p == null)
			return;
		if(null == p.left && null == p.right)
			return;
		Node temp = p.left;
		p.left = p.right;
		p.right = temp;
		reverseLeftAndRight(p.left);
		reverseLeftAndRight(p.right);
	}
	
	//use Queue
	public void reverseAgain(Node p){
		if(p == null)
			return;
		if(null == p.left && null == p.right)
			return;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(p);
		Node temp;
		Node q = p;
		while(!queue.isEmpty()){
			if(null != q.left){
				queue.offer(q.left);
			}
			if(null != q.right){
				queue.offer(q.right);
			}
			temp = q.left;
			q.left = q.right;
			q.right = temp;
			q = queue.poll();
		}
		
	}
	
	public void reverse(){
		reverseAgain(root);
	}
	
	public void POS(){
		postOrder2(root);
	}
	
	 public void morris_inorder(Node root) {  
	        while(root != null) {  
	            if(root.left != null) {  
	                Node temp = root.left;  
	                while(temp.right != null && temp.right != root) {  
	                    temp = temp.right;  
	                }  
	                if(temp.right == null) {  
	                    temp.right = root;  
	                    root = root.left;  
	                } else {  
	                    System.out.print(root.value + " ");  
	                    temp.right = null;  
	                    root = root.right;  
	                }  
	            } else {  
	                System.out.print(root.value + " ");  
	                root = root.right;  
	            }  
	        }  
	          
	}  
	 
	public static void main(String... args){
		int[] arr = {15,6,23,4,7,71,5,50};
		BinaryTree b = new BinaryTree(arr);
		b.reverse();
		b.ppp();
		
	}
}
