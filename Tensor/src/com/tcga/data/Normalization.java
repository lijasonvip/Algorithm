package com.tcga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Normalization {
//befor Tensor.java
//change the stratege for preprocess data
	public static void main(String... args){
		Normalization n  = new Normalization();
		String base = "F://毕设/cancer subtype/data/";
		
		n.normalGene("matrix/barcode-geneexp-nothing.txt");
		n.normalMethy("matrix/barcode-methy.txt");
		
		//n.statistic(base + "matrix/barcode-geneexp-nothing.txt");
		//n.statistic(base + "matrix/barcode-methy.txt");
	}
	
	//
	public void normalMethy(String filename){
		File file = new File(filename);
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        
	        while ((tempString = reader.readLine()) != null) {
	        	List<String> arow = new LinkedList<String>();
	        	String[] row = tempString.split("\t");
	        	for(int i=0;i<row.length;i++){
	        		String temp = methyEnrich(row[i],"0.22526217737200738");
	        		arow.add(temp);
	        	}
	        	saveRow(arow,"matrix/barcode-methy-mean.txt");
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
	}
	
	//normalization
	public void normalGene(String filename){
		File file = new File(filename);
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        
	        while ((tempString = reader.readLine()) != null) {
	        	List<String> arow = new LinkedList<String>();
	        	String[] row = tempString.split("\t");
	        	for(int i=0;i<row.length;i++){
	        		String temp = different(row[i]);
	        		arow.add(temp);
	        	}
	        	saveRow(arow,"matrix/barcode-geneexp-nothing-0520.txt");
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
	}
	
	public void saveRow(List<String> g, String filename){
		String base = "F://毕设/cancer subtype/data/";
		StringBuffer sbgene = new StringBuffer();
		for(int i=0;i<g.size()-1;i++){
			sbgene.append(g.get(i)+"\t");
		}
		sbgene.append(g.get(g.size()-1));
		//saveContent(sbgene, base + "matrix/barcode-geneexp-nothing-0520.txt");
		saveContent(sbgene, base + filename);
	}
	
	//save 
	public void saveContent(StringBuffer s, String filename){
			String c = s.toString();
			try {
				FileWriter fw = new FileWriter(filename, true);
				fw.write(c);
				fw.write("\n");
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//gene different expression
	public String different(String in){
		if(in == "null" || in.equals("null")){
			return "0";
		}
		else if(Double.parseDouble(in) < 0.5 || Double.parseDouble(in) > 2){
			return "1";
		}
		else
			return "0";
	}
	
	//methylation mean value is 0.22526217737200738
	//methylation enrichment
	public String methyEnrich(String in, String mean){
		if(in == "NA" || in.equals("NA")){
			return "0";
		}
		else if(Double.parseDouble(in) > Double.parseDouble(mean)){
			return "1";
		}
		else
			return "0";
	}
	
	//statistic mean, max, min, for input file.
	public void statistic(String filename){
		File file = new File(filename);
		
		Map<String,String> map = new HashMap<String, String>();
		double min = 0;
		double max = 0;
		double mean = 0;
		double sum = 0;
		int count = 0;
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	String[] row = tempString.split("\t");
	        	for(int i=0;i<row.length;i++){
	        		if(compare(min, Double.parseDouble(row[i])) > 0){
	        			min = Double.parseDouble(row[i]);
	        		}
	        		if(compare(max, Double.parseDouble(row[i])) < 0){
	        			max = Double.parseDouble(row[i]);
	        		}
	        		sum += Double.parseDouble(row[i]);
	        		count ++;
	        	}
	        }
	        System.out.println(count);
	        mean = sum / (double)count;
	        saveStatistic(mean, min, max,filename);
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
	}
	
	//compare one < two, return <0. else >0 or = 0
	public int compare(double one, double two){
		
		if(one < two){
			return -1;
		}
		else if (one > two){
			return 1;
		}else{
			return 0;
		}
	}
	
	//save statistic value
	public void saveStatistic(double mean, double min, double max,String filename){
		try {
			FileWriter fw = new FileWriter("F://毕设/cancer subtype/data/matrix/statistic.txt", true);
			fw.write("filename");
			fw.write("\n");
			fw.write(Double.toString(mean) + "\t" + Double.toString(min) + "\t" + Double.toString(max));
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
