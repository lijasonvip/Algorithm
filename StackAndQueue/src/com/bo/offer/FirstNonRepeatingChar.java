package com.bo.offer;

import java.util.Arrays;

public class FirstNonRepeatingChar {

	//offer 35
	public static char FirtNotRepeatChar(String data){
		if(data == null)
			return '\0';
		int[] map = new int[256];
		Arrays.fill(map, 0);
		for (int i = 0; i < data.length(); i++) {
			char ch = data.charAt(i);
			map[ch] ++;
		}
		for (int i = 0; i < map.length; i++) {
			if (map[i] == 1) {
				return (char)i;
			}
		}
		return '\0';
		
	}
	
	public static void main(String[] args) {
//		char[] chinese1 = {'��','��','��','��'};
//		String ch = "�Ұ��й�";
//		char[] chinese = ch.toCharArray();
//		System.out.println(chinese);
	}
}
