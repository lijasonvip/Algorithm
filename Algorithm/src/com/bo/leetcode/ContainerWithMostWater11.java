package com.bo.leetcode;

public class ContainerWithMostWater11 {

	/*
	 * 设置两个指针i, j, 一头一尾, 相向而行. 假设i指向的挡板较低, j指向的挡板较高(height[i] < height[j]).
	 * 下一步移动哪个指针? -- 若移动j, 无论height[j-1]是何种高度, 形成的面积都小于之前的面积. -- 若移动i,
	 * 若height[i+1] <= height[i], 面积一定缩小; 但若height[i+1] > height[i], 面积则有可能增大.
	 * 综上, 应该移动指向较低挡板的那个指针.
	 */

	/**
	 * 超时
	 */
	public static int maxArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int max = 0;
		int i = 0, j = height.length - 1;
		while (i < j) {
			max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
			if (height[i] < height[j]) { // should move i
				int k;
				for (k = i + 1; k < j && height[k] <= height[i]; ++k) {
				}
				i = k;
			} else { // should move j
				int k;
				for (k = j - 1; k > i && height[k] <= height[j]; --k) {
				}
				j = k;
			}
		}
		return max;
	}
	
	/**
	 * 25%
	 */
	public int maxArea2(int[] height) {
	    int left = 0, right = height.length - 1;
		int maxArea = 0;

		while (left < right) {
			maxArea = Math.max(maxArea, Math.min(height[left], height[right])
					* (right - left));
			if (height[left] < height[right])
				left++;
			else
				right--;
		}

		return maxArea;
	}
	
	/**
	 * 86%
	 * https://github.com/coder-pft/Algorithms-in-Java/commit/479a42fdbbf4303d89beef8df9e270a29580110d
	 */
	public int maxArea3(int[] height) {
	    int left = 0;
		int right = height.length-1;
		int max = 0,temp = 0;
		while(left<right) {
			if(height[left] > height[right]){
				temp = (right-left) * height[right];
				right--;
			}else{
				temp = (right-left) * height[left];
				left++;
			}
			max = max > temp ? max :temp;
		}
		return max;
	   }
	
	public static void main(String[] args) {
		int[] height = {1,2,4,3};
		System.out.println(maxArea(height));
	}
}
