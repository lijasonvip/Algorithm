package com.bo.offer;

import java.nio.channels.NonWritableChannelException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import com.bo.sort.Heap;

public class Sort {

	/**
	 * @param data
	 * @idea each iteration choose the min value and exchange to sorted head
	 *       sequence
	 * @compare not use the input data information, scan the whole sequence
	 *          anyway. but without moving a lot only exchange in linear space
	 */
	public static void SelectSort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			int min = data[i];
			// use k to store min index and check if need to exchange
			int k = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < min) {
					min = data[j];
					k = j;
				}
			}
			// exchange
			if (k != i) {
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
		boolean isswap;
		// i index control n-1 rounds not the data index
		for (int i = 1; i < data.length; i++) {
			isswap = false;
			for (int j = 0; j < data.length - i; j++) {
				if (data[j] < data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
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
	 * @idea back trace and exchange while not sorted fit for partially sorted
	 *       data and small number of dataset
	 */
	public static void InsertSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			for (int j = i; j > 0 && data[j] < data[j - 1]; j--) {
				int temp = data[j - 1];
				data[j - 1] = data[j];
				data[j] = temp;
			}
		}
	}

	/**
	 * @param data
	 * @idea based on insert sort, exchange non adjacent data to sort the local
	 *       sequence and sort those local later
	 * @compare trade off the scale and effectivity, partition the large
	 *          sequence small and partially sorted.. keep the h distance
	 *          subsequence sorted suit for large scale of data, non extra
	 *          storage
	 */
	public static void ShellSort(int[] data) {
		int n = data.length;
		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1; // 1,4,13,40. vary the distance h with input data
							// length
		while (h >= 1) {
			// h sorted
			for (int i = h; i < data.length; i++) {
				for (int j = i; j >= h && data[j] < data[j - h]; j -= h) {
					int temp = data[j];
					data[j] = data[j - h];
					data[j - h] = temp;
				}

			}
			// update step
			h = h / 3;
		}
	}

	/**
	 * @param data
	 * @param low
	 * @param high
	 * @param temp
	 *            recursive merge within nlogn use a global int[] temp to store
	 *            sorted sequence during merging process, because in every
	 *            recursive allocate a array would cause problem. running time
	 *            is the depth of the tree saving time but waste storage
	 */
	public static void MergeSort(int[] data, int low, int high, int[] temp) {
		if (data != null && low < high && temp.length == data.length) {
			int mid = (low + high) / 2;
			MergeSort(data, low, mid, temp);
			MergeSort(data, mid + 1, high, temp);
			MergeArray(data, low, mid, high, temp);
		}
	}

	/**
	 * @param data
	 * @param low
	 * @param mid
	 * @param high
	 * @param temp
	 *            merge two sorted sequence data[low...mid] and
	 *            data[mid+1...high] and keep them sorted top down merge method.
	 *            there is also a bottom up one
	 * 
	 */
	public static void MergeArray(int[] data, int low, int mid, int high, int[] temp) {
		int i = low;
		int j = mid + 1;
		int k = low;
		while (i <= mid && j <= high) {
			if (data[i] < data[j])
				temp[k++] = data[i++];
			else
				temp[k++] = data[j++];
		}
		while (i <= mid)
			temp[k++] = data[i++];
		while (j <= high)
			temp[k++] = data[j++];

		for (i = low; i <= high; i++) {
			data[i] = temp[i];
		}
	}

	/**
	 * @param data
	 *            best explanation:
	 *            http://developer.51cto.com/art/201403/430986.htm
	 */
	public static void QuickSort(int[] data, int start, int end) {
		if (data != null && start < end) {
			int q = Partition(data, start, end);
			QuickSort(data, start, q - 1);
			QuickSort(data, q + 1, end);
		}
	}

	/**
	 * @param data
	 * @param start
	 * @param end
	 * @return
	 */
	public static int Partition(int[] data, int start, int end) {
		int i = start;
		int j = end;
		int pivot = data[start];
		while (i < j) {
			while (i < j && data[j] >= pivot)
				j--;
			if (i < j) {
				// dig out the first bigger in right to left, do not worry
				// data[i]
				// it's stored in pivot from begin
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i] <= pivot)
				i++;
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		// iterate until i==j
		data[i] = pivot;
		return i;
	}

	/**
	 * @param data
	 * @param start
	 * @param end
	 *            another version
	 */
	public static void Quick_Sort(int[] data, int start, int end) {
		if (start < end && data != null) {
			// partition
			int i = start, j = end, pivot = data[start];
			while (i < j) {
				while (i < j && data[j] >= pivot)
					j--;
				if (i < j) {
					data[i++] = data[j];
				}
				while (i < j && data[i] <= pivot)
					i++;
				if (i < j) {
					data[j--] = data[i];
				}
			}
			data[i] = pivot;
			Quick_Sort(data, start, i - 1);
			Quick_Sort(data, i + 1, end);
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
	 * @param A
	 *            in put data where max num is k
	 * @param k,
	 *            max of input data
	 * @return count sort first count those num's count and store the count at
	 *         position num then change count array to store how many num little
	 *         or equal than position index, by plusing count before current
	 *         index all done http://blog.jobbole.com/74574/
	 */
	public static int[] CountSort(int[] A, int k) {
		if (A == null || k < 1)
			return null;
		int[] B = new int[A.length];
		int[] C = new int[k + 1]; // counting array
		for (int i = 0; i < A.length; i++) {
			C[A[i]] = C[A[i]] + 1;
		}
		// how many small or equal i
		for (int i = 1; i < C.length; i++) {
			C[i] = C[i - 1] + C[i];
		}
		// how many small or equal i mean i stays at position how many - 1
		for (int i = 0; i < A.length; i++) {
			B[C[A[i]] - 1] = A[i];
			C[A[i]] = C[A[i]] - 1;
		}
		return B;
	}

	/**
	 * @param data
	 * list array to store is wonderful use
	 */
	public static void BucketSort(double[] data) {
		if (data == null)
			System.out.println("Err with no input data");
		// a list array. jesus crist
		LinkedList[] arrList = new LinkedList[data.length];
		for (int i = 0; i < data.length; i++) {
			int floor = (int) Math.floor(10 * data[i]);
			if (arrList[floor] == null)
				arrList[floor] = new LinkedList<Double>();
			arrList[floor].add(data[i]); 
		}

		// in each bucket, we sort them, sort the linked list
		for (int i = 0; i < arrList.length; i++) {
			if (arrList[i] != null) {
				Collections.sort(arrList[i]);
			}
		}
		// result
		int count = 0;
		for (int i = 0; i < arrList.length; i++) {
			if (arrList[i] != null) {
				for (int j = 0; j < arrList[i].size(); j++) {
					data[count++] = (double) arrList[i].get(j);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] data = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
		double array[] = { 0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68 };
		// SelectSort(data);
		// InsertSort(data);
		// ShellSort(data);
		// int[] b = new int[data.length];
		// MergeSort(data, 0, data.length-1, b);
		// Quick_Sort(data, 0, data.length-1);
		// BubbleSort(data);
		// Heap_Sort(data);
		 int[] result = CountSort(data, 50);
		//BucketSort(array);
		for (int i : result) {
			System.out.print(i + ",");
		}
	}

}
