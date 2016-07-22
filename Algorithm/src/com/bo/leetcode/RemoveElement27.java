package com.bo.leetcode;

public class RemoveElement27 {

	public static int removeElement(int[] nums, int val){
		int index = 0;
		for (int i = 0; i < nums.length;i++) {
			if (nums[i] != val) {
				nums[index++] = nums[i];
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		int[] data = {3,2,2,3};
		System.out.println(removeElement(data, 3));
	}
}
