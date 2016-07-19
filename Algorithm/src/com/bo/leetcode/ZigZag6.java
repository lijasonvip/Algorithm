package com.bo.leetcode;

public class ZigZag6 {

	
	/*n=numRows
	Δ=2n-2    1                           2n-1                         4n-3
	Δ=        2                     2n-2  2n                    4n-4   4n-2
	Δ=        3               2n-3        2n+1              4n-5       .
	Δ=        .           .               .               .            .
	Δ=        .       n+2                 .           3n               .
	Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
	Δ=2n-2    n                           3n-2                         5n-4
	*/
	/**
	 * https://segmentfault.com/a/1190000005751185
	 * 91%
	 */
	public static String convert(String normal, int rows){
		if (rows == 1) {
			return normal;
		}
		StringBuilder zig = new StringBuilder();
		int magic = rows * 2 - 2;
		int init = magic;
		for(int i=0;i<rows;i++){
			fill(zig, i, init, magic, normal);
			init -= 2;
		}
		return zig.toString();
	}
	
	public static void fill(StringBuilder zig, int start, int init, int magic, String normal){
		while(start < normal.length()){
			if (init == 0) {
				init = magic;
			}
			zig.append(normal.charAt(start));
			start = start + init;
			init = magic - init;
		}
	}
	
	 /**
	 * 49%
	 */
	public String convert2(String s, int numRows) {
	        if(numRows <= 1) return s;
	        StringBuilder[] rows = new StringBuilder[numRows];
	        
	        int currRow = 0;
	        int step = 1;
	        for(int i=0; i<s.length(); i++) {
	            if(rows[currRow] == null)
	                rows[currRow] = new StringBuilder();
	            rows[currRow].append(s.charAt(i));
	            currRow += step;
	            if(currRow == rows.length || currRow == -1) {
	                step = -step;
	                currRow += (2*step);
	            }
	        }
	        
	        StringBuilder output = new StringBuilder();
	        for(int i=0; i<rows.length; i++) {
	            if(rows[i] != null)
	                output.append(rows[i]);
	        }
	        return output.toString();
	    }
	
	public static void main(String[] args) {
		System.out.println(convert("123456789ABCDEFGHIJK", 4));
	}
}
