package com.bo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses22 {

	/**
	 * 深度优先 29%
	 */
	public static List<String> generateParenthesis1(int n) {
		List<String> result = new LinkedList<String>();
		if (n > 0)
			generateParenthesisCore("", n, n, result);
		return result;
	}

	private static void generateParenthesisCore(String prefix, int left, int right, List<String> result) {
		if (left == 0 && right == 0)
			result.add(prefix);
		// Has left Parenthesis
		if (left > 0)
			generateParenthesisCore(prefix + '(', left - 1, right, result);
		// has more right Parenthesis
		if (left < right)
			generateParenthesisCore(prefix + ')', left, right - 1, result);
	}

	static StringBuilder sb = new StringBuilder("(");

	/**
	 * 理解递归经典 29%
	 */
	public static List<String> generateParenthesis2(int n) {
		List<String> rst = new LinkedList<String>();
		combination(n - 1, n, sb, rst);
		return rst;
	}

	public static void combination(int left, int right, StringBuilder sb, List<String> rst) {
		if (left == 0 && right == 0) {
			rst.add(sb.toString());
		}
		if (left > 0) {
			combination(left - 1, right, sb.append("("), rst);
			sb.deleteCharAt(sb.length() - 1);
		}
		if (right > left) {
			combination(left, right - 1, sb.append(")"), rst);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	/**
	 * 16% 动态规划
	 *  f(0): "" 
	 *  f(1): "("f(0)")" 
	 *  f(2): "("f(0)")"f(1), "("f(1)")" 
	 *  f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")" 
	 *  So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(n-1)")"f(1), "(f(n-1)")"
	 */
	public static List<String> generateParenthesis3(int n) {
		List<List<String>> lists = new ArrayList<>();
		// f(0)
		lists.add(Collections.singletonList(""));

		for (int i = 1; i <= n; ++i) {
			// f(i)
			final List<String> list = new ArrayList<>();

			for (int j = 0; j < i; ++j) {
				// f(i) 由 f(0...i-1) 求得
				for (final String first : lists.get(j)) {
					for (final String second : lists.get(i - 1 - j)) {
						// (f(i-1))f(1)
						list.add("(" + first + ")" + second);
					}
				}
			}

			// 添加 f(i)
			lists.add(list);
		}
		// 取最后一个返回 f(n)
		return lists.get(lists.size() - 1);
	}

	/**
	 * 92%
	 */
	public static List<String> generateParenthesis4(int n) {
		char[] chars = new char[n * 2];
		List<String> result = new ArrayList<>();
		f(result, chars, 0, n, n);
		return result;
	}

	private static void f(List<String> result, char[] characters, int size, int leftNum, int rightNum) {
		if (leftNum == 0 && rightNum == 0) {
			result.add(String.copyValueOf(characters));
		}
		if (leftNum > 0 && rightNum > 0) {
			characters[size] = '(';
			f(result, characters, size + 1, leftNum - 1, rightNum);
		}
		if (rightNum > 0 && rightNum > leftNum) {
			characters[size] = ')';
			f(result, characters, size + 1, leftNum, rightNum - 1);
		}
		//递归时候由size=6直接回到size=2
	}
	
	/**
	 * 回溯
	 */
	public List<String> generateParenthesis5(int n) {
        List<String> list = new ArrayList<>();
        if (n > 0) generateParenthesis("", n * 2, 0, list, n * 2);
        return list;
    }
    
    public void generateParenthesis(String exp, int i, int sum, List<String> list, int max) {
        if (sum < 0 || sum > max / 2) {
            return;
        } else if (sum == 0 && i == 0) {
            list.add(exp);
        } else if (i > 0){
            generateParenthesis(exp + "(", i - 1, sum + 1, list, max);
            generateParenthesis(exp + ")", i - 1, sum - 1, list, max);
        }
    }
    
    public List<String> generateParenthesis6(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    
    public void backtrack(List<String> list, String str, int open, int close, int max){
        
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        
        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }

	public static void main(String[] args) {
		List<String> reStrings = generateParenthesis4(3);
	}
}
