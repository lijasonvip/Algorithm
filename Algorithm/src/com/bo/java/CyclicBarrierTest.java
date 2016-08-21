package com.bo.java;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//http://www.cnblogs.com/dolphin0520/p/3920397.html
public class CyclicBarrierTest {

	// 通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用
	public static void main(String[] args) {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N);
		for (int i = 0; i < N; i++)
			new Writer(barrier).start();
		
		//写数据完了还有别的操作可以使用Runnable
		
		CyclicBarrier barrier2 = new CyclicBarrier(N, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("当前线程：" + Thread.currentThread().getName());
			}
		});
	}

	static class Writer extends Thread {
		private CyclicBarrier cyclicBarrier;

		public Writer(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
			try {
				Thread.sleep(5000); // 以睡眠来模拟写入数据操作
				System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("所有线程写入完毕，继续处理其他任务...");
		}
	}
}
