package com.bo.bookcode;

import java.util.List;

public class ListQuestion {

	// 链表加法 从左到右加 进位时进到右边一位

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode pa = l1, pb = l2, cur = dummy;
		int carry = 0;
		while (pa != null || pb != null) {
			int x = (pa != null) ? pa.val : 0;
			int y = (pb != null) ? pb.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
			if (pa.next != null) {
				pa = pa.next;
			}
			if (pb.next != null) {
				pb = pb.next;
			}
		}
		if (carry != 0) {
			cur.next = new ListNode(carry);
		}

		return dummy.next;
	}

	// 链表一遍就地反转

	public static ListNode reverse(ListNode head, int m, int n) {
		if (m < 1) {
			return new ListNode(-1);
		}

		ListNode dummy = new ListNode(-1);
		dummy.next = head;

		ListNode pre = dummy;
		for (int i = 0; i < m - 1; i++) {
			pre = pre.next;
		}

		ListNode cur = pre.next;
		ListNode curnext = cur.next;
		ListNode prenext = pre.next;
		for (int i = m; i < n; i++) {
			pre.next = curnext;
			cur.next = curnext.next;
			curnext.next = prenext;
			prenext = pre.next;
			curnext = cur.next;
		}

		return dummy.next;
	}

	public static void testRecerse() {
		ListNode head = construct(new int[] { 1, 2, 3, 4, 5, 6 });
		ListNode dummy = reverse(head, 1, 6);
		printList(dummy);
	}

	// 划分链表 使得小于某数的都在前面大于某数的都在后面

	public static ListNode partitioin(ListNode head, int x) {
		ListNode left_dummy = new ListNode(-1);
		ListNode right_dummy = new ListNode(-1);

		ListNode left = left_dummy, right = right_dummy;
		for (ListNode cur = head; cur != null; cur = cur.next) {
			if (cur.val < x) {
				left.next = cur;
				left = cur;
			} else {
				right.next = cur;
				right = cur;
			}
		}
		left.next = right_dummy.next;
		right.next = null;
		return left_dummy.next;
	}

	public static void testPartition() {
		ListNode head = construct(new int[] { 1, 4, 3, 2, 5, 2 });
		ListNode dummy = partitioin(head, 3);
		printList(dummy);
	}

	public static void main(String[] args) {
		// testRecerse();
		// testPartition();
//		testRemove();
		testR();
	}

	// 链表中删除重复 链表已经有序

	public static ListNode removeDuplicate(ListNode head) {
		ListNode pre = head;
		ListNode cur = pre.next;
		while (cur != null) {
			if (cur.val == pre.val) {
				pre.next = cur.next;
			}
			pre = pre.next;
			if (pre != null) {
				cur = pre.next;
			} else
				break;

		}
		return head;
	}

	public static ListNode removeAllDup(ListNode head) {
		return null;
	}

	// 旋转链表
	// 1-2-3-4-5-null k=2, 先找长度len 尾部5指向头 从前往后走len-k断开即可
	public static ListNode rotateRight(ListNode head, int k) {
		if (head == null || k == 0) {
			return head;
		}
		int len = 1;
		ListNode p = head;
		while (p.next != null) {
			len++;
			p = p.next;
		}

		k = len - k % len;
		p.next = head;
		for (int step = 0; step < k; step++) {
			p = p.next;
		}
		head = p.next;
		p.next = null;
		return head;
	}

	// 删除倒数第N个节点 快慢指针

	// 链表中成对交换数据
	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		for (ListNode prev = dummy, cur = prev.next, curnext = cur.next; curnext != null;) {
			prev.next = curnext;
			cur.next = curnext.next;
			curnext.next = cur;
			prev = cur;
			cur = cur.next;
			curnext = cur != null ? cur.next : null;
		}
		return dummy.next;
	}

	// 链表分组翻转
	public static ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null || k < 2) {
			return head;
		}

		ListNode nextgroup = head;
		for (int i = 0; i < k; i++) {
			if (nextgroup != null) {
				nextgroup = nextgroup.next;
			} else
				return head;
		}

		// nextgroup 是下一组的头
		// newnextgroup 是翻转后的组的头
		ListNode newnextgroup = reverseKGroup(nextgroup, k);
		ListNode prev = null, cur = head;
		while (cur != nextgroup) {
			ListNode next = cur.next;
			cur.next = prev != null ? prev : newnextgroup;
			prev = cur;
			cur = next;
		}
		return prev;

	}

	public static ListNode reverseKGroup2(ListNode head, int k) {
		if (head == null || head.next == null || k < 2)
			return head;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		for (ListNode prev = dummy, end = head; end != null; end = prev.next) {
			for (int i = 1; i < k && end != null; i++)
				end = end.next;
			if (end == null)
				break;
			prev = reverse(prev, prev.next, end);
		}
		return dummy.next;
	}

	public static ListNode reverse(ListNode prev, ListNode begin, ListNode end) {
		ListNode end_next = end.next;
		for (ListNode p = begin, cur = p.next, next = cur.next; cur != end_next; p = cur, cur = next, next = next != null
				? next.next : null) {
			cur.next = p;
		}
		begin.next = end_next;
		prev.next = end;
		return begin;
	}

	
	// 判断有没有环 两个快慢指针
	
	// 找环的入口  除了快慢指针外再设第三个慢指针 
	public static ListNode detectCycle(ListNode head){
		ListNode slow = head, fast = head;
		while(fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
			if (slow ==fast) {
				ListNode slow2 = head;
				while(slow2 != slow){
					slow = slow.next;
					slow2 = slow2.next;
				}
				return slow2;
			}
		}
		return null;
	}
	
	//重排链表 类似洗牌
	public static void reorderList(ListNode head){
		if (head == null || head.next == null) {
			return;
		}
		
		ListNode slow = head, fast = head, prev = null;
		//从中间断开
		while(fast != null && fast.next != null){
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null; //cut
		slow = reverse(slow);
		//merge two list
		ListNode cur = head;
		while(cur.next != null){
			ListNode temp = cur.next;
			cur.next = slow;
			slow = slow.next;
			cur.next.next =temp;
			cur = temp;
		}
		cur.next = slow;
	}
	
	public static ListNode reverse(ListNode head){
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy, cur = head, curnext = head.next;
		while(curnext != null){
			pre.next = curnext;
			cur.next = curnext.next;
			curnext = cur.next;
		}
		return dummy.next;
	}
	
	public static void testR(){
		ListNode head = construct(new int[]{1,2,3,4,5});
		printList(reverse(head));
	}
	
	public static void testRemove() {
		ListNode head = construct(new int[] { 1, 1, 2, 3, 3 });
		printList(removeDuplicate(head));
	}

	public static void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static ListNode construct(int[] arr) {
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		for (int i : arr) {
			cur.next = new ListNode(i);
			cur = cur.next;
		}
		return dummy.next;
	}
}

class ListNode {
	ListNode next;
	int val;

	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
		this.next = null;
	}
}
