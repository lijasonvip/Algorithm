package com.bo.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//java中Comparator的使用：对用户集合进行排序，先按照年龄大小排序，若年龄相等，则按照姓名排序
public class Compare implements Comparator<User> {
	public int compare(User u1, User u2){
		if (u1.equals(u2)) {
			return 0;
		}else if (u1.getAge() < u2.getAge()) {
			return 1;
		}else if (u1.getAge() == u2.getAge()) {
			int f = u1.getName().compareTo(u2.getName());
			if(f < 0){
				return -1;
			}
			return 0;
		}else
			return -1;
	}
	public static void main(String[] args) {
		List<User> users = new ArrayList<User>();
        users.add(new User(10,"a"));
        users.add(new User(11,"d"));
        users.add(new User(15,"s"));
        users.add(new User(6,"x"));
        users.add(new User(17,"a"));
        users.add(new User(17,"b"));
        users.add(new User(17,"c"));
        users.add(new User(17,"d"));
        Compare comparator = new Compare();
        //sort除了指定需要排序的数据外还要指定用户自定义的比较器，比较器实现了Comparator的compare函数
        Collections.sort(users, comparator);
         
        for (User u : users){
            System.out.println(u);
        }
        
      
	}
}

class User {
    private int age;
    private String name;
     
    public User() {
         
    }
    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
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
    @Override
    public String toString() {
        return "User [age=" + age + ", name=" + name + "]";
    }
     
 
}