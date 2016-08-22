package com.bo.designpattern;

import java.util.HashMap;
import java.util.Map;
//http://blog.csdn.net/itachi85/article/details/50510124
public class SingletonAll {

}

// 懒汉 线程不安全
class Single_LazyLoad{
	private static Single_LazyLoad instance;
	
	private Single_LazyLoad(){
		System.out.println("construction");
	}
	
	public static Single_LazyLoad getInstance(){
		if (instance == null) {
			instance = new Single_LazyLoad();
		}
		return instance;
	}
}

//懒汉 线程安全  同步方法 效率很低
class Single_Safe{
	private static Single_Safe instance;
	private Single_Safe(){
		System.out.println("construction");
	}
	public static synchronized Single_Safe getInstance(){
		if (instance == null) {
			instance = new Single_Safe();
		}
		return instance;
	}
}

//饿汉
//通过类加载方式避免了多线程 但是类装载就实例化常驻内存
class Single_Hungry{
	private static Single_Hungry instance = new Single_Hungry();
	private Single_Hungry(){
		System.out.println("construction");
	}
	
	public static Single_Hungry getInstance(){
		return instance;
	}
}

//饿汉 变种
// 静态代码块 类初始化是实例化
class Single_Hungry_B{
	private static Single_Hungry_B instance = null;
	
	static{
		instance = new Single_Hungry_B();
	}
	private Single_Hungry_B(){
		System.out.println("construction");
	}
	
	public static Single_Hungry_B getInstance(){
		return instance;
	}
}

//双重检验锁
//两次判空 第一次是为了不必要的同步 第二次是singleton等于null才创建实例
class Single_DL{
	private volatile static Single_DL instance;
	private Single_DL(){
		System.out.println("construction");
	}
	
	public static Single_DL getInstance(){
		if (instance == null) {
			synchronized (Single_DL.class) {
				if (instance == null) {
					instance = new Single_DL();
				}
			}
		}
		return instance;
	}
}

//静态内部类
//第一次加载类是不会初始化instance，第一次调用getInstance方法是虚拟机加载SingletonHolder并初始化instance，
class Single_Inner{
	private Single_Inner(){
		System.out.println("consruction");
	}
	
	public static Single_Inner getInstance(){
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder{
		private static final Single_Inner instance = new Single_Inner();
	}
}


//枚举


//使用容器实现单例模式
class SingletonManager{
	private static Map<String, Object> objMap = new HashMap<>();
	private SingletonManager(){}
	public static void registerService(String key, Object instance){
		if (!objMap.containsKey(key)) {
			objMap.put(key, instance);
		}
	}
	public static Object getService(String key){
		return objMap.get(key);
	}
}
