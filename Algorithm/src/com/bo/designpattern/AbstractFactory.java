package com.bo.designpattern;

public class AbstractFactory {

	//Client
	public static void main(String[] args) {
		AbstractFac sportFac = new SportFactory();
		AbstractFac businessFac = new BusinessFactory();
		
		Bmwwww bmw;
		bmw = sportFac.bmwwwwFactory();
		bmw.showProduct();
		
		bmw = businessFac.bmwwwwFactory();
		bmw.showProduct();
		
		System.out.println("-----------------");
		Audiiii audi;
		audi = sportFac.audiiiiFactory();
		audi.showProduct();
		
		audi = businessFac.audiiiiFactory();
		audi.showProduct();
	}
}

interface Bmwwww{
	public void showProduct();
}

class BmwwwwSport implements Bmwwww{
	@Override
	public void showProduct() {
		System.out.println("Bmw sport");
	}
}

class BmwwwwBusiness implements Bmwwww{
	@Override
	public void showProduct() {
		System.out.println("Bmw business");
	}
}

interface Audiiii{
	public void showProduct();
}

class AudiiiiSport implements Audiiii{
	@Override
	public void showProduct() {
		System.out.println("Audi Sport");
	}
}

class AudiiiiBusiness implements Audiiii{
	@Override
	public void showProduct() {
		System.out.println("Audi Business");
	}
}

interface AbstractFac{
	public Bmwwww bmwwwwFactory();
	public Audiiii audiiiiFactory();
}

class SportFactory implements AbstractFac{
	@Override
	public Bmwwww bmwwwwFactory() {
		return new BmwwwwSport();
	}
	
	@Override
	public Audiiii audiiiiFactory() {
		return new AudiiiiSport();
	}
}

class BusinessFactory implements AbstractFac{
	@Override
	public Bmwwww bmwwwwFactory() {
		return new BmwwwwBusiness();
	}
	
	@Override
	public Audiiii audiiiiFactory() {
		return new AudiiiiBusiness();
	}
}
