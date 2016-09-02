package com.bo.structure;


public class Heap {
	
	  public static void main(String[] args) {
	        int[] a = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
	        Heap_Sort(a);
	        for(int aa:a){
	        	System.out.println(aa);
	        }
	    }
	/**
	 * @param data
	 *            we use array to store a Heap, if root index is 0 then it's
	 *            left child index is 2*i+1 and right child index is 2*i+2 else
	 *            if root index is 1, then left child index is 2*i and right
	 *            child index is 2*i+1 check here:
	 *            http://www.cnblogs.com/mengdd/archive/2012/11/30/2796845.html
	 */
	public static void Heap_Sort(int[] data) {
		Build_Max_Heap(data);
		int len = data.length;
		int Heap_Size = data.length;
		for (int i = len - 1; i >= 0; i--) {
			// exchange A[0] with A[i] to swap large to end
			int temp = data[0];
			data[0] = data[i];
			data[i] = temp;
			Heap_Size--;
			Max_Heapfy(data, 0, Heap_Size);
		}
	}

	/**
	 * @param data
	 *            from the first non leaf down to root because leaf node always
	 *            keep the heap properties
	 */
	public static void Build_Max_Heap(int[] data) {
		for (int i = data.length / 2 - 1; i >= 0; i--) {
			Max_Heapfy(data, i, data.length);
		}
	}

	/**
	 * @param data
	 * @param i
	 * @param Heap_Size
	 *            use to control length of heap, used in Heap_Sort after
	 *            Build_Heap. After swapping the root to last, last node is
	 *            sorted as largest and don't need to Heapfy again. keep heap
	 *            properties of node i, recursively select the largest node to i
	 *            position
	 */
	public static void Max_Heapfy(int[] data, int i, int Heap_Size) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int large;
		if (left < Heap_Size && data[left] > data[i])
			large = left;
		else
			large = i;
		if (right < Heap_Size && data[right] > data[large])
			large = right;
		// swap data[i] with data[large]
		if (large != i) {
			int temp = data[i];
			data[i] = data[large];
			data[large] = temp;
			Max_Heapfy(data, large, Heap_Size);
		}
	}
	
	/**
	 * @param data
	 * @return the max of a Max_Heap
	 * consider the max heap is built before.
	 */
	public static int Heap_Max(int[] data){
		return data[0];
	}
	
	/**
	 * @param data
	 * @return the max of a Max_Heap
	 * return and delete. like pop of a stack or queue
	 */
	public static int Extract_Max(int[] data){
		int Heap_Size = data.length;
		if (Heap_Size < 1) {
			return Integer.MIN_VALUE;
		}
		else{
			int max = data[0];
			data[0] = data[Heap_Size-1];
			Heap_Size--;
			Max_Heapfy(data, 0, Heap_Size);
			return max;
		}
	}
	
	/**
	 * @param data
	 * @param i
	 * @param key
	 * update a new value of head at position i and key >= data[i] so to say increase.
	 */
	public static void Increse_Key(int[] data, int i, int key){
		if(key < data[i]){
			System.out.println("Error to update a smaller key at position i");
		}
		data[i] = key;
		int parent = (i-1)/2;
		while(i>0 && data[parent] < data[i]){
			int temp = data[parent];
			data[parent] = data[i];
			data[i] = temp;
			i = (i-1)/2;
		}
	}
	
	/**
	 * @param data
	 * @param key
	 * @return
	 */
	public static int[] Insert(int[] data, int key) {
		int[] heap = new int[data.length+1];
		for (int i = 0; i < data.length; i++) {
			heap[i] = data[i];
		}
		heap[heap.length-1] = Integer.MIN_VALUE;
		Increse_Key(heap, heap.length-1, key);
		return heap;
	}
}
