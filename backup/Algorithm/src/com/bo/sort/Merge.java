package com.bo.sort;

public class Merge {
	public static void main(String... args){
		int test[] = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		
		mergeSort(test,0,test.length-1);
		for(int i:test){
			System.out.print(Integer.toString(i) + " ");
		}
		
	}
	
	public static void mergeSort(int[] data, int first, int last){
		if(first < last){
			int mid = first + (last - first) / 2;
			mergeSort(data, first, mid);
			mergeSort(data, mid+1, last);
			mergeArray(data, first, mid, last);
		}
	}
	
	public static void mergeArray(int[] a, int first, int mid, int last){
		int i,j,k;
		i = first;
		j = mid+1;
		k=0;
		int result[] = new int[last - first + 1];
		
		while(i<=mid && j<= last){
			if(a[i] < a[j])
				result[k++] = a[i++];
			else
				result[k++] = a[j++];
		}
		while(i <= mid)
			result[k++] = a[i++];
		while(j <= last)
			result[k++] = a[j++];
		for(k=0;k<result.length;k++){
			a[first+k] = result[k];
		}
	}
}
