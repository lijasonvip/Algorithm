package com.bo.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LockList {
	 /** 
     * 创建一个列表，一个线程进行写入，一个线程读取 iterator 和 listIterator 方法返回的迭代器是快速失败的 
     */  
    public void readWrite() {  
        List<Integer> nums = new ArrayList<Integer>();  
          
        List<Integer> synNums = Collections.synchronizedList(nums);  
          
        //启动写入线程  
        new WriteListThread(synNums).start();  
          
        //启动删除线程  
        new DeleteListThread(synNums).start();  
    }  
  
    public static void main(String[] args) {  
        new LockList().readWrite();  
    }  
	
}
class WriteListThread extends Thread {  
    private List<Integer> nums;  
  
    public WriteListThread(List<Integer> nums) {  
        super("WriteListThread");  
        this.nums = nums;  
    }  
  
    // 不停写入元素1  
    public void run() {  
        while (true) {  
                    nums.add(new Random().nextInt(1000));  
                                                                                                                                                                System.out.println(Thread.currentThread().getName());  
          
        }  
    }  
}  
  
  
class DeleteListThread extends Thread {  
    private List<Integer> nums;  
  
    public DeleteListThread(List<Integer> nums) {  
        super("DeleteListThread");  
        this.nums = nums;  
    }  
  
    // 删除第一个元素  
    public void run() {  
        while (true) {  
            try{  
                System.out.println(Thread.currentThread().getName()+":"+nums.remove(0));  
            }catch(Exception e){  
                continue ;  
            }  
          
        }  
    }  
} 