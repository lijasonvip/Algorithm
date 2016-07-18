package com.bo.leetcode;

//https://discuss.leetcode.com/topic/49771/java-simple-easy-understand-solution-with-explanation
public class SumOfTwoIntegers {
	/**
	 * 用位运算实现 只战胜了7.55% 太慢了
	 */
	public static int sum(int a, int b){
		int sum = a;
		while(b != 0){ 	//直到没有进位
			sum = a ^ b;  //求和不考虑进位
			b = (a & b) << 1; // 计算进位
			a = sum;
		}
		return sum;
	}
	
	public static int sum3(int a, int b){
		return b == 0 ? a : sum3(a^b, (a&b)<<1);
	}
	
	
	
	/**
	 * 另一种做法
	 */
	public static int sum2(int a, int b){
		if (a == 0) {
			return b;
		}
		if (b == 0) {
			return a;
		}
		int sum = 0;
		int mask = 1;
		int c = 0;
		while(mask != 0){
			int m1 = (a & mask) != 0 ? 1 : 0;
			int m2 = (b & mask) != 0 ? 1 : 0;
			
			if (m1 == 1 && m2 == 1) {
				if (c == 1) {
					sum |= mask;
				}
				c = 1;
			}else if ((m1 ^ m2) == 1) {
				if (c == 1) {
					c = 1;
				}else
					sum |= mask;
			}else {
				 if (c == 1)
	                {
	                    sum |= mask;
	                    c = 0;
	                }
			}
			mask <<= 1;
		}
		return sum;
	}
	
	//进而求减法
	public static int subtract(int a, int b){
		while(b != 0){
			int borrow = (~a) & b;		//只有0-1才需要借位，对a取反若出现1-1即需要借位 故用&来判断
			a = a ^ b;					//相同的作差后为0了 不同的为1 所以异或 借位的情况再考虑
			b = borrow << 1;
		}
		return a;
	}
	
	//递归减法
	public static int getSubtract(int a, int b) {
		return (b == 0) ? a : getSubtract(a ^ b, (~a & b) << 1);
	}
	
	public static void main(String[] args) {
		System.out.println(sum(-7, 9));
		System.out.println(~2);
	}
}
