package com.bo.leetcode;

public class LongestPalindromicSubstring {

	// 最长回文子串

	// https://leetcode.com/articles/longest-palindromic-substring/
	// 五种方法
	// 99%的方法
	// https://discuss.leetcode.com/topic/35582/java-easy-understanding-solution-beats-97/3

	/*
	 * 动态规划法 比如P[i,j]（表示以i开始以j结束的子串）是回文字符串，那么P[i+1,j-1]也是回文字符串 P[i,j]=false
	 * 表示子串[i,j]不是回文串。P[i,j]=true 表示子串[i,j]是回文串。 初始化 P[i,i]=1; |- P[i+1, j-1],
	 * if(s[i]==s[j]) P[i,j]=| |_ false, if(s[i]!=s[j])
	 */
	public static String longestPalindrome1(String s) {
		int maxLen = 0;
		int start = 0;
		boolean[][] P = new boolean[50][50];
		if (s.length() == 1)
			return s;
		// 初始化
		for (int i = 0; i < s.length(); i++) {
			P[i][i] = true; // P[i][j]表示以i开始，以j结束的子串
			if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
				P[i][i + 1] = true;
				start = i;
				maxLen = 2;
			}
		}
		for (int len = 3; len <= s.length(); len++) { // 长度从3开始
			for (int i = 0; i <= s.length() - len; i++) { // 设置初始位置
				int j = i + len - 1; // 与i相对的位置
				// 若在两端的位置i,j位置处的字符相等，再者从i+1~j-1之间为true,更新
				if (P[i + 1][j - 1] == true && s.charAt(i) == s.charAt(j)) {
					P[i][j] = true;
					maxLen = len;
					start = i;
				}
			}
		}
		// System.out.println("start: "+start+" maxLen:"+maxLen);
		if (maxLen >= 2)
			return s.substring(start, start + maxLen);
		return null;
	}

	/**
	 * DP 29%
	 */
	public static String longest(String s) {
		if (s.length() < 2) {
			return s;
		}
		int start = 0;
		int maxlen = 0;
		boolean[][] p = new boolean[s.length()][s.length()];
		// 检查是否有连续两个相同的
		for (int i = 0; i < p.length - 1; i++) {
			p[i][i] = true;
			if (s.charAt(i) == s.charAt(i + 1)) {
				p[i][i + 1] = true;
				maxlen = 2;
				start = i;
			}
		}
		// 检查长度大于等于3的
		for (int len = 3; len <= p.length; len++) {
			for (int i = 0; i < p.length - len + 1; i++) {
				int j = i + len - 1;
				if (p[i + 1][j - 1] == true && s.charAt(i) == s.charAt(j)) {
					// 因为len递增所以最后找到的总是最长的
					// 但是当有相同长度的总是找到相同长度中最后一个 可以用break跳出一层循环
					p[i][j] = true;
					maxlen = len;
					start = i;
					continue;
					// 引入continue后提升到了31.29%
				}
			}
		}
		if (maxlen >= 2) {
			return s.substring(start, start + maxlen);
		}
		return null;
	}

	/**
	 * 中心扩展 30.9%
	 */
	public static String longest_center(String s) {
		if (s.length() < 2) {
			return s;
		}
		int len = s.length();
		int start = 0;
		int maxlen = 0;
		int index = 0;
		for (int i = 0; i < len; i++) {
			int j = i - 1, k = i + 1;
			while (j >= 0 && k < len && s.charAt(j) == s.charAt(k)) {
				if (k - j + 1 > maxlen) {
					maxlen = k - j + 1;
					start = j;
				}
				j--;
				k++;
			}
		}
		for (int i = 0; i < len; i++) {
			int j = i, k = i + 1;
			while (j >= 0 && k < len && s.charAt(j) == s.charAt(k)) {
				if (k - j + 1 > maxlen) {
					maxlen = k - j + 1;
					start = j;
				}
				j--;
				k++;
			}
		}
		if (maxlen >= 2) {
			return s.substring(start, start + maxlen);
		}
		return null;

	}

	/**
	 * Manacher O(n) 插入分隔符 使得新字符串长度始终是奇数个 用一个辅助数组P
	 * 记录以每个字符为中心的最长回文半径，也就是P[i]记录以Str[i]字符为中心的最长回文串半径
	 * http://www.cnblogs.com/Lyush/p/3221503.html
	 * http://www.cnblogs.com/heyonggang/p/3386724.html
	 * http://articles.leetcode.com/longest-palindromic-substring-part-ii/
	 */
	public static String longest_manacher(String s) {
		String T = preProcess(s);
		int n = T.length();
		int[] p = new int[n];
		int C = 0, R = 0;
		for (int i = 1; i < n - 1; i++) {
			int i_mirror = C - (i - C); // 2 * C - i
			p[i] = (R > i) ? Math.min(R - i, p[i_mirror]) : 0;

			// Attempt to expand palindrome centered at i
			while (T.charAt(i + 1 + p[i]) == T.charAt(i - 1 - p[i]))
				p[i]++;
			// If palindrome centered at i expand past R,
			// adjust center based on expanded palindrome.
			if (i + p[i] > R) {
				C = i;
				R = i + p[i];
			}
		}
		// Find the maximum element in P.
		int maxLen = 0;
		int centerIndex = 0;
		for (int i = 1; i < n - 1; i++) {
			if (p[i] > maxLen) {
				maxLen = p[i];
				centerIndex = i;
			}
		}
		return s.substring((centerIndex - 1 - maxLen) / 2, maxLen);
	}

	/**
	 * Transform S into T. For example, S = "abba", T = "^#a#b#b#a#$". ^ and $
	 * signs are sentinels appended to each end to avoid bounds checking
	 */
	public static String preProcess(String s) {
		int n = s.length();
		if (n == 0) {
			return "^$";
		}
		String ret = "^";
		for (int i = 0; i < n; i++) {
			ret += "#" + s.charAt(i);
		}
		ret += "#$";
		return ret;
	}

	/**
	 * 97%
	 */
	public String longestPalindrome(String s) {
		char[] ca = s.toCharArray();
		int rs = 0, re = 0;
		int max = 0;
		for (int i = 0; i < ca.length; i++) {
			if (isPalindrome(ca, i - max - 1, i)) {
				rs = i - max - 1;
				re = i;
				max += 2;
			} else if (isPalindrome(ca, i - max, i)) {
				rs = i - max;
				re = i;
				max += 1;
			}
		}
		return s.substring(rs, re + 1);
	}

	private boolean isPalindrome(char[] ca, int s, int e) {
		if (s < 0)
			return false;

		while (s < e) {
			if (ca[s++] != ca[e--])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {

		String string = "abb";
		// System.out.println(string.substring(1,4));
		System.out.println(longest_manacher(string));
	}
}
