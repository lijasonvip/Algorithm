package com.bo.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//两个排序的数组中找最小的k个数字对 第一个数字来自第一个数组 第二个数字来自第二个数组
public class KSmallestPairs {

	/**
	 * 思路： 使用优先队列存储数字对 自定义数字对的比较函数 遍历所有对 维持一个k大小的优先队列 如果新对更小则从对中拿出一个大的替换
	 * 
	 */
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>(k, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return -a[0] - a[1] + b[0] + b[1];
			}
		});
		List<int[]> res = new ArrayList<>();
		for (int i = 0; i < k && i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {
				int[] cur = new int[2];
				cur[0] = nums1[i];
				cur[1] = nums2[j];
				if (heap.size() < k)
					heap.offer(cur);
				else {
					int[] top = heap.peek();
					if (top[0] + top[1] > cur[0] + cur[1]) {
						heap.poll();
						heap.offer(cur);
					}
				}
			}
		}
		for (int[] a : heap) {
			res.add(a);
		}
		return res;
	}

	/**
	 * 更快的算法 不用优先队列
	 * 因为数组已经排序了 所以并不需要遍历所有的数字对
	 */
	public static List<int[]> ksmall(int[] nums1, int[] nums2, int k) {
		List<int[]> ret = new ArrayList<int[]>();
		if (nums1.length == 0 || nums2.length == 0 || k == 0) {
			return ret;
		}

		int[] index = new int[nums1.length];
		while (k-- > 0) {
			int min_val = Integer.MAX_VALUE;
			int in = -1;
			for (int i = 0; i < nums1.length; i++) {
				if (index[i] >= nums2.length) {
					continue;
				}
				if (nums1[i] + nums2[index[i]] < min_val) {
					min_val = nums1[i] + nums2[index[i]];
					in = i;
				}
			}
			if (in == -1) {
				break;
			}
			int[] temp = { nums1[in], nums2[index[in]] };
			ret.add(temp);
			index[in]++;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		int[] num1 = {1,7,11};
		int[] num2 = {2,4,6};
		List<int[]> res = ksmall(num1, num2, 3);
		for(int[] i:res)
			System.out.println(i[0]+" " + i[1]);
	}
}