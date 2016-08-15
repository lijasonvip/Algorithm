package com.bo.designpattern;

public class Singleton {

	//测试单例模式
	//首先顺序声明两个Singleton 都用getInstance获得  用==测试发现对象相等创建的是一个实例
	//然后用多线程测试，发现会打印多次构造函数被调用说明有多个实例
	//这种方式不是线程安全的

	//lazy loading
	
	 //私有的、类型为Singleton自身的静态成员变量
	private static Singleton singleton = null;
	
	//构造方法被设为私有，防止外部使用new来创建对象，破坏单例
	private Singleton(){
		System.out.println("构造函数被调用");
	}
	
	//公有的静态方法，供外部调用来获取单例对象
	public static Singleton getInstance(){
		if (singleton == null) {
			//第一次调用该方法时，创建对象
			System.out.println("first singleton");
			singleton = new Singleton();
		}
		return singleton;
	}
	

	
}
//DCL  double checked locking
class Singleton2{
	
	private volatile static Singleton2 singleton = null;
	
	private Singleton2() {
		System.out.println("constructor");
	}
	
	public static Singleton2 getInstance(){
		if (singleton == null) {
			synchronized (Singleton2.class){
				if (singleton == null) {
					singleton = new Singleton2();
				}
			}
		}
		return singleton;
	}
}
