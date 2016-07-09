package com.bo.offer;

import java.util.ArrayList;
import java.util.List;

public class PrintToMaxofNDigits {

	//offer 12
	public static  void PrintToMaxOfNDigits(int n){
		if(n < 0)
			return;
		char[] number = new char[n];
		for(int i=0;i<10;i++){
			number[0] = (char) (i + '0');
			PrintRecursive(number, 0);
		}
	}
	
	/**
	 * 递归全排列
	 */
	public static void PrintRecursive(char[] number, int index){
		if(index == number.length - 1){
			PrintNumber(number);
			return;
		}
		for(int i=0;i<10;i++){
			number[index+1] = (char)(i+'0');
			PrintRecursive(number, index+1);
		}
	}
	
	public static void PrintNumber(char[] number){
		boolean isbegin = true;
		for(int i=0;i<number.length;i++){
			if (isbegin && number[i] != '0') {
				isbegin = false;
			}
			if (!isbegin) {
				System.out.print(number[i]);
			}
		}
		System.out.println();
	}
	
	
	//数组元素全排列
	 static List<int[]> allSorts = new ArrayList<int[]>();
    public static void permutation(int[] nums, int start, int end) {
        if (start == end) { // 当只要求对数组中一个数字进行全排列时，只要就按该数组输出即可
            int[] newNums = new int[nums.length]; // 为新的排列创建一个数组容器
            for (int i=0; i<=end; i++) {
                newNums[i] = nums[i];
            }
            allSorts.add(newNums); // 将新的排列组合存放起来
        } else {
            for (int i=start; i<=end; i++) {
                int temp = nums[start]; // 交换数组第一个元素与后续的元素
                nums[start] = nums[i];
                nums[i] = temp;
                permutation(nums, start + 1, end); // 后续元素递归全排列
                nums[i] = nums[start]; // 将交换后的数组还原
                nums[start] = temp;
            }
        }
    }
	public static void main(String[] args) {
		PrintToMaxOfNDigits(2);
	}
}
