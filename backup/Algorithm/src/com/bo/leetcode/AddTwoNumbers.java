package com.bo.leetcode;

import java.util.List;

public class AddTwoNumbers {
	
	/**
	 * leetcode 2 
	 */
	public static ListNode addTwo(ListNode l1, ListNode l2) {
		if(l1 == null && l2 == null)
			return null;
		ListNode head = l1;
		ListNode nodel1 = head;
		ListNode nodel2 = l2;
		int takeover = 0;
		while(l1 != null && l2 != null){
			int temp = l1.val + l2.val + takeover;
			takeover = 0;
			if(temp > 9){
				takeover = takeover + 1;
				temp = temp - 10;
			}
			l1.val = temp;
			nodel1 = l1;
			nodel2 = l2;
			l1 = l1.next;
			l2 = l2.next;
		}
		if(nodel2.next != null)
			nodel1.next = nodel2.next;
		while(nodel1.next != null){
			int temp = nodel1.next.val + takeover;
			takeover=0;
			if(temp > 9){
				takeover = takeover + 1;
				temp = temp - 10;
			}
			nodel1.next.val = temp;
			nodel1 = nodel1.next;
		}
		
		if(takeover == 1){
			ListNode newnode = new ListNode(1);
			nodel1.next = newnode;
		}
		
		return head;
	}
	
	/**
	 * add a dummy head
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    ListNode dummyHead = new ListNode(0);
	    ListNode p = l1, q = l2, curr = dummyHead;
	    int carry = 0;
	    while (p != null || q != null) {
	        int x = (p != null) ? p.val : 0;
	        int y = (q != null) ? q.val : 0;
	        int sum = carry + x + y;
	        carry = sum / 10;
	        curr.next = new ListNode(sum % 10);
	        curr = curr.next;
	        if (p != null) p = p.next;
	        if (q != null) q = q.next;
	    }
	    if (carry > 0) {
	        curr.next = new ListNode(carry);
	    }
	    return dummyHead.next;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(5);
		l1.next = null;
		ListNode l2 = new ListNode(5);
		l2.next = null;
		ListNode sum = addTwo(l1, l2);
	}
}

class ListNode {
	int val;
	ListNode next;

	public ListNode() {}
	
	ListNode(int val) {
		this.val = val;
	}
}
