package com.bo.utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IO {

	/**
	 * author: 
	 * date:
	 * other:
	 */
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		while (scanner.hasNext()) {
//			String str = scanner.nextLine();
//
//		}
//
//		scanner.close();
//		String test = "hello";
//		System.out.println(test.substring(2));
		String a = "Wor#d";
		System.out.println(sort(a));
		
//		String in = "1223 22 3232 2016";
//		sort2(in);
//		
//		String d = "1,4,3,110,2,90,7";
//		System.out.println(sort3(d));
//		
//		String hui = "1233421";
//		String str = String.valueOf(isHuiWen(hui));
//		System.out.println(isHuiWen(hui));
	}

	public static String sort(String str){

		List<Character> list = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			if (isLetter(str.charAt(i))) {
				list.add(str.charAt(i));
			}
		}
		Collections.sort(list);
		StringBuilder result = new StringBuilder();
		int k = 0;
		//把不是字母的插进来
		for (int i = 0; i < str.length(); i++) {
			if (!isLetter(str.charAt(i))) {
				result.append(str.charAt(i));
			} else {
				result.append(list.get(k));
				k++;
			}
		}

		return result.toString();
	}
	
	private static boolean isLetter(char c) {
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
	}

	public static void sort2(String str){
		String[] data = str.split(" ");
		int[] val = new int[data.length];
		for (int i = 0; i < val.length; i++) {
			if (data[i].length() > 3) {
				val[i] = Integer.parseInt(data[i].substring(data[i].length()-3));
			}else
				val[i] = Integer.parseInt(data[i]);
		}
		boolean swap = false;
		for(int i=0;i<data.length;i++){
			swap = false;
			for(int j=0;j<data.length-i-1;j++){
				if (val[j] > val[j+1]) {
					int temp = val[j];
					val[j] = val[j+1];
					val[j+1] = temp;
					String t = data[j];
					data[j] = data[j+1];
					data[j+1] = t;
					swap = true;
				}
				
			}
			if (!swap) {
				break;
			}
		}
		for(String s:data)
			System.out.println(s);
	}
	
	public static String sort3(String str){
		
		String[] data = str.split(",");
		if (data.length < 2) {
			System.out.println(str);
		}
		int[] val = new int[data.length];
		for (int i = 0; i < val.length; i++) {
			val[i] = Integer.parseInt(data[i]);
		}
		Arrays.sort(val);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < val.length; ) {
			int start = i;
			int end = i;
			while(i<val.length){
				if (i+1 < val.length && val[i+1] == val[i]+1) {
					i++;
					continue;
				}
				end = i;
				if (start == end) {
					sb.append(val[start]+" ");
				}else
					sb.append(val[start]+" " + val[end]+" ");
				i++;
				break;
				
			}
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	public static int isHuiWen(String in){
		if (in.length() == 1) {
			return 1;
		}
		int i=0,j=in.length()-1;
		while(i<j){
			if (in.charAt(i) == in.charAt(j)) {
				i++;
				j--;
			}else {
				return 0;
			}
		}
		if (i == j+1 || i == j) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
