package com.bo.offer;

import javax.naming.spi.DirStateFactory.Result;

public class Bit {

	/**
	 * @param in
	 * 26 to 10
	 */
	public static int ToDecimal(String in) {
		char[] ch = in.toCharArray();
		int len = ch.length;
		int sum = 0;
		for (int i = 0; i < ch.length; i++) {
			int pow = len - i -1;
			int remain = ch[i] - 'A' + 1;
			
			sum = (int)Math.pow(26, pow) * remain + sum;
		}
		return sum;
	}
	
	/**
	 * @param in
	 * 10 to 26
	 */
	public static String To26(int in){
		StringBuffer result = null;
		int remain  = in % 26;
		while(remain != 0){
			in = in / 26;
			char ch = (char) ('A' - remain + 1);
			result.append(String.valueOf(ch));
			remain = in % 26;
		}
		result.reverse();
		return result.toString();
	}
	
	public static void main(String[] args) {
		//System.out.println(Math.pow(2, 3));
		//System.out.println(ToDecimal("ABC"));
		System.out.println(To26(731));
	}
}
