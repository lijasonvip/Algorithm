package com.bo.leetcode;

import java.util.Arrays;

public class LongestCommonPrefix14 {
	
	
    /**
     * 64%
     */
    public String longestCommonPrefix(String[] strs) {
    	if (strs == null) {
			return null;
		}
        if(strs.length == 0)
            return "";
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();
        
        int len = Math.min(first.length, last.length);
        int i=0;
        while(i<len && first[i] == last[i])
            i++;
        return String.valueOf(first).substring(0, i);
    }
    
    /**
     * 14%
     */
    public String longestCommonPrefix2(String[] strs) {
        if(strs.length == 0) return "";
        
        StringBuilder commonPrefix = new StringBuilder(strs[0]);
        for(int i=1; i<strs.length; i++) {
            int end = Math.min(commonPrefix.length(), strs[i].length());
            commonPrefix.delete(end,commonPrefix.length());
            int j;
            for(j=0; j<end; j++) {
                if(strs[i].charAt(j) != commonPrefix.charAt(j))
                    break;
            }
            commonPrefix.delete(j,commonPrefix.length());
        }
        return commonPrefix.toString();
    }
}
