package com.bo.nature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		String temp = "";
		String[] aStrings = {"ad","bc"};
		temp = aStrings[0];
		aStrings[0] = "hello";
		System.out.println(temp);
	}

	public List<String> ReadAllRowsByColumn(String filename, int column) {
		List<String> result = new ArrayList<>();
		File file = new File(filename);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				if (column != 99) {
					String[] cols = tempString.split("\t");
					result.add(cols[column]);
				} else {
					result.add(tempString);
				}
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return result;
	}
}
