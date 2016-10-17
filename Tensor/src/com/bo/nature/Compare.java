package com.bo.nature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Compare {

	String path;
	String parameter;
	boolean hyperpositive;

	public Compare() {
		this.hyperpositive = true;
	}

	public Compare(String path, String param, boolean hyper) {
		this.path = path;
		this.parameter = param;
		this.hyperpositive = hyper;
	}

	// 和现有结果的分类进行比较

	public static void main(String[] args) {
		String path = "/home/bo/graduate/data/cancer/";
		Compare c = new Compare(path, "compare_parameters", false);
		// 先处理原始subtype文件，保存一份有共同病人的编号和类型文件
		// c.CommonPatientsFiles();

		// 打印共同表格
		c.CompareTable();

	}

	// pam50 class label: Basal,Her2,LumB,LumA,Normal
	public void CompareTable() {

		String ntffile;
		if (hyperpositive) {
			ntffile = path + "hyperpositive/ntf_"+parameter+".txt";
		}else{
			ntffile = path + "hypernegative/ntf_"+parameter+".txt";
		}
		String pamfile = path + "pam50subtypes.txt";
		ArrayList[] ntf = getArrayList(ntffile);
		ArrayList[] pam = getArrayList(pamfile);
		List<String> ntftype = getAllType(ntffile);
		List<String> pamtype = getAllType(pamfile);
		int[][] table = new int[ntf.length][pam.length];
		for (int i = 0; i < ntf.length; i++) {
			for (int j = 0; j < pam.length; j++) {
				int comij = CountCommon(ntf[i], pam[j]);
				table[i][j] = comij;
			}
		}

		PrettyPrint(table, ntftype, pamtype);
	}

	public void PrettyPrint(int[][] table, List<String> row, List<String> col) {
		String rows[] = new String[table.length + 3];
		rows[0] = "N\\P\t";
		for (int i = 0; i < col.size(); i++) {
			rows[0] = rows[0] + col.get(i) + "\t";
		}
		rows[0] = rows[0] + "total\t" + "%";
		int ii = 0;
		for (; ii < row.size(); ii++) {
			rows[ii + 1] = row.get(ii) + "\t";
		}
		ii++;
		rows[ii++] = "total\t";
		rows[ii] = "%\t";

		int[] rowsum = new int[row.size()];
		int[] colsum = new int[col.size()];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				rows[i + 1] = rows[i + 1] + table[i][j] + "\t";
				rowsum[i] += table[i][j];
				// colsum[j] += table[j][i];
			}
		}

		for (int i = 0; i < table[0].length; i++) {
			for (int j = 0; j < table.length; j++) {
				colsum[i] += table[j][i];
			}
		}
		DecimalFormat df = new DecimalFormat("0.000");
		for (int i = 1; i < rows.length - 2; i++) {
			double per = (double) rowsum[i - 1] / (double) 521;
			rows[i] += rowsum[i - 1] + "\t" + df.format(per);
			// System.out.println(rows[i]);
		}
		for (int i = 0; i < colsum.length; i++) {
			rows[row.size() + 1] += colsum[i] + "\t";
			double per = (double) colsum[i] / (double) 521;
			rows[row.size() + 2] += df.format(per) + "\t";
		}
		rows[row.size() + 1] += "521";
		rows[row.size() + 2] += "\t" + "1";
		String com_table;
		if (hyperpositive) {
			com_table = path + "hyperpositive/compare_" + parameter + ".txt";
		}else{
			com_table = path + "hypernegative/compare_" + parameter + ".txt";
		}
		File file = new File(com_table);
		if (file.exists())
			file.delete();
		for (int i = 0; i < rows.length; i++) {
			System.out.println(rows[i]);
			saveCompareTable(rows[i], com_table);
		}
	}

	public void saveCompareTable(String content, String filename) {
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

	public int CountCommon(ArrayList<Integer> first, ArrayList<Integer> second) {
		int count = 0;
		for (int i : first) {
			if (second.contains(i)) {
				count += 1;
			}
		}
		return count;
	}

	public ArrayList[] getArrayList(String filename) {
		List<String> rows = ReadAllRowsByColumn(filename, 99);
		ArrayList[] arrayLists = new ArrayList[5];
		// arrayLists行号表示types中第i个type。list表示这个type中的病人编号,编号从0开始
		List<String> types = getAllType(filename);
		for (int i = 0; i < rows.size(); i++) {
			String row = rows.get(i);
			String[] cols = row.split("\t");
			int index = types.indexOf(cols[1]);
			if (arrayLists[index] == null) {
				arrayLists[index] = new ArrayList<Integer>();
			}
			arrayLists[index].add(i);
		}
		return arrayLists;
	}

	// 保存共同病人顺序的两个结果标签
	// 文件预处理
	public void CommonPatientsFiles() {
		List<String> commonbars = ReadAllRowsByColumn(
				"/media/bo/000F8BA00000D206/Data/my construct data/norepeatmanifest.txt", 99);
		List<String> patients = getPatient(commonbars);
		List<String> subpatients = ReadAllRowsByColumn(
				"/media/bo/000F8BA00000D206/Data/my construct data/BRCA.547.PAM50.SigClust.Subtypes.txt", 0);
		List<String> subs = getPatient(subpatients);
		List<String> allsubs = ReadAllRowsByColumn(
				"/media/bo/000F8BA00000D206/Data/my construct data/BRCA.547.PAM50.SigClust.Subtypes.txt", 99);
		for (String p : patients) {
			int index = subs.indexOf(p);
			String[] rows = allsubs.get(index).split("\t");
			String row = rows[0] + "\t" + rows[3];
			saveCommonFiles(row);
		}
	}

	public void saveCommonFiles(String content) {
		String filename = "/media/bo/000F8BA00000D206/Data/my construct data/pam50subtypes.txt";
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

	public List<String> getPatient(List<String> bars) {
		List<String> p = new ArrayList<>();
		for (String s : bars) {
			String temp = s.substring(8, 12);
			p.add(temp);
		}
		return p;
	}

	public List<String> getAllType(String filename) {
		List<String> all = ReadAllRowsByColumn(filename, 1);
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < all.size(); i++) {
			if (!result.contains(all.get(i))) {
				result.add(all.get(i));
			}
		}
		return result;
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
