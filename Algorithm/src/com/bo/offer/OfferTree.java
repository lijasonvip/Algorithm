package com.bo.offer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.sql.rowset.Predicate;

import edu.princeton.cs.algs4.Stack;

/**
 * @author bo
 *
 */
public class OfferTree {

	
	/**
	 * @param root
	 * convert a BST to sorted double strand list with out creating new list
	 * BST's left and right to represent new list's previous and latest node
	 * return new list's head node
	 * offer 27
	 */
	public static Node ConvertToList(Node root){
		Node last = null;
		last = ConvertNode(root, last);
		
		//need to return head node so backtrace
		Node head = last;
		while(head != null && head.left != null){
			head = head.left;
		}
		return head;
	}
	//java 值传递问题导致运行出错 因为last递归过程中不会变化这就不好了， 解决方案是使用全局变量或者修改代码通过返回值改变last值
	//yes. modify by using return last method. all done successfully
	public static Node ConvertNode(Node node, Node last){
		if (node == null) {
			return last;
		}
		Node current = node;
		if(current.left != null)
			last = ConvertNode(node.left, last);
		current.left = last;
		if(last != null){
			last.right = current;
		}
		last = current;
		if(current.right != null)
			last = ConvertNode(current.right, last);
		return last;
	}
	
	//path is link from root to leaf
	/**
	 * @param root
	 * @param expectsum
	 * offer 25
	 */
	public static void FindPath(Node root, int expectsum) {
		if(root == null || expectsum <= 0){
			System.out.println("Err");
		}
		List<Integer> path = new LinkedList<Integer>();
		int cursum = 0;
		FindPath(root, path, cursum, expectsum);
			
	}
	
	/**
	 * @param node
	 * @param path
	 * @param cursum
	 * @param expectsum
	 * use list to replace stack because of its add behind and remove behind ability
	 */
	public static void FindPath(Node node, List<Integer> path, int cursum, int expectsum){
		
		cursum = cursum + node.value;
		path.add(node.value);
		
		
		boolean leaf = node.left == null && node.right == null;
		if(cursum == expectsum && leaf){
			System.out.println("A path found: ");
			for(int p:path){
				System.out.print(p +" ");
			}
			System.out.println();
		}
		// not leaf , then go to it's children
		if(node.left != null){
			FindPath(node.left, path, cursum, expectsum);
		}
		if(node.right != null)
			FindPath(node.right, path, cursum, expectsum);
		
		//pop current node when go back
		path.remove(path.size()-1);
		
	}
	
	/**
	 * @param root
	 *  we implement non-recursive traverse method by three way respectively
	 * offer 24
	 */
	public static void PostOrder(Node root){
		
	}
	
	/**
	 * @param seq
	 * @return
	 * offer 24
	 */
	public static boolean VerifySequenceOFBST(int[] seq){
		if(seq.length < 1 || seq == null)
			return false;
		int root = seq[seq.length - 1];
		//left tree small than root
		int i = 0;
		for(;i<seq.length-1;i++){
			if (seq[i] > root) {
				break;
			}
		}
		//right tree larger than root
		int j= i; // because of i++ takes one more step
		for(;j<seq.length-1;j++){
			if(seq[j] < root){
				return false;
			}
		}
		//check left
		boolean left = true;
		if(i>0){
			int[] leftseq = SubArray(seq, 0, i);
			left = VerifySequenceOFBST(leftseq);
		}
		//check right
		boolean right = true;
		if(i<seq.length-1){
			int[] rightseq = SubArray(seq, i, seq.length-i-1);
			right = VerifySequenceOFBST(rightseq); 
			
		}
		return left && right;
	}
	
	/**
	 * @param root
	 * offer 23
	 * it's just a level order traverse
	 */
	public static void PrintTreeTopDown(Node root){
		
	}
	
	/**
	 * @param root
	 * @return
	 * mirror of a tree recursion version
	 * offer 19
	 */
	public static Node MirrorTree(Node root){
		if(root == null)
			return null;
		//both children null not one of them
		if(root.left == null && root.right == null)
			return root;
		
		//one of them null also swap
		Node temp = root.left;
		root.left = root.right;
		root.right = temp;
		if(root.left != null){
			MirrorTree(root.left);
		}
		if(root.right != null){
			MirrorTree(root.right);
		}
		return root;
	}
	
