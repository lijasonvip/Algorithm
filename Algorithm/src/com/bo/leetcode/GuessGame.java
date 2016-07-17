package com.bo.leetcode;

public class GuessGame {

	public static final int known = 6;
	
	public static int guess(int num){
		if (num == known) {
			return 0;
		}else {
			return (num > known ? -1 : 1);
		}
	}
	
	public static int GuessNumber(int n){
		int low = 1;
		int high = n;
		while(low <= high){
			int mid = (low + high) / 2;
			if(guess(mid) == 0){
				return mid;
			}else if (guess(mid) > 0) {
				low = mid + 1;
			}else {
				high = mid - 1;
			} 
		}
		//not found
		return low;
		
	}
	
	/**
	 * 下面这个版本比我上面的快 为什么
	 *  1.判断的时候我每次都要guess(mid) 使用变量存储后就可以少耗费时间了
	 *  2.mid的求法  改成下面的就更快，为什么呢
	 *  因为会溢出，当输入的数非常大的时候 (low + high) / 2 会溢出
	 */
	public static int GuessNumber3(int n){
		int start = 1, end = n;
	    while(start < end) {
	        int mid = start + (end - start)/2;
	        int result = guess(mid);
	        if(result == 0) {
	            return mid;
	        }
	        else if(result == -1) {
	            end = mid - 1;
	        }
	        else if(result == 1) {
	            start = mid + 1;
	        }
	    }
	    return start;
	}
	
	/**
	 * 猜了x但是不对的时候要付出x块钱 求要付多少钱才能保证猜对
	 */
	/**
	 * For each number x in range[i~j]
		we do: result_when_pick_x = x + max{DP([i~x-1]), DP([x+1, j])}
		--> // the max means whenever you choose a number, the feedback is always bad and therefore leads you to a worse branch.
			then we get DP([i~j]) = min{xi, ... ,xj}
		--> // this min makes sure that you are minimizing your cost.
	 */
	public static int getMoneyAmount(int n){
		int[][] table = new int[n+1][n+1];
		return DP(table, 1, n);
	}
	
	public static int DP(int[][] t, int s, int e){
		if(s >= e)
			return 0;
		if(t[s][e] != 0)
			return t[s][e];
		int res = Integer.MAX_VALUE;
		for(int x=s; x <= e; x++){
			int tmp = x + Math.max(DP(t, s, x-1), DP(t, x+1, e));
			res = Math.min(res, tmp);
		}
		t[s][e] = res;
		return res;
	}
	
	public static int GuessNumber2(int n){
		int low = 1;
		int high = n;
		while(low <= high){
			int mid = (low + high) / 2;
			switch (guess(mid)) {
			case 1:
				low = mid + 1;
				break;
			case -1:
				high = mid - 1;
				break;
			default:
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int m = getMoneyAmount(3);
		System.out.println(m);
	}
}
