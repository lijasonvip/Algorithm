package com.bo.designpattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	public static void main(String[] args) {
		// 单线程线程池 只有一个线程在工作 串行执行执行到异常结束换一个线程替换
		// ExecutorService pool = Executors. newSingleThreadExecutor();

		// 创建固定大小的线程池 每次提交一个任务就创建一个线程 直到达到最大大小 异常后才补充
		// ExecutorService pool = Executors.newFixedThreadPool(2);

		// 创建一个可缓存的线程池 大小超过任务需求就会回收很久不用的线程 任务增加时再添加新线程处理
		// ExecutorService pool = Executors.newCachedThreadPool();

		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		// Thread t1 = new ThreadTest();
		// Thread t2 = new ThreadTest();
		// Thread t3 = new ThreadTest();
		// Thread t4 = new ThreadTest();
		// Thread t5 = new ThreadTest();
		// // 将线程放入池中进行执行
		// pool.execute(t1);
		// pool.execute(t2);
		// pool.execute(t3);
		// pool.execute(t4);
		// pool.execute(t5);
		// // 关闭线程池
		// pool.shutdown();

		// 创建无线大小的线程池 支持定时以及周期执行任务的需求
//		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
//		executor.scheduleAtFixedRate(new Runnable() {
//			//每隔一段时间除法
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("============");
//			}
//		}, 1000, 5000, TimeUnit.MILLISECONDS);
//
//		executor.scheduleAtFixedRate(new Runnable() {
//
//			@Override
//			public void run() {
//				//每隔一段时间打印系统时间
//				// TODO Auto-generated method stub
//				System.out.println(System.nanoTime());
//			}
//		}, 1000, 2000, TimeUnit.MILLISECONDS);

		
//		ExecutorService pool = Executors.newCachedThreadPool();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			
			//线程池执行多线程的方法
			pool.execute(new Runnable() {
				@Override
				public void run() {
//					Singleton singleton = Singleton.getInstance();
					//上面的测试非线程安全的单例模式 会多次初始化
					 
					//饿汉式单例模式线程安全测试 只构造一次 成功
					Printer printer = Printer.getInstance();
					System.out.println(Thread.currentThread() + " is runing " );
				}
			});
		}
		
	}
}

class ThreadTest extends Thread {
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running...");
	}
}
