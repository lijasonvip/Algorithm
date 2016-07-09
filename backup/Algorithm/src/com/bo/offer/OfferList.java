package com.bo.offer;

import java.awt.List;
import java.util.Stack;

public class OfferList {

	/**
	 * offer 26
	 * two method: use hashmap , clone a new node linked after original node then copy the sibling
	 */
	public static ComplexNode CloneList(ComplexNode original){
		if(original == null)
			return null;
		
		//first clone all node and lined them after original node respectively
		ComplexNode c = original;
		
		while(c != null){
			ComplexNode cn = new ComplexNode();
			cn.value = c.value;
			cn.next = c.next;
			cn.sibling = c.sibling;
			
			c.next = cn;
			c = cn.next;
		}
		//clone sibling
		ComplexNode d = c;
		while(d != null){
			ComplexNode cclone = d.next;
			if(d.sibling != null){
				cclone.sibling = c.sibling.next;
				
			}
			d = cclone.next;
		}
		//return node at odd position
		ComplexNode node = d;
		ComplexNode clonedHead = null;
		ComplexNode clonedNode = null;
		//first copy head
		if(node != null){
			clonedHead = node.next;
			clonedNode = node.next;
			node = node.next;
		}
		while(node != null){
			clonedNode.next = node.next;
			clonedNode = clonedNode.next;
			node = node.next;
		}
		
		return clonedHead;
	}
	/**
	 * @param n1
	 * @param n2
	 * @return
	 * merge two sorted list using recursion
	 */
	public static ListNode MergeSortedList(ListNode n1, ListNode n2){
		if(n1 == null)
			return n2;
		else if (n2 == null) {
			return n1;
		}
		ListNode merged = null;
		if(n1.value < n2.value){
			merged = n1;
			merged.next = MergeSortedList(n1.next, n2);
		}else{
			merged = n2;
			merged.next = MergeSortedList(n1, n2.next);
		}
		return merged;
		
	}
	
	/**
	 * @param n
	 * @return Reverse a list and return the head after reversing
	 */
	public static ListNode Reverse(ListNode n) {
		if (n == null)
			return null;
		ListNode reverseHead = null;
		ListNode before = null;
		ListNode cur = n;
		ListNode after = n;
		while (cur != null) {
			after = cur.next;
			if (after == null) {
				reverseHead = cur;
			}

			cur.next = before;
			before = cur;
			cur = after;
		}
		return reverseHead;
	}

	/**
	 * @param head
	 * @return
	 * http://blog.csdn.net/yunzhongguwu005/article/details/10350339
	 */
	public static ListNode Reverse_Recursive(ListNode head) {
		if(head == null || head.next == null)
			return head;
		else{
			ListNode newHead = Reverse_Recursive(head.next);
			head.next.next = head;
			head.next = null;
			return newHead;
		}
	}

	/**
	 * @param l
	 * @param k
	 *            print the k the node from the end watch out for null l and 0 k
	 *            and length < k sometimes set two pointers can solve list
	 *            problem better. like find middle one or test circle can let
	 *            second pointer walk twice faster than first pointer.
	 */
	public static ListNode KthBack(ListNode l, int k) {
		if (l == null || k < 1)
			return null;
		int i = 1;
		ListNode first = l;
		while (i < k) {
			if (first.next != null) {
				first = first.next;
				++i;
			} else {
				return null;
			}
		}
		while (first.next != null) {
			l = l.next;
			first = first.next;
		}
		return l;

	}

	/**
	 * print a list from backforward use stack to store all node then pop and
	 * print non recursive method
	 */
	public static void PrintBackforward(ListNode l) {
		Stack<ListNode> s = new Stack<ListNode>();
		if (l != null) {
			s.push(l);
		}
		while (l.next != null) {
			s.push(l.next);
			l = l.next;
		}
		while (!s.isEmpty()) {
			System.out.print(s.pop().value + " ");
		}
	}

	/**
	 * recursive method
	 */
	public static void PrintBack(ListNode l) {
		if (l != null) {
			if (l.next != null) {
				PrintBack(l.next);
			}
			System.out.print(l.value + " ");
		}
	}

	public static void Print(ListNode l) {
		if (l != null) {
			System.out.print(l.value + " ");
			Print(l.next);
		}
	}

	public static ListNode construct(int[] m) {
		if (m.length == 0)
			return null;
		ListNode head = new ListNode(m[0]);
		ListNode cur = head;
		if (m.length > 1) {
			for (int i = 1; i < m.length; i++) {
				ListNode t = new ListNode(m[i]);
				cur.next = t;
				cur = cur.next;
			}
			return head;
		} else
			return head;

	}

	public static void main(String[] args) {
		int[] l = { 3, 5, 7, 1, 9, 10, 12, 14, 15 };
		// int[] l = {3, 5};
		ListNode ln = construct(l);
		// PrintBack(ln);
		// System.out.println();
		// PrintBackforward(ln);
		// System.out.println();
		// ListNode lNode = KthBack(ln, 4);
		// System.out.println(lNode.value);
		int[] a = {1,3,5,7,9};
		int[] b = {2, 4, 6, 8, 10};
		ListNode n1 = construct(a);
		ListNode n2 = construct(b);
//		ListNode reverse = Reverse_Recursive(ln);
		ListNode merged = MergeSortedList(n1, n2);
		Print(merged);
	}
}

class ListNode {
	int value;
	ListNode next;

	public ListNode() {
	}

	public ListNode(int v) {
		this.value = v;
		this.next = null;
	}

}

class ComplexNode{
	int value;
	ComplexNode next;
	ComplexNode sibling;
	
	public ComplexNode(){}
	public ComplexNode(int value){
		this.value = value;
		this.next = null;
		this.sibling = null;
	}
}