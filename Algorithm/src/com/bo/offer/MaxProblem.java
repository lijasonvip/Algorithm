package com.bo.offer;

import java.util.Arrays;

//http://www.tuicool.com/articles/baYJ3a
//http://www.acmerblog.com/max-sum-rectangle-in-a-matrix-5955.html
public class MaxProblem {

	/**
	 * 动态规划求最大子序列和， 注释掉的start end可以记录最大的位置
	 */
	public static int Maxsum_DP(int[] data){
		//输入数据的合法性验证
		int max = Integer.MIN_VALUE;
		int sum = 0;
		int curstart = 0;
		for (int i = 0; i < data.length; i++) {
			if(sum < 0){
				sum = data[i];
				curstart = i;
			}
			else{
				sum += data[i];
			}
			
			if(sum > max){
				max = sum;
//				start = curstart;
//				end = i;
			}
		}
		return max;
	}
	
	/**
	 * 分治法求最大子序列和
	 * 最大序列和可能出现三个地方 左半部分，右半部分，跨数据中部占左右两半部分
	 */
	public static int Maxsum_DC(int[] data, int left, int right){
		//合法性验证
		if (left == right) {
			if (data[left] > 0) {
				return data[left];
			}else
				return 0;
		}
		int mid = (left + right) / 2;
		int maxleftsum = Maxsum_DC(data, left, mid);
		int maxrightsum = Maxsum_DC(data, mid+1, right);
		int maxleftbordersum = 0, leftbordersum = 0;
		for(int i=mid;i>=left;i--){
			leftbordersum += data[i];
			if (leftbordersum > maxleftbordersum) {
				maxleftbordersum = leftbordersum;
			}
		}
		int maxrightbordersum = 0, rightbordersum = 0;
		for (int j = mid+1;j<=right;j++) {
			rightbordersum += data[j];
			if (rightbordersum > maxrightbordersum) {
				maxrightbordersum = rightbordersum;
			}
		}
		return Math.max(maxleftsum > maxrightsum ? maxleftsum : maxrightsum, maxleftbordersum+maxrightbordersum);
	}
	
	
	/**
	 * 最大连续子数组 子矩阵
	 */
	public static int[][] arrSum(int[][] arr){
		int m = arr.length;
		int n = arr[0].length;
		int[][] p = new int[m+1][n+1];
		Arrays.fill(arr, 0);
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				p[i][j] = p[i-1][j] + p[i][j-1] + arr[i-1][j-1] - p[i-1][j-1];
			}
		}
		return p;
	}
	
	public static int maxArrSum(int[][] arr){
		int m = arr.length;
		int n = arr[0].length;
		int p[][] = arrSum(arr);
		int ans = Integer.MIN_VALUE;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				for(int endi=i;endi<=m;endi++){
					for(int endj=j;endj<=n;endj++){
						//大方框减去小方框
						int sum = p[endi][endj] - p[i-1][endj] - p[endi][j-1] + p[i-1][j-1];
						if(ans < sum)
							ans = sum;
					}
				}
			}
		}
		return ans;
	}
	
	//============================================================================
	// Name        : MaxReaption.java
	// Author      : GaoTong
	// Date        : 2014/8/3
	// Copyright   : www.acmerblog.com
	//============================================================================

	  /**
	 * 重复次数最的数字
	 * http://www.acmerblog.com/maximum-repeating-number-6035.html
	 * 遍历arr[]中的元素，做如下操作 arr[arr[i]%k] += k .  之所以增加k是
	 * 因为可以恢复修改过的arr[i]，它最初的数据即为 arr[i] % k , 同时 arr[i] / k 就代表
	 * 数字 i 出现的次数。此方法的前提是要保证计算不会溢出
	 */
	public  static int maxRepeating(int[]arr, int k){
	        int maxCnt = 0; // maxCnt/k 为当前最大的重复次数
	        int ans = arr[0];
	        for (int i = 0; i< arr.length; i++){
	            arr[arr[i]%k] += k;
	            // arr[arr[i]%k] / k  代表 arr[i]%k 出现的次数, 由于k是固定的，这里可以直接用arr[arr[i]%k]比较
	            if(maxCnt < arr[arr[i]%k]){
	                maxCnt = arr[arr[i]%k];
	                ans = arr[i]%k;
	            }
	        }

	        //恢复数组
	        for (int i = 0; i< arr.length; i++){
	            arr[i] = arr[i] % k;
	        }
	        return ans;
	    }

	
	public static void main(String[] args) {
//		int[] data = {0,-2,3,5,-1,2};
//		int[] data2 = {-9,-2,-3,-5,-3};
//		System.out.println(Maxsum_DP(data2));
//		
//		
//		System.out.println();
//		System.out.println(Maxsum_DC(data, 0, data.length-1));
		
		int arr[] = {2, 3, 3, 5, 7,3, 7,4, 1,7,7};
        int k = 8;
        System.out.println("The maximum repeating number is : " + maxRepeating(arr, k));
	}
}
