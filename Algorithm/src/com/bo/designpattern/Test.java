package com.bo.designpattern;

public class Test {

	public static void main(String[] args) {
		//测试

		Runnable runnable = new Runnable() {
			Singleton singleton = Singleton.getInstance();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName());
			}
		};
		
		for(int i=0;i<500;i++){
			Thread thread = new Thread(runnable);
			thread.start();
		}
		

	}
}
