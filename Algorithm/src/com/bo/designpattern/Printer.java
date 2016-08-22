package com.bo.designpattern;

public class Printer {

	//在定义变量时就实例化  类被加载时就实例化一个单例对象
	
//	private static Printer printer = new Printer();
	
	private Printer(){
		System.out.println("constructor");
	}
	
//	public static Printer getInstance(){
//		return printer;
//	}
	
	//缺点是常驻内存 降低效率  通过静态内部类解决
	
	public static Printer getInstance(){
		return PrinterHolder.instance;
	}
	
	private static class PrinterHolder{
		private static Printer instance = new Printer();
	}
}
