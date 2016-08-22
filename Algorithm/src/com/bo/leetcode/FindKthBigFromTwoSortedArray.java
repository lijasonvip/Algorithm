package com.bo.leetcode;

import java.util.Arrays;

//http://blog.csdn.net/realxie/article/details/8078043
//http://blog.csdn.net/kenby/article/details/6833407
//http://www.acmerblog.com/leetcode-solution-median-of-two-sorted-arrays-6270.html
public class FindKthBigFromTwoSortedArray {

	//中位数可以通过第k大求得
	public static int Median(int[] A, int[] B){
		int total = A.length + B.length;
		if ((total & 0x1) != 0) {
			return FindKth(A, 0, A.length-1, B, 0, B.length-1, total/2+1);
		}else
			return (FindKth(A, 0, A.length-1, B, 0, B.length-1, total/2) + FindKth(A, 0, A.length-1, B, 0, B.length-1, total/2+1))/2;
	}
	
	/**
	 * leetcode 找第k大
	 */
	public static int find_kth(int[] A, int[] B, int k){
		if (A.length > B.length) {
			//A 是比较短的数组
			return find_kth(B, A, k);
		}
		if (A.length == 0) {
			return B[k-1];
		}
		if(k == 1)
			return Math.min(A[0], B[0]);
		
		//把k分成两部分
		//ia + ib = k; 核心思想
		int ia = Math.min(k/2, A.length);
		int ib = k - ia;
		if (A[ia - 1] < B[ib - 1]) {
			return find_kth(Arrays.copyOfRange(A, ia, A.length), B, k-ia);
		}else if (A[ia-1] > B[ib-1]) {
			return find_kth(A, Arrays.copyOfRange(B, ib, B.length), k-ib);
		}else {
			return A[ia-1];
		}
	}
	
	
	/**
	 * 二分加分治法找两个排序数组的第k大 题目还有找中位数
	 * 画图很容易理解
	 */
	public static int FindKth(int[] A, int aBeg, int aEnd, int[] B, int bBeg, int bEnd, int k) {
		if (aBeg > aEnd) {
			return B[bBeg + k - 1];
		}
		if (bBeg > bEnd) {
			return A[aBeg + k - 1];
		}
		int aMid = aBeg + (aEnd - aBeg) / 2;
		int bMid = bBeg + (bEnd - aBeg) / 2;

		// 从A和B开始位置到两个数组中间位置的元素个数
		// 加2是因为数组下标从0开始而第k大从1开始
		int halflen = aMid - aBeg + bMid - bBeg + 2;

		if (A[aMid] < B[bMid]) {
			if (halflen > k) {
				// 此时在合并的数组中A[aBeg...aMid]和元素一定在B[bMid]左侧
				// 即此时第k大的元素一定比B[bMid]这个元素小
				// 故以后没有必要搜索B[bMid...bEnd]这些元素
				return FindKth(A, aBeg, aEnd, B, bBeg, bMid - 1, k);
			} else {
				// 此时在合并的数组中A[aBeg...aMid]元素一定在B[bMid]的左侧，
				// 所以前K个元素中一定包含A[aBeg...aMid]（可以使用反证法来证明这点）。
				// 但是无法判断A[amid+1...aEnd]与B[bBeg...bEnd]之间的关系，帮需要对他们进行判断
				// 此时K就剩下除去A[aBeg...aMid]这些元素，个数为k - (aMid - aBeg + 1)
				return FindKth(A, aMid + 1, aEnd, B, bBeg, bEnd, k - (aMid - aBeg + 1));
			}
		} else {
			// 注释与上面相似
			if (halflen > k) {
				return FindKth(A, aBeg, aMid - 1, B, bBeg, bEnd, k);
			} else {
				return FindKth(A, aBeg, aEnd, B, bMid + 1, bEnd, k - (bMid - bBeg + 1));
			}
		}
	}
	
	
	/**
	 * 找两个排序数组中的中位数  下中位数
	 * 对偶数求两个的情况可分别求上和下中位数求均值
	 */
	public static int FindMedian(int[] A, int aBeg, int aEnd, int[] B){
		//合并后大数组的中位数下标
		int center = (A.length + B.length -1) / 2;
		
		int aMid = aBeg + (aEnd - aBeg) / 2;
		//不在A中去B中找
		if (aBeg > aEnd) {
			return FindMedian(B, 0, B.length-1, A);
		}
		/* 数组A中有p个数小于A[p], 当且进当数组B中有c-p个数小于A[p], A[p]才是中位数 */ 
		if (A[aMid] >= B[center-aMid-1] && A[aMid] <= B[center-aMid]) {
			return A[aMid];
		}
		
		 /* A[p]太小了，从数组A中找一个更大的数尝试 */  
	    if (A[aMid] < B[center-aMid-1]) {  
	        return FindMedian(A, aMid+1, aEnd, B); 
	    }  
	  
	    /* A[p]太大了，从数组A中找一个更小的数尝试 */  
	    return FindMedian(A, aBeg, aMid-1, B);
	}
	
	public static void main(String[] args) {
		int[] A = {2,3,7,9,15,35};
		int[] B = {4,10,13,16,77,99,100};
		
		System.out.println(FindKth(A, 0, A.length-1, B, 0, B.length-1, 6));
		
		System.out.println(find_kth(A, B, 8));
	}
}
