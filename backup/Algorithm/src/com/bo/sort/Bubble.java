package com.bo.sort;

public class Bubble {
	public static void sort(int[] data){
		for(int i=0;i<data.length-1;i++){
			for(int j=0;j<data.length-i-1;j++){
				if(data[j] > data[j+1]){
					int temp = data[j];
					data[j] = data[j+1];
					data[j+1] = temp;
				}
			}
		}
	}
	
	public static void main(String... args){
		
		int test[] = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		sort(test);
		for(int i:test){
			System.out.print(Integer.toString(i) + " ");
		}
	}
}
