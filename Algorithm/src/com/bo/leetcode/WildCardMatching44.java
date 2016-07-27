package com.bo.leetcode;

public class WildCardMatching44 {

	/**
	 * The basic idea is to have one pointer for the string and one pointer for
	 * the pattern. This algorithm iterates at most length(string) +
	 * length(pattern) times, for each iteration, at least one pointer advance
	 * one step.
	 * str
	 * pattern
	 */
	public static boolean isMatch(String str, String pattern) {
		int s = 0, p = 0, match = 0, starIdx = -1;            
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
           //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else return false;
        }
        
        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;
        
        return p == pattern.length();
	}
	
	 /**
	 * dp
	 */
	public boolean isMatch2(String s, String p) {
	        int m = s.length(), n = p.length();
	        int count = 0;
	        for (int i = 0; i < n; i++) {
	            if (p.charAt(i) == '*') count++;
	        }
	        if (count==0 && m != n) return false;
	        else if (n - count > m) return false;
	        
	        boolean[] match = new boolean[m+1];
	        match[0] = true;
	        for (int i = 0; i < m; i++) {
	            match[i+1] = false;
	        }
	        for (int i = 0; i < n; i++) {
	            if (p.charAt(i) == '*') {
	                for (int j = 0; j < m; j++) {
	                    match[j+1] = match[j] || match[j+1]; 
	                }
	            } else {
	                for (int j = m-1; j >= 0; j--) {
	                    match[j+1] = (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j)) && match[j];
	                }
	                match[0] = false;
	            }
	        }
	        return match[m];
	    }
	
	/**
	 * dp
	 * o(mn) time abd i(n) space
	 */
	public boolean isMatch3(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] match = new boolean[2][n + 1];
        match[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == 0) { // initialized first column
                    match[i % 2][j] = i == 0;
                    continue;
                }
                if (p.charAt(j - 1) == '*') {
                    match[i % 2][j] = (i > 0 && match[(i - 1) % 2][j]) || match[i % 2][j - 1];
                } else {
                    match[i % 2][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') && match[(i - 1) % 2][j - 1];
                }
                
            }
        }
        return match[m % 2][n];
    }
	
	public static void main(String[] args) {
		String s = "aa";
		System.out.println(s.matches("*"));
	}
}
