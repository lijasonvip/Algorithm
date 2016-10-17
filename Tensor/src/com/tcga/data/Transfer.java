package com.tcga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transfer {
//4
	//save rows and cols as barcodes and genes
	public static void main(String... args){
		Transfer t = new Transfer();
		String base = "F://毕设/cancer subtype/data/integrated/";
		t.saveList(base+"barcodes.txt", t.barcodeAll(base+"manifest.txt"));
		t.saveList(base+"genes.txt", t.genesAll(base+"crossed/TCGA-A1-A0SD-01A-11D-A112-05.txt"));
	}
	
	public void saveList(String filename,List<String> content){
		for(String c:content){
			try {
				FileWriter fw = new FileWriter(filename, true);
				fw.write(c);
				fw.write("\n");
				fw.close();
				int count = 1;
				System.out.println("saving to ..." + filename + "\t" +count +"/" + content.size());
				count ++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//1.write out all barcodes to keep them in order
	public List<String> barcodeAll(String filename){
		File file = new File(filename);
		
		List<String> result = new ArrayList<String>();
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	//from manifest
	        	String[] three = tempString.split("\t");
	        	result.add(three[0]);
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
	//2.write out all genes to keep them in order
	public List<String> genesAll(String filename){
		File file = new File(filename);
		
		List<String> result = new ArrayList<String>();
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	//from one of those crossed files
	        	String[] gs = tempString.split("\t");
	        	result.add(gs[0]);
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
