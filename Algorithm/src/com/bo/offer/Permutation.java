package com.bo.offer;

public class Permutation {

	//offer 28
	//permutation of a string
	/**
	 * @param str
	 * offer 28
	 */
	public static void Permutate(char[] str){
		if(str == null)
			return;
		Permutate(str,0);
	}
	
	public static void Permutate(char[] str, int begin){
		if(begin == str.length-1){
			System.out.println(str);
		}else{
			for(int i=begin;i<str.length;i++){
				char temp = str[i];
				str[i] = str[begin];
				str[begin] = temp;
				Permutate(str, begin+1);
				//change back
				temp = str[i];
				str[i] = str[begin];
				str[begin] = temp;
			}
		}
		
	}
	
	public static void main(String[] args) {
		char[] c = {'a','b','c'};
		Permutate(c);
	}
}
