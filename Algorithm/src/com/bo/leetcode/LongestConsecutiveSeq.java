package com.bo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//http://www.acmerblog.com/leetcode-solution-longest-consecutive-sequence-6300.html
public class LongestConsecutiveSeq {

	//最长递增子序列
	
	/**
	 * 1. 排序
	 * 2. 哈希
	 */
	public static int LongestConsecutive(int[] data){
		Map<Integer, Boolean> map = new HashMap<>();
		for (int d : data) {
			map.put(d, false);
		}
		int longest = 0;
		for (int d : data) {
			if (map.get(d) == true) {
				continue;
			}
			int length = 1;
			map.put(d,true);
			for (int j = d+1; map.get(j)!= null; ++j) {
				map.put(j, true);
				++length;
			}
			
			for (int j = d-1; map.get(j) != null; --j) {
				map.put(j,true);
				++length;
			}
			longest = Math.max(longest, length);
		}
		return longest;
	}
	
	/**
	 * My coding
	 */
	public static int Longest(int[] data){
		if (data.length < 2) {
			return data.length;
		}
		Map<Integer, Boolean> map = new HashMap<>();
		for(int d : data)
			map.put(d, false); //all not used
		
		int longest = 0;
		for(int d:data){
			if(map.get(d) == true)
				continue; //这个数字已经计算过他的前后元素是不是连续不需要再考虑了
			int length = 1; //考虑d单个元素的时候长度为1
			for (int j = d+1; map.get(j) != null; ++j) {
				//比如当前考虑d=2, 接下来考虑3,4,5是不是也在数组中
				map.put(j,true);
				++length;
			}
			for(int j=d-1;map.get(j) != null; --j){
				//考虑 2,1,0,-1等
				map.put(j, true);
				++length;
			}
			longest = Math.max(longest, length);
		}
		return longest;
	}
	
	public static void main(String[] args) {
		int[] data = {100,4,200,1,3,2,5};
		System.out.println(Longest(data));
	}
}
