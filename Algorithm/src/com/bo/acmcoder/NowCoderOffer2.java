package com.bo.acmcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class NowCoderOffer2 {

	// 34题往后的题目

	public static void main(String[] args) {
		FindContinuousSequence(15);
	}

	//句子中单词旋转
	public static String ReverseSentence(String str){
		if (str == null || str.length() == 0) {
			return "";
		}
		if (str.equals(" ")) {
			return " ";
		}
		StringTokenizer token = new StringTokenizer(str);
		Stack<String> stack = new Stack<>();
		while(token.hasMoreTokens()){
			stack.push(token.nextToken());
		}
		StringBuilder sBuilder = new StringBuilder();
		while(!stack.isEmpty())
			sBuilder.append(stack.pop() + " ");
		return sBuilder.deleteCharAt(sBuilder.length()-1).toString();
	}
	
	
	// 左旋转
	public static String LeftRotateString(String str, int n) {
		if (n > str.length()) {
			return "";
		}
		String a = str.substring(0, n);
		String b = str.substring(n, str.length());
		StringBuffer c = new StringBuffer(a).reverse().append(new StringBuffer(b).reverse());
		return c.reverse().toString();
	}

	// 反转单词的7种方法
	public static String reverse1(String s) {
		int length = s.length();
		if (length <= 1)
			return s;
		String left = s.substring(0, length / 2);
		String right = s.substring(length / 2, length);
		return reverse1(right) + reverse1(left);
	}

	public static String reverse2(String s) {
		int length = s.length();
		String reverse = "";
		for (int i = 0; i < length; i++)
			reverse = s.charAt(i) + reverse;
		return reverse;
	}

	public static String reverse3(String s) {
		char[] array = s.toCharArray();
		String reverse = "";
		for (int i = array.length - 1; i >= 0; i--)
			reverse += array[i];

		return reverse;
	}

	public static String reverse4(String s) {
		return new StringBuffer(s).reverse().toString();
	}

	public static String reverse5(String orig) {
		char[] s = orig.toCharArray();
		int n = s.length - 1;
		int halfLength = n / 2;
		for (int i = 0; i <= halfLength; i++) {
			char temp = s[i];
			s[i] = s[n - i];
			s[n - i] = temp;
		}
		return new String(s);
	}

	public static String reverse6(String s) {

		char[] str = s.toCharArray();

		int begin = 0;
		int end = s.length() - 1;

		while (begin < end) {
			str[begin] = (char) (str[begin] ^ str[end]);
			str[end] = (char) (str[begin] ^ str[end]);
			str[begin] = (char) (str[end] ^ str[begin]);
			begin++;
			end--;
		}

		return new String(str);
	}

	public static String reverse7(String s) {
		char[] str = s.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < str.length; i++)
			stack.push(str[i]);

		String reversed = "";
		for (int i = 0; i < str.length; i++)
			reversed += stack.pop();

		return reversed;
	}

	// 和为s的连续正数序列
	public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if (sum < 3) {
			return res;
		}
		int small = 1, big = 2;
		int middle = (sum + 1) / 2;
		int cursum = small + big;
		while (small < sum) {
			if (cursum == sum) {
				ArrayList<Integer> temp = new ArrayList<>();
				for (int i = small; i <= big; i++) {
					temp.add(i);
				}
				res.add(temp);
				big++;
				cursum += big;
			}

			if (cursum < sum) {
				big++;
				cursum += big;
			}
			if (cursum > sum) {
				cursum -= small;
				small++;
			}
			// while(cursum > sum && small < middle){
			// cursum -= small;
			// small++;
			// if (cursum == sum) {
			// ArrayList<Integer> temp = new ArrayList<>();
			// for(int i = small;i<= big; i++){
			// temp.add(i);
			// }
			// res.add(temp);
			// }
			// }
			// big ++;
			// cursum += big;
		}
		return res;

	}

	// 数组中只出现一次的数字
	public static void FindNumsAppearOnce(int[] array, int[] num1, int[] num2) {
		if (array.length < 2)
			return;
		int myxor = 0;
		int flag = 1;
		for (int i = 0; i < array.length; ++i)
			myxor ^= array[i];
		while ((myxor & flag) == 0)
			flag <<= 1;
		// num1[0] = myxor;
		// num2[0] = myxor;
		for (int i = 0; i < array.length; ++i) {
			if ((flag & array[i]) == 0)
				num2[0] ^= array[i];
			else
				num1[0] ^= array[i];
		}
	}

	public static void FindNumsAppearsOnce2(int[] array, int[] num1, int[] num2) {
		if (array == null || array.length == 0) {
			return;
		}
		int exclusiveor = 0;
		for (int i = 0; i < array.length; i++) {
			exclusiveor ^= array[i];
		}
		int difindex = FindFirstBit1FromRightToLeft(exclusiveor);

		for (int i = 0; i < array.length; i++) {
			if (BitAtIndex(array[i], difindex) == 1) {
				num1[0] ^= array[i];
			} else {
				num2[0] ^= array[i];
			}
		}
	}

	public static int FindFirstBit1FromRightToLeft(int exc) {
		int count = 0;
		while (((exc & 1) == 0) && (count < Integer.SIZE)) {
			exc = exc >> 1;
			count++;
		}
		return count;
	}

	public static int BitAtIndex(int num, int index) {
		num = num >> index;
		return (num & 1);
	}

	// 平衡二叉树的判断
	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		int left = TreeDepth(root.left);
		int right = TreeDepth(root.right);
		int dff = left - right;
		if (dff > 1 || dff < -1) {
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right);
	}

	// 二叉树的深度
	public static int TreeDepth(TreeNode pRoot) {
		if (pRoot == null) {
			return 0;
		}

		int left = TreeDepth(pRoot.left);
		int right = TreeDepth(pRoot.right);

		return (left > right) ? (left + 1) : (right + 1);
	}

	// 迭代求二叉树深度使用栈 求栈的最大深度即二叉树的深度 或者层次遍历最大层即深度
	public static int TreeDepth2(TreeNode root) {
		return 0;
	}

	public static void DFS(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode node;
		;
		while (!stack.isEmpty()) {
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

	// 数字在排序数组中出现的次数
	public static int GetNumberOfK(int[] array, int k) {
		int number = 0;
		if (array.length == 0) {
			return number;
		}
		int first = GetFirstK(array, k, 0, array.length - 1);
		int last = GetLastK(array, k, 0, array.length - 1);
		if (first > -1 && last > -1) {
			number = last - first + 1;
		}
		return number;
	}

	public static int GetFirstK(int[] array, int k, int start, int end) {
		if (start > end) {
			return -1;
		}
		int middle = start + (end - start) / 2;
		int middata = array[middle];
		if (middata == k) {
			if ((middle > 0 && array[middle - 1] != k) || middle == 0) {
				return middle;
			} else
				end = middle - 1;
		} else if (middata > k) {
			end = middle - 1;
		} else {
			start = middle + 1;
		}
		return GetFirstK(array, k, start, end);
	}

	public static int GetLastK(int[] array, int k, int start, int end) {
		if (start > end) {
			return -1;
		}
		int midindex = start + (end - start) / 2;
		int middata = array[midindex];
		if (middata == k) {
			if ((midindex < end && array[midindex + 1] != k) || midindex == end) {
				return midindex;
			} else {
				start = midindex + 1;
			}
		} else if (middata > k) {
			end = midindex - 1;
		} else {
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
			if (step < (len1 - len2)) {
				head1 = head1.next;
				continue;
			} else if (head1.val == head2.val) {
				return head1;
			} else {
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
