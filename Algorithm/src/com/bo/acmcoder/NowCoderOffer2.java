package com.bo.acmcoder;

import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.BlockingDeque;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.xml.crypto.Data;

import com.bo.offer.Tree;

public class NowCoderOffer2 {

	// 34题往后的题目

	public static void main(String[] args) {

		int[] data = {1,2,3,3,3,3,4,5};
		System.out.println(GetNumberOfK(data, 3));
	}
	
	//二叉树的深度
	public static int TreeDepth(TreeNode pRoot){
		if (pRoot == null) {
			return 0;
		}
		
		int left = TreeDepth(pRoot.left);
		int right = TreeDepth(pRoot.right);
		
		return (left > right) ? (left + 1) : (right + 1);
	}
	//迭代求二叉树深度使用栈 求栈的最大深度即二叉树的深度
	public static int TreeDepth2(TreeNode root){
		if (root == null) {
			return 0;
		}
		Stack<TreeNode> stack = new Stack<>();
		int max = 0;
		stack.push(root);
		TreeNode p = root.left;
		while(p != null || !stack.isEmpty()){
			while(p != null){
				stack.push(p);
				p = p.left;
			}
			if (stack.size() > max) {
				max = stack.size();
			}
			stack.pop();
			p = stack.peek().right;
			
		}
	}
	
	public static void DFS(TreeNode root){
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode node;;
		while(!stack.isEmpty()){
			node = stack.pop();
			System.out.print(node.val + " ");
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
	}
	
	//数字在排序数组中出现的次数
	public static int GetNumberOfK(int[] array, int k){
		int number = 0;
		if (array.length == 0) {
			return number;
		}
		int first = GetFirstK(array, k, 0, array.length-1);
		int last = GetLastK(array, k, 0, array.length - 1);
		if (first > -1 && last > -1) {
			number = last - first + 1;
		}
		return number;
	}
	public static int GetFirstK(int[] array, int k, int start, int end){
		if (start > end) {
			return -1;
		}
		int middle = start + (end - start) / 2;
		int middata = array[middle];
		if (middata == k) {
			if ((middle > 0 && array[middle-1] != k) || middle == 0) {
				return middle;
			}
			else
				end = middle - 1;
		}
		else if (middata > k) {
			end = middle - 1;
		}else {
			start = middle + 1;
		}
		return GetFirstK(array, k, start, end);
	}
	
	public static int GetLastK(int[] array, int k, int start, int end){
		if (start > end) {
			return -1;
		}
		int midindex = start + (end - start) / 2;
		int middata = array[midindex];
		if (middata == k) {
			if ((midindex < end && array[midindex+1] != k) || midindex == end) {
				return midindex;
			}else{
				start = midindex + 1;
			}
		}else if (middata > k) {
			end = midindex - 1;
		}else {
			start = midindex + 1;
		}
		return GetLastK(array, k, start, end);
	}

	// 两个链表的第一个公共节点
	public static ListNode FindFirstCommontNode(ListNode pHead1, ListNode pHead2) {
		if (pHead1 == null || pHead2 == null) {
			return null;
		}
		int len1 = 0, len2 = 0;
		ListNode head1 = pHead1, head2 = pHead2;
		while (head1 != null) {
			head1 = head1.next;
			len1++;
		}
		while (head2 != null) {
			head2 = head2.next;
			len2++;
		}
		if (len2 > len1) {
			head1 = pHead2;
			head2 = pHead1;
		} else {
			head1 = pHead1;
			head2 = pHead2;
		}
		int step = 0;
		for (; step < len1; step++) {
			if (step < (len1-len2)) {
				head1 = head1.next;
				continue;
			}else if (head1.val == head2.val) {
				return head1;
			}else {
				head1 = head1.next;
				head2 = head2.next;
			}
		}

		return null;
	}

	// 数组中的逆序对
	public static int InversePairs(int[] array) {
		if (array == null || array.length <= 0) {
			return 0;
		}
		int pairsNum = mergeSort(array, 0, array.length - 1);
		return pairsNum;
	}

	public static int mergeSort(int[] array, int left, int right) {
		if (left == right) {
			return 0;
		}
		int mid = (left + right) / 2;
		int leftNum = mergeSort(array, left, mid);
		int rightNum = mergeSort(array, mid + 1, right);
		return (Sort(array, left, mid, right) + leftNum + rightNum) % 1000000007;
	}

	public static int Sort(int[] array, int left, int middle, int right) {
		int current1 = middle;
		int current2 = right;
		int current3 = right - left;
		int temp[] = new int[right - left + 1];
		int pairsnum = 0;
		while (current1 >= left && current2 >= middle + 1) {
			if (array[current1] > array[current2]) {
				temp[current3--] = array[current1--];
				pairsnum += (current2 - middle); // 这个地方是current2-middle！！！！
				if (pairsnum > 1000000007)// 数值过大求余
				{
					pairsnum %= 1000000007;
				}
			} else {
				temp[current3--] = array[current2--];
			}
		}
		while (current1 >= left) {
			temp[current3--] = array[current1--];
		}
		while (current2 >= middle + 1) {
			temp[current3--] = array[current2--];
		}
		// 将临时数组赋值给原数组
		int i = 0;
		while (left <= right) {
			array[left++] = temp[i++];
		}
		return pairsnum;
	}

	// 第一个只出现一次的字符
	public static void input() {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		System.out.println(FindChar(string));
	}

	public static char FindChar(String str) {
		char[] ch = str.toCharArray();
		int[] map = new int[256];
		for (char c : ch) {
			map[c]++;
		}
		for (char c : ch) {
			if (map[c] == 1) {
				return c;
			}
		}
		return '\0';
	}

	// 丑数 只包含2.3.5因子的数叫丑数
	public static boolean isUgly(int number) {
		while (number % 2 == 0)
			number /= 2;
		while (number % 3 == 0)
			number /= 3;
		while (number % 5 == 0)
			number /= 5;

		return (number == 1) ? true : false;
	}

	// 第index个丑数 每次判断都要从头取余计算 效率太低
	public static int getUglyNumber(int index) {
		int count = 1;
		if (index <= 0) {
			return 0;
		}
		int num = 0;
		while (count <= index) {
			num++;
			if (isUgly(num)) {
				count++;
			}
		}
		return num;
	}

	// 下一个丑数是上一个丑数乘丑数因子所得
	public static int getUglyNumber2(int index) {
		if (index <= 0) {
			return 0;
		}
		int[] res = new int[index];
		int count = 0;
		int i2 = 0, i3 = 0, i5 = 0;
		res[0] = 1;
		int tmp = 0;
		while (count < index - 1) {
			tmp = Math.min(res[i2] * 2, Math.min(res[i3] * 3, res[i5] * 5));
			if (tmp == res[i2] * 2) {
				i2++;
			}
			if (tmp == res[i3] * 3) {
				i3++;
			}
			if (tmp == res[i5] * 5) {
				i5++;
			}
			res[++count] = tmp;
		}
		return res[index - 1];

	}
}
