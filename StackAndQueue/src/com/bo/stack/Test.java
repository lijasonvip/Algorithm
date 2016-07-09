package com.bo.stack;

public class Test {

	public static void main(String[] args) {
		TwoStackQueue<Integer> s = new TwoStackQueue<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.pop();
		s.pop();
		s.push(4);
		s.push(5);
		s.pop();
		
		while(!s.isEmpty()){
			System.out.println(s.pop());
		}
	}
}
