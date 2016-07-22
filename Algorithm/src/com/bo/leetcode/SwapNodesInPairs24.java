package com.bo.leetcode;

public class SwapNodesInPairs24 {

	/**
	 * 回溯 amazing idea 6%
	 */
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode node = head.next;
		head.next = swapPairs(node.next);
		node.next = head;
		return node;
	}

	public static ListNode swapPairs2(ListNode head) {
		if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while(current.next != null && current.next.next != null){
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            current.next = second;
            current.next.next = first;
            current = current.next.next;
        }
        return dummy.next;
	}
	
	public static void main(String[] args) {
		ListNode listNode = RemoveNthNode.construct(new int[]{1,2});
		swapPairs2(listNode);
	}
}
