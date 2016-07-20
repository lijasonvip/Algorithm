package com.bo.leetcode;

public class RegularExpressionMatching10 {

	//匹配正则表达式
	
	/**
	 *  1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
		2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
		3, If p.charAt(j) == '*': 
   			here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                      dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
        http://www.xuebuyuan.com/1936978.html
        	这里要注意的情况 *匹配它之前的字符可以出现任意多次 和 通配符匹配不一样
	 */
	public static boolean isMatch1(String s, String p){
		if (s.length() < 1 && p.length() < 1) {
			return true;
		}
        if(p.length() < 1)
            return false;
		boolean[][] dp = new boolean[s.length()+1][p.length()+1];
		dp[0][0] = true;
		if (p.charAt(0) == '*') {
			dp[0][1] = true;
		}
		for (int i = 1; i < p.length(); i++) {
	        if (p.charAt(i) == '*' && dp[0][i-1]) {
	            dp[0][i+1] = true;
	        }
	    }
	    for (int i = 0 ; i < s.length(); i++) {
	        for (int j = 0; j < p.length(); j++) {
	            if (p.charAt(j) == '.') {
	                dp[i+1][j+1] = dp[i][j];
	            }
	            if (p.charAt(j) == s.charAt(i)) {
	                dp[i+1][j+1] = dp[i][j];
	            }
	            if (p.charAt(j) == '*') {
	                if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
	                    dp[i+1][j+1] = dp[i+1][j-1];
	                } else {
	                    dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
	                }
	            }
	        }
	    }
	    return dp[s.length()][p.length()];
	}
	
	
	/**
	 * 递归方法
	 * 
	 */
	public static boolean isMatch(String s, String p){
		if (p.length() < 1) {
			return s.length() < 1;
		}
		if (p.length() == 1) {
			return (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
		}
		//next char is not *: must match current character
		if (p.charAt(1) != '*') {
			if (s.length() < 1) {
				return false;
			}
			return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
		}
		//next char is *
		while( !s.isEmpty() && (s.charAt(0) == p.charAt(0)) || (p.charAt(0) == '.')){
			if (isMatch(s, p.substring(2))) {
				return true;
			}
			s = s.substring(1);
		}
		//continue with the rest
		return isMatch(s, p.substring(2));
	}
	
	public static void main(String[] args) {
		String string = "abb";
		String p = "c*a*b";
		System.out.println(isMatch(string, p));
	}
}
