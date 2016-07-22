package com.bo.leetcode;

public class RemoveDuplicateFromSortedArray26 {

	/**
	 * 将不同的向前移动覆盖相同的
	 */
	public static int removeDuplicates(int[] nums){
		if (nums.length == 0) {
			return 0;
		}
		int index = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[index]) {
				index++;
				nums[index] = nums[i];
			}
		}
		return index+1;
	}
	
	
}
