package com.bo.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigValueTest {

	//java 中有BigInteger, BigDecimal 可以用来处理大数
	
	public static void main(String[] args) {
		int a = 3;
		String s = "12345";
		//String 初始化时要用new的  不能用valueOf
		BigInteger ba = BigInteger.valueOf(a);
//		BigInteger bs = BigInteger.valueOf(s);
	
//		BigDecimal bs = BigDecimal.valueOf("123");
	}
	
	public String multiply(String num1, String num2) {
	    BigInteger temp1 = new BigInteger(num1);
	    BigInteger temp2 = new BigInteger(num2);
	    BigInteger result = temp1.multiply(temp2);
	    
	    return result.toString();
	      
	   }
}
