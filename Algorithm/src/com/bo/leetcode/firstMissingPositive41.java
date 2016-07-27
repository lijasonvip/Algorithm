package com.bo.leetcode;

public class firstMissingPositive41 {
	
	public static void main(String[] args) {
		int[] nums = {3,4,-1,1};
		System.out.println(firstMissingPositive(nums));
	}

	/**
	 * 找第一个丢失的正整数
	 * 正整数在1-n之间，我们交换数字到对应的位置上去
	 * 如果位置i存的数字不是i那么就丢失了i
	 */
	public static int firstMissingPositive(int[] nums) {

		int i = 0, n = nums.length;
		while (i < n) {
	        // If the current value is in the range of (0,length) and it's not at its correct position, 
	        // swap it to its correct position.
	        // Else just continue;
			if (nums[i] >= 0 && nums[i] < n && nums[nums[i]] != nums[i])
				swap(nums, i, nums[i]);
			else
				i++;
		}
		int k = 1;

	    // Check from k=1 to see whether each index and value can be corresponding.
		while (k < n && nums[k] == k)
			k++;

	    // If it breaks because of empty array or reaching the end. K must be the first missing number.
		if (n == 0 || k < n)
			return k;
		else   // If k is hiding at position 0, K+1 is the number. 
			return nums[0] == k ? k + 1 : k;

	}
	
	public static int firstMissingPositive2(int[] nums){
		int i, j;
		for(i=0;i<nums.length;i++){
			int cur = nums[i];
			if (cur == i+1 || cur<=0 || cur > nums.length) {
				continue;
			}
			swap(nums, i, cur-1);
			i--;
		}
		for(i=0;i<nums.length;i++)
			if (nums[i] != i+1) {
				return i+1;
			}
		return nums.length+1;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
