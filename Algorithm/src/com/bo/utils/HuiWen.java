package com.bo.utils;

import java.util.Scanner;

public class HuiWen {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		HuiWen huiWen = new HuiWen();
		int hui;
		while(cin.hasNext()){
			hui = cin.nextInt();
			int s = huiWen.isHuiWen(hui);
			System.out.println(s);
		}
	}
	
	public int isHuiWen(int data){
		String in = Integer.toString(data);
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
