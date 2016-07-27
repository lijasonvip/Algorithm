package com.bo.leetcode;

import java.util.Stack;

public class TrappingRainWater {

	// 给了栏杆的高度 下雨后能存下多少水

	public static void main(String[] args) {
		int[] bar = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trap2(bar));
	}

	/**
	 * 21%
	 */
	public static int trap(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int rain = 0;
		int low = 0;
		int high = height.length - 1;
		int curMin = 0;
		while (low < high) {
			if (height[low] <= curMin) {
				rain += curMin - height[low];
				++low;
			} else if (height[high] <= curMin) {
				rain += curMin - height[high];
				--high;
			} else {
				curMin = Math.min(height[low], height[high]);
			}
		}
		return rain;
	}

	/**
	 * Keep track of the maximum height from both forward directions backward
	 * directions, call them leftmax and rightmax.
	 */
	public static int trap2(int[] height) {
		int a = 0;
		int b = height.length - 1;
		int max = 0;
		int leftmax = 0;
		int rightmax = 0;
		while (a <= b) {
			leftmax = Math.max(leftmax, height[a]);
			rightmax = Math.max(rightmax, height[b]);
			if (leftmax < rightmax) {
				max += (leftmax - height[a]);
				// leftmax is smaller than rightmax, so the (leftmax-A[a]) water
				// can be stored
				a++;
			} else {
				max += (rightmax - height[b]);
				b--;
			}
		}
		return max;
	}
}
