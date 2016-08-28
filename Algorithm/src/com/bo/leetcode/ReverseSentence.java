package com.bo.leetcode;

import java.util.StringTokenizer;

public class ReverseSentence {
	public static void main(String[] args) {
		char[] c = { 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'r', 'a', 'c', 't', 'i',
				'c', 'e' };
		System.out.println(myReverse(new String(c)));
	}

	public static char[] reverse(char[] input) {
		int len = input.length;

		char[] output = new char[len];

		int previousInputSavepoint = 0;
		int previousOutputSavepoint = len;
		boolean continueCopy = false;

		for (int idx = 0; idx < len; idx++) {
			if (Character.isWhitespace(input[idx]) || continueCopy) {
				int effectiveLength = idx - previousInputSavepoint;
				System.arraycopy(input, previousInputSavepoint, output, (previousOutputSavepoint - effectiveLength),
						effectiveLength);
				previousInputSavepoint = idx;
				previousOutputSavepoint -= effectiveLength;
				continueCopy = true;
			}

			if (Character.isLetter(input[idx]) && continueCopy)
				continueCopy = false;
		}

		System.arraycopy(input, previousInputSavepoint, output, 0, len - previousInputSavepoint);
		return output;
	}

	public static char[] reverseString(char array[]) {
		String input = new String(array);
		StringTokenizer str = new StringTokenizer(input, " ");
		StringBuffer lastString = new StringBuffer();
		StringBuffer curString = new StringBuffer();
		while (str.hasMoreTokens()) {
			String temp = str.nextToken();
			if (lastString.length() == 0) {
				lastString.append(temp);
			} else {
				curString.append(temp).append(" ").append(lastString.toString());
				lastString.delete(0, lastString.length());
				lastString.append(curString.toString());
				curString.delete(0, curString.length());
			}
		}
		return lastString.toString().toCharArray();
	}
	
	public static String myReverse(String str){
		StringTokenizer s = new StringTokenizer(str);
		StringBuilder end = new StringBuilder();
		StringBuilder cur = new StringBuilder();
		while(s.hasMoreTokens()){
			String temp = s.nextToken();
			if (end.length() == 0) {
				end.append(" ").append(temp);
			}else{
				cur.append(" ").append(temp).append(end.toString());
				end.delete(0, end.length());
				end.append(cur.toString());
				cur.delete(0, cur.length());
			}
			
		}
		return end.deleteCharAt(0).toString();
	}
}