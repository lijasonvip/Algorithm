package com.bo.java;

import java.util.StringTokenizer;

public class Tokenizer {

	public static void main(String[] args) {
		String s = "I am zhangwuji , she is my friends";
		StringTokenizer fx = new StringTokenizer(s, " ,");// 分别使用空格 逗号作为分隔符
		int number = fx.countTokens();
		while (fx.hasMoreTokens()) {
			String str = fx.nextToken();
			System.out.println("当前单词：" + str);
			System.out.println("还剩" + fx.countTokens() + "个单词");
		}
		System.out.println("共有单词" + number + "个");

	}

	private static void defaultTokenizer() {
		// java中如果没有指定分隔符，默认是空格
		String str = "this is a java program";
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		System.out.println("**************************");
		// st.hasMoreElements 用法 作用同上,只能写一个，st被调用后就到达分割末尾了
		// while(st.hasMoreElements()){
		// System.out.println(st.nextElement());
		// }
	}
}
