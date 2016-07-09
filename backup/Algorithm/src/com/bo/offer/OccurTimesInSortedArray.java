package com.bo.offer;

public class OccurTimesInSortedArray {

	//offer 38
	
	public static int GetFirstK(int[] data, int start, int end, int k){
		if(start > end)
			return -1;
		int middle = (start + end) / 2;
		if(k == data[middle]){
			//check left part to find first
			if((middle > 0 && data[middle-1] != k) || middle == 0)
				return middle;
			else
				end = middle -1;
		}else if(data[middle] > k)
			end = middle -1;
		else {
			start = middle + 1;
		}
		return GetFirstK(data, start, end, k);
	}
	
	public static int GetLastK(int[] data, int start, int end, int k){
		if(start > end)
			return -1;
		int middle = (start + end) / 2;
		if(data[middle] == k){
			if((middle < data.length -1 && data[middle+1] != k) || middle == data.length -1)
				return middle;
			else {
				start = middle + 1;
			}
		}
		else if (data[middle] > k) {
			end = middle -1;
		}else {
			start = middle + 1;
		}
		return GetLastK(data, start, end, k);
	}
	
	public static int OccurTime(int[] data, int k){
		if (data != null && data.length > 0) {
			int first = GetFirstK(data, 0, data.length-1, k);
			int last = GetLastK(data, 0, data.length-1, k);
			if(first > -1 && last > -1)
				return last - first + 1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		int[] data = {1,2,3,3,3,3,4,5};
		System.out.println(OccurTime(data, 3));
	}
}
