package com.bo.java;

public class Construction {

	/**
	 * parent中static初始化块 son中的static初始化块 parent中的初始化块 parent构造方法 son中的初始化块
	 * son构造方法
	 */
	public static void main(String[] args) {
		Sonn s = new Sonn();
		AA ab = new B();
		ab = new B();
	}
}

class Parent {

	{
		System.out.println("parent中的初始化块");
	}
	static {
		System.out.println("parent中static初始化块");
	}

	public Parent() {
		System.out.println("parent构造方法");
	}
}

class Sonn extends Parent {
	{
		System.out.println("son中的初始化块");
	}

	static {
		System.out.println("son中的static初始化块");
	}

	public Sonn() {
		System.out.println("son构造方法");
	}

}

class AA {

	static {
		System.out.print("1");
	}

	public AA() {
		System.out.print("2");
	}
}

class B extends AA {

	static {
		System.out.print("a");
	}

	public B() {
		System.out.print("b");
	}
}
