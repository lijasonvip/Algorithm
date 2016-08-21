package com.bo.designpattern;

public class FactoryMethod {

}

interface Cars{
	public void showProduct();
}

class Bmws implements Cars{
	@Override
	public void showProduct() {
		System.out.println("A Bmw");
	}
}

class Benci implements Cars{
	@Override
	public void showProduct() {
		System.out.println("A Benci");
	}
}

interface Factory{
	public Cars produce();
}