	public static Node MirrorTree_Iteration(Node root){
		if(root == null)
			return null;
		if(root.left == null && root.right == null)
			return root;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node pop = stack.pop();
			if(pop.left != null || pop.right != null){
				Node temp = pop.left;
				pop.left = pop.right;
				pop.right = temp;
			}
			if(pop.left != null)
				stack.push(pop.left);
			if(pop.right != null)
				stack.push(pop.right);
		}
		return root;
	}
	
	/**
	 * @param root1
	 * @param root2
	 * @return
	 * test if tree1 has subtree 2
	 * offer 18
	 */
	public static boolean HasSubTree(Node root1, Node root2){
		boolean result = false;
		if(root1 != null && root2 != null){
			if(root1.value == root2.value){
				result = TreeContainTree(root1, root2);
			}
			if(!result){
				result = HasSubTree(root1.left, root2);
			}
			if(!result){
				result = HasSubTree(root1.right, root2);
			}
		}
		return result;
	}
	
	/**
	 * @param root1
	 * @param root2
	 * @return
	 * first check null condition, mother null return false, son null return true
	 * second when root node equal, check left and right respectively.
	 * when all equal (&&) return true.
	 * sub function of offer 18's HasSubTree
	 */
	public static boolean TreeContainTree(Node root1, Node root2){
		boolean result = false;
		
		//the check order could not be changed.
		//because when checked a equal leaf. both children are null and 
		//if check root1 first false will return and get a wrong result 
		if (root2 == null) {
			return true;
		}
		if(root1 == null){
			return false;
		}
		if(root1.value != root2.value){
			return false;
		}
		boolean left = TreeContainTree(root1.left, root2.left);
		boolean right = TreeContainTree(root1.right, root2.right);
		return  left && right;
	}
	/**
	 * @param pre
	 * @param in
	 * @param start
	 * @param end
	 * @param length
	 *            length is length of in or pre, they are same
	 * @return in main: start,end,length is 0,in.length-1,in.length
	 *use preorder and inorder sequence to construct a binary tree recursively
	 *offer 6
	 */
	public static Node ConstructTree(int[] pre, int[] in, int start, int end, int length) {
		if (pre == null || in == null || pre.length == 0 || in.length == 0 || length <= 0) {
			return null;
		} else {
			// root node
			int value = pre[start];
			Node root = new Node(value);

			// condition for recursive process stop
			if (length == 1)
				return root;

			// divide left child and right child, find i
			int i = 0;
			while (i < length) {
				if (value == in[end - i]) {
					break;
				}
				i++;
			}

			root.left = ConstructTree(pre, in, start + 1, end - i - 1, length - i - 1);
			root.right = ConstructTree(pre, in, start + length - i, end, i);

			return root;
		}

	}

	/**
	 * @param pre
	 * @param in
	 * @return
	 * use pre order and in order sequence to construct a search binary tree 
	 * offer 6
	 */
	public static Node Construct(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length == 0 || in.length == 0) {
			return null;
		} else {
			Node root = new Node(pre[0]);
			if (pre.length == 1)
				return root;
			int leftpart = 0;
			while (leftpart < pre.length) {
				if (pre[0] == in[leftpart])
					break;
				leftpart++;
			}
			int[] leftsubpre = SubArray(pre, 1, leftpart);
			int[] leftsubin = SubArray(in, 0, leftpart);
			int[] rightsubpre = SubArray(pre, 1 + leftpart, pre.length-leftpart-1);
			int[] rightsubin = SubArray(in, 1 + leftpart, pre.length-leftpart-1);
			root.left = Construct(leftsubpre, leftsubin);
			root.right = Construct(rightsubpre, rightsubin);
			return root;
		}
	}

	/**
	 * @param arr
	 * @param start
	 * @param length
	 * @return slice of arr[] arr[start...start+length]
	 */
	public static int[] SubArray(int[] arr, int start, int length) {
		if (start + length > arr.length || start < 0) {
			return null;
		} else {
			int[] sub = new int[length];
			for (int i = 0; i < length; i++) {
				sub[i] = arr[i + start];
			}
			return sub;
		}

	}

	public static void PreOrder(Node node) {
		if (node != null) {
			System.out.print(node.value + " ");
			PreOrder(node.left);

			PreOrder(node.right);

		}
	}

	public static void main(String[] args) {
//		int[] pre = { 1, 2, 4, 7, 3, 5, 6, 8 };
//		int[] in = { 4, 7, 2, 1, 5, 3, 8, 6 };
//		Node t = ConstructTree(pre, in,0,pre.length-1,pre.length);
//		PreOrder(t);
//		System.out.println();
//		Node my = Construct(pre, in);
//		PreOrder(my);
//		Tree t1 = new Tree();
//		int[] first = {8,8,7,9,2,-1,-1,-1,-1,4,7};
//		t1.levelConstruct(first);
//		Tree t2 = new Tree();
//		int[] second = {8,9,2};
//		t2.levelConstruct(second);
//		boolean has = HasSubTree(t1.root, t2.root);
//		System.out.println(has);
		
//		int[] mirror = {8,6,10,5,7,9,11};
//		Tree tree = new Tree();
//		tree.levelConstruct(mirror);
//		
//		MirrorTree_Iteration(tree.root);
//		tree.PreOrder(tree.root);
//		System.out.println();
//		tree.LeverOrder(tree.root);
		
//		int[] seq = {5,7,6,9,11,10,8};
//		int[] seq2 = {7,4,6,5};
//		System.out.println(VerifySequenceOFBST(seq2));
		
		int[] t = {10,5,12,4,7,-1,-1};
		Tree tree = new Tree();
		tree.levelConstruct(t);
		FindPath(tree.root, 22);
		
//		int[] t = {10,6,14,4,8,12,16};
//		Tree tree = new Tree();
//		tree.levelConstruct(t);
//		Node node = ConvertToList(tree.root);
//		while(node != null){
//			System.out.print(node.value + " ");
//			node = node.right;
//		}
	}
}

