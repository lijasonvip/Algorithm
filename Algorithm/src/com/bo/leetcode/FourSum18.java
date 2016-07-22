package com.bo.leetcode;

import java.util.*;

public class FourSum18 {

	/**
	 * 由3sum拓展过来的 11%
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<>();

		Arrays.sort(nums);
		int i = 0, last = nums.length - 1;
		while (i < last) {
			int j = i + 1, a = nums[i];
			while (j < last) {
				int k = j + 1, m = last, b = nums[j];
				;
				while (k < m) {
					int c = nums[k], d = nums[m];
					int sum = a + b + c + d;
					if (sum == target)
						res.add(Arrays.asList(a, b, c, d));
					if (sum <= target)
						while (c == nums[k] && k < m)
							k++;
					if (sum >= target)
						while (d == nums[m] && k < m)
							m--;
				}
				while (b == nums[j] && j < last)
					j++;
			}
			while (a == nums[i] && i < last)
				i++;
		}

		return res;
	}

	/**
	 * 90%
	 */
	public static List<List<Integer>> fourSum3(int[] nums, int target) {
		if (nums.length < 4)
			return new ArrayList<List<Integer>>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		int l, r, sum;
		int length = nums.length;
		for (int i = 0; i < length - 3; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
				break;
			if (nums[i] + nums[length - 1] + nums[length - 2] + nums[length - 3] < target)
				continue;
			for (int j = length - 1; j > i + 2; j--) {
				if (j < nums.length - 1 && nums[j] == nums[j + 1])
					continue;
				if (nums[i] + nums[j] + nums[i + 1] + nums[i + 2] > target)
					continue;
				if (nums[i] + nums[j] + nums[j - 1] + nums[j - 2] < target)
					break;
				l = i + 1;
				r = j - 1;
				while (l < r) {
					sum = nums[i] + nums[j] + nums[l] + nums[r];
					if (sum == target) {
						result.add(Arrays.asList(nums[i], nums[l], nums[r], nums[j]));
						while (++l < r && nums[l] == nums[l - 1])
							;
						while (--r > l && nums[r] == nums[r + 1])
							;
					} else if (sum < target) {
						while (++l < r && nums[l] == nums[l - 1])
							;
					} else {
						while (--r > l && nums[r] == nums[r + 1])
							;
					}
				}
			}
		}
		return result;
	}

	public List<List<Integer>> fourSum2(int[] nums, int target) {
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
		int len = nums.length;
		if (nums == null || len < 4)
			return res;

		Arrays.sort(nums);

		int max = nums[len - 1];
		if (4 * nums[0] > target || 4 * max < target)
			return res;

		int i, z;
		for (i = 0; i < len; i++) {
			z = nums[i];
			if (i > 0 && z == nums[i - 1])// avoid duplicate
				continue;
			if (z + 3 * max < target) // z is too small
				continue;
			if (4 * z > target) // z is too large
				break;
			if (4 * z == target) { // z is the boundary
				if (i + 3 < len && nums[i + 3] == z)
					res.add(Arrays.asList(z, z, z, z));
				break;
			}

			threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
		}

		return res;
	}

	/*
	 * Find all possible distinguished three numbers adding up to the target in
	 * sorted array nums[] between indices low and high. If there are, add all
	 * of them into the ArrayList fourSumList, using
	 * fourSumList.add(Arrays.asList(z1, the three numbers))
	 */
	public void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
			int z1) {
		if (low + 1 >= high)
			return;

		int max = nums[high];
		if (3 * nums[low] > target || 3 * max < target)
			return;

		int i, z;
		for (i = low; i < high - 1; i++) {
			z = nums[i];
			if (i > low && z == nums[i - 1]) // avoid duplicate
				continue;
			if (z + 2 * max < target) // z is too small
				continue;

			if (3 * z > target) // z is too large
				break;

			if (3 * z == target) { // z is the boundary
				if (i + 1 < high && nums[i + 2] == z)
					fourSumList.add(Arrays.asList(z1, z, z, z));
				break;
			}

			twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
		}

	}

	/*
	 * Find all possible distinguished two numbers adding up to the target in
	 * sorted array nums[] between indices low and high. If there are, add all
	 * of them into the ArrayList fourSumList, using
	 * fourSumList.add(Arrays.asList(z1, z2, the two numbers))
	 */
	public void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
			int z1, int z2) {

		if (low >= high)
			return;

		if (2 * nums[low] > target || 2 * nums[high] < target)
			return;

		int i = low, j = high, sum, x;
		while (i < j) {
			sum = nums[i] + nums[j];
			if (sum == target) {
				fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

				x = nums[i];
				while (++i < j && x == nums[i]) // avoid duplicate
					;
				x = nums[j];
				while (i < --j && x == nums[j]) // avoid duplicate
					;
			}
			if (sum < target)
				i++;
			if (sum > target)
				j--;
		}
		return;
	}
}
