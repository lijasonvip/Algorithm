package com.bo.leetcode;

import java.util.Stack;
//http://www.acmerblog.com/next-greater-element-5811.html
public class NextGreaterElement {

	/**
	 * 寻找下一个较大元素  我右边第一个比我大的元素
	 * 方法一 使用二重循环 分别遍历
	 * 方法二  栈维护一个递减序列存储数组左边未找到NGE元素
	 */
	public static void findNGE(int[] data){
		if (data.length < 1) {
			return;
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(data[0]);
		int top,next;
		for (int i = 1; i < data.length; i++) {
			next = data[i];
			top = stack.peek();
			
			//判断是否找到了栈顶元素的NGE
			while(!stack.isEmpty() && top < next){
				System.out.println(top + "-->" + next);
				stack.pop();
				
				//继续判断栈顶
				if (!stack.isEmpty()) {
					top = stack.peek();
				}
			}
			stack.push(next);
		}
		while(!stack.isEmpty()){
			top = stack.peek();
			stack.pop();
			System.out.println(top + "-->" + -1);
		}
		
	}
	
	
	public static void main(String[] args) {
		int arr[] = {11,13,10,5,12,21,3};
		findNGE(arr);
	}
}
