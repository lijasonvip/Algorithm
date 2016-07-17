package com.bo.leetcode;

//http://www.acmerblog.com/leetcode-solution-reverse-linked-list-ii-6322.html
public class ReverseBetween {
	
	//一趟就地逆转链表中m到n节点
	
	/**
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 *  注意很多边界检查
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n) return head;
        if(head == null || head.next == null) return head;

        //加一个头结点和尾节点 因为可能从头结点开始逆转
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode last = dummy;
        
        int cnt = 1;
        while(cnt < m){
            last = last.next;
            cnt++;
        }

        ListNode pre = null;
        ListNode next = last.next;
        ListNode tmpNext = null;
        
        while (cnt <= n){
            tmpNext = next.next;
            next.next = pre;
            pre = next;
            next = tmpNext;
            cnt++;
        }
        last.next.next = tmpNext;
        last.next = pre;
        return dummy.next;
    }
}
