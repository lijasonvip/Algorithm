package com.bo.leetcode;

public class SuperPow {

	
	//求A^n 的公式
	public static int pow(int a, int b, int c){
		if (b == 1) {
			return a;
		}
		if (b == 0) {
			return 1;
		}
		int x = pow(a, b/2, c);
		x = (x * x) % c;
		if ((b & 1) == 1) {
			x = (x * a) % c;
		}
		return x;
	}
	
	/**
	 * b数组用来存放高位到低位的十进制数
	 * 
	 * ab % k = (a%k)(b%k)%k
	 */
	public static int superPow(int a, int[] b){
		int ans = 1;
		if (b == null || b.length == 0) {
			return 0;
		}
		a = a % 1337;
		for (int i = 0; i < b.length; i++) {
			ans = (pow(ans, 10, 1337) * pow(a, b[i], 1337) % 1337);
		}
		return ans;
	}
	
	
	
	public static void main(String[] args) {
		int ans = superPow(2, new int[]{1, 0});
		System.out.println(ans);
	}
}
