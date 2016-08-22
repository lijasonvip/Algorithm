package com.bo.java;

public class Box {

	public static void main(String[] args) {
		Integer i1 = 100, i2 = 100, i3 = 150, i4 = 150;
		System.out.println(i1 == i2); //true
		System.out.println(i3 == i4); //false
		//Integer对象的值在-128 到 127 之间时候 不会new新对象
	}
}
