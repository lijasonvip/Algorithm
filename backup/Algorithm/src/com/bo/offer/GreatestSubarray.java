package com.bo.offer;

public class GreatestSubarray {

	/**
	 * @param data
	 * @return
	 * offer 31
	 */
	public static int GreatestSumOfSubarray(int[] data){
		if(data == null || data.length < 1)
			return 0; //greatest sum could also be 0, here we need to distinguish them like using global variant
		
		int nCursum = 0;
		int nGreatestSum = Integer.MIN_VALUE;
		for (int i = 0; i < data.length; i++) {
			//if current sum is negative, we abort current sum because no matter what added will make sum smaller
			//only when current sum is positive we can add another negative because next positive could make it larger
			if(nCursum <= 0)
				nCursum = data[i];
			else
				nCursum = nCursum + data[i];
			
			if(nCursum > nGreatestSum)
				nGreatestSum = nCursum;
		}
		return nGreatestSum;
		
	}
	
	/**
	 * can also be solved by dynamic programming
	 */
	public static int GreatestSumOfSubarray_DP(int[] data){
		return 0;
	}
	
}
