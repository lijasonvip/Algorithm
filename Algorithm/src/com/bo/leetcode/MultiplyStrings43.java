package com.bo.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class MultiplyStrings43 {

	// 大数乘法

	public static void main(String[] args) {
		MultiplyStrings43 m = new MultiplyStrings43();
		List<Integer> res = m.subArraySum(new int[]{4,2,-5,11}, 3);
		
		System.out.println(m.find((long)304103414));
	}
	
	public long find(long n){
		String string = String.valueOf(n);
		String[] strings = new String[string.length()];
		for (int i = 0; i < strings.length; i++) {
			strings[i] = String.valueOf(string.charAt(i));
		}
		Arrays.sort(strings, new Comparator<String>() {
			public int compare(String a, String b){
				String s1 = a+b;
				String s2 = b + a;
				return s1.compareTo(s2);
			}
		});
		StringBuilder sBuilder = new StringBuilder();
		for (String s:strings) {
			sBuilder.append(s);
		}
		int count=0;
		while(sBuilder.charAt(0) == '0') {
			sBuilder.deleteCharAt(0);
			count++;
		}
		StringBuilder res = new StringBuilder();
		if (count != 0) {
			char head = sBuilder.charAt(0);
			sBuilder.deleteCharAt(0);
			res.append(head);
			for(int i=0;i<count;i++)
				res.append('0');
		}
		res.append(sBuilder.toString());
		
		return Long.valueOf(res.toString());
	}
	
	public List<Integer> subArraySum(int[] data, int k){
		if (data == null || k > data.length) {
			return null;
		}
		int[] res = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			res[i] = getCircle(data, k, i+1);
		}
		
		List<Integer> result = new ArrayList<>();
		for (int i : res) {
			result.add(i);
		}
		return result;
	}
	
	public int getCircle(int[] data, int k, int i){
		int index = i;
		int sum = 0;
		for(int j=0;j<k;j++){
			if (i > data.length -1) {
				i = 0;
			}
			sum += data[i];
			i++;
		}
		return sum;
	}
	

	/**
	 * 两个数字按位乘举个例子类似了leetcode的例子很好懂
	 */
	public static String multiply(String num1, String num2) {
		int m = num1.length(), n = num2.length();
		int[] pos = new int[m + n];

		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int p1 = i + j, p2 = i + j + 1;
				int sum = mul + pos[p2];

				pos[p1] += sum / 10;
				pos[p2] = sum % 10;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int p : pos)
			if (!(sb.length() == 0 && p == 0)) {
				sb.append(p);
			}

		return sb.length() == 0 ? "0" : sb.toString();
	}

	/**
	 * 不是按位计算但也很好懂
	 */
	public static String multiply2(String num1, String num2) {
		int n1 = num1.length(), n2 = num2.length();
		int[] products = new int[n1 + n2];
		for (int i = n1 - 1; i >= 0; i--) {
			for (int j = n2 - 1; j >= 0; j--) {
				int d1 = num1.charAt(i) - '0';
				int d2 = num2.charAt(j) - '0';
				products[i + j + 1] += d1 * d2;
			}
		}
		int carry = 0;
		for (int i = products.length - 1; i >= 0; i--) {
			int tmp = (products[i] + carry) % 10;
			carry = (products[i] + carry) / 10;
			products[i] = tmp;
		}
		StringBuilder sb = new StringBuilder();
		for (int num : products)
			sb.append(num);
		while (sb.length() != 0 && sb.charAt(0) == '0')
			sb.deleteCharAt(0);
		return sb.length() == 0 ? "0" : sb.toString();
	}
}
