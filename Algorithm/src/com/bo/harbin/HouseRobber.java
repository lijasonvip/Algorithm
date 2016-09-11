package com.bo.harbin;

import java.util.Arrays;

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
	
	//house robber 2
	//思路就是上面的拓展 如果抢了第一家 那么接下来从第3家抢到倒数第二家 
	//如果不抢第一家 那么接下来从第二家抢到最后一家
	//https://discuss.leetcode.com/topic/14375/simple-ac-solution-in-java-in-o-n-with-explanation/3
	public static int rob3(int[] num){
		if (num.length == 0) {
			return 0;
		}
		if (num.length == 1) {
			return num[0];
		}
		if (num.length == 2) {
			return Math.max(num[0], num[1]);
		}
		if (num.length == 3) {
			return Math.max(num[0], Math.max(num[1], num[2]));
		}
		
		return Math.max(rob(Arrays.copyOfRange(num, 1, num.length)), 
				num[0] + rob(Arrays.copyOfRange(num, 2, num.length-1)));
	}
	
	//house robber 3. 二叉树 不能抢直接相邻的两个房间
	
	public static int rob(TreeNode root) {
	    if (root == null) {
	        return 0;
	    }
	    
	    int val = 0;
	    
	    if (root.left != null) {
	        val += rob(root.left.left) + rob(root.left.right);
	    }
	    
	    if (root.right != null) {
	        val += rob(root.right.left) + rob(root.right.right);
	    }
	    
	    return Math.max(val + root.val, rob(root.left) + rob(root.right));
	}
	
}

class TreeNode{
	TreeNode left;
	TreeNode right;
	int val;
	
	public TreeNode(int val){
		this.val = val;
		this.left = null;
		this.right = null;
	}
}
