package com.bo.acmcoder;

import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

public class NowCoderOffer2 {

	// 34题往后的题目

	public static void main(String[] args) {

		int[] data = {7,5,6,4};
		InversePairs(data);
	}

	// 数组中的逆序对
	public static int InversePairs(int[] array) {
		if (array == null || array.length <= 0) {
			return 0;
		}
		int pairsNum = mergeSort(array, 0, array.length - 1);
		return pairsNum;
	}

	public static int mergeSort(int[] array, int left, int right) {
		if (left == right) {
			return 0;
		}
		int mid = (left + right) / 2;
		int leftNum = mergeSort(array, left, mid);
		int rightNum = mergeSort(array, mid + 1, right);
		return (Sort(array, left, mid, right) + leftNum + rightNum) % 1000000007;
	}

	public static int Sort(int[] array, int left, int middle, int right) {
		int current1 = middle;
		int current2 = right;
		int current3 = right - left;
		int temp[] = new int[right - left + 1];
		int pairsnum = 0;
		while (current1 >= left && current2 >= middle + 1) {
			if (array[current1] > array[current2]) {
				temp[current3--] = array[current1--];
				pairsnum += (current2 - middle); // 这个地方是current2-middle！！！！
				if (pairsnum > 1000000007)// 数值过大求余
				{
					pairsnum %= 1000000007;
				}
			} else {
				temp[current3--] = array[current2--];
			}
		}
		while (current1 >= left) {
			temp[current3--] = array[current1--];
		}
		while (current2 >= middle + 1) {
			temp[current3--] = array[current2--];
		}
		// 将临时数组赋值给原数组
		int i = 0;
		while (left <= right) {
			array[left++] = temp[i++];
		}
		return pairsnum;
	}

	// 第一个只出现一次的字符
	public static void input() {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		System.out.println(FindChar(string));
	}

	public static char FindChar(String str) {
		char[] ch = str.toCharArray();
		int[] map = new int[256];
		for (char c : ch) {
			map[c]++;
		}
		for (char c : ch) {
			if (map[c] == 1) {
				return c;
			}
		}
		return '\0';
	}

	// 丑数 只包含2.3.5因子的数叫丑数
	public static boolean isUgly(int number) {
		while (number % 2 == 0)
			number /= 2;
		while (number % 3 == 0)
			number /= 3;
		while (number % 5 == 0)
			number /= 5;

		return (number == 1) ? true : false;
	}

	// 第index个丑数 每次判断都要从头取余计算 效率太低
	public static int getUglyNumber(int index) {
		int count = 1;
		if (index <= 0) {
			return 0;
		}
		int num = 0;
		while (count <= index) {
			num++;
			if (isUgly(num)) {
				count++;
			}
		}
		return num;
	}

	// 下一个丑数是上一个丑数乘丑数因子所得
	public static int getUglyNumber2(int index) {
		if (index <= 0) {
			return 0;
		}
		int[] res = new int[index];
		int count = 0;
		int i2 = 0, i3 = 0, i5 = 0;
		res[0] = 1;
		int tmp = 0;
		while (count < index - 1) {
			tmp = Math.min(res[i2] * 2, Math.min(res[i3] * 3, res[i5] * 5));
			if (tmp == res[i2] * 2) {
				i2++;
			}
			if (tmp == res[i3] * 3) {
				i3++;
			}
			if (tmp == res[i5] * 5) {
				i5++;
			}
			res[++count] = tmp;
		}
		return res[index - 1];

	}
}
