package com.bo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Sum15 {

	/**
	 * 我的方法 TLE
	 */
	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums.length < 3)
			return res;

		for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					if (nums[i] + nums[j] + nums[k] == 0) {
						List<Integer> temp = new ArrayList<>();
						temp.add(nums[i]);
						temp.add(nums[j]);
						temp.add(nums[k]);
						Collections.sort(temp);
						if (!res.contains(temp))
							res.add(temp);
					}
				}
			}
		}
		return res;
	}

	/**
	 * 1. sort 2. 固定i 3. j从i往后移动 k从最后往前移动 4. 如果和等于0 添加 如果大于0 k往前减小 小于0 j往后增大
	 * k和j相遇结束一次循环 5. i向后移动到下一个unique值 unique 这种方法会去掉所有的重复情况 [-1,-1,2] 就不会在结果中
	 */
	public static List<List<Integer>> threeSum2(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums.length < 3) {
			return res;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2;) {
			
			int j = i + 1, k = nums.length - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				if (sum > 0) {
					k--;
					while (nums[k] == nums[k + 1] && j < k)
						k--;
				} else if (sum < 0) {
					j++;
					while (nums[j] == nums[j - 1] && j < k)
						j++;
				} else {
					List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k]);
					res.add(temp);
					k--;
					while (nums[k] == nums[k + 1] && j < k)
						k--;
					j++;
					while (nums[j] == nums[j - 1] && j < k)
						j++;
				}
			}
			i++;
			while (nums[i] == nums[i-1] && i < nums.length - 2)
				i++;
		}
		return res;
	}

	public static List<List<Integer>> threeSum3(int[] num) {
	    Arrays.sort(num);
	    List<List<Integer>> list = new ArrayList<List<Integer>>();
	    HashSet<List<Integer>> set = new HashSet<List<Integer>>();
	    for(int i=0;i<num.length;i++)
	    {
	        for(int j=i+1,k=num.length-1;j<k;)
	        {
	            if(num[i]+num[j]+num[k]==0)
	            {
	                List<Integer> l= new ArrayList<Integer>();
	                l.add(num[i]);
	                l.add(num[j]);
	                l.add(num[k]);
	                if(set.add(l))
	                list.add(l);
	                j++;
	                k--;
	            }
	            else if(num[i]+num[j]+num[k]<0)
	            j++;
	            else
	            k--;
	        }
	    }
	    return list;
	}


	public static void main(String[] args) {
		threeSum3(new int[] {-1,0,1,2,-1,-4});
	}
}
