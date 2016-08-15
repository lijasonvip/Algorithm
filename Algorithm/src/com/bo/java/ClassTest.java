package com.bo.java;

//这是Java匿名内部类的用法。对于一个接口，他就是实现；对于类，他就是继承。
public class ClassTest {
    public static void main(String[] args) {
        Printable printable = new Printable() {
            public void print() {
                System.out.println(getClass());
            }
        };
        printable.print();
 
        Person person = new Person();
        person.sayHello();
 
        Person person2 = new Person() {
            public void sayHello() {
                System.out.println("你好");
            }
        };
        person2.sayHello();
    }
}
 
interface Printable {
    void print();
}
 
class Person {
    public void sayHello() {
        System.out.println("Hello");
    }
}