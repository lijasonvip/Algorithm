package com.bo.leetcode;

public class MergeTwoSortedLists21 {

	// 合并两个排序的链表
	/**
	 * 10%
	 */
	public static ListNode mergeTwoLists1(ListNode l1, ListNode l2){
		if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode head;
        if(l1.val < l2.val){
            head = l1;
            l1 = l1.next;
        }else{
            head = l2;
            l2 = l2.next;
        }
        ListNode node = head;        
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                node.next = l1;
                l1 = l1.next;
                node = node.next;
            }else{
                node.next = l2;
                node = node.next;
                l2 = l2.next;
            }
        }
        // 链表这里直接指向不空的点就可以了 不用while循环了
        if(l1 != null){
            node.next = l1;
        }
        if(l2 != null){
            node.next = l2;
        }
        return head;
	}
	
	/**
	 * using dummy node
	 * 10%
	 */
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		ListNode ans = new ListNode(0);
		ListNode pre = ans;
		while(l1 != null && l2 != null){
			if (l1.val > l2.val) {
				pre.next = l2;
				l2 = l2.next;
			}else{
				pre.next = l1;
				l1 = l1.next;
			}
			pre = pre.next;
		}
		if(l1 != null)
			pre.next = l1;
		else {
			pre.next = l2;
		}
		return ans.next;
	}
	
	/**
	 * 递归
	 * amazing idea but 10%
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		}else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}
	
	
	public static void main(String[] args) {
		ListNode l1 = RemoveNthNode.construct(new int[]{1,3,5,7,9});
		ListNode l2 = RemoveNthNode.construct(new int[]{2,4,6,8,10});
		
		mergeTwoLists1(l1, l2);
	}
}
