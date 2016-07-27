package com.bo.utils;

import java.util.*;


public class Main {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		Main m = new Main();
		String string;
		while(cin.hasNext()){
			string = cin.nextLine();
			String s = m.sort(string);
			System.out.println(s);
		}
	}
	
	public String sort(String str){
		StringBuilder builder = new StringBuilder();
		
		//这个排序想法太牛逼了
		//按照ascii大小依次往里添加  用冒泡也是可以的
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == (i + 'a') || str.charAt(j) == (i + 'A')) {
					//添加的顺序是aAbBcC...
					builder.append(str.charAt(j));
				}
			}
		}
		StringBuilder result = new StringBuilder();
		int k = 0;
		//把不是字母的插进来
		for (int i = 0; i < str.length(); i++) {
			if (!isLetter(str.charAt(i))) {
				result.append(str.charAt(i));
			} else {
				result.append(builder.charAt(k));
				k++;
			}
		}

		return result.toString();
	}
	
	/**
	 * 作者: 李波
	 * 日期：2016/07/22
	 * 联系： 18813928842
	 */
	private boolean isLetter(char c) {
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
	}
}
