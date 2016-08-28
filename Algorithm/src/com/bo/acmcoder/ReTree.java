package com.bo.acmcoder;

import java.util.Arrays;

public class ReTree {
	
	public static void main(String[] args) {
		int[] pre = {1,2,4,7,3,5,6,8};
		int[] in = {4,7,2,1,5,3,6,8};
		TreeNode treeNode = reConstruction(pre, in);
	}

	public static TreeNode reConstruction(int[] pre, int[] in){
		if (pre.length == 1 && in.length == 1) {
			return new TreeNode(pre[0]);
		}
		if (pre.length == 0 && in.length == 0) {
			return null;
		}
		int root = pre[0];
		TreeNode node = new TreeNode(root);
		int i = 0;
		for (; i < in.length; i++) {
			if (in[i] == root) {
				break;
			}
		}
		//在线编程时solution里不能用 如果可以引入工具包的话是可以的
		node.left = reConstruction(Arrays.copyOfRange(pre, 1, i+1), Arrays.copyOfRange(in, 0, i));
		node.right = reConstruction(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i+1, in.length));
		
		return node;
	}
}

class TreeNode{
	TreeNode left;
	TreeNode right;
	int val;
	
	public TreeNode(int val){
		this.val = val;
		this.left = null;
		this.right = null;
	}
	
	public String toString(){
		return String.valueOf(this.val);
	}
}
