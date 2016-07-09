package com.bo.structure;

public class Max {

	public int maxSum(int[] in){
		int max = 0;
		for(int i=0;i<in.length;i++){
			int temp = 0;
			for(int j=i;j<in.length;j++){
				temp += in[j];
			}
			if(temp > max){
				max = temp;
			}
		}
		
		return max;
	}
}
