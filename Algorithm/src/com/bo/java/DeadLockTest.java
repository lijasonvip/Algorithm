package com.bo.java;

public class DeadLockTest {

	public static void main(String[] args) {
		Resource res1 = new Resource();
		Resource res2 = new Resource();

		Worker1 w1 = new Worker1(res1, res2);
		Worker2 w2 = new Worker2(res1, res2);

		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w2);

		t1.start();
		t2.start();
	}

	public boolean say() {
		return false;
	}
}

// 刀叉的例子 或者 哲学家进餐拿筷子的问题

class Worker1 implements Runnable {

	private Resource res1, res2;

	public Worker1(Resource res1, Resource res2) {
		this.res1 = res1;
		this.res2 = res2;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (res1) {
				System.out.println(res1);
				synchronized (res2) {
					System.out.println(res2);
				}
			}
		}
	}

}

class Worker2 implements Runnable {

	private Resource res1, res2;

	public Worker2(Resource res1, Resource res2) {
		this.res1 = res1;
		this.res2 = res2;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (res2) {
				System.out.println(res2);
				synchronized (res1) {
					System.out.println(res1);
				}
			}
		}

	}

}

class Resource {

}
