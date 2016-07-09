package com.bo.stack;

import java.util.Stack;

public class TwoStackQueue<T> {

	Stack<T> s1 = new Stack<T>();
	Stack<T> s2 = new Stack<T>();
	
	public void push(T e){
		s1.push(e);
	}
	
	public T pop(){
		if(!s2.isEmpty()){
			return s2.pop();
		}else if(!s1.isEmpty()){
			while(!s1.isEmpty()){
				s2.push(s1.pop());
			}
			return s2.pop();
		}else{
			return null;
		}
	}
	
	public boolean isEmpty(){
		if(s1.isEmpty() && s2.isEmpty()){
			return true;
		}else
			return false;
	}
}
