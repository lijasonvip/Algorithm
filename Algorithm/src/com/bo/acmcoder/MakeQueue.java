package com.bo.acmcoder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MakeQueue {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int pairs = scanner.nextInt();
		for (int i = 0; i < pairs; i++) {
			int n = scanner.nextInt();
			List<Integer> list = getResult(n);
			for (int j = 0; j < list.size() - 1; j++) {
				System.out.print(list.get(j) + " ");
			}
			System.out.println(list.get(list.size() - 1));
		}
	}

	public static List<Integer> getResult(int n) {
		List<Integer> list = new ArrayList<>();
		while (n > 0) {
			list.add(0, n);
			int tmp = list.remove(list.size() - 1);
			list.add(0, tmp);
			n--;
		}
		return list;
	}

	public static void getInput() {
		Scanner sc = new Scanner(System.in);
		int pairs = sc.nextInt();

		for (int i = 0; i < pairs; i++) {
			int n = sc.nextInt();
			List<Integer> list = new ArrayList<>();
			list = getPerm(list, n);
			int j = 0;
			while (j < list.size() - 1) {
				System.out.print(list.get(j) + " ");
				j++;
			}
			System.out.println(list.get(list.size() - 1));
		}
	}

	// 根据调整规则，将输出的序列“逆向”调整，即从大到小，每次将最小的数插入到链表头部，然后将末尾的数字调整到头部，直至1结束
	// 输入的时候逆序来,新数(小的)往前插,尾部数挪到前面
	// 输出的时候先把头部往后挪, 输出第二个数 正好是一个互反的构建过程
	public static List<Integer> getPerm(List<Integer> list, int n) {
		int tmp = 0;
		while (n > 0) {
			list.add(0, n);
			tmp = list.remove(list.size() - 1);
			list.add(0, tmp);
			n--;
		}
		return list;
	}
}
