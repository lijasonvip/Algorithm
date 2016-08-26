package com.bo.acmcoder;

import java.util.Scanner;

public class Singers {
	//合唱团的题目
	//dp[i][j] 表示第i个学生作为第j个备选人
	//同时记录max 和 min 因为会有负数出现 负数乘一个极大的负数min 会变成一个很大的max
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] energy = new int[n];
		for (int i = 0; i < energy.length; i++) {
			energy[i] = scanner.nextInt();
		}
		
		int k = scanner.nextInt();
		int d = scanner.nextInt();
		
		getMax(energy,k,d);
	}
	
	public static void getMax(int[] energy, int k, int d){
		int n = energy.length;
		int[][] max = new int[n][k];
		int[][] min = new int[n][k];
		int res = 0;
		for (int i = 0; i < n; i++) {
			max[i][0] = min[i][0] = energy[i];
		}
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < k; j++) {
				for (int kk = i-1; kk >= Math.max(i-d, 0); --kk) {
					max[i][j] = Math.max(max[i][j], Math.max(max[kk][j-1]*energy[i], min[kk][j-1])*energy[i]);
					min[i][j] = Math.min(min[i][j], Math.min(max[kk][j-1]*energy[i], min[kk][j-1]*energy[i]));
				}
			}
			res = Math.max(res, Math.max(max[i][k-1], min[i][k-1]));
		}
		System.out.println(res);
	}
}