package com.bo.acmcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

import javax.naming.spi.DirStateFactory.Result;

public class NowCoderOffer {

	// 牛客网的剑指offer

	public static void main(String[] args) {
		String string = "abcd";
		Permutation2(string);
	}

	// 字符串的排列 递归
	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chars = str.toCharArray();
		TreeSet<String> temp = new TreeSet<>();
		Permutation(chars, 0, temp);
		res.addAll(temp);
		return res;
	}

	public static void Permutation(char[] chars, int begin, TreeSet<String> res) {
		if (chars == null || chars.length == 0 || begin < 0 || begin > chars.length - 1) {
			return;
		}
		if (begin == chars.length - 1) {
			res.add(String.valueOf(chars));
		} else {
			for (int i = begin; i < chars.length; i++) {
				swap(chars, begin, i);
				Permutation(chars, begin + 1, res);
				swap(chars, i, begin);
			}
		}
	}

	public static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	// 字符串排列迭代算法
	public static ArrayList<String> Permutation2(String str) {
		ArrayList<String> res = new ArrayList<>();

		if (str != null && str.length() > 0) {
			char[] seq = str.toCharArray();
			Arrays.sort(seq); // 排列
			res.add(String.valueOf(seq)); // 先输出一个解

			int len = seq.length;
			while (true) {
				int p = len - 1, q;
				// 从后向前找一个seq[p - 1] < seq[p]
				while (p >= 1 && seq[p - 1] >= seq[p])
					--p;
				if (p == 0)
					break; // 已经是“最小”的排列，退出
				// 从p向后找最后一个比seq[p]大的数
				q = p;
				--p;
				while (q < len && seq[q] > seq[p])
					q++;
				--q;
				// 交换这两个位置上的值
				swap(seq, q, p);
				// 将p之后的序列倒序排列
				reverse(seq, p + 1);
				res.add(String.valueOf(seq));
			}
		}

		return res;
	}

	public static void reverse(char[] seq, int start) {
		int len;
		if (seq == null || (len = seq.length) <= start)
			return;
		for (int i = 0; i < ((len - start) >> 1); i++) {
			int p = start + i, q = len - 1 - i;
			if (p != q)
				swap(seq, p, q);
		}
	}

	// 二叉搜索树与双向链表
	public static TreeNode Convert(TreeNode pRootOfTree) {

		if (pRootOfTree == null)
			return null;
		if (pRootOfTree.left == null && pRootOfTree.right == null)
			return pRootOfTree;
		TreeNode left = Convert(pRootOfTree.left);
		TreeNode p = left;
		while (p != null && p.right != null) {
			p = p.right;
		}
		if (left != null) {
			p.right = pRootOfTree;
			pRootOfTree.left = p;
		}
		TreeNode right = Convert(pRootOfTree.right);
		if (right != null) {
			pRootOfTree.right = right;
			right.left = pRootOfTree;
		}

		return left != null ? left : pRootOfTree;
	}

	// 复杂链表的复制
	public RandomListNode Clone2(RandomListNode pHead) {
		RandomListNode p = pHead;
		RandomListNode t = pHead;
		while (p != null) {
			RandomListNode q = new RandomListNode(p.label);
			q.next = p.next;
			p.next = q;
			p = q.next;
		}
		while (t != null) {
			RandomListNode q = t.next;
			if (t.random != null)
				q.random = t.random.next;
			t = q.next;

		}
		RandomListNode s = new RandomListNode(0);
		RandomListNode s1 = s;
		while (pHead != null) {
			RandomListNode q = pHead.next;
			pHead.next = q.next;
			q.next = s.next;
			s.next = q;
			s = s.next;
			pHead = pHead.next;

		}
		return s1.next;

	}

	public RandomListNode Clone(RandomListNode pHead) {
		CloneNodes(pHead);
		Sibling(pHead);
		return reconnext(pHead);
	}

	public void CloneNodes(RandomListNode pHead) {
		RandomListNode work = pHead;
		while (work != null) {
			RandomListNode temp = new RandomListNode(work.label);
			temp.next = temp.random = null;
			temp.next = work.next;
			work.next = temp;
			work = work.next.next;
		}
	}

	public void Sibling(RandomListNode pHead) {
		RandomListNode work = pHead;
		while (work != null) {
			if (work.random != null) {
				work.next.random = work.random.next;
			}

			work = work.next.next;
		}
	}

	public RandomListNode reconnext(RandomListNode pHead) {
		RandomListNode work = pHead;
		RandomListNode head = null;
		RandomListNode node = null;
		if (head == null) {
			head = work.next;
			node = head;
			work = work.next.next;
		}
		while (work != null) {
			node.next = work.next;
			work = work.next.next;
			node = node.next;
		}
		return head;
	}

	// 找一个和为某值得路径
	private ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Integer> list = new ArrayList<Integer>();

	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		if (root == null)
			return listAll;
		list.add(root.val);
		target -= root.val;
		if (target == 0 && root.left == null && root.right == null)
			listAll.add(new ArrayList<Integer>(list));
		FindPath(root.left, target);
		FindPath(root.right, target);
		list.remove(list.size() - 1);
		return listAll;
	}

	public static TreeNode constructTree(int[] data) {
		TreeNode[] nodes = new TreeNode[data.length];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new TreeNode(data[i]);
		}
		for (int i = 0; i < (nodes.length - 1) / 2; i++) {
			nodes[i].left = nodes[2 * i + 1];
			nodes[i].right = nodes[2 * i + 2];
		}
		return nodes[0];
	}

	// 后续遍历是不是一颗排序二叉树
	public static boolean VerifySequenceOfBST(int[] sequence) {
		if (sequence == null || sequence.length == 0) {
			return false;
		}
		int root = sequence[sequence.length - 1];
		int i = 0;
		for (; i < sequence.length - 1; i++) {
			if (sequence[i] > root) {
				break;
			}
		}
		int j = i;
		for (; j < sequence.length - 1; ++j) {
			if (sequence[j] < root) {
				return false;
			}
		}
		boolean left = true;
		if (i > 0) {
			left = VerifySequenceOfBST(Arrays.copyOfRange(sequence, 0, i));
		}
		boolean right = true;
		if (i < sequence.length - 1) {
			right = VerifySequenceOfBST(Arrays.copyOfRange(sequence, i, sequence.length - 1));
		}
		return (left && right);
	}

	// 从上到下打印二叉树
	public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<>();

		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			res.add(node.val);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		return res;
	}

	// 入栈顺序是否合法
	public static boolean IsPopOrder(int[] pushA, int[] popA) {
		Stack<Integer> stack = new Stack<>();
		int j = 0;
		for (int i = 0; i < pushA.length; i++) {
			stack.push(pushA[i]);
			while (!stack.isEmpty() && stack.peek() == popA[j]) {
				stack.pop();
				j++;
			}
		}
		return stack.isEmpty();
	}

	// 包含min 和 max 函数的栈
	// 写个内部类吧
	class MinMaxStack {
		Stack<Integer> original = new Stack<>();
		Stack<Integer> min = new Stack<>();

		public void push(int node) {
			if (original.isEmpty()) {
				original.push(node);
				min.push(node);
			} else {
				original.push(node);
				if (node < min.peek()) {
					min.push(node);
				} else {
					min.push(min.peek());
				}
			}
		}

		public void pop() {
			if (!original.isEmpty()) {
				original.pop();
				min.pop();
			}
		}

		public int top() {
			if (!original.isEmpty()) {
				return original.peek();
			}
			return -1;
		}

		public int min() {
			return min.peek();
		}
	}

	// 打印矩阵
	public ArrayList<Integer> printMatrix(int[][] matrix) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return result;
		}

		printMatrixClockWisely(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, result);

		return result;
	}

	public void printMatrixClockWisely(int[][] matrix, int startRow, int startCol, int endRow, int endCol,
			ArrayList<Integer> result) {
		if (startRow < endRow && startCol < endCol) {
			for (int j = startCol; j <= endCol; j++) {
				result.add(matrix[startRow][j]);
			} // Right
			for (int i = startRow + 1; i <= endRow - 1; i++) {
				result.add(matrix[i][endCol]);
			} // Down
			for (int j = endCol; j >= startCol; j--) {
				result.add(matrix[endRow][j]);
			} // Left
			for (int i = endRow - 1; i >= startRow + 1; i--) {
				result.add(matrix[i][startCol]);
			} // Up
			printMatrixClockWisely(matrix, startRow + 1, startCol + 1, endRow - 1, endCol - 1, result);
		} else if (startRow == endRow && startCol < endCol) {
			for (int j = startCol; j <= endCol; j++) {
				result.add(matrix[startRow][j]);
			}
		} else if (startRow < endRow && startCol == endCol) {
			for (int i = startRow; i <= endRow; i++) {
				result.add(matrix[i][endCol]);
			}
		} else if (startRow == endRow && startCol == endCol) {
			result.add(matrix[startRow][startCol]);
		} else {
			return;
		}
	}

	// 镜像二叉树
	// 递归
	public void Mirror(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			return;
		}
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		if (root.left != null) {
			Mirror(root.left);
		}
		if (root.right != null) {
			Mirror(root.right);
		}
	}

	// 迭代
	public static void Mirror_Iteration(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node.left != null || node.right != null) {
				TreeNode temp = node.left;
				node.left = node.right;
				node.right = temp;
			}
			if (node.left != null) {
				stack.push(node.left);
			}
			if (node.right != null) {
				stack.push(node.right);
			}
		}
	}

	// 树的子结构 判断B是不是A的子树
	public static boolean HasSubTree(TreeNode root1, TreeNode root2) {
		boolean res = false;
		if (root1 != null && root2 != null) {
			if (root1.val == root2.val) {
				// 根节点值相同的时候
				res = Same(root1, root2);
			}
			if (!res) {
				res = HasSubTree(root1.left, root2);
			}
			if (!res) {
				res = HasSubTree(root1.right, root2);
			}
		}
		return res;
	}

	public static boolean Same(TreeNode first, TreeNode second) {
		if (second == null) {
			return true;
		}
		if (first == null) {
			return false;
		}
		if (first.val != second.val) {
			return false;
		}
		return Same(first.left, second.left) && Same(first.right, second.right);
	}

	// 合并排序链表
	public static ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}

		ListNode m;
		if (list1.val < list2.val) {
			m = list1;
			m.next = Merge(list1.next, list2);
		} else {
			m = list2;
			m.next = Merge(list1, list2.next);
		}
		return m;
	}

	// 反转链表 考虑输入为空
	public static ListNode ReverseList(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy, prenext = head, cur = head, curnext = head.next;
		while (curnext != null) {
			pre.next = curnext;
			cur.next = curnext.next;
			curnext.next = prenext;
			prenext = curnext;
			curnext = cur.next;
		}
		return dummy.next;
	}

	// 链表倒数第k个节点
	// 考虑k大于链表长度 输入为空的情况
	public static ListNode FindKthToTail(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		ListNode temp = head;
		while (temp.next != null && k > 1) {
			temp = temp.next;
			k--;
		}
		if (k != 1) {
			return null;
		}
		while (temp.next != null) {
			temp = temp.next;
			head = head.next;
		}
		return head;
	}

	public static ListNode construct(int[] data) {
		ListNode head = new ListNode(data[0]);
		ListNode cur = head;
		for (int i = 1; i < data.length; i++) {
			cur.next = new ListNode(data[i]);
			cur = cur.next;
		}
		return head;
	}

	// 调整数组 使得奇数在偶数的前面
	public static void reOrderArray_NotStable(int[] array) {
		// 快排划分的方法不稳定 不能保证原来奇偶数的顺序
		int i = 0, j = array.length - 1;
		while (i < j) {
			while (i < j && array[i] % 2 == 1)
				i++;
			while (i < j && array[j] % 2 == 0)
				j--;
			if (i < j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
	}

	/**
	 * 1.要想保证原有次序，则只能顺次移动或相邻交换。 2.i从左向右遍历，找到第一个偶数。 3.j从i+1开始向后找，直到找到第一个奇数。
	 * 4.将[i,...,j-1]的元素整体后移一位，最后将找到的奇数放入i位置，然后i++。 5.終止條件：j向後遍歷查找失敗。
	 */
	public static void reOrderArray_Stable(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
					int t = array[j];
					array[j] = array[j + 1];
					array[j + 1] = t;
				}
			}
		}
	}

	public static void reOrderArray2(int[] array) {
		List<Integer> odd = new ArrayList<>();
		List<Integer> even = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0) {
				even.add(array[i]);
			} else {
				odd.add(array[i]);
			}
		}
		int i = 0;
		for (int e : odd) {
			array[i++] = e;
		}
		for (int e : even) {
			array[i++] = e;
		}
	}

	// 数值的整数次方 代码的完整性 考虑周全 整数负数0等
	public static double exposional(double base, int exponent) {
		double temp = 1;
		if (exponent > 0) {
			for (int i = 1; i <= exponent; i++) {
				temp = temp * base;
				if (temp > Double.MAX_VALUE) {
					return -1;
				}
			}
			return temp;
		} else if (exponent < 0) {
			exponent = -exponent;
			for (int i = 1; i <= exponent; i++) {
				temp = temp * base;
				if (temp > Double.MAX_VALUE) {
					return -1;
				}
			}
			temp = 1.0 / temp;
			return temp;
		} else {
			return 1;
		}
	}

	// 统计二进制数中1的个数
	// http://www.360doc.com/content/15/1108/16/1317564_511672961.shtml
	int CountOne(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	int CountOne2(int n) {
		int count = 0;
		int flag = 1;
		while (flag > 0) {
			count += (n & flag) != 0 ? 1 : 0;
			flag = flag << 1;
		}
		return count;
	}

	// 变态青蛙跳 可以跳 1 2 ... n 个 动态规划
	// http://www.nowcoder.com/questionTerminal/22243d016f6b47f2a6928b4313c85387
	public int JumpFloorII(int target) {
		if (target == 0) {
			return 0;
		}

		int[] dp = new int[target + 1];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= target; i++) {
			dp[i] = 0;
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j];
			}
		}

		return dp[target];
	}

	// 青蛙跳台阶
	public int JumpFloor(int target) {
		if (target == 1) {
			return 1;
		}
		if (target == 2) {
			return 2;
		}

		return JumpFloor(target - 1) + JumpFloor(target - 2);
	}

	// 优化的Fibonacci
	public int Fibonacci(int n) {
		int fib1 = 1;
		int fib2 = 1;
		if (n < 3) {
			return n == 0 ? 0 : 1;
		}
		int cur = 0;
		for (int i = 3; i <= n; i++) {
			cur = fib1 + fib2;
			fib1 = fib2;
			fib2 = cur;
		}
		return cur;
	}

	// 旋转数组中查找
	public int minNumberInRotateArray(int[] array) {
		if (array == null || array.length == 0)
			return 0;
		int start = 0;
		int end = array.length - 1;
		int mid = (start + end) / 2;
		if (array[end] == array[start] || array[mid] == array[end] || array[mid] == array[start]) {
			return findMin(array);
		}
		if (array[mid] > array[start] && array[mid] < array[end]) {
			return array[0];
		}
		while (true) {
			mid = (start + end) / 2;
			if (array[mid] > array[start]) {
				start = mid;
			} else if (array[mid] < array[end]) {
				end = mid;
			}
			if ((start + 1) == end)
				break;
		}
		return array[end];

	}

	public int findMin(int[] array) {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= array.length - 1; i++) {
			result = Math.min(result, array[i]);
		}
		return result;
	}

}

// 两个栈实现队列
// 这里注意原Solution里的push操作定义了int node参数 我自己代码写的是x 贴上去后出问题还在瞎找原因 切记
class NowCodeStack {
	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();

	public void push(int x) {
		stack1.push(x);
	}

	// 提前判断空
	public int pop() {
		if (!stack2.isEmpty()) {
			return stack2.pop();
		} else {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
			return stack2.pop();
		}
	}
}

class ListNode {
	int val;
	ListNode next = null;

	ListNode(int val) {
		this.val = val;
	}

	public String toString() {
		return String.valueOf(this.val);
	}
}

class RandomListNode {
	int label;
	RandomListNode next = null;
	RandomListNode random = null;

	RandomListNode(int label) {
		this.label = label;
	}

	public String toString() {
		return String.valueOf(label);
	}
}
