package com.bo.leetcode;

public class CountAndSay38 {

	// 1, 11, 21, 1211, 111221, ...

	/**
	 * 15%
	 */
	public static String countAndSay(int n) {
		String s = "1";
		for (int i = 1; i < n; i++) {
			int len = s.length();
			String temp = "";
			for (int j = 0; j < len; j++) {
				int count = 1;
				while (j + 1 < len && s.charAt(j) == s.charAt(j + 1)) {
					j++;
					count++;
				}
				temp += String.valueOf(count) + s.charAt(j);
			}
			s = temp;
		}
		return s;
	}

	/**
	 * 38%
	 */
	public static String countAndSay2(int n) {
		StringBuilder builder = new StringBuilder();
		char say = '1';
		int count, current;
		String countAndSay = "1";

		for (int i = 2; i <= n; i++) {

			current = 0;
			count = 1;
			builder.delete(0, builder.length());

			for (int j = 0; j < countAndSay.length(); j++) {

				if (j == current)
					say = countAndSay.charAt(j);
				else if (countAndSay.charAt(j) == say)
					count++;
				else {
					builder.append(count).append(say);
					current = j--;
					count = 1;
				}

			}

			// key point
			builder.append(count).append(say);
			countAndSay = builder.toString();
		}

		return countAndSay;
	}

	/**
	 * 85%
	 * 这个方法好
	 */
	public String countAndSay3(int n) {
		return iteration(n);
	}

	private String iteration(int n) {
		StringBuilder read = new StringBuilder("1");

		while (n > 1) {
			read = say(read);
			n--;
		}

		return read.toString();
	}

	private String recursion(int n) {
		return count(n).toString();
	}

	private StringBuilder count(int n) {
		if (n == 1) {
			return new StringBuilder("1");
		}

		return say(count(n - 1));
	}

	private StringBuilder say(StringBuilder read) {
		StringBuilder result = new StringBuilder();

		char value = read.charAt(0);
		int count = 1;

		for (int i = 1; i < read.length(); i++) {
			if (read.charAt(i) != value) {
				result.append(count);
				result.append(value);
				count = 0;
				value = read.charAt(i);
			}
			count++;
		}
		result.append(count);
		result.append(value);
		return result;
	}

	public static void main(String[] args) {
		System.out.println(countAndSay(6));
	}
}
