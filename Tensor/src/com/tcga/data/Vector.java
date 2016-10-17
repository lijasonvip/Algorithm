package com.tcga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Vector {
//2
	//here we do
	//1.for a patient(barcode id),we statistic every checked genes which have also been mythelated.
	//2.generate a matrix file barcode.txt: gene\texpression value/mythelation value
	
	public static void main(String... args){
		Vector v = new Vector();
		String base = "F://毕设/cancer subtype/data/integrated/";
//		Map g = v.geneExp(base+"US82800149_251976011821_S01_GE2_105_Dec08.txt_lmean.out.logratio.gene.tcga_level3.data.txt");
//		Map m = v.methy(base + "jhu-usc.edu_BRCA.HumanMethylation27.4.lvl-3.TCGA-BH-A18V-01A-11D-A12E-05.txt");
		List<String[]> mans = v.Manifest();
		for(int i=1;i<mans.size()+1;i++){
			String[] man = mans.get(i-1);
			v.crossCheck(v.geneExp(base+man[1]), v.methy(base+man[2]), man[0]);
			System.out.println(man[0]+" saved!\t"+i+"/"+mans.size());
		}
	}
	
	public List<String[]> Manifest(){
		List<String[]> man = new ArrayList<String[]>();
		File file = new File("F://毕设/cancer subtype/data/integrated/manifest.txt");
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        		String[] mans = tempString.split("\t");
	        		man.add(mans);
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
	    return man;
	}
	
	//find expressed gene which was also mythelated
	public void crossCheck(Map a, Map b, String barcode){
		Iterator itgene = a.keySet().iterator();
		while(itgene.hasNext()){
			String key = (String) itgene.next();
			Iterator itmy = b.keySet().iterator();
			while(itmy.hasNext()){
				String keykey = (String)itmy.next();
				if((keykey.contains(key) && keykey.contains(";")) || keykey.equals(key)){
					saveCross(key,(String)a.get(key),(String)b.get(keykey),barcode);
					break;
				}
			}
			
			
		}
	}
	
	//save to barcode.txt with expression/mythe value
	public void saveCross(String key, String gene, String mythe, String barcode){
		try {
			FileWriter fw = new FileWriter("F://毕设/cancer subtype/data/integrated/crossed/"+barcode+".txt", true);
			fw.write(key + "\t" +gene+"/" + mythe);
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map geneExp(String filename){
		//read file and get gene and expression value
		File file = new File(filename);
		
		Map<String,String> map = new HashMap<String, String>();
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        while ((tempString = reader.readLine()) != null) {
	        	line ++;
	        	if(line > 3){
	        		String[] geneExp = tempString.split("\t");
	        		map.put(geneExp[0], geneExp[1]);
	        		
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
	    return map;
	}
	
	public Map methy(String filename){
		File file = new File(filename);
		
		Map<String,String> map = new HashMap<String, String>();
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        while ((tempString = reader.readLine()) != null) {
	        	line ++;
	        	if(line > 3){
	        		String[] geneMythe= tempString.split("\t");
	        		if(map.get(geneMythe[2]) == null || map.get(geneMythe[2]) == "NA"){
	        			map.put(geneMythe[2], geneMythe[1]);
	        		}
	        		
	        		
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
	    return map;
		
	}
	
	public void writeVector(){}
}
