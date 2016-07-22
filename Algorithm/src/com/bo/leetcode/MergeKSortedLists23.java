package com.bo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLists23 {

	// k个排序链表的合并
	public static ListNode mergeKLists1(ListNode[] lists) {
		ListNode h, res;
		List<ListNode> merged = new LinkedList<>();
		// 数组中所有链表节点摘下来放到新链表中
		for (int i = 0; i < lists.length; i++) {
			h = lists[i];
			while (h != null) {
				merged.add(h);
				h = h.next;
			}
		}
		if (merged.size() == 0) {
			return null;
		}
		Collections.sort(merged, new Comparator<ListNode>() {
			public int compare(ListNode l1, ListNode l2) {
				if (l1.val > l2.val)
					return 1;
				else if (l1.val < l2.val) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		res = h = merged.remove(0);
		while (!merged.isEmpty()) {
			h.next = merged.remove(0);
			h = h.next;
		}
		return res;
	}

	/**
	 * 优先队列
	 */
	public static ListNode mergeKLists2(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
			public int compare(ListNode l1, ListNode l2) {
				return Integer.compare(l1.val, l2.val);
			}
		});
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		for (ListNode node : lists)
			if (node != null)
				queue.add(node);

		// 抽出队列元素有next的话next加入队列，头摘出来链接成新链表
		while (!queue.isEmpty()) {
			tail.next = queue.poll();
			tail = tail.next;

			if (tail.next != null)
				queue.add(tail.next);
		}
		return dummy.next;
	}

	/**
	 * 递归
	 */
	public ListNode mergeKLists3(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		int k = lists.length;
		return split(lists, 0, k - 1);
	}

	/**
	 * 划分
	 */
	public ListNode split(ListNode[] lists, int left, int right) {
		if (left == right)
			return lists[left];
		int m = (left + right) / 2;
		return mergeSort(split(lists, left, m), split(lists, m + 1, right));
	}

	/**
	 * 合并两个有序链表
	 */
	public ListNode mergeSort(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = mergeSort(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeSort(l1, l2.next);
			return l2;
		}
	}

	public static void main(String[] args) {
		ListNode l1 = RemoveNthNode.construct(new int[] { 1, 5, 9 });
		ListNode l2 = RemoveNthNode.construct(new int[] { 2, 4, 8 });
		ListNode l3 = RemoveNthNode.construct(new int[] { 3, 6, 9 });
		ListNode[] lists = { l1, l2, l3 };
		mergeKLists2(lists);
	}

}
