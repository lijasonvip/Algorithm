package com.bo.acmcoder;

import java.util.*;

import com.bo.sort.Count;

public class MedianOfStreamData {

	
}

class StreamPriorityQueue{
	private int size;
	
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
		public int compare(Integer a, Integer b){
			return b-a;
		}
	});
	
	public void insert(Integer num){
		if (size % 2 == 0) {
			maxHeap.offer(num);
			int max = maxHeap.poll();
			minHeap.offer(max);
		}else{
			minHeap.offer(num);
			int min = minHeap.poll();
			maxHeap.offer(min);
		}
		size++;
	}
	
	public Double getMedian(){
		if (size % 2 == 0) {
			return new Double(minHeap.peek() + maxHeap.peek()) / 2;
		}
		else{
			return (double)minHeap.peek();
		}
	}
}
