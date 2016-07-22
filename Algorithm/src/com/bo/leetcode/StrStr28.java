package com.bo.leetcode;

public class StrStr28 {

	/**
	 * 10%
	 */
	public static int strStr(String haystack, String needle) {
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == needle.length())
					return i;
				if (i + j == haystack.length())
					return -1;
				if (needle.charAt(j) != haystack.charAt(i + j))
					break;
			}
		}
	}

	// KMP
	public static int strStr2(String haystack, String needle) {
		char[] s = haystack.toCharArray();
		char[] t = needle.toCharArray();
		int[] next = next(t);
		int i = 0, j = 0;
		while (i <= s.length - 1 && j <= t.length - 1) {
			if (j == -1 || s[i] == t[j]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		if (j < t.length) {
			return -1;
		} else
			return i - t.length;
	}

	public static int[] next(char[] t) {
		int[] next = new int[t.length];
		if (t.length > 0)
			next[0] = -1;
		int i = 0;
		int j = -1;
		while (i < t.length - 1) {
			if (j == -1 || t[i] == t[j]) {
				i++;
				j++;
				if (t[i] != t[j]) {
					next[i] = j;
				} else {
					next[i] = next[j];
				}
			} else {
				j = next[j];
			}
		}
		return next;
	}

	public static void main(String[] args) {
		System.out.println(strStr("hello", "llo"));
	}
}
