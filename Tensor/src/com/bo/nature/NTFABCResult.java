package com.bo.nature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NTFABCResult {
	
	//NTF处理数据后得到R个簇 这时候如何决定阈值进行划分又是个问题 只对病人这一列进行划分 因为病人标签有监督
	//之前的思路好像是排序之后进行取值
	//这里我们的简单思路是大于平均值
	
	public static void main(String[] args) {
		NTFABCResult ntf = new NTFABCResult();
		double[][] matrix = ntf.MatrixOfNTFAResult();
		System.out.println("total: "+matrix[0].length);
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(i+" " + ntf.CountLargerThanMean(matrix[i]));
		}
	}
	
	public int CountLargerThanMean(double[] data){
		double sum = 0.0;
		for(double d:data){
			sum += d;
		}
		double mean = sum / data.length;
		int count = 0;
		for(double d:data){
			if(Math.max(d, mean) == d){
				count ++;
			}
		}
		return count;
	}
	
	/**
	 * @return
	 * get the A' matrix
	 */
	public double[][] MatrixOfNTFAResult(){
		double[][] matrix = turnMatrix(listToarray(barcodes_genes("/home/bo/graduate/data/compare/A.txt")));
		return matrix;
	}
	
	/**
	 * @param filename
	 * @return
	 * read A.txt
	 */
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

}
