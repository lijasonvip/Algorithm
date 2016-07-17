package com.bo.structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
//http://www.2cto.com/kf/201402/280250.html
public class Priority {

	//测试Java优先队列
	public static void main(String[] args) {
		PriorityQueue<Integer> p = new PriorityQueue<>();
		
		//存放数字
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			p.offer(random.nextInt(i+10));
		}
		
		Print(p);
		
		List<Integer> integers = Arrays.asList(25,16,15,6,1,40,0,77);
		p.addAll(integers);
		Print(p);
		
		//从大到小的顺序定义一个优先队列方法
		p = new PriorityQueue<Integer>(integers.size(), Collections.reverseOrder());
		p.addAll(integers);
		Print(p);
		
		//存放字符
		String fact = "UDJKKDJL WSAPLMAD IUJSAA ATHSHJ";
		List<String> list = Arrays.asList(fact.split(""));
		//直接赋值
		PriorityQueue<String> pq = new PriorityQueue<String>(list);
		Print2(pq);
		
		pq = new PriorityQueue<String>(list.size(), Collections.reverseOrder());
		pq.addAll(list);
		Print2(pq);
		//去重
		Set<Character> set = new HashSet<>();
		for (char c:fact.toCharArray()) {
			set.add(c);
		}
		PriorityQueue<Character> pc = new PriorityQueue<Character>(set);
		Print3(pc);
		
		int[] arr = {1,3, 5, 6 ,2, 5, 7};
		PriorityQueue<int[]> comp = new PriorityQueue<>(3, new Comparator<int[]>() {
			public int compare(int[] a, int[] b){
				return 0;
			}
		});
		
	}
	
	public static void Print(PriorityQueue<Integer> p){
		while(p.peek() != null)
			System.out.print(p.remove() + " ");
		System.out.println();
	}
	public static void Print2(PriorityQueue<String> p){
		while(p.peek() != null)
			System.out.print(p.remove() + " ");
		System.out.println();
	}
	public static void Print3(PriorityQueue<Character> p){
		while(p.peek() != null)
			System.out.print(p.remove() + " ");
		System.out.println();
	}
}
