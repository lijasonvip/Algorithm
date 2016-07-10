package com.bo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	//找两个数使得和为目标值
	/**
	 * @param nums
	 * @param target
	 * @return leetcode 1 O(n^2)
	 */
	public static int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] { i, j };
				}
			}
		}
		// check validation of input data
		// if(nums == null || nums.length < 1)
		throw new IllegalArgumentException("No two sum solution");
	}

	/**
	 * two-pass hash table
	 * 1. put nums[i] and i into map
	 * 2. hashmap can get a data in O(1).  
	 * nums[j] = target - nums[j];
	 * if(map.contains[nums[j]] && j != i)  return i,j
	 * Hash map can get <Key, Value> as fast as array
	 * Time O(n) but space also O(n)
	 */
	public static int[] twoSum2(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			int numsj = target - nums[i];
			if(map.containsKey(numsj) && map.get(numsj) != i){
				return new int[]{i,map.get(numsj)};
			}
		}
		throw new IllegalArgumentException("No solution");
	}
	
	/**
	 * one-pass hash table 
	 * no need to put all data in map once time, because j<i, so we can always test j just after i was putted.
	 * time O(n) but space also O(n)
	 * genius
	 */
	public static int[] twoSum3(int[] nums, int target){
		Map<Integer, Integer> map = new HashMap<>();
		for (int j = 0; j < nums.length; j++) {
			int numsi = target - nums[j];
			if(map.containsKey(numsi)){
				return new int[]{map.get(numsi),j};
			}
			map.put(nums[j], j);
		}
		throw new IllegalArgumentException("No solution");
	}

	/**
	 * can also first sort the array
	 *  
	 */
	public static int twoSum4(int[] nums, int target){
		Arrays.sort(nums);
		int left = 0, right = nums.length - 1;
		int ans = 0;
		while(left < right){
			if(nums[left] + nums[right] > target){
				ans = ans + (right - left);
				right --;
			}else{
				left ++;
			}
		}
		return ans;
	}
	
	public static void PrintArray(int[] arr){
		System.out.println();
		for(int i:arr){
			System.out.print(i + " ");
		}
	}
	public static void main(String[] args) {
		int[] nums = { 2, 7, 4, 15 };
		int[] ij = twoSum2(nums, 9);
		System.out.println("[" + ij[0] + ", " + ij[1] + "]");
		int what = twoSum4(nums, 9);
		System.out.println(what);

	}
}
