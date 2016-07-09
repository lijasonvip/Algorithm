package com.bo.offer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueue {
	
	public static boolean IsPopOrder(int[] push, int[] pop){
		if (push == null || pop== null || push.length<1 || pop.length < 1) {
			return false;
		}
		boolean ispoporder = false;
		int jpop = 0;
		Stack<Integer> pStack = new Stack<Integer>();
		for (int i = 0; i < pop.length; i++) {
			pStack.push(push[i]);
			while (!pStack.isEmpty()) {
				int top = pStack.peek();
				if(top == pop[jpop]){
					pStack.pop();
					jpop++;
					//after last time check, jpop move on one more step to pop.length not pop.length-1
				}else{
					break;
				}
			}
		}
		if(pStack.isEmpty() && jpop == pop.length)
			ispoporder = true;
		return ispoporder;
	}
	public static void main(String[] args) {
//		TwoQueueStack<Integer> s = new TwoQueueStack<Integer>();
//		s.push(1);
//		s.push(2);
//		s.push(3);
//		int i = s.pop();
//		int j = s.pop();
//		s.push(4);
//		System.out.println((int) s.pop());
		int[] push = {1,2,3,4,5};
		int[] pop = {4,3,5,1,2};
		System.out.println(IsPopOrder(push, pop));
	}
}

class TwoQueueStack<T> {
	Queue<T> q1 = new LinkedList<T>();
	Queue<T> q2 = new LinkedList<T>();

	public void push(T t) {
		if (q1.isEmpty() && q2.isEmpty()) {
			q1.offer(t);
		} else if (!q1.isEmpty()) {
			q1.offer(t);
		} else {
			q2.offer(t);
		}
	}

	public T pop() {
		if (!q1.isEmpty()) {
			T cur = q1.poll();
			while (!q1.isEmpty()) {
				q2.offer(cur);
				cur = q1.poll();
			}
			return cur;
		} else if (!q2.isEmpty()) {
			T cur = q2.poll();
			while (!q2.isEmpty()) {
				q1.offer(cur);
				cur = q2.poll();
			}
			return cur;
		} else
			return null;
	}

	public boolean isEmpty() {
		if (q1.isEmpty() && q2.isEmpty()) {
			return true;
		} else
			return false;
	}
}

// offer 21
// use a assist stack to store current min, push min or current top of min stack always
class StackWithMin {
	Stack<Integer> data = new Stack<Integer>();
	Stack<Integer> min = new Stack<Integer>();

	public void push(int t) {
		if (min.isEmpty()) {
			min.push(t);
		} else {
			int mint = min.peek();
			if (t < mint) {
				min.push(t);
			}else{
				min.push(mint);
			}
		}
		data.push(t);
	}
	
	public int pop() {
		//check isempty before
		int top = data.pop();
		min.pop();
		return top;
	}
	
	public int min(){
		//check isempty before
		int mint = min.peek();
		return mint;
	}
	
	public boolean isEmpty(){
		return data.isEmpty();
	}
}

class TwoStackQueue<T> {

	Stack<T> s1 = new Stack<T>();
	Stack<T> s2 = new Stack<T>();

	public void push(T e) {
		s1.push(e);
	}

	public T pop() {
		if (!s2.isEmpty()) {
			return s2.pop();
		} else if (!s1.isEmpty()) {
			while (!s1.isEmpty()) {
				s2.push(s1.pop());
			}
			return s2.pop();
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		if (s1.isEmpty() && s2.isEmpty()) {
			return true;
		} else
			return false;
	}
}
