package com.bo.structure;

public class TestEqual {

	public static void main(String[] args) {
		String s1 = "Monday";
		String s2 = "Monday";
		
		if (s1 == s2) {
			System.out.println("s1 == s2");
		}else
			System.out.println("s1 != s2");
		
		String s3 = new String("Monday");
		if(s1 == s3)
			System.out.println("s1 == s3");
		else
			System.out.println("s1 != s3");
		if (s1.equals(s3)) {
			System.out.println("s1 equals s3");
		}else
			System.out.println("s1 not equals s3");
		
		s3 = s3.intern();
		//检查字符串缓冲池中是否存在Monday字符串，如果存在就返回池中字符串，不存在会吧Monday添加到池中，然后返回其引用
		if (s1 == s3) {
			System.out.println("s1 == s3.intern()");
		}else
			System.out.println("s1 != s3.intern()");
		if (s1.equals(s3)) {
			System.out.println("s1 equal s3.intern()");
		}else
			System.out.println("s1 not equal s3.intern()");
		
		//所以比较字符串时候可以先把new的也全部intern
		
		
	}
}
