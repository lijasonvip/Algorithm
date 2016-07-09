package com.bo.offer;

public class Duplicate {

	//offer 51
	/**
	 * @param data
	 * @return if duplicated, return duplicate num else return -1
	 */
	public static int getDuplicate(int[] data){
		if(data == null ||  data.length < 1)
			return -1;
		//输入数组的数介于0,n-1
		for (int i = 0; i < data.length; i++) {
			if (data[i] < 0 || data[i] > data.length-1) {
				return -2;
			}
		}
		for (int i = 0; i < data.length; i++) {
			while(data[i] != i){
				//不在排序后的位置上
				if (data[i] == data[data[i]]) {
					//重复
					return data[i];
				}
				//交换
				int temp = data[i];
				data[i] = data[temp];
				data[temp] = temp;
			}
		}
		return -1;
	}
	
	//offer 52
	
	/**
	 * @param A
	 * @return 构建乘积数组，使得 Bi = A0 * A1 *...*Ai-1 * Ai *...*An-1
	 */
	public int[] multiplyArray(int[] A){
		int[] B = new int[A.length];
		B[0] = 1;
		//topdown
		for (int i = 1; i < A.length; i++) {
			B[i] = B[i-1] * A[i-1];
		}
		double temp = 1;
		//bottomup
		for (int i = 0; i < A.length-2; i++) {
			temp *= A[i+1];
			B[i] *= temp;
		}
		
		return B;
	}
	
	//offer 53
	public static boolean RegularMatch(String str, String pattern){
		return true;
	}
	
	//offer 54
	public static boolean isNumeric(String str){
		if (str == null) {
			return false;
		}
		int index=0;
		char[] ch = str.toCharArray();
		if(str.charAt(index) == '+' || str.charAt(index) == '-'){
			index ++;
		}
		if(str.length() < index+1)
			return false;
		boolean numeric = true;
		while(ch[index] >= '0' && ch[index] <= '9' && index < str.length()){
		
			index ++;
		}
		
		
		
	}
	
	public static void scanDigits(char ch){
		while(ch >= '0' && ch <= '9'){
			
		}
	}
	
	public static void main(String[] args) {
		int[] data = {2,3,1,0,2,5,3};
		System.out.println(getDuplicate(data));
	}
	
}
