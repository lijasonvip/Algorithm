package com.bo.acmcoder;

import java.util.Arrays;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		cardMain();
	}

	public static int[] cards(int[] card){
		int[] a = Arrays.copyOfRange(card, 0, card.length/2);
		int[] b = Arrays.copyOfRange(card, card.length/2, card.length);
		int bright = 0;
		int aright = 0;
		for (int i = 0; i < card.length; i+=2) {
			card[i] = a[aright];
			aright++;
			card[i+1] = b[bright];
			bright++;
		}
		
		return card;
	}
	
	public static void cardMain(){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()){
			int group = sc.nextInt();
			for(int i=0;i<group;i++){
				int n = sc.nextInt();
				int k = sc.nextInt();
				int[] card = new int[2*n];
				for (int j = 0; j < 2*n; j++) {
					card[i] = sc.nextInt();
				}
				for (int j = 0; j < k; j++) {
					card = cards(card);
				}
				StringBuilder sBuilder = new StringBuilder();
				for (int j = 0; j < card.length; j++) {
					sBuilder.append(card[j]+" ");
				}
				System.out.println(sBuilder.delete(sBuilder.length()-1,sBuilder.length()).toString());
			}
		}
	}
}
