package com.bo.java;

public class MultiThread extends Thread {

	public static void main(String[] args) {
//		Thread1 t1 = new Thread1("A");
//		Thread1 t2 = new Thread1("B");
//		
//		t1.start();
//		t2.start();
		
//		Thread t3 = new Thread(new Thread2("C"));
//		Thread t4 = new Thread(new Thread2("D"));
//		
//		t3.start();
//		t4.start();
		
		Thread2 my2 = new Thread2();
		new Thread(my2, "E").start();;
		new Thread(my2, "F").start();;
		new Thread(my2, "G").start();;
		
		
	}
}

class Thread1 extends Thread {
	private String name;

	private int count = 10;
	
	public Thread1(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
//			System.out.println(name + " runing : " + i);
			System.out.println(name + " runing count = " + count--);
			try {
				sleep((int) Math.random() * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class Thread2 implements Runnable{
	
	private String name;
	private int count = 10;
	
	public Thread2(){}
	
	public Thread2(String name){
		this.name = name;
	}
	
	public  void run(){
		for(int i=0;i<10;i++){
//			System.out.println(name + " runing : " + i);
			System.out.println(Thread.currentThread().getName() + " runing count = " + count--);
			try {
				Thread.sleep((int) Math.random() * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}