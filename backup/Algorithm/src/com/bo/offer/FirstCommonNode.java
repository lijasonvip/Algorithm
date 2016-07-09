package com.bo.offer;

public class FirstCommonNode {

	/**
	 * @param l1
	 * @param l2
	 * @return
	 * first. get the lenght of each listnode respectively
	 * second. calculate the differ of two length
	 * thirst. let the long one move the differ lenth and then move forward together
	 * Becaue. common node means key and next attribute are the same, a Y-like listnode
	 */
	public static ListNode2 FindFirstCommonNode(ListNode2 l1, ListNode2 l2){
		int l1size = 0, l2size = 0;
		ListNode2 l1Node = l1;
		ListNode2 l2Node = l2;
		while(l1Node != null){
			l1Node = l1Node.next;
			l1size ++;
		}
		
		while(l2Node != null){
			l2Node = l2Node.next;
			l2size ++;
		}
		
		ListNode2 longer;
		ListNode2 shorter;
		if(l1size > l2size){
			longer = l1;
			shorter = l2;
		}else{
			longer = l2;
			shorter = l1;
		}
		int gap = Math.abs(l1size - l2size);
		while(longer != null){
			if(gap > 0){
				gap --;
				longer = longer.next;
			}else{
				if(longer == shorter){
					return longer;
				}
				else{
					longer = longer.next;
					shorter = shorter.next;
							
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		ListNode2 _1 = new ListNode2(1);
		ListNode2 _2 = new ListNode2(2);
		ListNode2 _3 = new ListNode2(3);
		ListNode2 _4 = new ListNode2(4);
		ListNode2 _5 = new ListNode2(5);
		ListNode2 _6 = new ListNode2(6);
		ListNode2 _7 = new ListNode2(7);
		
		_1.next = _2;
		_2.next = _3;
		_3.next = _6;
		_6.next = _7;
		_4.next = _5;
		_5.next = _6;
		
		ListNode2 rListNode2 = FindFirstCommonNode(_1, _4);
		System.out.println(rListNode2.key);
		
	}
}

class ListNode2{
	int key;
	ListNode2 next;
	
	public ListNode2() {
		// TODO Auto-generated constructor stub
	}
	
	public ListNode2(int key){
		this.key = key;
		this.next = null;
	}
}