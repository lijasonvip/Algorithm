package com.tcga.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Patients {
//1
	//here we do these things
	//1.for BIC (breast cancer), extract all common patients who have checked both gene expression and mythelation
	//2.copy two files for one patients to integrated directory
	//3.generate a manifest file which is composed of: barcode\tgeneexpression\tmythelation
	
	public static void main(String... args){
		Patients p = new Patients();
		List<String[]> cross = p.Crossed("F://毕设/cancer subtype/data/expression-genes/expression-genes-BIC-UNC-AligentG4502-07/file_manifest.txt", 
				"F://毕设/cancer subtype/data/methylation/methylation-BIC-JHUUSC-HumanMethylation/file_manifest.txt");
		
		for(int i=0;i<cross.size();i++){
			String[] temp = cross.get(i);
			String[] methys = temp[1].split("\\.");
			String barcode = methys[5];
			
			p.saveFileList(barcode, temp[0], temp[1]);
			
			//
			String geneBase = "F://毕设//cancer subtype//data//expression-genes//expression-genes-BIC-UNC-AligentG4502-07//Expression-Genes//UNC__AgilentG4502A_07_3//Level_3/";
			String methyBase = "F://毕设/cancer subtype/data/methylation/methylation-BIC-JHUUSC-HumanMethylation/DNA_Methylation/JHU_USC__HumanMethylation27/Level_3/";
			p.copyFile(geneBase+ temp[0], "F://毕设/cancer subtype/data/integrated/"+temp[0], false);
			p.copyFile(methyBase+temp[1], "F://毕设/cancer subtype/data/integrated/"+temp[1], false);
			
			System.out.println(temp[0] + " " + temp[1]);
		}
		//p.copyFile("F://毕设/cancer subtype/A Unique Breast Cancer Subtype.pdf", "F://毕设/cancer subtype/data/integrated/A Unique Breast Cancer Subtype.pdf", false);
//		p.saveFileList("hello", "test", "test244");
	}
	
	public void saveFileList(String barcode, String gene, String methy){
		try {
			FileWriter fw = new FileWriter("F://毕设/cancer subtype/data/integrated/manifest.txt", true);
			fw.write(barcode+"\t" + gene + "\t" + methy);
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//"F://毕设/cancer subtype/data/
	public boolean copyFile(String from, String to, boolean overlay){
		File fromFile = new File(from);
		
		if(!fromFile.exists()){
			System.out.println("source file not exist");
			return false;
		}else if(!fromFile.isFile()){
			System.out.println("source file is not a file");
			return false;
		}
		
		File toFile = new File(to);
		if(toFile.exists()){
			if(overlay){
				new File(to).delete();
			}
		}else{
			if(!toFile.getParentFile().exists()){
				if(!toFile.getParentFile().mkdirs()){
					return false;
				}
			}
		}
		
		int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
            in = new FileInputStream(fromFile);  
            out = new FileOutputStream(toFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {  
            return false;  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	}
	
	public List<String> crossedBetweenList(List<String> s1, List<String> s2){
		List<String> result = new ArrayList<String>();
		
		for(String s:s1){
			if(s2.contains(s)){
				result.add(s);
			}
		}
		System.out.println(s1.size()+"\n" + s2.size()+"\n" + "crossed:\t" +result.size());
		return result;
	}
	
	public List<String[]> Crossed(String file1, String file2){
		List<String[]> result = new ArrayList<String[]>();
		
		List<String> r1 = getParticipants(file1);
		List<String> r2 = getParticipants(file2);
		
		
		for(String temp:r1){
			String[] genemythy = new String[2];
			String s1 = temp.split(",")[0];
			for(String temp2:r2){
				String s2 = temp2.split(",")[0];
				if(s1 != null && s2 != null && s1.contains(s2)){
					genemythy[0] = temp.split(",")[1];
					genemythy[1] = temp2.split(",")[1];
					result.add(genemythy);
				}
			}
			
			
		}
		
		System.out.println(file1 + " \n" + r1.size() + "\n"+file2 + " \n"+r2.size() + "\ncrossed:\t" + result.size());
		return result;
	}
	
	public List<String> getParticipants(String filename){
		List<String> result = new ArrayList<String>();
		
		File file = new File(filename);
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        while ((tempString = reader.readLine()) != null) {
	        	line ++;
	        	if(line > 4){
	        		String[] barcode = tempString.split("\t");
	        		String bar = barcode[4];
	        		String[] patients = bar.split("-");
	        		String patient = patients[2];
	        		result.add(bar+","+barcode[6]);
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
