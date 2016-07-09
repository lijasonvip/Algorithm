package com.bo.offer;

import javax.xml.soap.Node;
//http://blog.csdn.net/yunzhongguwu005/article/details/10350339
public class ReverseList {
	
	/**
	 * 反转链表 维护三个指针
	 *  还有一种办法 就是 从第二到第N个节点依次插入到第一个节点之前
	 */
	public static List Reverse(List head){
		List reverseHead = null;
		
		List node = head;
		List prev = null;
		while (node != null) {
			List next = node.next;
			if (next == null) {
				reverseHead = node;
			}
			node.next = prev;
			prev = node;
			node = next;
		}
		
		
		return reverseHead;
	}
	
	public static List Reverse2(List head){
		if (head == null || head.next == null) {
			return head;
		}
		
		List node = head;
		List next = head.next;
		while(next != null){
			//把next摘出来准备往头上插
			node.next = next.next;
			next.next = head;
			head = next;
			next = node.next;
		}
		return head;
	}
	//这个有意思
	public static List Reverse_recursive(List head){
		if(head == null || head.next == null)
			return head;
		else{
			List newhead = Reverse_recursive(head.next);
			//利用递归找到最后一个作为头返回
			//依次改next节点
			head.next.next = head;
			//比如原来5.next = 6
			//改之后 5.next.next = 5 即 6.next = 5
			//然后5.next = null 把5指向6的链接断开 
			//依次倒着来一遍即可
			head.next = null;
			return newhead;
		}
	}
	
	/**
	 * 递归合并
	 */
	public static List MergeList(List first, List second){
		if (first == null) {
			return second;
		}
		else if (second == null) {
			return first;
		}
		
		List merged = null;
		if(first.val <= second.val){
			merged = first;
			merged.next = MergeList(first.next, second);
		}else{
			merged = second;
			merged.next = MergeList(first, second.next);
		}
		return merged;
	}
	
	public static void main(String[] args) {
		int[] data = {1,2,3,4,5,6};
		List head = construct(data);
		printList(head);
		List tail = Reverse_recursive(head);
		printList(tail);
		
//		int[] first = {1,3,5, 7, 9};
//		int[] second = {2,4,6,8,10};
//		printList(MergeList(construct(first), construct(second)));
	}
	
	public static List construct(int[] data){
		List head = new List();
		if(data.length > 0)
			head = new List(data[0]);
		List node = head;
		for (int i = 1; i < data.length; i++) {
			List next = new List(data[i]);
			node.next = next;
			node = node.next;
		}
		return head;
	}
	
	public static void printList(List head){
		while(head != null){
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}
	
}

class List{
	int val;
	List next;
	
	public List(){}
	
	public List(int val){
		this.val = val;
	}
}