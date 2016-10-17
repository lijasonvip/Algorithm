package com.bo.nature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.TabableView;
import javax.xml.crypto.Data;

import com.tcga.data.Patients;

public class ResultAnalyse {

	String path;
	boolean is_weighted;
	boolean hyperpositive;
	boolean is_original;
	String ntf_file_name;
	

	public ResultAnalyse() {
		this.is_weighted = true;
		this.hyperpositive = true;
		this.is_original = true;
		this.ntf_file_name = "NTFResult";
	}

	/**
	 * @param path
	 * @param w
	 *            是否考虑权重
	 *            使用什么距离度量
	 *            使用原始数据还是01矩阵计算距离
	 */
	public ResultAnalyse(String path, boolean w, boolean hyper, boolean original,String ntf_file_name) {
		this.path = path;
		this.is_weighted = w;
		this.hyperpositive = hyper;
		this.is_original = original;
		this.ntf_file_name = ntf_file_name;
	}

	// 把NTF分解得到的三个文件A B C 根据阈值对病人进行分组
	// 分到不同组里是关键
	public static void main(String[] args) {
		String path = "/home/bo/graduate/data/cancer/";
		ResultAnalyse ra = new ResultAnalyse(path, false,true,true,"NTFResult");
		double[][] result = ra.getResultFile();
		// double threshold = ra.findThreshold(result);
		// System.out.println(threshold);
		// 新找阈值的方法
		double threshold = ra.Perfect_Threshold(0.2, result);
		int[][] index = ra.indexAfterDivide(result, threshold);
		int[] conflict = ra.Conflict(index);
		System.out.println(ra.CountConflict(conflict));
		ra.DivideIntoClusters(index, conflict);
	}

	public void WorkFlow() {
		double[][] result = getResultFile();
		// double threshold = findThreshold(result);
		// System.out.println(threshold);
		// 新找阈值的方法
		double threshold = Perfect_Threshold(0.2, result);
		int[][] index = indexAfterDivide(result, threshold);
		int[] conflict = Conflict(index);
		System.out.println(CountConflict(conflict));
		DivideIntoClusters(index, conflict);
	}

	//
	public void DivideIntoClusters(int[][] index, int[] conflict) {
		// 存放521个病人的最宗分组
		int[] target = new int[CountConflict(conflict)];
		ArrayList[] fiveCluster = NoConflictList(index, conflict);
		System.out.println("dealing with confilic patients...");
		int count = 0;
		for (int i = 0; i < conflict.length; i++) {
			if (conflict[i] != 0) {

				int minDistance = FindMinDistanceInOriginal(fiveCluster, i);
				target[count] = minDistance;
				System.out.println(count + " finished.");
				count++;
			}
		}

		for (int i = 0; i < target.length; i++) {
			int cluster = target[i];
			fiveCluster[cluster].add(i);
		}
		// 到此， fiveCluster中存放了五个分组机各个分组中的病人编号
		// 把病人编号与barcode对应并保存相应的分组结果
		List<String> patiens = ReadAllRowsByColumn(path + "norepeatmanifest.txt", 99);
		System.out.println(patiens.size());

		// 删除已存在的文件
		String ntf_result;
		if (hyperpositive) {
			ntf_result = path + "hyperpositive/ntf_"+ntf_file_name+".txt";
		}
		else{
			ntf_result = path + "hypernegative/ntf_"+ntf_file_name+".txt";
		}
		File file_ntf_result = new File(ntf_result);
		if (file_ntf_result.exists()) {
			file_ntf_result.delete();
		}

		for (int i = 0; i < fiveCluster.length; i++) {
			ArrayList<Integer> cluster = fiveCluster[i];
			for (int c : cluster) {
				String barcode = patiens.get(c);
				int subtype = i + 1;
				saveNTFResult(barcode + "\t" + subtype,ntf_result);
			}
		}
		System.out.println("All Done!");
	}

