package com.bo.leetcode;

import java.util.Arrays;

public class SumClosest16 {

	 public static int threeSumClosest(int[] nums, int target) {
	        
	        int min = Integer.MAX_VALUE;
	        int sum = 0;
	        int res = 0;
	        Arrays.sort(nums);
	        for(int i=0;i<nums.length-2;i++){
	            int j = i+1, k = nums.length-1;
	            while(j < k){
	                sum = nums[i] + nums[j] + nums[k];
	                int dif = target - sum;
	                
	                if(dif == 0){
	                    return sum;
	                }else if(dif > 0){
	                    k--;
	                    if(Math.abs(dif) < min){
	                        min = Math.abs(dif);
	                        res = sum;
	                    }
	                }else{
	                	j++;
	                    if(Math.abs(dif) < min){
	                        min = Math.abs(dif);
	                        res = sum;
	                    }
	                }
	            }
	        }
	        return res;
	    }
	 
	 /**
	 * 92%
	 * best
	 */
	public static int threeSumClosest2(int[] nums, int target) {
		    Arrays.sort(nums);
		    int closest=nums[0]+nums[1]+nums[2];
		    int low,high;
		    for(int i=0;i<nums.length-1;i++){
		        low=i+1;
		        high=nums.length-1;
		        while(low<high){
		            if(nums[low]+nums[high]==target-nums[i]) return target;
		            else if(nums[low]+nums[high]>target-nums[i]){
		                while(low<high&&nums[low]+nums[high]>target-nums[i]) high--;
		                if(Math.abs(nums[i]+nums[low]+nums[high+1]-target)<Math.abs(closest-target))
		                    closest=nums[i]+nums[low]+nums[high+1];
		            }
		            else{
		                while(low<high&&nums[low]+nums[high]<target-nums[i]) low++;
		                if(Math.abs(nums[i]+nums[low-1]+nums[high]-target)<Math.abs(closest-target))
		                    closest=nums[i]+nums[low-1]+nums[high];
		            }
		        }
		    }
		    return closest;
		}
	 
	 public static int threeSumClosest3(int[] nums, int target) {
		    Arrays.sort(nums);
		    int last = nums.length - 1;
		    int dif = Integer.MAX_VALUE;
		    int result = target;
		    for (int i = 0; i <= last - 2; i++) {
		        if (nums[i] * 3 - target >= dif) {
		            break; //end search early if the minDif is already found
		        }
		        int low = i + 1;
		        int high = last;
		        int temp = target - nums[i];
		        if (temp - 2 * nums[last] >= 0) {// if the largest numbers are still smaller than the target, no need to search using two pointers;
		            temp -= nums[last] + nums[last - 1];
		            dif = temp  < dif ? temp : dif;
		            result = target - temp;
		            continue;
		        }
		        
		        while (low < high) {
		            int crt = nums[i] + nums[low] + nums[high];
		            if (target > crt) {
		                if (target - crt < dif) {
		                    result = crt;
		                    dif = target - crt;
		                }
		                low++;
		            } else if (target < crt) {
		                if (crt - target < dif) {
		                    result = crt;
		                    dif = crt - target;
		                }
		                high--;
		            } else {
		                return target;
		            }
		        }
		    }
		    return result;
		}
	 
	 public static void main(String[] args) {
		System.out.println(threeSumClosest3(new int[]{1,1,1,0}, -100));
	}
}
