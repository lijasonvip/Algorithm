package com.bo.sort;

public class Selection {

	public static void sort(int[] data){
		for(int i=0;i<data.length-1;i++){
			int k = i;
			for(int j=i+1; j<data.length;j++){
				if(data[j] < data[k]){
					k = j;
				}
			}
			if(k != i){
				int temp = data[i];
				data[i] = data[k];
				data[k] = temp;
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
