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

public class Tensor {
//5
	//here we construct three way tensor: patients x gene x medical
	
	public static void main(String... args){
		Tensor t = new Tensor();
		List<String> b = t.barcodes_genes("F://毕设/cancer subtype/data/integrated/barcodes.txt");
		List<String> g = t.barcodes_genes("F://毕设/cancer subtype/data/integrated/genes.txt");
		
		t.tensor(b, g);
	}
	
	//list all genes
	//list all barcodes
	public List<String> barcodes_genes(String filename){
		File file = new File(filename);
		
		List<String> allGeneValue = new LinkedList<String>();
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	allGeneValue.add(tempString);
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
	    return allGeneValue;
	}
	
	//gene expression value and methy value
	public Map values(String filename){
		File file = new File(filename);
		
		Map<String,String> map = new HashMap<String, String>();
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	String[] twins = tempString.split("\t");
	        	map.put(twins[0], twins[1]);
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
	    return map;
	}
	
	//save two matrix
	public void tensor(List<String> barcodes, List<String> genes){
		int count=1;
		for(String bar:barcodes){
			
			System.out.println(bar + " " + count + "/" + barcodes.size());
			count ++;
			List<String> allGeneValue = new LinkedList<String>();
			List<String> allMethyValue = new LinkedList<String>();
			String base = "F://毕设/cancer subtype/data/";
			Map values = values(base + "integrated/crossed/"+bar+".txt");
			for(String gene:genes){
				String[] vs = ((String)values.get(gene)).split("/");
				//do normalization for expression value
				String gExp = vs[0];
				String methy = vs[1];
				allGeneValue.add(exponential(gExp));
				allMethyValue.add(normalMethy(methy));
				
			}
			saveMatrix(allGeneValue, allMethyValue);
			
		}
	}
	
	public void saveMatrix(List<String> g, List<String> m){
		//save two files respectively
		String base = "F://毕设/cancer subtype/data/";
		StringBuffer sbgene = new StringBuffer();
		for(int i=0;i<g.size()-1;i++){
			sbgene.append(g.get(i)+"\t");
		}
		sbgene.append(g.get(g.size()-1));
		saveContent(sbgene, base + "matrix/barcode-geneexp-expo.txt");
		StringBuffer sbmy = new StringBuffer();
		for(int j=0;j<m.size()-1;j++){
			sbmy.append(m.get(j)+"\t");
		}
		sbmy.append(m.get(m.size()-1));
		saveContent(sbmy, base + "matrix/barcode-methy.txt");
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
	
	//exponential calculation
	public String exponential(String in){
		if(in == "null" || in.equals("null")) return "0";
		else{
			double out = Math.exp(Double.parseDouble(in));
			return Double.toString(out);
		}
	}
	
	public String normalMethy(String in){
		if(in.equals("NA") || in == "NA"){
			return "0";
		}else
			return in;
	}
	
	//normalization to [0,1]
	public String normalization(String in){
		
		return null;
	}
}
