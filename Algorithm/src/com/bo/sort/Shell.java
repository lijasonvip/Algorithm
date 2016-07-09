package com.bo.sort;

public class Shell {

	public static void sort(int[] data){
		int increment = 1;
		if(increment < data.length / 3){
			increment = 3 * increment +1;
		}
		while(increment >= 1){
			for(int i=increment;i<data.length;i++){
				for(int j=i;j>=increment;j-=increment){
					if(data[j] < data[j-increment]){
						int temp = data[j];
						data[j] = data[j-increment];
						data[j-increment] = temp;
					}
				}
			}
			increment /= 3;
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
