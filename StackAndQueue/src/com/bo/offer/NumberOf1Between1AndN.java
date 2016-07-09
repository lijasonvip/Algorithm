package com.bo.offer;
public class NumberOf1Between1AndN {

	//offer 32
	/**
	 * @param n
	 * @return occurance of 1 between 1 and n
	 * first method. count all 1 of 1 ... n, sum all
	 * slow, O(nlogn)
	 */
	public static int Occurance1(int n){
		int num = 0;
		for(int i=1; i<=n;i++){
			num += NumberOfN(i);
		}
		
		return num;
	}
	
	public static int NumberOfN(int n){
		int num = 0;
		while(n > 0){
			if(n % 10 == 1)
				num ++;
			n = n / 10;
		}
		return num;
	}
	
	public static int Occurance1_Recursive(int n){
		return 0;
	}
}
