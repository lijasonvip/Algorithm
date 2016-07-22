package com.bo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {

	public static void main(String[] args) {
		String[] init = {"one", "Two", "Three", "Four"};
		
		List list = new ArrayList(Arrays.asList(init));
		list.add("five");
		System.out.println("before: " + list);
		
		//返回一个只包含指定对象的不可变列表
		list = Collections.singletonList("TP");
		System.out.println("after: " + list);
	}
}
