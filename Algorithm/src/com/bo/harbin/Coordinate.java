package com.bo.harbin;

import java.util.Scanner;

public class Coordinate {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int left = -90;
		int right = 90;
		int middle;
		for(int i=0;i<6;i++){
			middle = (left + right) / 2;
			if (n >= middle) {
				System.out.println(1);
				left = middle;
			}else{
				System.out.println(0);
				right = middle;
			}
		}
		System.out.println();
	}
}