class Tree {
	Node root;

	Tree() {
		root = null;
	}

	Tree(Node root) {
		this.root = root;
	}

	Tree(int[] arr) {
		for (int i : arr) {
			insert(i);
		}
	}

	public void insert(int value) {
		root = insert(root, value);
	}

	public Node insert(Node node, int value) {
		if (node == null) {
			node = new Node(value);
		} else if (node.value >= value) {
			node.left = insert(node.left, value);
		} else {
			node.right = insert(node.right, value);
		}
		return node;
	}

	/**
	 * @param node
	 *            insert node one by one need a parent point to store parent of
	 *            current point
	 */
	public void insert(Node node) {
		if (root == null) {
			root = node;
		} else {
			Node parent = root;
			Node cur = parent;
			while (cur != null) {
				if (cur.value >= node.value) {
					parent = cur;
					cur = cur.left;
				} else {
					parent = cur;
					cur = cur.right;
				}
			}
			if (node.value >= parent.value)
				parent.right = node;
			else
				parent.left = node;

		}
	}

	/**
	 * @param data
	 * level traverse order to construct a tree. if a node value is -1 means null
	 * have left must have right
	 */
	public void levelConstruct(int[] data){
		if(data == null || data.length < 1 || data[0] == -1)
			System.out.println("Err in");
		Node[] all = new Node[data.length];
		for (int i = 0; i < data.length; i++) {
			if(data[i] != -1){
				all[i] = new Node(data[i]);
			}else
				all[i] = null;
			
		}
		for (int i = 0; i <= (data.length-2)/2; i++) {
			all[i].left = all[2*i+1];
			all[i].right = all[2*i+2];
		}
		root = all[0];
	}
	
	/**
	 * @param node
	 */
	public void PreOrder(Node node) {
		if (node != null) {
			System.out.print(node.value + " ");
			PreOrder(node.left);

			PreOrder(node.right);

		}
	}

	public void InOrder(Node node) {
		if (node != null) {

			InOrder(node.left);
			System.out.print(node.value + " ");
			InOrder(node.right);

		}
	}
	
	public void LeverOrder(Node node){
		Queue<Node> queue = new LinkedList<Node>();
		if (node != null) {
			System.out.print(node.value + " ");
			queue.offer(node);
			while(!queue.isEmpty()){
				Node pop = queue.poll();
				if(pop.left != null){
					System.out.print(pop.left.value + " ");
					queue.offer(pop.left);
				}
				if(pop.right != null){
					System.out.print(pop.right.value + " ");
					queue.offer(pop.right);
				}
			}
		}
	}
}

class Node {
	int value;
	Node left;
	Node right;

	public Node() {
	}

	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
}
