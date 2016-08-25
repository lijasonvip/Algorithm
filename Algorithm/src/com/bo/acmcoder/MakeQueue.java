package com.bo.acmcoder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MakeQueue {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int group = scanner.nextInt();
		for (int i = 0; i < group; i++) {
			int n = scanner.nextInt();
			System.out.println(makeOriginal(n));
		}
	}
	
	public static String makeOriginal(int n){
		Queue<Integer> queue = new LinkedList<>();
		for (int i = n; i > 0; i--) {
			queue.offer(i);
		}
		StringBuilder res = new StringBuilder();
		while(!queue.isEmpty()){
			int x = queue.poll();
			res.append(x + " ");
			if (!queue.isEmpty()) {
				x = queue.poll();
				queue.offer(x);
			}
		}
		res.reverse();
		return res.deleteCharAt(0).toString();
	}
}
