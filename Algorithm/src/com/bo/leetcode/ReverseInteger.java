package com.bo.leetcode;

public class ReverseInteger {

	/**
	 * 辗转
	 * 48%
	 */
	public static int reverse(int x){
		long result = 0;
		while(x != 0){
			result = result * 10 + x%10;
			if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
				return 0;
			}
			x = x / 10;
		}
		return (int)result;
	}
	
	public static int reverse2(int x)
	{
	    int result = 0;

	    while (x != 0)
	    {
	        int tail = x % 10;
	        int newResult = result * 10 + tail;
	        if ((newResult - tail) / 10 != result)
	        { return 0; }
	        result = newResult;
	        x = x / 10;
	    }

	    return result;
	}
	
	public static int reverse3(int x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            if (Integer.MAX_VALUE / 10 < res || (Integer.MIN_VALUE - x % 10) < res * 10) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        
        return sign * res;
    }
	
	public static int reverse4(int x) 
    {
        int result = 0;
        while(x != 0)
        {
            int newResult = result*10 + x%10;
            if((newResult - x%10)/10 != result)
                return 0; 
            x = x/10;
            result = newResult;
        }
        return result;
    }
	
	public static void main(String[] args) {
		System.out.println(reverse(-321));
	}
}
