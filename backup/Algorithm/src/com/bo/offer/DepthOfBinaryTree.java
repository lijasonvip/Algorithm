package com.bo.offer;

public class DepthOfBinaryTree {

	//offer 39
	
	public static int TreeDepth(Node root){
		if(root == null)
			return 0;
		int nleft = TreeDepth(root.left);
		int nright = TreeDepth(root.right);
		
		return (nleft > nright) ? nleft+1 : nright + 1;
	}
	
	
	//offer 39 extend
	/**
	 * @param root
	 * @return
	 * binary tree is or is not a balanced tree
	 * easy but visit a node repeat again and again. highly time consuming
	 */
	public static boolean IsBalanced(Node root){
		if(root == null)
			return true;
		int left = TreeDepth(root.left);
		int right = TreeDepth(root.right);
		if(Math.abs(left - right) > 1)
			return false;
		return IsBalanced(root.left) && IsBalanced(root.right);
	}
	
	/**
	 * @param root
	 * @param d
	 * post order traverse based recursion method
	 * here we use Depth class because java references. in fact not necessary because depth was update after each recursion
	 */
	public static boolean IsBalanced_Better(Node root, Depth d){
		if(root == null){
			d.depth = 0;
			return true;
		}
		Depth left = new Depth();
		Depth right = new Depth();
		if(IsBalanced_Better(root.left, left) && IsBalanced_Better(root.right, right)){
			int diff = left.depth - right.depth;
			if(diff < 1 || diff > -1){
				d.depth = 1 + (left.depth > right.depth ? left.depth : right.depth);
				return true;
			}
		}
		return false;
	}
	
	public static boolean IsBalanced_Better(Node root){
		Depth depth = new Depth();
		depth.depth = 0;
		return IsBalanced_Better(root, depth);
	}
	
	
	public static void main(String[] args) {
		int[] t = {1,2,3,4,5,-1,6,-1,-1,7,-1};
		Tree tree = new Tree();
		tree.levelConstruct(t);
		System.out.println(TreeDepth(tree.root));
		System.out.println(IsBalanced_Better(tree.root));
	}
}

//use new Depth to store depth during recursion
//or we can use global variants
class Depth{
	int depth;
	
	public Depth() {
		// TODO Auto-generated constructor stub
	}
}
