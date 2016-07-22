package com.bo.leetcode;

public class DivideTwoIntegers29 {

	// https://discuss.leetcode.com/topic/15568/detailed-explained-8ms-c-solution/2
	//77%
	public static int divide(int dividend, int divisor) {
		//考虑溢出
		// 1. divisor == 0
		// 2. dividend = MIN and divisor = -1, abs(MIN) = INT_Max + 1
		// 也就是做减法的时候
		if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
            return Integer.MAX_VALUE;
		//异号则负
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        
        //当正数处理
        long dvd = Math.abs((long)dividend);
        long dvs = Math.abs((long)divisor);
        long res = 0;
        while (dvd >= dvs) { 
        	//减去移位后的除数即除了2倍除数
            long temp = dvs, multiple = 1;
            //这里判断移位后的大小避免多移
            while (dvd >= (temp << 1)) {
            	
                temp <<= 1;
                multiple <<= 1;
            }
            //最后再做一次减法 商加1
            dvd -= temp;
            res += multiple;
        }
        return sign == 1 ? (int)res : (int)-res; 
	}

	public static void main(String[] args) {
		System.out.println(divide(15, 3));
	}
}
