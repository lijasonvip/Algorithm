package com.bo.sort;

public class MergeBottomUp {
	
	public static void sort(int[] data){
		for(int inc=1;inc<data.length; inc = inc+inc){
			for(int every=0;every<data.length-inc; every += inc+inc){
				
				merge(data, every, every+inc-1, Math.min(every+inc+inc-1, data.length-1));
			}
		}
	}
	
	public static void merge(int[] a, int first, int mid, int last){
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
	public static void main(String... args){
		int test[] = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		
		sort(test);
		for(int i:test){
			System.out.print(Integer.toString(i) + " ");
		}
		
	}
}
