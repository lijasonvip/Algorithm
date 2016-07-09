package com.bo.leetcode;

//http://www.acmerblog.com/remove-duplicate-character-5906.html
public class RemoveDuplicate {

	/**
	 * 删除重复字符
	 */
	public static void Remove(String str){
		char[] ch = str.toCharArray();
		int j = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[j] != 0) {
				ch[j++] = ch[i];
				for (int k = i+1; k < ch.length; k++) {
					if (ch[k] == ch[i]) {
						ch[k] = 0;
					}
				}
			}
		}
		ch[j] = 0;
	}
	
	public static void main(String[] args) {
		
	}
}
