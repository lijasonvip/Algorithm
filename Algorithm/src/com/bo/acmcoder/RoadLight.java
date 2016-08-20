package com.bo.acmcoder;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class RoadLight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int len = sc.nextInt();
		int[] a = new int[n];
		for(int i=0;i<n;i++){
			a[i] = sc.nextInt();
		}
		Arrays.sort(a);
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < a.length; i++) {
			if (a[i] - a[i-1] > max) {
				max = a[i] - a[i-1];
			}
		}
		max = max / 2;
		
		if (a[0] != 0) {
			max = Math.max(max, a[0]);
		}
		
		if (a[n-1] != len) {
			max = Math.max(max, a[n-1]);
		}
		
		//注意输出格式
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println(df.format(max));
	}

	public static void light2(){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int len = sc.nextInt();
		int[] spot = new int[n];
		for (int i = 0; i < n; i++)
			spot[i] = sc.nextInt();
		Arrays.sort(spot);
		double maxdistance = Double.MIN_VALUE;
		for (int i = 1; i < n; i++) {
			if (spot[i] - spot[i - 1] > maxdistance)
				maxdistance = spot[i] - spot[i - 1];
		}
		double left = spot[0];
		double right = len - spot[n - 1];
		if (left > right && left > maxdistance / 2) {
			System.out.printf("%.2f", left);
		} else if (right >= left && right > maxdistance / 2) {
			System.out.printf("%.2f", right);
		} else {
			System.out.printf("%.2f", maxdistance / 2);
		}
	}
	public static void light() {
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("0.00");

		while (in.hasNext()) {
			int n = in.nextInt();// 路灯个数
			int l = in.nextInt();// 路径长度

			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = in.nextInt();// 记录每一个路灯的坐标

			Arrays.sort(a);

			double maxDis = -1;
			for (int i = 1; i < n; i++)
				maxDis = Math.max(maxDis, a[i] - a[i - 1]);

			maxDis /= 2.0f;

			if (a[0] != 0) {
				maxDis = Math.max(maxDis, a[0]);
			}
			if (a[n - 1] != l) {
				maxDis = Math.max(maxDis, l - a[n - 1]);
			}

			System.out.println(df.format(maxDis));

		}
	}
}
