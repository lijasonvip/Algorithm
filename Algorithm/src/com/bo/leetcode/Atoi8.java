package com.bo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Atoi8 {
	
	/**
	 * 1. empty string
	 * 2. remove spaces
	 * 3. handle signs
	 * 4. convert number and avoid overflow
	 * 
	 * additional
	 * 5. 0x01
	 * 6. 1e-5
	 * 7. chinese and other ugly character
	 * 
	 * 
	 */
	public static int atoi(String s){
		int index = 0, sign = 1, total = 0;
		//1. empty string
		if (s.length() == 0) {
			return 0;
		}
		
		//2. remove spaces and chinese and other ugly character
		//只需要去掉空格就行了
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < s.length(); i++) {
//			if(!ugly(s.charAt(i)))
//				sb.append(s.charAt(i));
//		}
//		s = sb.toString();
		
		//2. remove space  空格只在开头有
		 while(s.charAt(index) == ' ' && index < s.length())
		        index ++;
		 
		 //判断八进制 以0x开头
		 int isbinary = 0;
		 if (s.substring(index,index+2) == "0x") {
			isbinary = 1;
			//二进制数接下来转换的时候判断是01
			index += 2;
			//直接处理二进制并返回
			
			
		}
		 
		 //以下不考虑二进制了
		
		//3. special char only once 不需要
		if (countSpecial(s)) {
			return 0;
		}
		
		//4, handle sign  二进制数符号位自带
		if (s.charAt(index) == '+' || s.charAt(index) == '-') {
			sign = s.charAt(index) == '+' ? 1 : -1;
			index++;
		}
		
		//5. Convert number and avoid overflow
		while(index < s.length()){
			int digit = s.charAt(index) - '0';
			if (digit < 0 || digit > 9) {
				break;
			}
			//overflow: if total overflow after * 10 + digit
			if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit) {
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			total = 10 * total + digit;
			index ++;
		}
		return total;
		
		
	}
	
	
	public int myAtoi(String str) {
	    int index = 0, sign = 1, total = 0;
	    //1. Empty string
	    if(str.length() == 0) return 0;

	    //2. Remove Spaces
	    while(str.charAt(index) == ' ' && index < str.length())
	        index ++;

	    //3. Handle signs
	    if(str.charAt(index) == '+' || str.charAt(index) == '-'){
	        sign = str.charAt(index) == '+' ? 1 : -1;
	        index ++;
	    }
	    
	    //4. Convert number and avoid overflow
	    while(index < str.length()){
	        int digit = str.charAt(index) - '0';
	        if(digit < 0 || digit > 9) break;

	        //check if total will be overflow after 10 times and add digit
	        if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
	            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

	        total = 10 * total + digit;
	        index ++;
	    }
	    return total * sign;
	}
	public static boolean ugly(char c){
		//not 0-9 + - x e
		List<Character> list = Arrays.asList('-','+','x','e','1','2','3','4','5','6','7','8','9');
		if (list.contains(c)) {
			return false;
		}
		return true;
	}
	
	public static boolean countSpecial(String s){
		
		
		return true;
	}
}
