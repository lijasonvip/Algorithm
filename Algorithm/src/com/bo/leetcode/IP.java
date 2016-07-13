package com.bo.leetcode;

public class IP {

	public static boolean isIP(String ip){
		//判断空
		boolean b = false;
		ip = ip.trim();
		if (ip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = ip.split("\\.");  
            if(Integer.parseInt(s[0])<255)  
                if(Integer.parseInt(s[1])<255)  
                    if(Integer.parseInt(s[2])<255)  
                        if(Integer.parseInt(s[3])<255)  
                            b = true;  
		}
		
		return b;
	}
	
	public static void main(String[] args) {
		String ip = " 111.111.111.111 ";
		System.out.println(isIP(ip));
	}
}
