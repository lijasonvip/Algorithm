package com.bo.offer;
import java.nio.channels.Pipe;

import javax.swing.text.GapContent;

public class Sort {

	/**
	 * @param data
	 * @idea each iteration choose the min value and exchange to sorted head sequence 
	 * @compare not use the input data information, scan the whole sequence anyway. but without moving a lot only exchange in linear space
	 */
	public static void SelectSort(int[] data) {
		for (int i = 0; i < data.length-1; i++) {
			int min = data[i];
			//use k to store min index and check if need to exchange
			int k = i;
			for (int j = i+1; j < data.length; j++) {
				if(data[j] < min){
					min = data[j];
					k = j;
				}
			}
			//exchange
			if(k != i){
				int temp = data[i];
				data[i] = min;
				data[k] = temp;
			}
		}
	}
	
	/**
	 * @param data
	 */
	public static void BubbleSort(int[] data) {
		boolean isswap = false;
		//i index control n-1 rounds not the data index
		for (int i = 1; i < data.length; i++) {
			isswap = false;
			for (int j = 0; j < data.length-i; j++) {
				if(data[j] < data[j+1]){
					int temp = data[j];
					data[j] = data[j+1];
					data[j+1] = temp;
					isswap = true;	
				}
				
			}
			if (isswap == false) {
				break;
			}
		}
	}
	
	/**
	 * @param data
	 * @idea insert data[i] into proper position at data[0]...data[i-1], 
	 * @idea  back trace and exchange while not sorted 
	 * fit for partially sorted data and small number of dataset
	 */
	public static void InsertSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			for (int j = i; j > 0 && data[j] < data[j-1]; j--) {
				int temp = data[j-1];
				data[j-1] = data[j];
				data[j] = temp;
			}
		}
	}
	
	/**
	 * @param data
	 * @idea based on insert sort, exchange non adjacent data to sort the local sequence and sort those local later
	 * @compare trade off the scale and effectivity, partition the large sequence small and partially sorted..
	 *  keep the h distance subsequence sorted 
	 *  suit for large scale of data, non extra storage
	 */
	public static void ShellSort(int[] data) {
		int n = data.length;
		int h = 1;
		while(h < n/3)
			h = 3*h +1;  //1,4,13,40. vary the distance h with input data length
		while(h >= 1){
			//h sorted
			for (int i = h; i < data.length; i++) {
				for (int j = i; j >=h && data[j] < data[j-h]; j-=h) {
					int temp = data[j];
					data[j] = data[j-h];
					data[j-h] = temp;
				}
				
				
			}
			// update step 
			h = h/3;
		}
	}
	
	/**
	 * @param data
	 * @param low
	 * @param high
	 * @param temp
	 * recursive merge within nlogn
	 * use a global int[] temp to store sorted sequence during merging process, because in every 
	 * recursive allocate a array would cause problem. running time is the depth of the tree
	 * saving time but waste storage
	 */
	public static void MergeSort(int[] data, int low, int high, int[] temp) {
		if(data != null && low < high && temp.length == data.length){
			int mid = (low + high) / 2;
			MergeSort(data, low, mid,temp);
			MergeSort(data, mid+1, high,temp);
			MergeArray(data,low,mid,high,temp);
		}
	}
	
	/**
	 * @param data
	 * @param low
	 * @param mid
	 * @param high
	 * @param temp
	 * merge two sorted sequence data[low...mid] and data[mid+1...high] and keep them sorted
	 * top down merge method. there is also a bottom up one
	 * 
	 */
	public static void MergeArray(int[] data, int low, int mid, int high, int[] temp){
		int i = low;
		int j = mid + 1;
		int k = low;
		while(i <= mid && j <= high){
			if(data[i] < data[j])
				temp[k++] = data[i++];
			else
				temp[k++] = data[j++];
		}
		while(i <= mid)
			temp[k++] = data[i++];
		while(j <= high)
			temp[k++] = data[j++];
		
		for(i = low;i<=high;i++){
			data[i] = temp[i]; 
		}
	}
	
	/**
	 * @param data
	 * best explanation: http://developer.51cto.com/art/201403/430986.htm
	 */
	public static void QuickSort(int[] data,int start,int end){
		if(data != null && start < end){
			int q = Partition(data,start, end);
			QuickSort(data,start,q-1);
			QuickSort(data, q+1, end);
		}
	}
	
	/**
	 * @param data
	 * @param start
	 * @param end
	 * @return
	 */
	public static int Partition(int[] data, int start, int end){
		int i = start;
		int j = end;
		int pivot = data[start];
		while(i < j){
			while(i<j && data[j] >= pivot)
				j--;
			if (i < j) {
				//dig out the first bigger in right to left, do not worry data[i]
				//it's stored in pivot from begin
				data[i] = data[j];
				i++;
			}
			while(i<j && data[i] <= pivot)
				i++;
			if(i<j){
				data[j] = data[i];
				j--;
			}
		}
		//iterate until i==j
		data[i] = pivot;
		return i;
	}
	
	/**
	 * @param data
	 * @param start
	 * @param end
	 * another version
	 */
	public static void Quick_Sort(int[] data, int start, int end){
		if (start < end && data != null) {
			//partition
			int i=start,j=end,pivot=data[start];
			while(i<j){
				while(i<j && data[j] >= pivot)
					j--;
				if (i<j) {
					data[i++] = data[j];
				}
				while(i<j && data[i] <= pivot)
					i++;
				if(i<j){
					data[j--] = data[i];
				}
			}
			data[i] = pivot;
			Quick_Sort(data, start, i-1);
			Quick_Sort(data, i+1, end);
		}
		
	}
	
	/**
	 * @param data we use array to store a Heap, if root index is 0 then it's left child index is 2*i+1 and right child index is 2*i+2
	 * else if root index is 1, then left child index is 2*i and right child index is 2*i+1
	 * check here: http://www.cnblogs.com/mengdd/archive/2012/11/30/2796845.html 
	 */
	public static void Heap_Sort(int[] data){}
	
	public static void main(String[] args) {
		int[] data = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
		//SelectSort(data);
		//InsertSort(data);
		//ShellSort(data);
//		int[] b = new int[data.length];
//		MergeSort(data, 0, data.length-1, b);
		//Quick_Sort(data, 0, data.length-1);
		BubbleSort(data);
		for(int i:data){
			System.out.print(i + " ");
		}
	}
	
}
