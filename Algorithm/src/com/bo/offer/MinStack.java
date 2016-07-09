package com.bo.offer;

import java.util.LinkedList;
import java.util.Stack;

public class MinStack {
	
}

class StackWithMin{
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();
	
	public void push(int val){
		if (stack1.isEmpty()) {
			stack2.push(val);
		}else{
			
			if (val < min()) {
				stack2.push(val);
			}else{
				stack2.push(min());
			}
		}
		stack1.push(val);
	}
	
	public int pop(){
		stack2.pop();
		return stack1.pop();
	}
	
	public boolean isEmpty(){
		return stack1.isEmpty();
	}
	
	public int min(){
		return stack2.peek();
	}
	
	
}
