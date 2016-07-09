package com.bo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//http://dsqiu.iteye.com/blog/1701324
public class LongestSubString {

	/**
	 * leetcode 3 O(n^2) and has no constraint of input s dp[i] represent length
	 * of longest nonrepeat substring at positon i
	 */
	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() < 1)
			return 0;
		if (s.length() == 1)
			return 1;
		int[] dp = new int[s.length()];
		char[] c = s.toCharArray();

		dp[0] = 1;
		int lastbigindex = 0;
		int maxlen = 0;
		for (int i = 1; i < s.length(); i++) {
			// 回头查找是否出现 查找到上次最小的起始位置 不算真正的动态规划
			for (int j = i - 1; j >= lastbigindex; j--) {
				if (c[j] == c[i]) {
					dp[i] = i - j;
					lastbigindex = j + 1;
					break;
				} else if (j == lastbigindex) {
					dp[i] = dp[i - 1] + 1;
				}
			}
			if (dp[i] > maxlen)
				maxlen = dp[i];
		}
		return maxlen;
	}

	// official solution2 O(n)
	public static int lengthOfLongestSubstring2(String s) {
		int n = s.length();
		Set<Character> set = new HashSet<>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n) {
			// try to extend the range [i, j]
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			} else {
				set.remove(s.charAt(i++));
			}
		}
		return ans;
	}

	public static int lengthOfLongestSubstring3(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>(); // current index of
														// character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}
			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return ans;
	}

	public static int lengthOfLongestSubstring4(String s) {
		int n = s.length(), ans = 0;
		int[] index = new int[128]; // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			i = Math.max(index[s.charAt(j)], i);
			ans = Math.max(ans, j - i + 1);
			index[s.charAt(j)] = j + 1;
		}
		return ans;
	}

	// 判断重复使用hash的思路 因为所有字符都出现再0-255中的256种可能
	// 每一趟找一个最大maxlen出来 这种hash方法不仅可以判断和当前首元素的重复 还可以判断此趟过程中的第一次重复
	public static int LNRS_Hash(String s) {
		if (s == null || s.length() <= 1)
			return s.length();
		char[] c = s.toCharArray();
		int[] visited = new int[256];

		int maxlen = 0;
		int i, j;
		for (i = 0; i < s.length(); i++) {
			for (int k = 0; k < visited.length; k++) {
				visited[k] = -1;
			}
			visited[c[i]] = 1;
			for (j = i + 1; j < s.length(); j++) {
				if (visited[c[j]] == -1) {
					visited[c[j]] = 1;
				} else {
					if (j - i > maxlen) {
						maxlen = j - i;
					}
					break;
				}
			}
			if (j == s.length() && j - i > maxlen) {
				maxlen = j - i;
			}
		}
		return maxlen;
	}

	// dp + hash
	public static int LNRS_DP_Hash(String s) {
		if (s == null || s.length() <= 1)
			return s.length();
		int[] dp = new int[s.length()];
		dp[0] = 1;
		char[] c = s.toCharArray();

		int maxlen = 0;
		int[] visit = new int[256];
		// not visit assign -1 otherwise visit[c[i]] = i
		for (int i = 0; i < visit.length; i++) {
			visit[i] = -1;
		}
		int last_start = 0;
		visit[c[0]] = 0;
		for (int i = 1; i < s.length(); i++) {
			if (visit[c[i]] == -1) {
				dp[i] = dp[i - 1] + 1;
				// store the index except 1
				visit[c[i]] = i;
			} else {

				if (last_start <= visit[c[i]]) {
					// visit[c[i]] stores it's repeat occur position
					// dp[i] = i - visit[c[i]] update the longest distance as
					// the differ
					// old version i - j , change the j as visit[c[i]], because
					// visit before and we store the position
					dp[i] = i - visit[c[i]];
					last_start = visit[c[i]] + 1;
					// update repeated position as latest one(last occur index)
					visit[c[i]] = i;
				} else {
					// current renew substring is longer than old occur
					// like q in "qwnfenpglqdq"
					dp[i] = dp[i - 1] + 1;
					visit[c[i]] = i;
				}
			}
			if (dp[i] > maxlen)
				maxlen = dp[i];
		}
		return maxlen;
	}

	// can still save dp[] space, dp store max or dp[i-1] can save that too
	// and character can also not use 256 but 26 only need to store visit[c[i] -
	// 'a']
	public static int LNRS_DP_Hash_Better(String s) {
		if (s == null || s.length() <= 1)
			return s.length();
		char[] c = s.toCharArray();
		int[] visit = new int[256];

		int currentdp = 1;
		int maxlen = 0;
		int last_start = 0;
		for (int i = 0; i < visit.length; i++) {
			visit[i] = -1;
		}
		visit[c[0]] = 0;
		for (int i = 1; i < s.length(); i++) {
			if (visit[c[i]] == -1) {
				currentdp++;
				visit[c[i]] = i;
			} else {
				if (last_start <= visit[c[i]]) {
					currentdp = i - visit[c[i]];
					last_start = visit[c[i]] + 1;
					visit[c[i]] = i;
				} else {
					currentdp++;
					visit[c[i]] = i;
				}
			}
			if (currentdp > maxlen)
				maxlen = currentdp;
		}
		return maxlen;
	}

	public static void main(String[] args) {
		String s = "qwnfenpglqdq";
		System.out.println(lengthOfLongestSubstring2(s));
	}
}
