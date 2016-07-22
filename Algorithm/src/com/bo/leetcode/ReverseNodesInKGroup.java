package com.bo.leetcode;

public class ReverseNodesInKGroup {

	// 链表上依次划分k元组 组内元素反转
	/**
	 * 递归 + 尾插
	 */
	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode curr = head;
		int count = 0;
		while (curr != null && count != k) { // find the k+1 node
			curr = curr.next;
			count++;
		}
		if (count == k) { // if k+1 node is found
			curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
			// head - head-pointer to direct part,
			// curr - head-pointer to reversed part;
			while (count-- > 0) { // reverse current k-group:
				ListNode tmp = head.next; // tmp - next head in direct part
				head.next = curr; // preappending "direct" head to the reversed
									// list
				curr = head; // move head of reversed part to a new node
				head = tmp; // move "direct" head to the next node in direct
							// part
			}
			head = curr;
		}
		return head;
	}
	
	/**
	 * 就地反转链表
	 */
	public static ListNode reverse(ListNode head){
		ListNode dummy = new ListNode(0);
		ListNode cur = head;
		while(cur != null){
			ListNode tmp = cur.next;
			cur.next = dummy.next;
			dummy.next = cur;
			cur = tmp;
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		ListNode listNode = RemoveNthNode.construct(new int[] { 1, 2, 3, 4, 5, 6 });
		reverse(listNode);
	}
}
