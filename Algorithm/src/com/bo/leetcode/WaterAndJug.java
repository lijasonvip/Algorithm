package com.bo.leetcode;

public class WaterAndJug {

	public static void main(String[] args) {
		can1(3, 5, 4);
	}

	public static boolean canMeasureWater(int x, int y, int z) {
		if (z > x && z > y)
			return false;
		return z % gcd(x, y) == 0;
	}

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	/**
	 * 22%
	 * https://discuss.leetcode.com/topic/49238/math-solution-java-solution/2
	 */
	public static boolean can1(int x, int y, int z) {
		// limit brought by the statement that water is finallly in one or both
		// buckets
		if (x + y < z)
			return false;
		// case x or y is zero
		if (x == z || y == z || x + y == z)
			return true;

		// get GCD, then we can use the property of Bézout's identity
		return z % GCD1(x, y) == 0;
	}

	/**
	 * 辗转相除
	 */
	public static int GCD1(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	public static boolean can2(int x, int y, int z) {
		return z == 0 || (long) x + y >= z && z % gcd2(x, y) == 0;
	}

	public static int gcd2(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}

	/**
	 * 线性同余
	 */
	public static boolean can3(int x, int y, int z) {
		if (z == 0 || z == x + y)
			return true;
		if (z > x + y)
			return false;
		if (x == 0)
			return z == y;
		if (y == 0)
			return z == x;
		if (x == y)
			return z == x;
		int y_in = (y > x) ? y : x;
		int x_in = (y > x) ? x : y;
		int r = y_in % x_in;
		if (r == 0) {
			return z % x_in == 0;
		} else {
			return z % gcd3(r, x_in) == 0;
		}
	}

	public static int gcd3(int a, int b) {
		if (a % b == 0) {
			return b;
		} else {
			return gcd3(b, a % b);
		}
	}

	// 也可用哈希表的方法解决
}
