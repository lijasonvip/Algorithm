package com.bo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses20 {

	/**
	 * 括号匹配 使用栈
	 * 12%
	 */
	public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c:s.toCharArray()){
            if(stack.isEmpty()){
                stack.push(c);
            }else if(match(stack.peek(),c)){
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        if(stack.isEmpty())
            return true;
        else
            return false;
    }
    
    public boolean match(char a, char b){
        if(a == '(' && b == ')')
            return true;
        else if(a == '[' && b == ']')
            return true;
        else if(a == '{' && b == '}')
            return true;
        else
            return false;
    }
    
    /**
     * 也是用栈 但是判断匹配的时候用差值计算
     * 各自匹配的左右括号之间的差大于0小于3也就是在是1或2
     */
    public boolean isValid2(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        if (s.length() % 2 != 0) {
            return false;
        } else {
            Stack<Character> stack = new Stack<Character>();
            for (int i = 0; i < s.length(); i++) {
                char c1 = s.charAt(i);
                if(!stack.isEmpty()){
                    char s1 = stack.peek();
                    if(c1 - s1 >0 && c1 - s1 < 3){
                        stack.pop();
                    }else{
                        stack.push(c1);
                    }
                }else{
                    stack.push(c1);
                }
            }
            return stack.isEmpty();
        }
    }
    
    /**
     * 使用hashmap判断匹配
     */
    public boolean isValid3(String s) {
        if (s == null) {
            return true;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (char symbol : s.toCharArray()) {
            if (!map.containsKey(symbol)) {
                stack.push(symbol);
            } else if (stack.isEmpty() || stack.pop() != map.get(symbol)) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
