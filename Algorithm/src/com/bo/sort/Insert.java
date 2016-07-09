package com.bo.sort;

public class Insert {

	public static void sort(int[] data){
		
		for(int i=1; i<data.length;i++){
			for(int j=i;j>0;j--){
				if(data[j] < data[j-1]){
					int temp = data[j-1];
					data[j-1] = data[j];
					data[j] = temp;
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
