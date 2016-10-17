package com.tcga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
//compare two algorithms results
public class Analyse {

	public static void main(String... args){
		
		Analyse a = new Analyse();
		a.analyse();

		
	}
	
	
	//get patients data
	public List<String> barcodes_genes(String filename){
		File file = new File(filename);
		
		List<String> allPatients = new LinkedList<String>();
		
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	allPatients.add(tempString);
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
	    return allPatients;
	}

	//list<String> string is composed of R cols delimated by \t
	public double[][] listToarray(List<String> in){
		int size = in.get(0).split("\t").length;
		double[][] result = new double[in.size()][size];
		for(int j=0;j<in.size();j++){
			String s = in.get(j);
			String[] temp = s.split("\t");
			double[] d = new double[temp.length];
			for(int i=0;i<temp.length;i++){
				result[j][i] = Double.parseDouble(temp[i]);
			}
		}
		return result;
	}
	
	
	// T' of T
	public double[][] turnMatrix(double[][] in){
		double[][] result = new double[in[0].length][in.length];
		for(int i=0;i<in.length;i++){
			for(int j=0;j<in[0].length;j++){
				result[j][i] = in[i][j];
			}
		}
		return result;
	}
	
	
	//all Result
	public void analyse(){
		
		double[][] matrix = turnMatrix(listToarray(barcodes_genes("F://毕设/cancer subtype/data/matrix/compare/A.txt")));
		List<int[]> all = new LinkedList<int[]>();;
		for(int i=0;i<matrix.length;i++){
			int[] index = ResultSort(extractResult(matrix[i]));
			all.add(index);
		}
		
		List<String> in = barcodes_genes("F://毕设/cancer subtype/data/matrix/compare/no/group.txt");
		Map map = extractGroup(in);
		//cross here
		for(int i=0;i<all.size();i++){
			int[] indextemp = all.get(i);
			Set<String> keys = map.keySet();
			for(String k:keys){
				int[] indexgroup = Group((String)map.get(k));
				int cross = crossCheck(indextemp, indexgroup);
				int col = i+1;
				System.out.println("column " + col + " and group " + k + " crossed: " + cross);
			}
			
			
		}
		
		
	}
	public int[] Group(String in){
		String[] s = in.split(",");
		int[] r = new int[s.length];
		for(int i=0;i<s.length;i++){
			r[i] = Integer.parseInt(s[i]);
		}
		return r;
	}
	//extract group info
	public Map<String,String> extractGroup(List<String> in){
		List<Result> list = new LinkedList<Result>();
		int countgroup = 0;
		List<String> unique = new LinkedList<String>();
		for(int i=0;i<in.size();i++){
			Result remp = new Result(Double.parseDouble(in.get(i)), i+1);
			list.add(remp);
			if(!unique.contains(in.get(i))){
				countgroup++;
				unique.add(in.get(i));
			}
		}
		Map<String,String> map = new HashMap<String, String>();
		for(String u:unique){
			for(Result r:list){
				if(r.getData() == Double.parseDouble(u)){
					if(map.get(u) == null){
						String temp = Double.toString(r.getIndex());
						map.put(u, temp.substring(0, temp.length()-2));
					}else
					map.put(u, map.get(u)+","+r.getIndex());
				}
				
			}
		}
		return map;
	}
	
	//compose Result List Result: data, index
	public List<Result> extractResult(double[] in){
		List<Result> r = new LinkedList<Result>();
		for(int i=0;i<in.length;i++){
			Result temp = new Result(in[i], i+1);
			r.add(temp);
		}
		return r;
	}
	
	//Result sort by data
	public int[] ResultSort(List<Result> in){
		
		int[] index = new int[in.size()];
		Result[] data = new Result[in.size()];
		for(int i=0;i<data.length;i++){
			data[i] = in.get(i);
		}
		
		for(int i=1; i<data.length;i++){
			for(int j=i;j>0;j--){
				if(data[j].getData() < data[j-1].getData()){
					Result temp = data[j-1];
					data[j-1] = data[j];
					data[j] = temp;
				}
			}
		}
		
		//here control to return how many patients considered
		//for(int i=0;i<data.length;i++){
		for(int i=0;i<120;i++){
			index[i] = data[i].getIndex();
		}
		return index;
	}
	
	//cross check, return the common patients id counts
	public int crossCheck(int[] a, int[] b){
		int count = 0;
		for(int i=0;i<a.length;i++){
			
			for(int j=0;j<b.length;j++){
				if(b[j] == a[i]){
					count++;
					continue;
				}
			}
		}
		return count;
	}
}
