package com.bo.leetcode;

public class ValidPerfectSquare {

	// 给定一个正整数，判断是不是一个数的平方

	/**
	 * 利用数学规律 1 4 = 1+3 9 = 1+3+5 16 = 1+3+5+7 只战胜18% 太慢了
	 */
	public static boolean isPerfect(int num) {
		int i = 1;
		while (num > 0) {
			num -= i;
			i += 2;
		}
		return num == 0;
	}

	/**
	 * 二分查找的思路 这里要注意判断 x * x < n > n 时会发生的溢出 使用差或除法可以避免
	 * 只战胜了18.5% 太慢
	 */
	public static boolean isPerfect2(int num) {
		if (num < 0) {
			return false;
		}
		int left = 1, right = num;
		while (left <= right) {
			// 避免加法溢出
			int mid = left + (right - left) / 2;

			// 避免乘法溢出
			if (mid > num / mid) {
				right = mid - 1;
			} else if (mid < num / mid) {
				left = mid + 1;
			} else {
				return num % mid == 0;
			}
		}
		return false;
	}

	// 另一种避免溢出的思路是使用long
	public boolean isPerfectSquare(int num) {
		int small = 1;
		int large = num + 1;
		while (small < large) {
			int mid = (small + large) / 2;
			long sq = (long) mid * mid;

			if (sq < num)
				small = mid + 1;
			else if (sq > num)
				large = mid;
			else
				return true;
		}
		return false;
	}
	
	/**
	 * 用牛顿法求平方根 使用int会溢出出错 使用龙避免
	 * 战胜36.8%
	 */
	public static boolean isPerfect3(int num){
		long r = num;
		while(r * r > num){
			r = (r + num / r) / 2;
		}
		return r*r == num;
	}

}
