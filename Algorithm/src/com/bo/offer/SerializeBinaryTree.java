package com.bo.offer;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SerializeBinaryTree {

	// 二叉树的序列化与反序列化问题

	// 序列化是将一颗二叉树保存到文件中 反序列化就是从文件中读取二叉树节点值重构原来的二叉树
	public static void serialize(TreeNode root, PrintStream ps) {
		if (root == null)
			ps.print("# ");
		else {
			ps.print(root.val + " ");
			serialize(root.left, ps);
			serialize(root.right, ps);
		}
	}

	public static TreeNode deserialize(Scanner cin) {
		String token = cin.next();
		if (token.equals("#"))
			return null;
		int val = Integer.parseInt(token);
		TreeNode root = new TreeNode(val);
		root.left = deserialize(cin);
		root.right = deserialize(cin);

		return root;

	}

	public static void main(String[] args) throws FileNotFoundException {
		TreeNode root = new TreeNode(30);
		root.left = new TreeNode(20);
		root.left.left = new TreeNode(10);
		root.right = new TreeNode(40);
		root.right.left = new TreeNode(35);
		root.right.right = new TreeNode(50);
		PrintStream ps = new PrintStream(new File("E://serialize.txt"));
		serialize(root, ps);

		Scanner cin = new Scanner(new File("E://serialize.txt"));
		TreeNode back = deserialize(cin);

		System.out.println(back.val);

	}

	// 迭代DFS
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		TreeNode x = root;
		Deque<TreeNode> stack = new LinkedList<>();
		while (x != null || !stack.isEmpty()) {
			if (x != null) {
				sb.append(String.valueOf(x.val));
				sb.append(' ');
				stack.push(x);
				x = x.left;
			} else {
				sb.append("null ");
				x = stack.pop();
				x = x.right;
			}
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data.length() == 0)
			return null;
		String[] node = data.split(" ");
		int n = node.length;
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode root = new TreeNode(Integer.valueOf(node[0]));
		TreeNode x = root;
		stack.push(x);

		int i = 1;
		while (i < n) {
			while (i < n && !node[i].equals("null")) {
				x.left = new TreeNode(Integer.valueOf(node[i++]));
				x = x.left;
				stack.push(x);
			}
			while (i < n && node[i].equals("null")) {
				x = stack.pop();
				i++;
			}
			if (i < n) {
				x.right = new TreeNode(Integer.valueOf(node[i++]));
				x = x.right;
				stack.push(x);
			}
		}
		return root;
	}

	// 递归DFS
	// Encodes a tree to a single string.
	public String serialize2(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		dfs(root, sb);
		return sb.toString();
	}

	private void dfs(TreeNode x, StringBuilder sb) {
		if (x == null) {
			sb.append("null ");
			return;
		}
		sb.append(String.valueOf(x.val));
		sb.append(' ');
		dfs(x.left, sb);
		dfs(x.right, sb);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize2(String data) {
		String[] node = data.split(" ");
		int[] d = new int[1];
		return dfs(node, d);
	}

	private TreeNode dfs(String[] node, int[] d) {
		if (node[d[0]].equals("null")) {
			d[0]++;
			return null;
		}
		TreeNode x = new TreeNode(Integer.valueOf(node[d[0]]));
		d[0]++;
		x.left = dfs(node, d);
		x.right = dfs(node, d);
		return x;
	}

	// BFS
	// Encodes a tree to a single string.
	public String serialize3(TreeNode root) {
		if (root == null)
			return "";
		Queue<TreeNode> qu = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		qu.offer(root);
		sb.append(String.valueOf(root.val));
		sb.append(' ');
		while (!qu.isEmpty()) {
			TreeNode x = qu.poll();
			if (x.left == null)
				sb.append("null ");
			else {
				qu.offer(x.left);
				sb.append(String.valueOf(x.left.val));
				sb.append(' ');
			}
			if (x.right == null)
				sb.append("null ");
			else {
				qu.offer(x.right);
				sb.append(String.valueOf(x.right.val));
				sb.append(' ');
			}
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize3(String data) {
		if (data.length() == 0)
			return null;
		String[] node = data.split(" ");
		Queue<TreeNode> qu = new LinkedList<>();
		TreeNode root = new TreeNode(Integer.valueOf(node[0]));
		qu.offer(root);
		int i = 1;
		while (!qu.isEmpty()) {
			Queue<TreeNode> nextQu = new LinkedList<>();
			while (!qu.isEmpty()) {
				TreeNode x = qu.poll();
				if (node[i].equals("null"))
					x.left = null;
				else {
					x.left = new TreeNode(Integer.valueOf(node[i]));
					nextQu.offer(x.left);
				}
				i++;
				if (node[i].equals("null"))
					x.right = null;
				else {
					x.right = new TreeNode(Integer.valueOf(node[i]));
					nextQu.offer(x.right);
				}
				i++;
			}
			qu = nextQu;
		}
		return root;
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int val) {
		this.val = val;
	}
}
