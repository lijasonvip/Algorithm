package com.bo.offer;

public class CheckMoreThanHalf {

	//offer 29
	
	/**
	 * @param data
	 * partition based method
	 * occur times more than half of data length means in sorted array, this value will show in middle.
	 * so we can partition from a random value check it's position left or right than middle. left then we find right else find left.
	 * partition to make sure left part is all smaller than value and right part all larger than value.
	 */
	public static int MoreThanHalf(int[] data){
		if(CheckInvalideArray(data))
			return 0;
		int middle = data.length >> 2;
		int start = 0;
		int end = data.length - 1;
		int index = Partition(data, start, end);
		while(index != middle){
			if(index > middle){
				end = index -1;
				index = Partition(data, start, end);
			}
			else{
				start = index + 1;
				index = Partition(data, start, end);
			}
		}
		int result = data[middle];
		if(CheckMoreThanHalf(data, result))
			return result;
		
		return 0;
		
	}
	
	/**
	 * @param data
	 * take use of properties of array.
	 * when a numbers occur times more than half of data length means its times more than others times sum
	 * 
	 */
	public static int MoreThanHalf2(int[] data){
		if(!CheckInvalideArray(data))
			return 0;
		int result = data[0];
		int times = 1;
		for (int i = 1; i < data.length; i++) {
			if(times == 0){
				result = data[i];
				times = 1;
			}
			else if (data[i] == result) {
				times ++;
			}else {
				times --;
			}
		}
		if(CheckMoreThanHalf(data, result))
			return result;
		
		return 0;
	}
	
	public static int Partition(int[] data, int start, int end){
		int i = start, j = end;
		int index = RandIndex(start, end);
		int pivot = data[index];
		while(i<j){
			while(i<j && data[j] > pivot)
				j--;
			
			while(i<j && data[i] < pivot)
				i++;
			int temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}
		data[i] = pivot;
		return i;
		
	}
	
	public static int RandIndex(int start, int end){
		int rand;
		if(end > start)
			rand = (int)(Math.random()*(end - start));
		else if(end == start) {
			rand = start;
		}else {
			rand = 0;
		}
		return rand;
	}
	
	public static boolean CheckMoreThanHalf(int[] data, int value){
		int times = 0;
		for (int i = 0; i < data.length; i++) {
			if(data[i] == value){
				times ++;
			}
		}
		if(times * 2 <= data.length)
			return false;
		return true;
	}
	
	public static boolean CheckInvalideArray(int[] data){
		if(data == null || data.length < 1)
			return false;
		else
			return true;
	}
	
	public static void main(String[] args) {
		int[] data = {1, 2, 3, 2, 2, 2, 5, 4, 2};
		System.out.println(MoreThanHalf2(data));
	}
}
