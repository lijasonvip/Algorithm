package com.bo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://discuss.leetcode.com/topic/49456/c-solution-with-explanations
public class LargestDivisibleSubset {

	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5,6,7,8,9,16,12,32};
		LargestSubset(nums);
	}
	
	/**
	 * dp 数组存储最大子集的长度
	 * prev 数组存放序列上元素的下标
	 * dp 初始全为1因为最小集包含自身
	 * 从后向前
	 */
	public static List<Integer> LargestSubset(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return res;
		Arrays.sort(nums);
		int[] dp = new int[nums.length]; // to store largest subset length of
											// nums[i]
		int[] prev = new int[nums.length]; // to store index of element which
											// makes nums[i] to a largest subset
//		Arrays.fill(nums, 1);
//		prev[0] = 0;
		//有上面这两句反而不对了
		for (int i = 1; i < nums.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] % nums[j] == 0) {
					if (dp[j] + 1 > dp[i]) {
						// 向前找到一个能整除的数 这个数的添加能使要求集合变大则添加
						dp[i] = dp[j] + 1;
						// 记下使得最大的这哥下标
						prev[i] = j;
					}
				}
			}
		}

		// 遍历找最大的位置
		int max = 0;
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] > dp[max])
				max = i;
		}

		// 从后向前加入结果中
		int len = dp[max];
		while (true) {
			res.add(nums[max]);
			max = prev[max];
			if (--len < 0) {
				break;
			}
		}
		return res;
	}
	
	/**
	 * 从前向后
	 * 思路一样的
	 */
	public static List<Integer> LargestSubset2(int[] nums) {
		List<Integer> res = new ArrayList<>();
		
		return res;
	}
	
}
