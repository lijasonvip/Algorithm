package com.bo.nature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferToMatrix {

	String path;
	boolean hypermethlated; // true: hyper == 1, false hyper == 0

	public TransferToMatrix() {
	}

	public TransferToMatrix(String path,boolean hyper){
		this.path = path;
		this.hypermethlated = hyper;
	}

	public static void main(String[] args) {
		TransferToMatrix ttm = new TransferToMatrix();
		// ttm.delQuoteForMethy();
		// List<String> common = ttm.getCommonBars();
		// List<String> rnabars = ttm.getRnaBars();

		// 抽取表达数据
		// ttm.RNA_Expression(common, rnabars);

		// List<String> methybars = ttm.getMethyBars();
		// 抽取甲基化数据
		// ttm.Methylation(common, methybars);

		// 甲基化数据转为0,1矩阵, 划分的阈值可以在MathMean()函数中设置返回值。
		// ttm.Average_Methy();
	}
	// now we get all common patients and methylation and expression data
	// next transfer two data sets to matrix

	public void WorkFlow() {
		// 读取共同病人编号
		List<String> common = getCommonBars();
		// 读取原始表达数据中所有病人编号 进而抽取共同病人列
		List<String> rnabars = getRnaBars();

		// 抽取表达数据
		RNA_Expression(common, rnabars);

		// 原始甲基化病人的所有barcode
		List<String> methybars = getMethyBars();

		// 抽取甲基化数据
		Methylation(common, methybars);

		// 甲基化数据转为0,1矩阵, 划分的阈值可以在MathMean()函数中设置返回值。
		Average_Methy();
	}

	public List<String> CommonP(List<String> first, List<String> second) {
		List<String> result = new ArrayList<>();
		for (String n : first) {
			if (second.contains(n)) {
				result.add(n);
			}
		}
		return result;
	}

	/**
	 * @param common
	 * @param methybar
	 *            保存两份文件，第一份是所有的cgxxx 编号，留待后用，第二份是切割只含共同辨认的甲基化数据
	 *            分别保存为27k450kcg.txt和27k450k.methy.txt
	 */
	public void Methylation(List<String> common, List<String> methybar) {
		// 跳过第一行
		String filename = path + "original/BRCA.methylation.27k.450k.txt";
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("file not exists, Please check " + filename + " again!");
			System.exit(0);
		}

		// 删除已存在的两个文件
		String methy_cgs = path + "middle/27k450kcg.txt";
		File file_methy_cgs = new File(methy_cgs);
		if (file_methy_cgs.exists()) {
			file_methy_cgs.delete();
		}

		String methy_methy = path + "middle/27k450k.methy.txt";
		File file_methy_methy = new File(methy_methy);
		if (file_methy_methy.exists()) {
			file_methy_methy.delete();
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				if (line > 1) {
					String cg = tempString.split("\t")[0];
					String transferd = cutColumn_Methy(tempString, common, methybar);

					saveCGMethy(cg.substring(1, cg.length() - 1));
					saveMethylation(transferd, 1);
				}
				line++;
				System.out.println(line);
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

	// 保存了17814个基因 和对应的表达数据

	/**
	 * @param common
	 * @param rnabar
	 *            保存三份文件，切割含共同病人的0.1矩阵到brca.exp.547.cy35.txt，
	 *            一份共同病人的原始数据到cutpatient_original_rna.txt
	 *            一份第一列基因名到allgenes.txt留待后用
	 */
	public void RNA_Expression(List<String> common, List<String> rnabar) {
		String filename = path + "original/BRCA.exp.547.med.txt";
		// 按行处理文件
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("file not exists, Please check " + filename + " again!");
			System.exit(0);
		}
		// 删除已经存在的三个文件
		String brca_exp = path + "middle/brca.exp.547.cy35.txt";
		File file_brca_exp = new File(brca_exp);
		if (file_brca_exp.exists()) {
			file_brca_exp.delete();
		}

		// cutpatient_original_rna.txt
		String brca_cut = path + "middle/cutpatient_original_rna.txt";
		File file_brca_cut = new File(brca_cut);
		if (file_brca_cut.exists()) {
			file_brca_cut.delete();
		}

		// allgenes
		String brca_allgenes = path + "middle/allgenes.txt";
		File file_brca_allgenes = new File(brca_allgenes);
		if (file_brca_allgenes.exists()) {
			file_brca_allgenes.delete();
		}

		BufferedReader reader = null;
		int count = 0;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				String gene_symbol = tempString.split("\t")[0];
				// 同时转存一份原始数据
				String transferd = cutColumn_Exp(tempString, common, rnabar);
				saveGeneSymbol(gene_symbol);
				saveExpression(transferd);
				System.out.println(count++);
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

	public void saveGeneSymbol(String content) {
		String filename = path + "middle/allgenes.txt";
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

	public void saveExpression(String content) {
		String filename = path + "middle/brca.exp.547.cy35.txt";
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

	public void saveCGMethy(String content) {
		String filename = path + "middle/27k450kcg.txt";
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

	public void saveOriginal(String content) {
		String filename = path + "middle/cutpatient_original_rna.txt";
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

	public void saveMethylation(String content, int choice) {
		// 两次使用，第一次是保存double类型的到27k450k.methy.txt
		// 第二次是保存为0,1矩阵到27k450k.01matrx.txt
		String filename1 = path + "middle/27k450k.methy.txt";
		String filename2 = path + "middle/27k450k.01matrix.txt";
		if (choice == 1) {
			try {
				FileWriter fw = new FileWriter(filename1, true);
				fw.write(content);
				fw.write("\n");
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				FileWriter fw = new FileWriter(filename2, true);
				fw.write(content);
				fw.write("\n");
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String cutColumn_Exp(String rna, List<String> common, List<String> rnabar) {
		String[] expvalue = rna.split("\t");

		StringBuffer result = new StringBuffer();
		StringBuffer original = new StringBuffer();
		List<String> smallcommon = ToParticipantList(common);
		List<String> smallrna = ToParticipantList(rnabar);
		for (String com : smallcommon) {
			int index = smallrna.indexOf(com);
			if (index > expvalue.length - 1 || index < -1) {
				// 忽略缺省数据的基因
				result.append("\t");
				original.append("\t");
				continue;
			}
			String temp = "";
			if (!expvalue[index].equals("") && expvalue[index] != null) {

				temp = expvalue[index];
				expvalue[index] = gene_expression(expvalue[index]);

			}
			original.append(temp + "\t");
			result.append(expvalue[index] + "\t");

		}
		// 转存一份共同病人列的数据到cutpatient_original_rna.txt
		saveOriginal(original.toString());
		return result.toString();
	}

	public String cutColumn_Methy(String rna, List<String> common, List<String> rnabar) {
		String[] expvalue = rna.split("\t");
		StringBuffer result = new StringBuffer();
		List<String> smallcommon = ToParticipantList(common);
		List<String> smallrna = ToParticipantList(rnabar);
		for (String com : smallcommon) {
			int index = smallrna.indexOf(com);
			if (index > expvalue.length - 1 || index < -1) {
				// 忽略缺省数据的基因
				continue;
			}
			result.append(expvalue[index] + "\t");

		}

		return result.toString();
	}

	public List<String> ToParticipantList(List<String> in) {
		List<String> out = new ArrayList<>();
		for (String t : in) {
			if (t.equals("NAME") || t == "NAME" || t.equals("bars") || t == "bars")
				out.add("NAME");
			else
				out.add(t.substring(8, 12));
		}
		return out;
	}

	public String gene_expression(String exp) {
		double a = Double.parseDouble(exp);
		double cy35 = Math.exp(a);
		if (cy35 < 0.5 || cy35 > 2.0) {
			return "1";
		} else
			return "0";
	}

	/**
	 * 读取共同的病人编号
	 */
	public List<String> getCommonBars() {
		List<String> result = new ArrayList<>();
		String filename = path + "norepeatmanifest.txt";
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
				result.add(tempString);
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

	/**
	 * 读取表达数据的病人编号
	 */
	public List<String> getRnaBars() {
		List<String> result = new ArrayList<>();
		String filename = path + "547bars.txt";
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

				String[] barcode = tempString.split("\t");
				for (int i = 0; i < barcode.length; i++) {
					// contain -07
					result.add(barcode[i]);
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

	/**
	 * 抽取甲基化病人的第1行出来 所有的病人barcode
	 */
	public List<String> getMethyBars() {
		List<String> result = new ArrayList<>();
		String filename = path + "27k450kbars.txt";

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

				String[] barcode = tempString.split("\t");
				for (int i = 0; i < barcode.length; i++) {
					// contain -07

					result.add(barcode[i]);
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

	public void delQuoteForMethy() {
		String filename = "/media/bo/000F8BA00000D206/Data/Methylation/27k450kbars.txt";
		File file = new File(filename);
		BufferedReader reader = null;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {

				String[] barcode = tempString.split("\t");
				for (int i = 0; i < barcode.length; i++) {
					// contain -07
					if (barcode[i].contains("\"")) {
						String temp = barcode[i];
						barcode[i] = temp.substring(1, temp.length() - 1);
					}
					stringBuffer.append(barcode[i] + "\t");
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

		try {
			FileWriter fw = new FileWriter(filename, true);
			fw.write(stringBuffer.toString());
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 甲基化数据划分 大于平均数的表示为1，这里还可以使用其他阈值
	// 读入切割后的甲基化文件转存为0.1矩阵到27k450k.01matrix.txt
	public void Average_Methy() {
		List<String> result = new ArrayList<>();
		String filename = path + "middle/27k450k.methy.txt";
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("file not exists, Please check " + filename + " again!");
			System.exit(0);
		}
		// 删除已存在的文件
		String avg_methy = path + "middle/27k450k.01matrix.txt";
		File file_avg_methy = new File(avg_methy);
		if (file_avg_methy.exists())
			file_avg_methy.delete();
		BufferedReader reader = null;
		int count = 0;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				String row = Calculate_Row(tempString);
				saveMethylation(row, 2);
				// System.out.println(count++);
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

	/**
	 * @param str
	 * @return 这里控制最后的甲基化数据 若高于平均值为0其他为1 或者相反 或者平均值换成一个其他阈值
	 */
	public String Calculate_Row(String str) {
		StringBuffer sb = new StringBuffer();
		String[] rows = str.split("\t");
		double[] each = new double[rows.length];
		for (int i = 0; i < rows.length; i++) {
			if (!rows[i].equals("")) {
				each[i] = GetDoubleFromString(rows[i]);
			} else
				each[i] = 0;
		}

		double mean = MathMean(each);
		// System.out.println(mean);
		for (int i = 0; i < each.length; i++) {
			if (!hypermethlated) {
				// 高甲基化为0，否则为1
				if (each[i] > mean)
					each[i] = 0;
				else
					each[i] = 1;
			}else{
				// 高甲基化为1，否则为0
				if (each[i] > mean)
					each[i] = 1;
				else
					each[i] = 0;
			}
			sb.append(Integer.toString((int) (each[i])) + "\t");
		}
		return sb.toString();
	}

	// 发现大于均值并不稀疏，我们设置参数0.6
	public double MathMean(double[] data) {
		double sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum = sum + data[i];
		}
		return sum / (double) data.length;
		// return 0.6;
	}

	public double GetDoubleFromString(String in) {
		if (in.equals("NA") || in == "NA")
			// 甲基化翻转，高甲基化为0，其他为1
			return 1;
		else
			return Double.parseDouble(in);
	}

	// gene_symbol和cg00000292这样的编码进行映射

}
