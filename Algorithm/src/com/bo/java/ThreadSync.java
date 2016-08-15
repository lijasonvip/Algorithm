package com.bo.java;

public class ThreadSync {

	public static void main(String[] args) {
		final Bank bank = new Bank();
		Thread tpush = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					bank.pushin(100);
					bank.check();
				}
			}
		});
		
		Thread twithdraw = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					if (bank.getCount() > 100) {
						bank.withdraw(100);
						bank.check();
					}
					
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					
			}
		});
		twithdraw.start();
		tpush.start();
	}
}

class Bank{
	private int count = 0;
	
	public synchronized void pushin(int money){
		count += money;
		System.out.println(System.currentTimeMillis() + " push in : " + money);
	}
	
	public synchronized void withdraw(int money){
		if (count < 0) {
			System.out.println("Nothing left.");
			return;
		}
		count -= money;
		System.out.println(System.currentTimeMillis() + " withdraw : " + money);
	}
	
	public void check(){
		System.out.println("left: " + count);
	}
	
	public int getCount(){
		return count;
	}
}