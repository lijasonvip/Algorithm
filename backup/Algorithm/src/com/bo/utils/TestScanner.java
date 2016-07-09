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
}
