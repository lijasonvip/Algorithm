package com.bo.tree;
//http://www.cnblogs.com/KeenLeung/archive/2012/11/03/2750545.html


public class Tree {

	Node root;
	
	public static void main(String... args){
		Tree t = new Tree();
		int[] arr = {15,6,50,4,7,23,71,5};
		for(int i:arr){
			t.insert(i);
		}
		t.pTraverse();
	}
	
	Tree(){
		root = null;
	}
	
	public void insert(int idata){
		Node newNode = new Node(idata);
		
		if(root == null){
			root = newNode;
		}else{
			Node current = root;
			Node parent;
			while(true){
				parent = current;
				if(idata < current.idata){
					current = current.left;
					if(current == null){
						parent.left = newNode;
						return	;
					}
				}
				else{
					current = current.right;
					if(current == null){
						parent.right = newNode;
						return	;
					}
				}
			}
		}
	}
	
	public void pTraverse(){
		preOrder(root);
	}
	
	public void preOrder(Node node){
		if(node == null){
			return;
		}
		else{
			node.displayNode();
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	public void inOrder(Node localRoot){
		if(localRoot != null){
			inOrder(localRoot.left);
			localRoot.displayNode();
			inOrder(localRoot.right);
		}
	}
	public void displayTree(){}
	
	public Node find(int idata){
		Node current = root;
		while(current.idata != idata){
			if(current.idata < idata){
				current = current.right;
			}else{
				current = current.left;
			}
			if(current == null)
				return null;
		}
		return current;
	}
	
	public Node findMinNode(){
		Node current, last;
		last = null;
		current = root;
		if(current.left == null)
			return current;
		else{
			while(current != null){
				last = current;
				current = current.left;
			}
			return last;
		}
	}
	
	public boolean delete(int key){
		//find the target node first
		Node current = root;
		Node parent = root;
		boolean isLeft = false;
		while(current.idata != key){
			parent = current;
			if(key < current.idata){
				isLeft = true;
				current = current.left;
			}
			else{
				isLeft = false;
				current = current.right;
			}
			if(current == null)
				return false;
		}
		
		//if its a leaf node, just delete it
		if(current.left == null && current.right == null){
			if(current == root)
				root = null;
			else
				if(isLeft)
					parent.left = null;
				else
					parent.right = null;
		}
		
		//if target is not leaf, and only have one child. two situations：
		//target is left child, or right child.
		else
			if(current.right == null) //only one left child
			{
				if(current == root)
					root = current.left;
				else
					if(isLeft)
						parent.left = current.left;
					else
						parent.right = current.left;
			}
			else
				if(current.left == null){
					if(current == root){
						root = current.right;
					}else{
						if(isLeft){
							parent.left = current.right;
						}else
							parent.right = current.right;
					}
				}
		
		//if target node have two children, here we need to find a new node to replace the current node
		//the fittest node is successor node. thats mean: smallest one of those who are bigger than current node
		//we need a function to get successor node
				else
				{
					Node successor = getSuccessor(current);
					if(current == root)
						root = successor;
					else
						if(isLeft)
							parent.left = successor;
						else
							parent.right = successor;
					successor.left = current.left;
				}
		return true;
		
	}
	
	private Node getSuccessor(Node delNode){
		Node sucParent = delNode;
		Node suc = delNode;
		Node current = delNode.right;
		while(current != null){
			sucParent = suc;
			suc = current;
			current = current.left;
		}
		if(suc != delNode.right){
			sucParent.left = suc.right;
			suc.right = delNode.right;
		}
		return suc;
	}
	
	public void delete(){}
}
