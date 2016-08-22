package com.bo.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTest {
	public static void main(String[] args) throws Exception {
//		SerializePerson();
		Person2 p = DeSerilizePerson();
		System.out.println(p.getName());
	}

	private static void SerializePerson() throws FileNotFoundException, IOException{
		Person2 person = new Person2();
		person.setName("xuhe");
		person.setAge(25);
		person.setSex("男");
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("E://Person.txt")));
		oo.writeObject(person);
		System.out.println("Person对象序列和成功");
		oo.close();
	}
	
	private static Person2 DeSerilizePerson() throws Exception, IOException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E://Person.txt")));
		Person2 person = (Person2) ois.readObject();
		System.out.println("Person对象反序列化成功");
		return person;
	}
}

class Person2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private int age;
	private String name;
	private String sex;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
