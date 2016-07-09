package com.bo.offer;


public class Probability {

	//offer 43
	
	/**
	 * @param num
	 * @return
	 * num 个骰子 出现所有和的情况的概率
	 */
	public static void PrintProbability(int num){
		if(num < 1)
			return;
		int max_sum = num * 6;
		int[] probability = new int[max_sum - num + 1];
		
		Probability_All(num, probability);
		double total = Math.pow((double)6, num);
		for (int i = 0; i < probability.length; i++) {
			double pro = (double) probability[i] / total;
			System.err.println(i+num + " " + pro);
		}
	}
	
	public static void Probability_All(int num, int[] probability){
		for (int i = 1; i < 7; i++) {
			Probability_Recursive(num, num, i, probability);
		}
	}
	
	/**
	 * @param original 骰子个数
	 * @param current 当前是倒数几个骰子的情况
	 * @param sum  当前的和
	 * @param probability
	 */
	public static void Probability_Recursive(int original, int current, int sum, int[] probability){
		if (current == 1) {
			//递归的终止条件
			probability[sum-original] ++;
		}
		else{
			for (int i = 1; i < 7; i++) {
				Probability_Recursive(original, current-1, i+sum, probability);
			}
		}
	}
	
	public static void main(String[] args) {
		PrintProbability(2);
	}
}
