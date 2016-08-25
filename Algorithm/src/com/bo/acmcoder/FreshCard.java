package com.bo.acmcoder;

import java.util.Scanner;

public class FreshCard {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int group = scanner.nextInt();
		for (int i = 0; i < group; i++) {
			int n = scanner.nextInt();
			int[] cards = new int[2*n];
			int k = scanner.nextInt();
			for (int j = 0; j < cards.length; j++) {
				//竟然把这里的j写成了i 最后数组都没获取到正确的数据 debug才发现
				//我是不是傻
				cards[j] = scanner.nextInt();
			}
			
			for (int j = 0; j < k; j++) {
				cards = fresh(cards);
			}
			if (n == 1) {
				System.out.print(cards[0] + " " + cards[1]);
			}else{
				for (int j = 0; j < cards.length-1; j++) {
					System.out.print(cards[j] + " ");
				}
				System.out.print(cards[cards.length - 1]);
			}
			System.out.println();
		}
		
	}
	public static int[] fresh(int[] cards){
		int i = cards.length/2 -1, j = cards.length - 1;
		int[] res = new int[cards.length];
		for(int k=cards.length-1;k>=0;k-=2){
			res[k] = cards[j--];
			res[k-1] = cards[i--];
		}
		return res;
	}
}
