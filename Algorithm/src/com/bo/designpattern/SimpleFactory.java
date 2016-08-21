package com.bo.designpattern;


public class SimpleFactory {

	
}

//抽象产品
interface Car{
	public void showProduct();
}

//具体产品
class BMWn implements Car{
	public void showProduct(){
		System.out.println("BMW");
	}
}

class Audi implements Car{
	public void showProduct() {
		System.out.println("Audi");	
	}
}

//工厂类
class CarFactory{
	//造出具体的产品
	public static Car produce(String str){
		if (str.equals("Audi")) {
			return new Audi();
		}else if(str.equals("BMW")) {
			return new BMWn();
		}
		return null;
	}
}

//用户
// 对public的Factory 就没有bug了 这里为了理解
class Client{
	public static void main(String[] args) {
//		String name = "BMW";
//		Car car = Factory.produce(name);
//		car.showProduct();
	}
}


