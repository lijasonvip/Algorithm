package com.bo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionsDemo {

	public static void main(String[] args) {
		String[] init = { "one", "Two", "Three", "Four" };

		List list = new ArrayList(Arrays.asList(init));
		list.add("five");
		System.out.println("before: " + list);

		// 返回一个只包含指定对象的不可变列表
		list = Collections.singletonList("TP");
		System.out.println("after: " + list);

		Set<String> set = new HashSet<String>();

		// populate the set
		set.add("TP");
		set.add("IS");
		set.add("FOR");
		set.add("TECHIES");

		// create a synchronized set
		Set<String> synset = Collections.synchronizedSet(set);

		System.out.println("Synchronized set is :" + synset);
	}
}
