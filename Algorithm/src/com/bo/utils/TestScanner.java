package com.bo.utils;

import java.util.Scanner;

public class TestScanner {

	//get terminal input
	public String scanTerminal(){
		
		Scanner s = new Scanner(System.in);
		System.out.println("Please input a string: ");
		String line = s.nextLine();
		System.out.println(">>>"+line);
		return line;
	}
	
	public static void main(String[] args) {
		System.out.println('a' - 'A');
		String a = "heLLo";
		char[] b = a.toCharArray();
		char[] copy = a.toLowerCase().toCharArray();
		copy[2] = 'l';
		System.out.println(String.valueOf(copy));
	}
}
