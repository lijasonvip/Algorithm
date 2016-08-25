package com.bo.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Compare {

	//测试Comparable 和 Comparator
	public static void main(String[] args) {

		Set<Student> set = new TreeSet<>();
		set.add(new Student("Li", 22));
		set.add(new Student("Wang", 55));
		set.add(new Student("Xu", 2));
		
		for(Student s:set){
			System.out.println(s);
		}
		
		List<User> list = new ArrayList<>();
		list.add(new User("A", 33));
		list.add(new User("B", 32));
		list.add(new User("C", 60));
		list.add(new User("D", 22));
		list.add(new User("E", 15));
		
		Collections.sort(list, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				//return  o1.getName().compareTo(o2.getName());
				return o1.getAge() - o2.getAge();
			}
		});
		
		for(User u:list){
			System.out.println(u);
		}
	}
	
}

class Student implements Comparable<Student>{
	private String name;
	private int age;
	
	public Student(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Student name = " + name + " age = " + age;
	}
	
	@Override
	public int compareTo(Student o) {
		return this.age - o.age;
	}
}

class User{
	private String name;
	private int age;
	
	public User(String name, int age){
		this.name = name;
		this.age = age;
	}

	public String toString() {
		return "Student name = " + name + " age = " + age;
	}

	public String getName() {
		return name;
	}


	public int getAge() {
		return age;
	}

}
