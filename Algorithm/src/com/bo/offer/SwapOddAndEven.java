package com.bo.offer;

public class SwapOddAndEven {

	public static void swap(int[] data) {
		if (data.length < 1 || data == null) {
			System.out.println("Ops, data is null!");
		}
		int i = 0, j = data.length - 1;
		while (i < j) {
			while (i < j && data[j] % 2 == 0) {
				j--;
			}
			while (i < j && data[i] % 2 == 1) {
				i++;
			}
			if (i < j) {
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		swap(data);
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
	}
}
