package com.tensor.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tensor {

	public static void main(String... args){
		Tensor t = new Tensor();
		
		List<String> firstArr = t.readFileByLines("F://毕设/张量分解参考/data/word title.txt");
		List<String> secondArr = t.readFileByLines("F://毕设/张量分解参考/data/title author.txt");
		
		int[][] first = t.toArray(firstArr);
		int[][] second = t.toArray(secondArr);
		
		int tensor[][][] = t.toTensor(first, second);
		
		t.printTensor(tensor);
	}
	
	
	public void printTensor(int[][][] tensor){
		for(int k=1;k<tensor[0][0].length;k++){
			System.out.println("=========="+k+"th:"+"=========");
			for(int i=1;i<tensor.length;i++){
				for(int j=1;j<tensor[0].length;j++){
					System.out.print(tensor[i][j][k] + "\t");
				}
				System.out.println();
			}
			
		}
	}
	
	public int[][][] toTensor(int[][] first, int[][] second){
		int[][][] tensor = new int[first.length][first[0].length][second[0].length];
		
		for(int i=1;i<first.length;i++){
			for(int j=1;j<first[0].length;j++){
				if(first[i][j] != 0){
					for(int k=1;k<second[0].length;k++){
						if(second[j][k] != 0){
							tensor[i][j][k] = first[i][j];
							continue;
						}
					}
				}
			}
		}
		
		
		return tensor;
	}
	
	public int[][] toArray(List<String> in){
		
		int imax = 0, jmax = 0;
		for(String s:in){
			String[] row = s.split(",");
			int itemp = Integer.parseInt(row[0]);
			int jtemp = Integer.parseInt(row[1]);
			
			if(itemp > imax)
				imax = itemp;
			if(jtemp > jmax)
				jmax = jtemp;
		}
		
		int[][] result = new int[imax+1][jmax+1];
		for(String t:in){
			String[] row = t.split(",");
			int i = Integer.parseInt(row[0]);
			int j = Integer.parseInt(row[1]);
			
			result[i][j] = Integer.parseInt(row[2]);
		}
		
		
		return result;
	}
	
	public List<String> readFileByLines(String fileName) {
		List<String> result = new ArrayList<String>();
		
		File file = new File(fileName);
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        while ((tempString = reader.readLine()) != null) {
	        	result.add(tempString);
	            System.out.println("line " + line + ": " + tempString);
	            line++;
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

