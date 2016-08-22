package com.bo.designpattern;

public class Decorator {

	public static void main(String[] args) {
		SunWuKong sun = new Monkey();
		
		SunWuKong bird = new Bird(sun);
		SunWuKong fish = new Fish(sun);
		
		//second way
		SunWuKong fish2 = new Fish(new Bird(sun));
		fish.move();
	}
}

interface SunWuKong{
	public void move();
}

class Monkey implements SunWuKong{
	@Override
	public void move() {
		System.out.println("Monkey move");
	}
}

class Change implements SunWuKong{
	private SunWuKong sun;
	public Change(SunWuKong sun){
		this.sun = sun;
	}
	
	@Override
	public void move() {
		sun.move();
	}
}

class Fish extends Change{
	public Fish(SunWuKong sun) {
		super(sun);
	}
	@Override
	public void move() {
		System.out.println("Fish move");
	}
}

class Bird extends Change{
	public Bird(SunWuKong sun){
		super(sun);
	}
	
	@Override
	public void move() {
		System.out.println("Bird move");
	}
}
