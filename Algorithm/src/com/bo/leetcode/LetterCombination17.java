package com.bo.leetcode;

import java.util.LinkedList;
import java.util.List;

public class LetterCombination17 {

	/**
	 * 广度优先
	 */
	public static List<String> letterCombinations(String digits) {
        String mapping[] = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        LinkedList<String> ans = new LinkedList<>();
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			while(ans.peek().length() == i){
				String t = ans.remove();
				for(char s : mapping[x].toCharArray())
					ans.add(t + s);
			}
		}
        return ans;
    }
	
	/**
	 * my
	 */
	public static List<String> letterCombinations2(String digits) {
		if (digits == null) {
			return null;
		}
        String mapping[] = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> res = new LinkedList<>();
        res.add("");
        
        for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			while(res.peek().length() == i){
				String top = res.remove();
				for(char c:mapping[x].toCharArray()){
					res.add(top+c);
				}
			}
		}
        
        return res;
    }
	
	public static void main(String[] args) {
		String s = "234";
		letterCombinations(s);
	}
	
}
