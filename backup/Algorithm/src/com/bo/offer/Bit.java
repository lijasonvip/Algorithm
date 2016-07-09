package com.bo.offer;

import com.bo.sort.Count;

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
		StringBuffer result = new StringBuffer();
		int remain  = in % 26;
		while(remain != 0){
			in = in / 26;
			char ch = (char) (remain + 'A' - 1);
			result.append(ch);
			remain = in % 26;
		}
		result.reverse();
		return result.toString();
	}
	
	
	//count num of 1 in a binary sequece
	
	/**
	 * @param n
	 * count number of 1 in a number n
	 * this is suit for unsigned int. because shifting a signed number need to change change the sign
	 * and sifting is much faster than *, / in computer calculation
	 */
	public static int Count_Unsigned(int n){
		int count=0;
		while(n > 0){
			if((n & 1) != 0)
				count++;
			n = n >> 1;
		}
		return count;
	}
	
	/**
	 * @param n
	 * @return
	 * a flag number shift from right to left when & with a 1 count ++, ignore the change in n
	 */
	public static int Count_Signed(int n){
		int flag = 1;
		int count = 0;
		while(flag > 0){
			if((n & flag) != 0){
				count ++;
			}
			flag = flag << 1;
		}
		return count;
	}
	
	/**
	 * @param n
	 * @return
	 * this  because n & (n-1) will turn the rightest 1 to 0 in n.
	 */
	public static int Count_One(int n){
		int count = 0;
		while(n > 0){
			count ++;
			n = (n-1) & n;
		}
		return count;
	}
	public static void main(String[] args) {
		//System.out.println(Math.pow(2, 3));
		System.out.println(ToDecimal("ABCD"));
		System.out.println(To26(19010));
		
	}
}
