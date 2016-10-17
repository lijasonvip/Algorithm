package com.tcga.data;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String... args){
		Map<String, String> test = new HashMap<String, String>();
		test.put("a", "h1");
		test.put("b", "h2");
		
		System.out.println((String) test.get("c"));
		
		String s = "-4.704375/0.498196719946353";
		String[] a = s.split("/");
		
	}
}
