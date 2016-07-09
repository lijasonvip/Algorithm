package com.bo.sort;

public class Quick {

	public static void sort(int[] data){
		sort(data, 0, data.length - 1);
	}
	
	public static void sort(int[] data, int low, int high){
		if(low >= high)
			return;
		int j = partition(data, low, high);
		sort(data, low, j-1);
		sort(data, j+1, high);
	}
	
	private static int partition(int[] a, int low, int high){
		int i= low, j= high+1;
		int key = a[low];
		
		while(true){
			while(a[++i] < key)
				if(i == high)
					break;
			while(a[--j] > key)
				if(j == low)
					break;
			if(i >= j)
				break;
			int temp = a[i];
			a[i] =a[j];
			a[j] = temp;
		}
		int t = a[low];
		a[low] = a[j];
		a[j] = t;
		return j;			
	}
	
	public static void main(String... args){
		int test[] = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		
		//sort(test);
		quicksort(test,0,test.length-1);
		for(int i:test){
			System.out.print(Integer.toString(i) + " ");
		}
		
	}
	
	public static void quicksort(int[] n, int left, int right){
		int q;
		if(left < right){
			q = partition2(n, left, right);
			quicksort(n,left,q-1);
			quicksort(n,q+1, right);
		}
	}
	
	public static int partition2(int[] n, int left, int right){
		int pivot = n[left];
		while(left < right){
			while(left < right && n[right] >= pivot){
				right--;
			}
			if(left < right)
				n[left++] = n[right];
			while(left<right && n[left] <= pivot){
				left++;
			}
			if(left < right)
				n[right--] = n[left];
			n[left] = pivot;
		}
		return left;
	}
}
