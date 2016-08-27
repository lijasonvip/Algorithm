package com.bo.acmcoder;

import java.util.Stack;

public class NowCoderOffer {

	// 牛客网的剑指offer
 
	//统计二进制数中1的个数
	//http://www.360doc.com/content/15/1108/16/1317564_511672961.shtml
	int CountOne(int n){
		int count = 0;
		while(n != 0){
			n = n & (n-1);
			count ++;
		}
		return count;
	}
	
	int CountOne2(int n){
		int count = 0;
		int flag = 1;
		while(flag > 0){
			count += (n & flag) != 0 ? 1 : 0;
			flag = flag << 1;
		}
		return count;
	}
	
	// 变态青蛙跳  可以跳 1 2 ... n 个  动态规划
	//http://www.nowcoder.com/questionTerminal/22243d016f6b47f2a6928b4313c85387
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
