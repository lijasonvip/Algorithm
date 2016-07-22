package com.bo.leetcode;

public class IntegerRoman {

	/**
	 * 整数转罗马数字 依次获取个十百千各个位置的数字符串拼接即可
	 */
	public static String intToRoman(int num) {
		String[] I = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		String[] X = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String[] C = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String[] M = { "", "M", "MM", "MMM" };

		/*
		 * Indices to retrieve from the above arrays: Thousands = num/1000
		 * Hundreds = (num % 1000)/100 Tens = (num % 100)/10 Units = num % 10
		 */

		return (M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10]).trim();
	}

	public static String intToRoman2(int num) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		String result = "";
		int base[] = { 1000, 500, 100, 50, 10, 5, 1, 0 };
		char baseC[] = { 'M', 'D', 'C', 'L', 'X', 'V', 'I' };
		int basen = 0;
		while (num > 0) {
			if (basen % 2 == 0 && num / base[basen] == 4) {
				result += baseC[basen];
				result += baseC[basen - 1];
				num -= base[basen] * 4;
			} else if (num >= base[basen]) {
				result += baseC[basen];
				num -= base[basen];
			} else if (basen % 2 == 0 && num / base[basen + 2] == 9) {
				result += baseC[basen + 2];
				result += baseC[basen];
				num -= base[basen + 2] * 9;
			} else {
				basen++;
			}
		}
		return result;
	}

	/**
	 * 罗马数字转整形
	 */
	public int romanToInt(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		// directly return the value if the length is 1
		if (s.length() == 1) {
			return helper(s.charAt(0));
		}
		// find the first valid index ("--VII")
		int index = 0;
		while (index < s.length() && helper(s.charAt(index)) == 0) {
			index++;
		}
		// case "--" should return 0
		if (index >= s.length()) {
			return 0;
		}

		index = index + 1;
		int sum = 0;
		int cur = 0, next = 0;

		while (index < s.length()) {
			cur = helper(s.charAt(index - 1));
			next = helper(s.charAt(index));

			if (cur < next) {
				sum -= cur;
			} else {
				sum += cur;
			}
			index++;
		}
		sum += next;

		return sum;
	}

	private int helper(char ch) {
		switch (ch) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		}

		return 0;
	}

	public static int romanToInt2(String s) {
		int res = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			switch (c) {
			case 'I':
				res += (res >= 5 ? -1 : 1);
				break;
			case 'V':
				res += 5;
				break;
			case 'X':
				res += 10 * (res >= 50 ? -1 : 1);
				break;
			case 'L':
				res += 50;
				break;
			case 'C':
				res += 100 * (res >= 500 ? -1 : 1);
				break;
			case 'D':
				res += 500;
				break;
			case 'M':
				res += 1000;
				break;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int a = 1234;
		System.out.println(intToRoman(a));
	}
}
