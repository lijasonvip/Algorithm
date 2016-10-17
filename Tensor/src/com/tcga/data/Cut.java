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

public class Cut {

	//cut a small data set from gene and methylation
	public static void main(String... args){
		Cut t = new Cut();
		List<String> b = t.barcodes_genes("F://毕设/cancer subtype/data/integrated/barcodes.txt");
		List<String> g = t.barcodes_genes("F://毕设/cancer subtype/data/integrated/genes.txt");
		
		t.tensor(200,b, g,100);
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
		public void tensor(int patients,List<String> barcodes, List<String> genes, int num){
			int count=1;
			for(int p=0;p<patients;p++){
				String bar = barcodes.get(p);
				System.out.println(bar + " " + count + "/" + barcodes.size());
				count ++;
				List<String> allGeneValue = new LinkedList<String>();
				List<String> allMethyValue = new LinkedList<String>();
				
				//no preprocessed list
				List<String> allGeneValueno = new LinkedList<String>();
				List<String> allMethyValueno = new LinkedList<String>();
				String base = "F://毕设/cancer subtype/data/";
				Map values = values(base + "integrated/crossed/"+bar+".txt");
				for(int i=0;i<num;i++){
					String gene = genes.get(i);
					String[] vs = ((String)values.get(gene)).split("/");
					//do normalization for expression value
					String gExp = vs[0];
					String methy = vs[1];
					
					
					allGeneValueno.add(exponentialno(gExp));
					allMethyValueno.add(normalMethyno(methy));
					
					allGeneValue.add(exponential(gExp));
					allMethyValue.add(normalMethy(methy));
					
				}
				
				saveMatrix(allGeneValueno, allMethyValue, "F://毕设/cancer subtype/data/matrix/compare/no");
				
				saveMatrix(allGeneValue, allMethyValue,"F://毕设/cancer subtype/data/matrix/compare");
				
			}
		}
		
		public void saveMatrix(List<String> g, List<String> m, String base){
			//save two files respectively
//			String base = "F://毕设/cancer subtype/data/";
			StringBuffer sbgene = new StringBuffer();
			for(int i=0;i<g.size()-1;i++){
				sbgene.append(g.get(i)+"\t");
			}
			sbgene.append(g.get(g.size()-1));
			saveContent(sbgene, base + "/barcode-geneexp.txt");
			StringBuffer sbmy = new StringBuffer();
			for(int j=0;j<m.size()-1;j++){
				sbmy.append(m.get(j)+"\t");
			}
			sbmy.append(m.get(m.size()-1));
			saveContent(sbmy, base + "/barcode-methy.txt");
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
				public String exponentialno(String in){
					if(in == "null" || in.equals("null")) return "0";
					else{
						return in;
					}
				}
				
				public String normalMethyno(String in){
					if(in.equals("NA") || in == "NA"){
						return "0";
					}else
						return in;
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
