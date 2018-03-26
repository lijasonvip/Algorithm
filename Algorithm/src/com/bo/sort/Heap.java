package com.bo.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Heap {
	private Heap() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public static void sort(Comparable[] pq) {
        int N = pq.length;
        for (int k = N/2; k >= 1; k--)
            sink(pq, k, N);
        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

   /***************************************************************************
    * Helper functions to restore the heap invariant.
    ***************************************************************************/

    private static void sink(Comparable[] pq, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

   /***************************************************************************
    * Helper functions for comparisons and swaps.
    * Indices are "off-by-one" to support 1-based indexing.
    ***************************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        

   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
//            StdOut.print(a[i]);
//            StdOut.print(" ");
        }
    }

    /**
     * Reads in a sequence of strings from standard input; heapsorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        Integer[] a = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        Heap.sort(a);
        show(a);
    }
	
    public static int[] HeapSort(int[] data){
    BuildHeap(data);
    int n = data.length;
    for (int last=n-1;last>=1;last--) {
      //exchange 0 with last
      int tmp = data[0];
      data[0] = data[last];
      data[last] = tmp;
      //shift down the top node, controlled by the length
      AdjustHeap(data, 0, last);
    }
    return data;
  }

  public static void BuildHeap(int[] data) {
    int n = data.length;
    for (int i=(n/2 -1); i>=0; i--) {
      AdjustHeap(data, i, n);
    }
  }

  public static void AdjustHeap(int[] data, int idx, int len) {
    int left = 2 * idx + 1;
    int right = left + 1;
    int largest = 0;

    if (left < len && data[left] > data[idx]) {
      largest = left;
    }else {
      largest = idx;
    }

    if (right < len && data[right] > data[largest]){
      largest = right;
    }

    if (largest != idx){
      int temp = data[idx];
      data[idx] = data[largest];
      data[largest] = temp;
      //shiftdown
      AdjustHeap(data, largest, len);
    }
  }
	
}
