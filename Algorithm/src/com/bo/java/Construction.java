package com.bo.java;

public class Construction {

	public static void main(String[] args) {
		Sonn s = new Sonn();
	}
}

class Parent{  
	  
    {  
        System.out.println("parent中的初始化块");  
    }  
    static{  
        System.out.println("parent中static初始化块");  
    }  
      
    public Parent(){  
        System.out.println("parent构造方法");  
    }  
}  
  
class Sonn extends Parent{  
    {  
        System.out.println("son中的初始化块");  
    }  
      
    static{  
        System.out.println("son中的static初始化块");  
    }  
      
    public Sonn(){  
        System.out.println("son构造方法");  
    }  
      
}  