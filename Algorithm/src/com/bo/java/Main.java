package com.bo.java;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {// 注意while处理多个case

			int cow = in.nextInt();

			String app = in.nextLine();
			String[] apps = app.split(" ");
			if (apps.length != cow) {
				System.out.println("-1");
			} else {
				int[] apples = new int[cow];
				for (int i = 0; i < apps.length; i++) {
					apples[i] = Integer.parseInt(apps[i]);
				}

				Main m = new Main();
				System.out.println(m.divide(cow, apples));
			}
		}
	}

	int flight(int tol) {
		double max = Math.sqrt(tol);
		while(max * max + max > tol){
			max --;
		}
		System.out.println(max);
		
	}

	public int divide(int cow, int[] apples) {
		Arrays.sort(apples);
		int sum = 0;
		for (int i = 0; i < cow; i++) {
			sum += apples[i];
		}
		int avg = sum / cow;
		int i = 0, j = cow - 1;
		int move = 0;
		while (i < j) {
			if ((avg - apples[i]) % 2 != 0 || (apples[j] - avg) % 2 != 0) {
				return -1;
			} else {
				if ((apples[i] < avg) && (apples[j] > avg)) {
					apples[i] += 2;
					apples[j] -= 2;
					move++;
				}
				if (apples[i] == avg)
					i++;
				if (apples[j] == avg)
					j--;
			}
		}
		return move;

	}
}
