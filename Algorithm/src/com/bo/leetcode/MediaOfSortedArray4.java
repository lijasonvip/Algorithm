package com.bo.leetcode;

import java.util.Arrays;

public class MediaOfSortedArray4 {

	/**
	 * 使用找第k个数思路
	 * 16%
	 */
	public static double findMedian(int[] nums1, int[] nums2){
		int m = nums1.length;
		int n = nums2.length;
		int total = m + n;
		if ((total & 0x1) == 1) {
			return findKth(nums1,nums2,total/2+1);
		}else
			return (findKth(nums1,nums2,total/2)+findKth(nums1,nums2,total/2+1))/2.0;
	}
	
	public static int findKth(int[] nums1, int[] nums2, int k){
		//假设nums2长
		if (nums1.length > nums2.length) {
			return findKth(nums2, nums1, k);
		}
		if (nums1.length == 0) {
			return nums2[k-1];
		}
		if (k == 1) {
			return Math.min(nums1[0], nums2[0]);
		}
		
		int ia = Math.min(k/2, nums1.length), ib = k - ia;
		if (nums1[ia-1] < nums2[ib - 1]) {
			return findKth(Arrays.copyOfRange(nums1, ia, nums1.length), nums2, k-ia);
		}else if (nums1[ia - 1] > nums2[ib - 1]) {
			return findKth(nums1, Arrays.copyOfRange(nums2, ib, nums2.length), k-ib);
		}else {
			return nums1[ia-1];
		}
	}
	
	/**
	 * https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation/2
	 * 48%
	 */
	public static double findMedian2(int[] nums1, int[] nums2){
		if (nums1.length > nums2.length) {
			return findMedian2(nums2, nums1);
		}
		if (nums2.length == 0) {
			return Double.MIN_NORMAL;
		}
		int imin = 0,imax = nums1.length,half_len = (nums1.length + nums2.length + 1)/2;
		int i = 0, j = 0;
		int max_left = 0;
		int min_right = 0;
		while(imin <= imax){
			i = (imin + imax) / 2;
			j = half_len - i;
			if (j > 0 && i < nums1.length && nums2[j-1] > nums1[i]){
				imin = i + 1;
			}else if (i > 0 && j < nums2.length && nums1[i-1] > nums2[j]) {
				imax = i-1;
			}else {
				
				if (i == 0) {
					max_left = nums2[j-1];
				}else if (j == 0) {
					max_left = nums1[i-1];
				}else {
					max_left = Math.max(nums1[i-1], nums2[j-1]);
				}
				break;
			}
			
		}
		if ((nums1.length + nums2.length)%2 == 1) {
			return max_left;
		}
		if (i == nums1.length) {
			min_right = nums2[j];
		}else if (j == nums2.length) {
			min_right = nums1[i];
		}else {
			min_right = Math.min(nums1[i], nums2[j]);
			
		}
		return (max_left+min_right)/2.0;
	}
	
	public static void main(String[] args) {
		int A[]={1,2,3,5,6};  
	    int B[]={4};
	    
	    System.out.println(findMedian2(A, B));
	}
}
