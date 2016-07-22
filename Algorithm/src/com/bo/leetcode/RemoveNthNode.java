package com.bo.leetcode;


public class RemoveNthNode {

    /**
     * 删除第k个节点
     */
    public static ListNode removeNth(ListNode head, int n) {
        ListNode node = head;
        if(n < 1) return head;
        if(n == 1) return head.next;
        ListNode pre=node;
        while(n > 1 && node.next != null){
        	pre = node;
            node = node.next;
            n--;
        }
        if(n>1){
            return null;
        }else{
        	pre.next = pre.next.next;
            return head;
        }
    }
    
    /**
     * using dummy head
     */
    public static ListNode removeNthFromEnd(ListNode head, int n){
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	ListNode fast = dummy;
    	for(int i=0;i<n;i++){
    		fast = fast.next;
    	}
    	ListNode slow = dummy;
    	while(fast.next != null){
    		fast = fast.next;
    		slow = slow.next;
    	}
    	slow.next = slow.next.next;
    	return dummy.next;
    }
    
    //递归解法
    
    
    public static ListNode construct(int[] data){
    	ListNode head = new ListNode(data[0]);
    	ListNode node = head;
    	for (int i = 1; i < data.length; i++) {
    		node.next = new ListNode(data[i]);
    		node = node.next;
			 
		}
    	return head;
    }
    
    
    
    public static void main(String[] args) {
		ListNode node = construct(new int[]{1});
		removeNthFromEnd(node, 1);
		System.out.println();
	}
}
