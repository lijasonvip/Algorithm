package com.bo.bookcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.events.StartDocument;

import com.bo.designpattern.Test;

public class ArraysQuestion {

	// 给一个排序数组 就地删除重复数字并返回新数组长度

	public static int RemoveDuplicate(int[] arr) {
		int index = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[index]) {
				arr[index] = arr[i];
				index++;
			}
		}

		return index + 1;
	}

	// 还是删除重复数字 但是允许最多重复两次 即删除重复出现多于三次的数字
	public static int RemoveDuplicateMoreThanTwice(int[] arr) {
		// 加一个变量记下元素出现的次数即可，如果没有排序则需要hashmap记下出现次数
		if (arr.length < 2) {
			return arr.length;
		}
		int index = 2;
		for (int i = 2; i < arr.length; i++) {
			if (arr[i] != arr[index - 2]) {
				arr[index] = arr[i];
				index++;
			}
		}
		return index;

	}

	// 旋转数组 旋转有序 用二分查找 剑指offer上的题 在旋转数组中查找
	// 难度在于左右边界的确定

	public static int SearchInRotatedSortedArray(int[] rotated, int target) {
		int first = 0, last = rotated.length;
		while (first != last) {
			int mid = first + (last - first) / 2;
			if (rotated[mid] == target) {
				return mid;
			}
			if (rotated[mid] < target) {
				// 正常二分的话此时要last = mid - 1了
				// 但是此时我们要判断mid在旋转轴的左边还是右边
				if (rotated[first] <= target && target < rotated[mid]) {
					// mid在轴左边
					last = mid;
				} else {
					first = mid + 1;
				}
			} else {
				// 正常的话此时要first = mid + 1;
				if (rotated[mid] < target && target <= rotated[last - 1]) {
					first = mid + 1;
				} else
					last = mid;
			}
		}
		return -1;
	}
	
	//允许重复
	//懒得看懂了。。。
	
	//两个排序数组的中位数
	
	public static double findMedianSortedArrays(int[] A, int[] B){
		
		return 0;
	}
	
	//最长连续序列
	
	//连续序列特征 统计最长序列 使用哈希 左右扩张
	public static int LongestConsecutive(int[] arr){
		Map<Integer, Boolean> map = new HashMap<>();
		for(int i:arr)
			map.put(i, false);
		
		int longest = 0;
		
		for(int i:arr){
			if (map.get(i)) {
				continue;
			}
			int length = 1;
			map.put(i, true);
		
			//向后数数
			for(int j=i+1;map.containsKey(j);j++){
				map.put(j, true);
				length++;
			}
			
			//向前数数
			for(int j=i-1;map.containsKey(j);j--){
				map.put(j, true);
				length++;
			}
			
			longest = Math.max(length, longest);
		}
		return longest;
	}
	
	//找到两个数的和为指定的数 two sum
	
	//4种方法  用哈希的油两种 单通 两通 哈希法皆可
	public static int[] twoSum(int[] arr, int target){
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<arr.length;i++)
			map.put(arr[i], i);
		
		for(int i=0;i<arr.length;i++){
			int numsj = target - arr[i];
			if (map.containsKey(numsj) && map.get(numsj) != i) {
				return new int[]{i, map.get(numsj)};
			}
		}
		
		throw new IllegalArgumentException();
	}
	
	//one pass solution
	public static int[] twoSum2(int[] arr, int target){
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<arr.length;i++){
			int numj = target - arr[i];
			if (map.containsKey(numj)) {
				return new int[]{i, map.get(numj)};
			}
			map.put(arr[i], i);
		}
		
		throw new IllegalArgumentException();
	}
	
	//数组中找三个数的和为0 要求结果从小到大
	
	public static List<List<Integer>> threeSum(int[] arr){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(arr);
		for(int i=0;i<arr.length-2;i++){
			if (i != 0 && arr[i] == arr[i-1]) {
				continue;
			}
			int j = i+1, k = arr.length-1;
			while(j < k){
				if (j != i+1 && arr[j] == arr[j-1]) {
					j++;
					continue;
				}
				int sum = arr[i] + arr[j] + arr[k]; 
				if (sum == 0) {
					res.add(Arrays.asList(arr[i],arr[j],arr[k]));
				}else if (sum > 0) {
					k--;
				}else{
					j++;
				}
				
			}
		}
		return res;
	}
	
	//数组中找三个数的和与目标数最接近的
	
	public static int Closest(int[] arr, int target){
		int result = 0;
		int min_gap = Integer.MAX_VALUE;
		
		Arrays.sort(arr);
		
		for(int i=0;i<arr.length-2;i++){
			int j = i+1;
			int k = arr.length - 1;
			
			while(j < k){
				int sum = arr[i] + arr[j] + arr[k];
				int gap = Math.abs(sum - target);
				if (gap < min_gap) {
					result = sum;
					min_gap = gap;
				}
				if (sum < target) {
					j++;
				}else{
					k--;
				}
			}
			
		}
		return result;
	}

	
	//找四个数的和为目标数 4种方法解决
	
	//排序后左右夹逼  O(n^3)
	public static List<List<Integer>> fourSum(int[] arr, int target){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (arr.length < 4) {
			return res;
		}
		Arrays.sort(arr);
		
		//too lazy to code
		
		return res;
	}
	
	//用哈希做缓存
	
	//删除数组中值为某数的所有元素 返回新数组的长度 保证稳定
	
	public static int removeElement(int[] arr, int target){
		int index = 0;
		for(int i=0;i<arr.length;i++){
			if (arr[i] != target) {
				arr[index++] = arr[i];
			}
		}
		return index;
	}
	
	
	
	public static void main(String[] args) {
		int[] arr = { 1, 1, 1, 2, 2, 3 };
		System.out.println(RemoveDuplicateMoreThanTwice(arr));
	}
}
