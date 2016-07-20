package com.bo.leetcode;

public class PalindromeNumber9 {

	//判断一个数是不是回文数
	//76.9%
	public static boolean isPalindrome(int x){
		if (x < 0 || (x != 0 && x % 10 == 0)) {
			return false;
		}
		int reverse = 0;
		while(x > reverse){
			reverse = reverse * 10 + x % 10;
			x = x / 10;
		}
		return (x == reverse) || (x == reverse / 10);
	}
	
	
	/**
	 * 32.9%
	 */
	public static boolean isPalindrome2(int x) {
        //optimizations
		int v = 0;
        if(x<0) return false;
        if(x<10) return true;
        if(x%10==0) return false;
        if(x<100&&x%11==0) return true;
        if(x<1000&&((x/100)*10+x%10)%11==0) return true;

        //actual logic
        v=x%10;
        x=x/10;
        while(x-v>0)
        {
                v=v*10+x%10;
                x/=10;
        }
        if(v>x){v/=10;}
        return v==x?true:false;
    }
}
