package com.bo.offer;

import java.util.Arrays;

public class LeastKNumbers {

	//offer 30
	
	/**
	 * @param data
	 * @param k
	 * @return
	 * only when input data could been modified
	 * O(n)
	 */
	public static int[] leastK(int[] data, int k){
		if(data == null || data.length  < 1 || k < 1 || k > data.length){
			return null;
		}
		int start = 0;
		int end = data.length - 1;
		int index = Partition(data, start, end);
		while(index != k-1){
			if(index > k-1){
				end = index - 1;
				index = Partition(data, start, end);
			}else {
				start = index + 1;
				index = Partition(data, start, end);
			}
		}
		return Arrays.copyOfRange(data, 0, index);
	}
	
	/**
	 * @param data
	 * O(nlogn), good for big data
	 */
	public static int[] leastK2(int[] data, int k){
		// red - black tree knowledge needed
		// In Java. TreeMap and TreeSet implemented using RB Tree.
		return null;
	}
	
	public static int Partition(int[] data, int start, int end){
		if(data == null || start < 0 || end < start)
			return 0;
		int i = start, j = end;
		int pivot = (int)(Math.random()*(end - start));
		while(i < j){
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
}
