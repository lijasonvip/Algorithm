package com.bo.nature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.rmi.CORBA.Util;

import com.bo.file.CopyFile;
import com.bo.file.Utils;

public class CommonPatients {
	public static void main(String[] args) {
		CommonPatients cp = new CommonPatients();
		
		// CopyFile copyFile = new CopyFile();
		// copyFile.copyFile("/media/bo/000F8BA00000D206/Data/RNA
		// Expression/BRCA.547.PAM50.SigClust.Subtypes.txt",
		// "/media/bo/000F8BA00000D206/Data/newMethy/test.txt");
		
//		List<String> common = cp.getCommonMethy();
		//移动集成甲基化数据
//		cp.IntegrateFiles(common);
		
		//删除重复病人的数据  ok: 521 病人 + minifest + norepeat manifest
		
		//cp.deleteRepeat();
		// String tString =
		// "jhu-usc.edu_BRCA.HumanMethylation450.16.lvl-3.TCGA-C8-A26X-01A-31D-A16G-05.txt";
		// System.out.println("TCGA-C8-A26X-01A-31D-A16G-05.txt".length());
		
		//RNA 和 subtype 数据是一样的，不用比较重复性

	}

	public List<String> getCommonMethy() {
		CommonPatients cp = new CommonPatients();
		Subtype st = new Subtype();
		// 统计了methy数据有611个，participant有505个
		Methylation methylation = new Methylation();
		Function f = new Function();
		List<String> bars = methylation.getAllBarcode();

		List<String> subbars = st.getNaturePatients(
				"/media/bo/000F8BA00000D206/Data/RNA Expression/BRCA.547.PAM50.SigClust.Subtypes.txt");

		// Set<String> set = f.ListToSet(bars); //common info with out -07 and
		// -05
		List<String> common = f.CommonP(bars, subbars);
		f.PrintList(common); // 比较list（没去重）加上27数据共有597个病人数据是有标签的 //比较去重后
		Set<String> barset = f.ListToSet(bars);
		Set<String> subset = f.ListToSet(subbars);

		// 去重后仍然有521项
		List<String> commonset = f.CommonP(f.SetToList(barset), f.SetToList(subset));
		f.PrintList(commonset);
		return commonset;
	}

	public void IntegrateFiles(List<String> common) {
		int count = 0;
		Utils utils = new Utils();
		CopyFile cf = new CopyFile();
		String base = "/media/bo/000F8BA00000D206/Data/Methylation/BRCA.HumanMethylation450.Level_3/";
		List<String> list = utils.listFiles(base);
		for (String folder : list) {
			List<String> files = utils.listFiles(base + folder);
			for (String file : files) {
				if (file.contains("jhu")) {
					String bar = file.substring(file.length() - 32, file.length() - 4);
					String p = bar.split("-")[2];
					if (common.contains(p)) {
						// move file to
						System.out.println("Moving..." + count++);
						cf.copyFile(base + folder + "/" + file,
								"/media/bo/000F8BA00000D206/Data/newMethy/" + bar + ".txt");
						utils.appendFile("/media/bo/000F8BA00000D206/Data/newMethy/manifest.txt", bar);
					}
				}
			}
		}
	}

	public void deleteRepeat(){
		Function f = new Function();
		Utils utils = new Utils();
		CopyFile copyFile = new CopyFile();
		List<String> manifest = f.readManifest("/media/bo/000F8BA00000D206/Data/newMethy/manifest.txt");
		List<String> repeate = new ArrayList<>();
		Set<String> set = new HashSet<String>();
		for(String m : manifest){
			String p = m.split("-")[2];
			if(set.contains(p)){
				repeate.add(m);
			}else{
				set.add(p);
				utils.appendFile("/media/bo/000F8BA00000D206/Data/newMethy/norepeatmanifest.txt", m);
			}
		}
		
		System.out.println(repeate.size());
		for(String r : manifest){
			if(repeate.contains(r)){
				copyFile.delFile("/media/bo/000F8BA00000D206/Data/newMethy/"+r+".txt");
				repeate.remove(r);
				System.out.println(repeate.size());
			}
		}
		
	}
}

// process Methylation data
// 处理甲基化数据的类 为了代码功能区分设计
class Methylation {
	public List<String> getAllBarcode() {
		List<String> allbars = new ArrayList<>();
		Utils utils = new Utils();
		String methyfilebase = "/media/bo/000F8BA00000D206/Data/Methylation/BRCA.HumanMethylation450.Level_3";
		List<String> list = utils.listFiles(methyfilebase);
		for (String s : list) {
			if (s != "Original Data" || !s.equals("Original Data")) {
				List<String> bars = ReadMethyManifest(methyfilebase + "/" + s + "/MANIFEST.txt");
				allbars.addAll(bars);
			}
		}
		return allbars;
	}

	public List<String> ReadMethyManifest(String filename) {
		List<String> result = new ArrayList<>();
		File file = new File(filename);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				if (tempString.contains("jhu")) {
					// System.out.println(tempString);
					String barfile = tempString.substring(tempString.length() - 32, tempString.length() - 4);
					// System.out.println(barfile);
					result.add(barfile.split("-")[2]);
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

// 一些功能函数都放到这里来
class Function {

	public List<String> readManifest(String filename) {
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

	// 比较两个list中公共元素
	public List<String> CommonP(List<String> first, List<String> second) {
		List<String> result = new ArrayList<>();
		for (String n : first) {
			if (second.contains(n)) {
				result.add(n);
			}
		}
		return result;
	}

	// TCGA-E2-A15I-11A-32D-A138-05 转为 A15I
	public String BarcodeToPaticipant(String bar) {
		String p = bar.split("-")[2];
		// TCGA-E2-A15I-11A-32D-A138-05
		return p;
	}

	public void PrintList(List<String> list) {
		//
		// for (String s : list) {
		// System.out.println(s);
		// }
		System.out.println("===============================================");
		System.out.println("list size:" + list.size());
	}

	// 这个函数是检测重复的
	// methy 的所有barcode不重复，继续检查participant
	public Set<String> ListToSet(List<String> list) {
		Set<String> set = new HashSet<>();
		int countin = 0;
		for (String s : list) {
			if (set.contains(s)) {
				// System.out.println(s);
				countin++;
			} else
				set.add(s);
		}
		System.out.println("those who repeat size: " + countin);
		System.out.println("set size:" + set.size());
		return set;
	}

	public List<String> SetToList(Set<String> set) {
		List<String> list = new ArrayList<>();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}
}

class Subtype {
	// nature subtype 中获取barcodes
	public List<String> getNaturePatients(String filename) {
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
					// 先比较整个barcode 没有相同的
					result.add(patient);
					// result.add(patient.substring(0,patient.length()-3));
					// 再比较participants
//					String[] ps = patient.split("-");
//					String p = ps[2];
//					result.add(p);

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

	public List<String> getTcgaPatients(String filename) {
		List<String> result = new ArrayList<>();
		File file = new File(filename);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// int line = 1;
			while ((tempString = reader.readLine()) != null) {
				// line++;
				// if (line > 2) {
				String[] barcode = tempString.split("\t");
				String patient = barcode[0];
				String[] ps = patient.split("-");
				String p = ps[2];
				result.add(p);

				// }
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

class RNA{
	public List<String> getRNABars(String filename) {
		List<String> result = new ArrayList<>();
		File file = new File(filename);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				
					String[] barcode = tempString.split("\t");
					for(int i=1;i<barcode.length;i++){
						//contain -07
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
}