	/**
	 * @param threshold
	 * @param percentage,
	 *            control the percentage of those conflict patient that need to
	 *            use KNN. another parameters need to be tuned if no parameter
	 *            found return min percentage it can control
	 *            使用findThreshold()函数找到的冲突比为0.318 保存此函数中所有使得冲突比小于0.318的阈值
	 *            如果找不到小于percentage的阈值则返回最小的冲突比的阈值
	 * @return
	 */
	public double Perfect_Threshold(double percentage, double[][] data) {
		double min = findThreshold(data);
		double max = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] > max) {
					max = data[i][j];
				}
				int[][] index = indexAfterDivide(data, data[i][j]);
				int[] conflict = Conflict(index);
				int conf = CountConflict(conflict);
				double per = (double) conf / (double) conflict.length;
				// System.out.println(per);
				if (per < min) {
					if (per < percentage) {
						return data[i][j];
					} else if (per < min) {
						min = per;
					}
					System.out.println(per + "\t" + data[i][j]);
				}
			}
		}
		System.out.println("max:" + max);
		double step = 0;
		int[][] index = indexAfterDivide(data, step);
		int[] conflict = Conflict(index);
		int conf = CountConflict(conflict);
		double per = (double) conf / (double) conflict.length;
		while (per > percentage && step <= max) {
			step = step + 0.00001;
			index = indexAfterDivide(data, step);
			conflict = Conflict(index);
			conf = CountConflict(conflict);
			per = (double) conf / (double) conflict.length;
			if (per < min) {
				System.out.println(per + "\t" + step);
				if (per < min) {
					min = per;
				}
			}
		}

		System.out.println("final threshold:" + min);

		return min;
	}

	public void saveNTFResult(String content,String filename) {
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

	public int CountConflict(int[] conflict) {
		int coutn = 0;
		for (int i = 0; i < conflict.length; i++) {
			if (conflict[i] != 0)
				coutn++;
		}
		return coutn;
	}

	public int FindMinDistanceInOriginal(ArrayList[] clusters, int patient) {
		// cluster中放的是属于cluster的病人编号，patient是要求距离的病人编号
		double[] distance = new double[clusters.length];
		for (int i = 0; i < distance.length; i++) {
			distance[i] = CalculateDistance(clusters[i], patient, i);
		}

		int minindex = 0;
		double min = distance[0];
		for (int i = 1; i < distance.length; i++) {
			if (distance[i] < min) {
				minindex = i;
				min = distance[i];
			}
		}
		return minindex;
	}

	public double CalculateDistance(ArrayList<Integer> list, int patient, int Aindex) {
		// 1. 读入病人的数据
		List<String> origi_rna;
		List<String> origi_methy;
		if (is_original) {
			origi_rna = ReadAllRowsByColumn(path + "originalcommon/original_rna.txt", 99);
			origi_methy = ReadAllRowsByColumn(path + "originalcommon/original_methy.txt", 99);
		}else{
			if (hyperpositive) {
				origi_rna = ReadAllRowsByColumn(path + "hyperpositive/rna.cy35.01matrix.txt", 99);
				origi_methy = ReadAllRowsByColumn(path + "hyperpositive/methy.mean.01matrix.txt", 99);
			}else{
				origi_rna = ReadAllRowsByColumn(path + "hypernegative/rna.cy35.01matrix.txt", 99);
				origi_methy = ReadAllRowsByColumn(path + "hypernegative/methy.mean.01matrix.txt", 99);
			}
		}

		double[][] rna_patiens = SplitListToMatrix(origi_rna);
		double[][] methy_patiens = SplitListToMatrix(origi_methy);

		// weight
		// 读入分解结果重基因列的第i列
		List<String> A_colomn;
		List<String> B_colomn;
		if (hyperpositive) {
			A_colomn = ReadAllRowsByColumn(path + "hyperpositive/A.txt", Aindex);
			B_colomn = ReadAllRowsByColumn(path + "hypernegative/C.txt", Aindex);
		}else{
			A_colomn = ReadAllRowsByColumn(path + "hypernegative/A.txt", Aindex);
			B_colomn = ReadAllRowsByColumn(path + "hypernegative/C.txt", Aindex);
		}
		
		double[] A_weight = listToDouble(A_colomn);

		// 读入诊断权重
		 
		double[] B_weight = listToDouble(B_colomn);

		double sumRna = 0;
		double sumMethy = 0;

		if (is_weighted) {
			for (int i = 0; i < list.size(); i++) {

				sumRna += Distance(getCols(rna_patiens, list.get(i)), getCols(rna_patiens, patient), A_weight);
			}
			for (int i = 0; i < list.size(); i++) {
				sumMethy += Distance(getCols(methy_patiens, list.get(i)), getCols(methy_patiens, patient), A_weight);
			}
			sumRna = sumRna / list.size();

			sumMethy = sumMethy / list.size();
			if (B_weight[0] == 0 && B_weight[1] != 0)
				return B_weight[1] * sumMethy + (1 - B_weight[1]) * sumRna;
			else if (B_weight[0] != 0 && B_weight[1] == 0) {
				return B_weight[0] * sumRna + (1 - B_weight[0]) * sumMethy;
			} else {
				return B_weight[1] * sumMethy + B_weight[0] * sumRna;
			}
		} else {
			for (int i = 0; i < list.size(); i++) {

				sumRna += Distance(getCols(rna_patiens, list.get(i)), getCols(rna_patiens, patient), null);
			}
			for (int i = 0; i < list.size(); i++) {
				sumMethy += Distance(getCols(methy_patiens, list.get(i)), getCols(methy_patiens, patient), null);
			}
			sumRna = sumRna / list.size();

			sumMethy = sumMethy / list.size();
			return sumRna + sumMethy;
		}

	}

	public double[] getCols(double[][] matrix, int index) {
		double[] col = new double[matrix.length];
		for (int i = 0; i < col.length; i++) {
			col[i] = matrix[i][index];
		}
		return col;
	}

	/**
	 * @param w
	 *            w == null 表示没有权重 欧氏距离
	 */
	public double Distance(double[] a, double[] b, double[] w) {
		double sum = 0;
		if (w == null) {
			for (int i = 0; i < b.length; i++) {
				sum += Math.pow((a[i] - b[i]), 2);
			}
		} else {
			for (int i = 0; i < b.length; i++) {
				sum += Math.pow((a[i] - b[i]), 2) * w[i];
			}
		}
		return Math.sqrt(sum);
	}

	public double[] listToDouble(List<String> data) {
		double[] result = new double[data.size()];
		for (int i = 0; i < data.size(); i++) {
			double temp = Double.parseDouble(data.get(i));
			result[i] = temp;
		}
		return result;
	}

	public double[][] SplitListToMatrix(List<String> data) {
		int row = data.size();
		int col = data.get(0).split("\t").length;
		double[][] matrix = new double[row][col];
		for (int i = 0; i < data.size(); i++) {
			String rows = data.get(i);
			String[] cols = rows.split("\t");
			for (int j = 0; j < cols.length; j++) {
				if (cols[j] == "NA" || cols[j].equals("NA")) {
					matrix[i][j] = 0;
				} else {
					double temp = Double.parseDouble(cols[j]);
					matrix[i][j] = temp;
				}
			}
		}
		return matrix;
	}

	/**
	 * @param index
	 * @param conflict
	 * @return 返回五个无冲突的分组 存放在ArrayList[]中，5个数组，每个数组中放的是无冲突的病人index的list
	 */
	public ArrayList[] NoConflictList(int[][] index, int[] conflict) {
		ArrayList[] arrList = new ArrayList[index.length];
		for (int i = 0; i < index.length; i++) {
			if (arrList[i] == null) {
				arrList[i] = new ArrayList<Integer>();
			}
			for (int j = 0; j < index[0].length; j++) {
				if (conflict[j] == 0 && index[i][j] != 0) {
					arrList[i].add(j);
				}
			}
		}
		return arrList;
	}

	/**
	 * @param conflict
	 *            冲突数组存的是521个病人，如果conflict[i] == 0 表示i个病人无冲突，否则有冲突 notarget[i]
	 *            存放的是第i个冲突的病人在521个病人中的编号
	 */
	public int[] noTargetPatient(int[] conflict) {
		int size = 0;
		for (int i = 0; i < conflict.length; i++) {
			if (conflict[i] != 0) {
				size++;
			}
		}
		int[] notarget = new int[size];
		int j = 0;
		for (int i = 0; i < conflict.length; i++) {
			if (conflict[i] != 0) {
				notarget[j++] = i;
			}
		}
		return notarget;
	}

	/**
	 * 返回这些有冲突的病人下标，方便后续做近似处理
	 */
	public int[] Conflict(int[][] index) {
		int[] conflict = new int[index[0].length];
		int count = 1;
		for (int i = 0; i < index[0].length; i++) {
			// 对冲突的定义：只要任两个同时为1则冲突，不管谁和谁冲突
			if (index[0][i] + index[1][i] + index[2][i] + index[3][i] + index[4][i] > 1) {
				// 和大于1就是冲突
				conflict[i] = 1;

			} else if (index[0][i] + index[1][i] + index[2][i] + index[3][i] + index[4][i] < 1) {
				// 这一部分是在阈值以下且没法分配的，所以我们要更新我们的阈值选择方法
				conflict[i] = 1;
			}

		}
		return conflict;
	}

	// index[i][j] == 1 表示 第j个病人属于簇i
	public int[][] indexAfterDivide(double[][] data, double threshold) {
		int[][] index = new int[data.length][data[0].length];
		for (int i = 0; i < index.length; i++) {
			for (int j = 0; j < index[0].length; j++) {
				if (data[i][j] >= threshold)
					index[i][j] = 1;
			}
		}
		return index;
	}

	public double findThreshold(double[][] data) {
		// 阈值肯定在每列元素中存在，位于每列最大最小值之间，
		// 这个阈值对每列元素形成一个划分，使得五列数据中所有大于阈值的元素的个数之和为列元素的个数
		// 对每列使用二分查找的思路直到找到这个元素
		// 如果没找到，返回最接近的阈值
		int min = 6;
		double threshold = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				double cut = data[i][j];
				int countcut = CountCut(data, cut);
				if (countcut == data[0].length) {
					// System.out.println("Perfectly fit: " + cut);
					return cut;
				}
				int diff = Math.abs(countcut - data[0].length);
				if (diff < 5) {
					// System.out.println(cut + "\t" + countcut);
					if (diff < min) {
						min = diff;
						threshold = cut;
					}
				}
			}
		}

		return threshold;
	}

	public int CountCut(double[][] data, double cut) {
		int[] count = new int[5];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] >= cut) {
					count[i] = count[i] + 1;
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < count.length; i++) {
			sum = sum + count[i];
		}
		return sum;
	}

	// R is predefined for NTF parameter
	// NTF 分解目标为几个簇的参数R
	public double[][] getResultFile() {
		String filename;
		String reverse;
		if (hyperpositive) {
			filename = path + "hyperpositive/B.txt";
			reverse = path + "hyperpositive/reverseB.txt";
		}else{
			filename = path + "hypernegative/B.txt";
			reverse = path + "hypernegative/reverseB.txt";
		}
		int R = 5;
		int patientrow = 521;
		double[][] result = new double[5][patientrow];
		File file_reverse = new File(reverse);
		if (file_reverse.exists()) {
			file_reverse.delete();
		}
		for (int i = 0; i < R; i++) {
			List<String> row = ReadAllRowsByColumn(filename, i);
			// needed in python to plot
			saveRows(row,reverse);
			result[i] = toArray(row);
		}

		return result;
	}

	public void saveRows(List<String> row, String filename) {
		StringBuffer sb = new StringBuffer();
		for (String string : row) {
			sb.append(string + "\t");
		}
		try {
			FileWriter fw = new FileWriter(filename, true);
			fw.write(sb.toString());
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double[] toArray(List<String> list) {
		double[] r = new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String string = list.get(i);
			r[i] = Double.parseDouble(string);
		}
		return r;
	}

	/**
	 * 按列读文件，返回column指定列 当column为99时不进行切割 整行作为list元素
	 */
	public List<String> ReadAllRowsByColumn(String filename, int column) {
		List<String> result = new ArrayList<>();
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("file not exists, Please check " + filename + " again!");
			System.exit(0);
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				if (column != 99) {
					String[] cols = tempString.split("\t");
					result.add(cols[column]);
				} else {
					result.add(tempString);
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
