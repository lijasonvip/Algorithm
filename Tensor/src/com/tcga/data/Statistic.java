package com.tcga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistic {
//3
	//to construct the tensor of patients x geneExpression x mythelation
	//here we do some statistic thing
	
	public static void main(String... args){
		Statistic s = new Statistic();
		List<String> files = s.listFiles("F://毕设/cancer subtype/data/integrated/crossed/");
		List<String> differ = s.crossCheckAll(files);
	}
	
	//one iteration is enough because if no difference between all other files with first
	//of course there is no difference between them
	public List<String> crossCheckAll(List<String> files){
		List<String> result = new  ArrayList<String>();	
		for(int i=0; i< files.size()-1;i++){
			for(int j=i+1; j< files.size();j++){
				System.out.println("now checking "+ files.get(i) + " and " + files.get(j) + "\t" + i+"/"+files.size() + " " + j + "/" +files.size());
				List<String> d1 = crossCheck(listGenes(files.get(i)),listGenes(files.get(j)),files.get(i),files.get(j));
				if(d1.size() < 1){
					System.out.println("There is no diffrence between these two files");
				}else{
					for(String s:d1){
						System.out.println("\t" + s);
					}
				}
				result.addAll(d1);
			}
		}
		return result;
	}
	
	public List<String> listGenes(String filename){
		String base = "F://毕设/cancer subtype/data/integrated/crossed/";
		File file = new File(base + filename);
		
		List<String> result = new  ArrayList<String>();		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        		String[] genes= tempString.split("\t");
	        		String gene = genes[0];
	        		result.add(gene);
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
	
	public List<String> listFiles(String path){
		List<String> result = new  ArrayList<String>();
		
		File dir = new File(path);
		File[] files = dir.listFiles();
		for(int i=0; i<files.length;i++){
			result.add(files[i].getName());
		}
		
		return result;
	}
	
	public List<String> crossCheck(List<String> a, List<String> b, String first, String second){
		List<String> result = new  ArrayList<String>();
		
		List<String> l1 = firstMore(a, b, first);
		List<String> l2 = firstMore(b, a, second);
		
		result.addAll(l1);
		result.addAll(l2);
		
		return result;
	}
	
	public List<String> firstMore(List<String> a, List<String> b, String first){
		List<String> result = new ArrayList<String>();
		for(String p:a){
			if(b.contains(p)){
				continue;
			}
			else{
				result.add(first + " " + p);
				System.out.println(first + " added " + p);
			}
		}
		return result;
	}
}
