package com.bo.java;

import java.util.ArrayList;
import java.util.Stack;

public class Buffer {

	public static void main(String[] args) {
		System.out.println(replaceSpace(new StringBuffer("hello java world")));
	}
	
	 public static String replaceSpace(StringBuffer str) {
	        int count = 0;
	    	for(int i=0;i<str.length();i++){
	            if(str.charAt(i) == ' ')
	                count ++;
	        }
	        StringBuffer result = new StringBuffer();
	        for(int i=str.length()-1;i>=0;i--){
	            if(str.charAt(i) != ' '){
	                result.insert(0, str.charAt(i));
	            }else{
	                result.insert(0,"%20");
	            }
	        }
	        return result.toString();
	    }
	 
	 public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
	        Stack<Integer> stack = new Stack<>();
	        ArrayList<Integer> res = new ArrayList<Integer>();
	        while(listNode != null){
	            stack.offer(listNode.val);
	            listNode = listNode.next;
	        }
	        while(!stack.isEmpty()){
	            res.add(stack.pop());
	        }
	        return res;
	    }
}
