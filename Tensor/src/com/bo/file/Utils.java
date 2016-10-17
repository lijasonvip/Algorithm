package com.bo.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

	
	// list all files under path
	public List<String> listFiles(String path){
		List<String> result = new  ArrayList<String>();
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
		File[] files = dir.listFiles();
		for(int i=0; i<files.length;i++){
			result.add(files[i].getName());
		}
		
		return result;
	}
	
	public void appendFile(String filename, String content){
		try {
			FileWriter fw = new FileWriter(filename, true);
			fw.write(content);
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map readMethy(String filename){
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
	
	public static void main(String... args){
		Utils utils = new Utils();
		List<String> list = utils.listFiles("/media/bo/000F8BA00000D206/Data/Methylation/BRCA.HumanMethylation450.Level_3");
		for (String s:list) {
			System.out.println(s);
		}
	}
}
