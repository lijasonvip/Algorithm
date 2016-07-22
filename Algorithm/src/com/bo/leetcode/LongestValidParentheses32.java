package com.bo.leetcode;

import java.util.Stack;

public class LongestValidParentheses32 {

	// 最长合法括号串长度

	/**
	 * 动态规划
	 * https://discuss.leetcode.com/topic/2426/my-dp-o-n-solution-without-using-stack
	 * 63%
	 * construct a array longest[], for any longest[i], it stores the longest length of valid parentheses which is end at i.
	 * If s[i] is '(', set longest[i] to 0,because any string end with '(' cannot be a valid one.
	 * Else if s[i] is ')'
	 * If s[i-1] is '(', longest[i] = longest[i-2] + 2  越界检查 (i-2) >= 0 ? longest[i-2]+2 : 2;
	 * Else if s[i-1] is ')' and s[i-longest[i-1]-1] == '(', longest[i] = longest[i-1] + 2 + longest[i-longest[i-1]-2]
	 * 		i-longest[i-1]-1 >= 0 && s.charAt(i-longest[i-1] -1) = '('
	 * 			i-longest[i-1]-2 >= 0 ? lonest[i-1]+2+longest[i-longest[i-1]-2] : lonest[i-1]+2; 
	 * 注意检查下标越界
	 * 
	 */
	public static int longestValidParentheses(String s) {
		if (s.length() < 2) {
			return 0;
		}
		int max = 0;
		int[] longest = new int[s.length()];
		longest[0] = 0;
		for (int i = 1; i < longest.length; i++) {
			char c = s.charAt(i);
			
			if (c == ')') {
				if (s.charAt(i - 1) == '(') {
					longest[i] = (i - 2) > 0 ? longest[i - 2] + 2 : 2;
					max = Math.max(max, longest[i]);
				} else {
					if (i-longest[i-1] -1 >= 0 && s.charAt(i-longest[i-1]-1) == '(') {
						longest[i] = longest[i - 1] + 2 + ((i - longest[i - 1] - 2) >= 0 ?  longest[i - longest[i - 1] - 2] : 0);
						max = Math.max(max, longest[i]);
					}
				}
			}
		}
		return max;
	}
	
	/**
	 * 使用栈
	 * 6.7%
	 * https://discuss.leetcode.com/topic/2289/my-o-n-solution-using-a-stack/2
	 * Scan the string from beginning to end.
	 * If current character is '(',push its index to the stack. 
	 * If current character is ')' and the character at the index of the top of stack is '(', 
	 * we just find a matching pair so pop from the stack. Otherwise, we push the index of')' to the stack.
	 * After the scan is done, the stack will only contain the indices of characters which cannot be matched. 
	 * Then let's use the opposite side - substring between adjacent indices should be valid parentheses.
	 * 
	 * 
	 */
	public static int longestValid(String s){
		Stack<Integer> stack = new Stack<>();
		int longest = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(')
				stack.push(i);
			else{
				if(!stack.isEmpty()){
					if (s.charAt(stack.peek()) == '(') {
						stack.pop();
					}else {
						stack.push(i);
					}
				}else
					stack.push(i);
			}
		}
		
		if (stack.isEmpty()) {
			return s.length();
		}else{
			//反序找最长间隔的子串长度
			int big = s.length(), top = 0;
			while(!stack.isEmpty()){
				top = stack.pop();
				longest = Math.max(longest, big - top - 1);
				big = top;
			}
			//栈中最后一个求得是和0的距离 top - 0 即big
			longest = Math.max(longest, big);
		}
		return longest;
	}

	public static void main(String[] args) {
		System.out.println(longestValidParentheses("(()())"));
	}
}
