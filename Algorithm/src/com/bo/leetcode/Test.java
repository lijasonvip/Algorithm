package com.bo.leetcode;

public class Test {

	public static void main(String[] args) {
		String string = "§u§1Hello World!";
		String aString = string.replaceAll("§[0-9a-z]", "");
		System.out.println(aString);
		
		System.out.println(string.substring(string.lastIndexOf("§")+2));
	}
	
	/**
	 * 
	 */
	public static void est(){
		
		int b = 1;
		b = b + 1;
		
	}
}
