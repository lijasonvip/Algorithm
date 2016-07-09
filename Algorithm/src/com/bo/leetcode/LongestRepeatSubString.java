package com.bo.leetcode;

import java.util.Arrays;

public class LongestRepeatSubString {

	public static int comlen(char[] a, char[] b) {
		int len = 0, i = 0, j = 0;
		if(i<a.length && j < b.length && a[i] == b[j]){
			++len;
		}
		return len;
	}
	
	public static int LRS_Base(String s){
		int maxlen = 0;
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				char[] a = subArray(arr, i);
				char[] b = subArray(arr, j);
				int len = comlen(a, b);
				
				if(len > maxlen)
					maxlen = len;
			}
		}
		return maxlen;
	}
	
	public static char[] subArray(char[] c, int start){
		char[] r = new char[c.length - start];
		for(int i=0;i<c.length-start;i++){
			r[i] = c[i+start];
		}
		return r;
	}
	
	public static void main(String[] args) {
		String s = "adfsdfddddda";
		System.out.println(LRS_Base(s));
	}
}
