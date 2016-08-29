package com.bo.acmcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NowCoderOffer {

	// 牛客网的剑指offer

	public static void main(String[] args) {
		int[] data = { 1, 3, 5, 7, 9 };
		int[] data2 = { 2, 4, 6, 8, 10 };
		ListNode head = Merge(construct(data), construct(data2));
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	
	//
	
	// 包含min 和 max 函数的栈
	//写个内部类吧
	class MinMaxStack{
		Stack<Integer> original = new Stack<>();
		Stack<Integer> min = new Stack<>();
		
		public void push(int node){
			if (original.isEmpty()) {
				original.push(node);
				min.push(node);
			}
			else{
				original.push(node);
				if (node < min.peek()) {
					min.push(node);
				}else{
					min.push(min.peek());
				}
			}
		}
		
		public void pop(){
			if (!original.isEmpty()) {
				original.pop();
				min.pop();
			}
		}
		
		public int top(){
			if (!original.isEmpty()) {
				return original.peek();
			}
			return -1;
		}
		
		public int min(){
			return min.peek();
		}
	}

	// 打印矩阵
	   public ArrayList<Integer> printMatrix(int[][] matrix) {
	        ArrayList<Integer> result = new ArrayList<Integer>() ;
	        if(matrix==null || matrix.length==0) { return result ; }
	 
	        printMatrixClockWisely(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, result);
	 
	        return result ;
	    }
	     
	    public void printMatrixClockWisely(int[][] matrix, int startRow, int startCol, int endRow, int endCol, ArrayList<Integer> result) {
	        if(startRow<endRow && startCol<endCol) {
	            for(int j=startCol; j<=endCol; j++) { result.add(matrix[startRow][j]) ; }   //Right
	            for(int i=startRow+1; i<=endRow-1; i++) { result.add(matrix[i][endCol]) ; }     //Down
	            for(int j=endCol; j>=startCol; j--) { result.add(matrix[endRow][j]) ; }     //Left
	            for(int i=endRow-1; i>=startRow+1; i--) { result.add(matrix[i][startCol]) ; }   //Up
	            printMatrixClockWisely(matrix, startRow + 1, startCol + 1, endRow - 1, endCol - 1, result) ;
	        }else if(startRow==endRow && startCol<endCol) {
	            for(int j=startCol; j<=endCol; j++) { result.add(matrix[startRow][j]) ; }
	        }else if(startRow<endRow && startCol==endCol) {
	            for(int i=startRow; i<=endRow; i++) { result.add(matrix[i][endCol]) ; }
	        }else if(startRow==endRow && startCol==endCol) {
	            result.add(matrix[startRow][startCol]) ;
	        }else {
	            return ;
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
