package com.bo.leetcode;

public class MultiplyStrings43 {

	// 大数乘法

	public static void main(String[] args) {
		System.out.println(multiply2("123", "45"));
	}

	/**
	 * 两个数字按位乘举个例子类似了leetcode的例子很好懂
	 */
	public static String multiply(String num1, String num2) {
		int m = num1.length(), n = num2.length();
		int[] pos = new int[m + n];

		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int p1 = i + j, p2 = i + j + 1;
				int sum = mul + pos[p2];

				pos[p1] += sum / 10;
				pos[p2] = sum % 10;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int p : pos)
			if (!(sb.length() == 0 && p == 0)) {
				sb.append(p);
			}

		return sb.length() == 0 ? "0" : sb.toString();
	}

	/**
	 * 不是按位计算但也很好懂
	 */
	public static String multiply2(String num1, String num2) {
		int n1 = num1.length(), n2 = num2.length();
		int[] products = new int[n1 + n2];
		for (int i = n1 - 1; i >= 0; i--) {
			for (int j = n2 - 1; j >= 0; j--) {
				int d1 = num1.charAt(i) - '0';
				int d2 = num2.charAt(j) - '0';
				products[i + j + 1] += d1 * d2;
			}
		}
		int carry = 0;
		for (int i = products.length - 1; i >= 0; i--) {
			int tmp = (products[i] + carry) % 10;
			carry = (products[i] + carry) / 10;
			products[i] = tmp;
		}
		StringBuilder sb = new StringBuilder();
		for (int num : products)
			sb.append(num);
		while (sb.length() != 0 && sb.charAt(0) == '0')
			sb.deleteCharAt(0);
		return sb.length() == 0 ? "0" : sb.toString();
	}
}
