package com.bo.nature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bo.file.Utils;

public class Tumors802 {

	String path;
	//超甲基化为1
	boolean hyperpositive;
	
	public Tumors802(){
		this.hyperpositive = true;
	}
	
	public Tumors802(String path, boolean hyper){
		this.path = path;
		this.hyperpositive = hyper;
	}
	public static void main(String[] args) {
		Tumors802 t = new Tumors802();
		t.WorkFlow();

	}

	public void WorkFlow() {
		// allgenes.txt 中有17814个基因
		// 映射后只有16154个了。 也就是说甲基化中能找到对应基因的少了点，接下来处理甲基化数据
		// 按allgenes的顺序存储矩阵，而且去掉没有映射到的基因
		// List<String> allgenes = getRNAGenes();
		// Map<String, String> maps = getCGAndGeneMap();
		// saveMapPairs(maps, allgenes);
		//cg 和 gene symbol 都对应上的有11761个
		 ReOrderMethyAndCutSome();
//		FindAllMapPairs();
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

	// 按基因顺序拍甲基化文件同时去掉这些没有映射到基因的甲基化数据
	// 同时，表达数据也要去掉这些没有甲基化数据的基因
	public void ReOrderMethyAndCutSome() {
		// read dataRNA Expression/
		List<String> methyrows = ReadAllRowsByColumn(path + "middle/27k450k.01matrix.txt", 99);
		List<String> exprows = ReadAllRowsByColumn(path + "middle/brca.exp.547.cy35.txt", 99);

		Map<String, String> maps = readMapFiles();
		// gene for real sequence
		// methy sequence
		List<String> cgs = ReadAllRowsByColumn(path + "middle/27k450kcg.txt", 0);
		List<String> genes = ReadAllRowsByColumn(path + "middle//allgenes.txt", 0);
		
		//同时转存一份原始数据
		List<String> origi_methy = ReadAllRowsByColumn(path + "middle/27k450k.methy.txt", 99);
		List<String> origi_rna = ReadAllRowsByColumn(path + "middle/cutpatient_original_rna.txt", 99);
		
		
		//删除已存在的文件
		if(hyperpositive){
			String methy_mean = path + "hyperpositive/methy.mean.01matrix.txt";
			File file_methy_mean = new File(methy_mean);
			if (file_methy_mean.exists()) {
				file_methy_mean.delete();
			}
			
			String rna_cy35 = path + "hyperpositive/rna.cy35.01matrix.txt";
			File file_rna_cy35 = new File(rna_cy35);
			if (file_rna_cy35.exists()) {
				file_rna_cy35.delete();
			}
		}else{
			String methy_mean = path + "hypernegative/methy.mean.01matrix.txt";
			File file_methy_mean = new File(methy_mean);
			if (file_methy_mean.exists()) {
				file_methy_mean.delete();
			}
			
			String rna_cy35 = path + "hypernegative/rna.cy35.01matrix.txt";
			File file_rna_cy35 = new File(rna_cy35);
			if (file_rna_cy35.exists()) {
				file_rna_cy35.delete();
			}
		}
		
		
		
		String com_gene = path+"CommonGenes.txt";
		File file_com_gene = new File(com_gene);
		if (file_com_gene.exists()) {
			file_com_gene.delete();
		}
		
		String original_methy = path+"originalcommon/original_methy.txt";
		File file_ori_methy = new File(original_methy);
		if (file_ori_methy.exists()) {
			file_ori_methy.delete();
		}
		
		String original_rna = path+"originalcommon/original_rna.txt";;
		File file_ori_rna = new File(original_rna);
		if (file_ori_rna.exists()) {
			file_ori_rna.delete();
		}
		
		int count = 1;
		for (String gene : genes) {
			String cg = maps.get(gene);
			if (cg != null && !cg.equals("")) {
				int cgindex = cgs.indexOf(cg);
				int geneindex = genes.indexOf(gene);
				//原始数据中有些不合规范的数据，这里把这些基因也切掉
				//或许是程序的错误，回头检查为什么表达数据的行中会少与521个元素
				if (cgindex != -1 && geneindex != -1 && exprows.get(geneindex).length() == 1042){
					if (hyperpositive) {
						saveToFile(methyrows.get(cgindex), path + "hyperpositive/methy.mean.01matrix.txt");
						saveToFile(exprows.get(geneindex), path + "hyperpositive/rna.cy35.01matrix.txt");
					}else{
						saveToFile(methyrows.get(cgindex), path + "hypernegative/methy.mean.01matrix.txt");
						saveToFile(exprows.get(geneindex), path + "hypernegative/rna.cy35.01matrix.txt");
					}
					System.out.println(count++);
					saveToFile(gene, path+"CommonGenes.txt");
					//保存了共同基因和共同
					
					//转存原始数据
					saveToFile(origi_methy.get(cgindex), path+"originalcommon/original_methy.txt");
					saveToFile(origi_rna.get(geneindex), path+"originalcommon/original_rna.txt");
				}
			}
		}
	}

	public void saveToFile(String content, String filename) {
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

	public Map<String, String> readMapFiles() {
		String filename = path + "allpairs.txt";
		Map<String, String> map = new HashMap<>();
		File file = new File(filename);
		if(!file.exists()){
			System.out.println("file not exists, Please check "+filename+" again!");
			System.exit(0);
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				String[] row = tempString.split("\t");
				map.put(row[0], row[1]);
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

	// 按照RNA Expression中gene symbol的顺序保存映射对
	public void saveMapPairs(Map<String, String> map, List<String> genes) {
		for (String g : genes) {
			String cg = map.get(g);
			if (cg != null) {
				saveMapPairs(g + "\t" + cg);
			}
		}
	}

	public List<String> getRNAGenes() {
		String filename = "/media/bo/000F8BA00000D206/Data/RNA Expression/allgenes.txt";
		List<String> result = new ArrayList<>();
		File file = new File(filename);

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

	public void saveMapPairs(String maps) {
		String filename = "/media/bo/000F8BA00000D206/Data/Methylation/MapsOfCGandGene_Symbol.txt";
		try {
			FileWriter fw = new FileWriter(filename, true);
			fw.write(maps);
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// map cg to gene
	// 存在一个cg对应两个或多个基因的情况，存在cg不对应基因的情况
	public Map<String, String> getCGAndGeneMap() {
		String base = "/media/bo/000F8BA00000D206/Data/Methylation/";
		String filename = base + "jhu-usc.edu_BRCA.HumanMethylation450.16.lvl-3.TCGA-D8-A27H-01A-11D-A16G-05.txt";
		Map<String, String> map = new HashMap<>();
		File file = new File(filename);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {

				if (line > 2) {
					String[] row = tempString.split("\t");
					String cg = row[0];
					String gene = row[2];
					if (gene.contains("-")) {
						String[] g = gene.split("-");
						for (String s : g) {
							map.put(s, cg);
						}
					} else if (gene.contains(";")) {
						String[] g = gene.split(";");
						for (String s : g) {
							map.put(s, cg);
						}
					} else if (gene.equals("NA") || gene == "NA") {
						continue;
					} else
						map.put(gene, cg);
				}
				line++;
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

	// readin 802 files and all cg
	public List<String> getAllCG(String filename) {
		List<String> result = new ArrayList<>();
		File file = new File(filename);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				line++;
				if (line > 2) {
					String[] barcode = tempString.split("\t");
					String patient = barcode[0];
					patient = patient.substring(1, patient.length() - 1);
					result.add(patient);

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

	// readin 802 files and all cg
	public List<String> ReadAllRowsByColumn(String filename, int column) {
		List<String> result = new ArrayList<>();
		File file = new File(filename);
		if(!file.exists()){
			System.out.println("file not exists, Please check "+filename+" again!");
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

	// 从所有文件中寻找methy数据的cgxxx编号对应的gene symbol
	public void FindAllMapPairs() {
		List<String> cgs = ReadAllRowsByColumn("/media/bo/000F8BA00000D206/Data/Methylation/27k450kcg.txt", 0);
		Utils utils = new Utils();
		String base = "/media/bo/000F8BA00000D206/Data/Methylation/BRCA.HumanMethylation450.Level_3/";
		List<String> list = utils.listFiles(base);
		for (String l : list) {
			List<String> files = utils.listFiles(base + l);
			// cg gene pair
			for (String f : files) {
				if (cgs.size() >= 1)
					getMapFromFile(base + l + "/" + f, cgs);

				if (cgs.size() < 1 || cgs == null) {
					break;
				}
			}
			if (cgs.size() < 1 || cgs == null) {
				break;
			}
		}

	}

	public void getMapFromFile(String filename, List<String> cgs) {
		Map<String, String> map = new HashMap<>();
		File file = new File(filename);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {

				if (line > 2) {
					String[] row = tempString.split("\t");
					if (row.length > 2) {
						String cg = row[0];
						String gene = row[2];
						if (gene != null && !gene.equals("")) {
							if (gene.contains("-")) {
								String[] g = gene.split("-");
								for (String s : g) {
									if (cgs.contains(cg)) {
										saveToFile(s + "\t" + cg,
												"/media/bo/000F8BA00000D206/Data/my construct data/allpairs.txt");
										cgs.remove(cg);
										System.out.println(cgs.size());
									}
								}
							} else if (gene.contains(";")) {
								String[] g = gene.split(";");
								for (String s : g) {
									if (cgs.contains(cg)) {
										saveToFile(s + "\t" + cg,
												"/media/bo/000F8BA00000D206/Data/my construct data/allpairs.txt");
										cgs.remove(cg);
										System.out.println(cgs.size());
									}
								}
							} else if (gene.equals("NA") || gene == "NA") {
								continue;
							} else if (cgs.contains(cg)) {
								saveToFile(gene + "\t" + cg,
										"/media/bo/000F8BA00000D206/Data/my construct data/allpairs.txt");
								cgs.remove(cg);
								System.out.println(cgs.size());
							}
						}
					}
				}
				line++;
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

}
