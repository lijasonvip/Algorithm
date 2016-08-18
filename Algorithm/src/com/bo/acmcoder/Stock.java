package com.bo.acmcoder;

import java.util.Arrays;
import java.util.Scanner;

public class Stock {

	//注意  主类要定义为Main 记得包含引入的包
	
	public static int price(int n){
		int i = 0; //统计下跌了多少次
		int j = 2;	// 每次下跌后上涨了的天数 包含下跌的那天
		int k = n;
		while(j < k){
			i += 2;
			k -= j;
			++j;
		}
		return n-i;
	}
	
	public static void main(String[] args) {
		//一行数据的时候这样就可以 
		//遇到多行数据的情况 第一行是指定个数 第二行是所有数字 我们用第一行的个数控制接下来多少次输入
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextInt()){
			int n = scanner.nextInt();
			System.out.println(price(n));
		}
		
		//多行例子如下
		// 4
		// 1 2 3 4
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()){
			int len = sc.nextInt();
			int[] data = new int[len];
			for (int i = 0; i < len; i++) {
				data[i] = sc.nextInt();
			}
			/// 接下来用算法处理读入的数据 然后输出即可
		}
	}
	
	public static int guessCode(int l, int r, int m){
		if (l > r) {
			return -1;
		}
		int count = 0;
		for(int i=l;i<=r;i++){
			if (count1(i) == m) {
				count++;
			}
		}
		
		if(count == 0)
			count = -1;
		return count;
	}
	
	public static int count1(int n){
		int count = 0;
		while(n != 0){
			n = n & (n-1);
			count ++;
		}
		return count;
	}
	
	public static String swapSort(int[] data, int[] copy){
		Arrays.sort(copy);
		int left = 0, right = data.length-1;
		while(left < data.length && data[left] == copy[left])
			left ++;
		while(right >= 0 && data[right] == data[right])
			right--;
		int i;
		for (i = 0; i < copy.length; i++) {
			if (copy[left+i] != copy[right-i]) {
				break;
			}
		}
		
		if (i > right - left) {
//			System.out.println("yes");
			return "yes";
		}else{
//			System.out.println("no");
			return "no";
		}
		
	}
}
