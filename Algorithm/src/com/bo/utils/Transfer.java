package com.bo.utils;

import java.util.LinkedList;
import java.util.List;

public class Transfer {

	public String Transfer(String in){
		//RxCy to original
		if(in.startsWith("R") && in.length() > 3 && in.substring(2).contains("C")){
			
			int indexC = in.indexOf("C");
			
			String row = in.substring(1, indexC);
			String col = in.substring(indexC+1);
			
			return itos(col)+row;
			
		}
		else{
			char[] cols = in.toCharArray();
			int index = 0;
			for(int i=0;i<cols.length;i++){
				if(cols[i] < 'A'){
					index = i;
					break;
				}
			}
			return "R"+in.substring(index)+"C" + stoi(in.substring(0,index));
		}
	}
	
	//BB to 54
	public int stoi(String in){
		char[] l = in.toCharArray();
		int times = l.length;
		int sum=0;
		for(int i=0;i<times;i++){
			sum = sum + (l[i]-'A'+1)* (int)Math.pow(26, times-i-1);
		}
		return sum;
	}
	
	//54 to BB
	public String itos(String in){
		int data = Integer.parseInt(in);
	        List<String> r = new LinkedList<String>();
	        
	        while(data>0){
	        	int remain = data % 26;
	        	char c = (char)(remain + 'A'-1);
	        	String CCC = String.valueOf(c);
	        	r.add(CCC);
	        	data = data/26;
	        }
	        String rr="";
	        for(int i=r.size()-1;i>-1;i--){
	        	rr += r.get(i);
	        }
	        return rr;
	}
	
	public static void main(String... args){
		Transfer t = new Transfer();
		String a = t.Transfer("BB22");
		System.out.println(a);
	}
}
