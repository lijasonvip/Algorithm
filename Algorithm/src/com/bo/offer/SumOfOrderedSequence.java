package com.bo.offer;

public class SumOfOrderedSequence {

	//offer 41
	/**
	 * @param data
	 * @param s
	 * @return
	 * 递增排序数组中找两个数使得他们的和正好为s
	 */
	public static int[] FindNumberWithSum(int[] data, int s){
		if(data == null || data.length < 1 )
			return null;
		int[] find = new int[2];
		int ahead = 0, behind = data.length - 1;
		while(ahead < behind){
			int sum = data[ahead] + data[behind];
			if(sum == s){
				find[0] = data[ahead];
				find[1] = data[behind];
				return find;
			}else if (sum < s) {
				ahead ++;
			}else{
				behind --;
			}
		}
		return null;
	}
	
	//offer 41扩展
	
	/**
	 * @param sum
	 * 打印所有和为sum的连续整数序列
	 */
	public static void FindContinuousSequence(int sum){
		if(sum < 3)
			return;
		int small = 1;
		int big = 2;
		int middle = (sum + 1) / 2;
		int cur_sum = small + big;
		while(small < middle){
			//等于和小于都是big+, 大于的时候small+
			if(cur_sum == sum){
				PrintSequence(small,big);
			}
			while(cur_sum > sum && small < middle){
				cur_sum -= small;
				small ++;
				if(cur_sum == sum){
					PrintSequence(small, big);
				}
			}
			big ++;
			cur_sum += big;
		}
	}
	
	public static void PrintSequence(int small, int big){
		for (int i = small; i <= big; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] data = {1,2,4,7,11, 15};
		int[] result = FindNumberWithSum(data, 15);
		if (result != null) {
			System.out.println("data:" +result[0] + " " + result[1]);
		}
		
		FindContinuousSequence(15);
		
	}
}
