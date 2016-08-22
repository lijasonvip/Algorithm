package com.bo.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.InsufficientResourcesException;

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
// 解决办：每个线程都按照同样的顺序访问资源

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




class Account {
    private int money;
     
    public Account(int money) {
        this.money = money;
    }
     
    public void debit(int amount) {
        System.out.println("after debit " + amount + " " + this.money + " -> " + (this.money-amount));
        this.money -= amount;
    }
     
    public void credit(int amount) {
        System.out.println("after credit " + amount + " " + this.money + " -> " + (this.money+amount));
        this.money += amount;
    }
     
    public int get() {
        return this.money;
    }
}
 
class OrderLock {
    private static final Object tieLock = new Object();
     
    public void transferMoney(final Account fromAcct, final Account toAcct, final int amount) 
            throws InsufficientResourcesException {
        class Helper {
            public void transfer() throws InsufficientResourcesException {
                if (fromAcct.get() < amount) 
                    throw new InsufficientResourcesException();
                else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }
         
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);
         
        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
                 
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
     
    class MyThread implements Runnable {
        private Account fromAcct;
        private Account toAcct;
        private int amount;
         
        public MyThread(Account fromAcct, Account toAcct, int amount) {
            this.fromAcct = fromAcct;
            this.toAcct = toAcct;
            this.amount = amount;
        }
 
 
        @Override
        public void run() {
            try {
                transferMoney(this.fromAcct, this.toAcct, this.amount);
            } catch (InsufficientResourcesException e) {
                System.out.println("操作失败");
            }
        }
         
    }
     
    public static void main(String[] args) {
        Account fromAcct = new Account(100);
        Account toAcct = new Account(230);
        OrderLock orderLock = new OrderLock();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            if ((i & 1) == 0)
                threadPool.execute(orderLock.new MyThread(fromAcct, toAcct, 10));
            else threadPool.execute(orderLock.new MyThread(toAcct, fromAcct, 10));
        }
    }
}
