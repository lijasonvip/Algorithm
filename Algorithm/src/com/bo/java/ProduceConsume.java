package com.bo.java;

import java.util.LinkedList;

import javax.swing.text.html.CSS;

public class ProduceConsume {
	//test
	public static void main(String[] args) {
		Storage storage = new Storage();
		//生产者对象
		PProducer p1 = new PProducer(storage);
		PProducer p2 = new PProducer(storage);
		PProducer p3 = new PProducer(storage);
		PProducer p4 = new PProducer(storage);
		PProducer p5 = new PProducer(storage);
		PProducer p6 = new PProducer(storage);
		PProducer p7 = new PProducer(storage);
		
		//消费者对象
		CConsumer c1 = new CConsumer(storage);
		CConsumer c2 = new CConsumer(storage);
		CConsumer c3 = new CConsumer(storage);
		
		//设置生产数量
		p1.setNum(10);
		p2.setNum(10);
		p3.setNum(10);
		p4.setNum(10);
		p5.setNum(10);
		p6.setNum(10);
		p7.setNum(80);
		
		//消费者数量
		c1.setNum(50);
		c1.setNum(20);
		c1.setNum(30);
		
		//启动线程
		c1.start();
		c2.start();
		c3.start();
		
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		p6.start();
		p7.start();
	}
}


class PProducer extends Thread {
	// 每次生产产品数量
	private int num;

	// 放置的仓库
	private Storage storage;

	// 设置仓库
	public PProducer(Storage storage) {
		this.storage = storage;
	}

	// run
	public void run() {
		produce(num);
	}

	// 调用仓库的生成函数
	public void produce(int num) {
		storage.produce(num);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

}

class CConsumer extends Thread{
	private int num;
	private Storage storage;
	public CConsumer(Storage storage){
		this.storage = storage;
	}
	
	public void run(){
		consume(num);
	}
	
	public void consume(int num){
		storage.consume(num);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	
}

class Storage {
	// 最大库存
	private final int MAX_SIZE = 100;

	private LinkedList<Object> list = new LinkedList<Object>();

	// 生产num个产品
	public void produce(int num) {
		// 同步代码块
		synchronized (list) {
			// 如果仓库剩余容量不足
			while (list.size() + num > MAX_SIZE) {
				System.out.println("要生产的数量： " + num + "\t库存了" + list.size() + "\t暂时不能执行");
				try {
					// 条件不满足 阻塞生产
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 生产条件满足
			for (int i = 0; i < num; i++) {
				list.add(new Object());
			}
			System.out.println("已经生产产品数：" + num + "\t现在容量为" + list.size());
			// 通知所有等待的线程重新运行
			list.notifyAll();
		}
	}

	// 消费num个产品
	public void consume(int num) {
		// 同步代码块
		synchronized (list) {
			// 如果仓库存储不够num
			while (list.size() < num) {
				System.out.println("要消费产品数量：" + num + "\t库存量" + list.size() + "\t暂时不能执行");
				try {
					// 等待
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 条件满足可以消费
			for (int i = 0; i < num; i++) {
				list.remove();
			}

			System.out.println("已经消费产品数：" + num + "\t仓库现在容量：" + list.size());
			list.notifyAll();
		}
	}

	public LinkedList<Object> getList() {
		return list;
	}

	public void setList(LinkedList<Object> list) {
		this.list = list;
	}

	public int getMax_Size() {
		return MAX_SIZE;
	}
}
