package com.bo.java;

public class JoinThread {

	// java 的 join 方法 
	//作用： 让主线程等待子线程结束后才能继续运行  主 子 不是指继承 而是调用
	
	public static void main(String[] args) {
		try{
			ThreadTest t1 = new ThreadTest("t1");
			t1.start();
			t1.join();
			System.out.println("finish:" + Thread.currentThread().getName());
		}catch(InterruptedException e){
			e.printStackTrace();
		}

	}
	
	/*
	 *  start: t1
		finish: t1
		finish:main
	 * */
}

class ThreadTest extends Thread{
	public ThreadTest(String name){
		super(name);
	}
	
	public void run(){
		System.out.println("start: " + this.getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("finish: " + this.getName());
	}
}

class Father extends Thread{
	
	public Father(){}
	
	public void run(){
		Son son = new Son();
		son.start();
		try {
			son.join();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Father is running");
	}
}

class Son extends Thread{
	public void run(){
		System.out.println("Son is runing.");
	}
}