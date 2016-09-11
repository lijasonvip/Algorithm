package com.bo.harbin;

public class HouseRobber {

	//不能抢两个相邻的房子 nums[i] 表示第i家有多少钱
	//dp[i][1] 表示抢i个房子 dp[i][0]表示不抢i个房子
	public static int rob(int[] nums) {
		if(nums.length < 1){
            return 0;
        }
        int[][] dp = new int[nums.length+1][2];
        
        for(int i=1;i<= nums.length;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = nums[i-1] + dp[i-1][0];
        }
        
        return Math.max(dp[nums.length][0], dp[nums.length][1]);
	}
	
	// O(1) 空间复杂度
	public static int rob2(int[] num) {
	    int prevNo = 0;
	    int prevYes = 0;
	    for (int n : num) {
	        int temp = prevNo;
	        prevNo = Math.max(prevNo, prevYes);
	        prevYes = n + temp;
	    }
	    return Math.max(prevNo, prevYes);
	}
	
	//house robber 2. 
	
}
