package com.bo.nature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Evaluation {

	String path;
	boolean hyperpositive;
	boolean is_original;
	boolean is_weighted;
	
	public Evaluation(){
		this.hyperpositive = true;
		this.is_original = true;
	}
	
	public Evaluation(String path, boolean hyper, boolean is_origi,boolean is_weighted){
		this.path = path;
		this.hyperpositive = hyper;
		this.is_original = is_origi;
		this.is_weighted = is_weighted;
	}
	
	public static void main(String[] args) {
		//载入一个文件我们测试一下,计算距离的文件也有两种，等会确定
		String path = "/home/bo/graduate/data/cancer/";
		boolean hyperpositive = true;
		boolean is_weighted = true;
		boolean is_original = true;
		//BeforeNTF(path, hyperpositive);
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
				for(int k=0;k<2;k++){
					Evaluation e = new Evaluation(path,getBool(i),getBool(j),getBool(k));
					e.WorkFlow();
				}
			}
		}
	}
	
	public static boolean getBool(int i){
		if (i == 0) {
			return false;
		}else {
			return true;
		}
	}

	
	public void WorkFlow(){
		//1. contingency table
		//2. sum of squared error criterion
		//3. silhouette value
		//		class-based precision and recall
		//4. precision and recall and f-score
		//5. adjust pair-wise precision
		//6. Mutual Information
		//7. RandIndex
		//8. Adjust Rand Index
		//9. Matching Index
		String parameter = "hyperpositive_"+hyperpositive + "_" + "is_weighted_"+is_weighted + "_"+"is_original_" + is_original;
		String ntffile;
		if (hyperpositive) {
			ntffile = path + "hyperpositive/ntf_"+parameter+".txt";
		}else{
			ntffile = path + "hypernegative/ntf_"+parameter+".txt";
			
			
		}
		String result_file = path + "result/result_"+parameter+".txt";
		File result = new File(result_file);
		if(result.exists())
			result.delete();
		
		
		String pamfile = path + "pam50subtypes.txt";
		ArrayList[] ntf = getArrayList(ntffile);
		ArrayList[] pam = getArrayList(pamfile);
		List<String> ntftype = getAllType(ntffile);
		List<String> pamtype = getAllType(pamfile);
//		double[][] data = getFile(); //读入矩阵文件计算类间距离，先不处理
		
		
		int[][] table = ContingencyTable(ntf, pam);
		//3和4都需要计算原始矩阵做距离运算，花费时间较多
		
		double match_index = MatchIndex(table);
		saveEvaluationResult("Match_Index:\t" + match_index, result_file);
		double ari = AdjustRandIndex(table);
		saveEvaluationResult("AdjustRandIndex:\t"+ari, result_file);
		
		//不同的对应关系会有不同的互信息
		double mutual_info = MutualInformation(table);
		saveEvaluationResult("Mutual_Information:\t"+mutual_info, result_file);
		
		//聚类结果和以前标签的对应关系会影响， 需要一个对应关系的map
		//两种思路
		Map<String, String> map = SimpleMap(table, ntftype, pamtype);
		double[] precision_recall = PrecisionAndRecall(map, table, ntftype, pamtype);
		double precision = precision_recall[0];
		saveEvaluationResult("Precision:\t"+precision, result_file);
		double recall = precision_recall[1];
		saveEvaluationResult("Recall:\t"+recall, result_file);
		double f_score = 2 * precision * recall / (recall + precision);
		saveEvaluationResult("f_score:\t"+f_score, result_file);
		//最后我们计算silhouette 和 sum of error
		String methy_file = "";
		String rna_file = "";
		if (is_original) {
			methy_file = path + "originalcommon/original_methy.txt";
			rna_file = path + "originalcommon/original_rna.txt";
		}else{
			if (hyperpositive) {
				rna_file = path + "hyperpositive/rna.cy35.01matrix.txt";
				methy_file = path + "hyperpositive/methy.mean.01matrix.txt";
			}else{
				rna_file = path + "hypernegative/rna.cy35.01matrix.txt";
				methy_file = path + "hypernegative/methy.mean.01matrix.txt";
			}
		}
		
		
		List<String> methy_list = ReadAllRowsByColumn(methy_file, 99);
		List<String> rna_list = ReadAllRowsByColumn(rna_file, 99);
		double[][] data_methy = SplitListToMatrix(methy_list);
		double[][] data_rna = SplitListToMatrix(rna_list);
		
		double sum_methy = SumOfSquaredError(ntf, data_methy);
		saveEvaluationResult("sum of error_methy:\t"+sum_methy, result_file);
		double sum_rna = SumOfSquaredError(ntf, data_rna);
		saveEvaluationResult("sum of error_rna:\t"+sum_rna, result_file);
		double avg1 = (sum_methy + sum_rna ) /(double) 2;
		saveEvaluationResult("sum of error_avg:\t"+avg1, result_file);
		double sil_methy = AllSilhouetteValue(ntf, data_methy);
		saveEvaluationResult("sil_methy:\t" + sil_methy, result_file);
		double sil_rna = AllSilhouetteValue(ntf, data_rna);
		saveEvaluationResult("sil_rna:\t"+sil_rna, result_file);
		double avg2 =( sil_methy + sil_rna ) /(double) 2;
		saveEvaluationResult("sil_avg:\t"+avg2, result_file);
		
		
		
		
		
	}
	
	public double MatchIndex(int[][] table){
		
		int row = table.length, col = table[0].length;
		double t = 0, p = 0, q = 0;
		for (int i = 0; i < row; i++) {
			double sumrow = 0;
			for (int j = 0; j < col; j++) {
				t += table[i][j] * table[i][j];
				sumrow += table[i][j];
			}
			p += sumrow * sumrow;
		}
		t = t - row * col;
		p = p - row * col;
		for (int i = 0; i < col; i++) {
			int sumcol = 0;
			for (int j = 0; j < row; j++) {
				sumcol += table[j][i];
			}
			q += sumcol * sumcol;
		}
		q = q - row * col;
		
		
		return t / Math.sqrt(p * q);
	}
	
	public void saveEvaluationResult(String content,String filename) {
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
	
	
	public double AdjustRandIndex(int[][] table){
		int row = table.length, col = table[0].length;
		double upleft = 0, upright = 0, downleft = 0, downright = 0;
		double upright_first = 0;
		for (int i = 0; i < row; i++) {
			int sumrow = 0;
			for (int j = 0; j < col; j++) {
				sumrow += table[i][j];
				upleft += countPairs(table[i][j]);
				
			}
			upright_first += countPairs(sumrow);
			downleft += countPairs(sumrow);
			
		}
		int upright_second = 0;
		for (int i = 0; i < col; i++) {
			int sumcol = 0;
			for (int j = 0; j < row; j++) {
				sumcol += table[j][i];
			}
			upright_second += countPairs(sumcol);
			downleft += countPairs(sumcol);
		}
		
		upright = upright_first * upright_second / (double) countPairs(row * col);
		downleft = downleft / 2;
		return (upleft - upright) / (downleft - upright);		
	}
	
	public int NTF_ArrayList(int pamindex, Map<String, String> map, List<String> ntftype, List<String> pamtype){
		String ntf_type = map.get(pamtype.get(pamindex));
		return ntftype.indexOf(ntf_type);
	}
	
	public void AdjustedRandIndex(){}
	

	
	public double MutualInformation(int[][] table){
//		int[] purity = new int[table.length];
//		for (int i = 0; i < purity.length; i++) {
//			purity[i] = MaxInRow(table,i);
//		}
		
		int total = table.length * table[0].length;
		double sumout = 0;
		double count = 0;
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				count += table[i][j];
			}
		}
		
		for (int i = 0; i < table.length; i++) {
			double sumin = 0;
			for (int j = 0; j < table[0].length; j++) {
				double a;
				if (table[i][j] == 0) {
					a = 0;
				}else{
					double foruplog = (double)(table[i][j] * count) / (double)(SumRowOrCol(table, i, true) * SumRowOrCol(table, j, false));
					a = table[i][j] * Math.log(foruplog) / Math.log(total);
				}
				
				sumin += a;
			}
			sumout += sumin;
		}
		return sumout / count;
	}

	public int SumRowOrCol(int[][] matrix, int roworcol, boolean is_row) {
		int sum = 0;
		if (is_row) {
			for (int i = 0; i < matrix[0].length; i++) {
				sum += matrix[roworcol][i];
			}
		}else{
			for (int i = 0; i < matrix.length; i++) {
				sum += matrix[i][roworcol];
			}
		}
		return sum;
	}

	public int MaxInRow(int[][] table, int row) {
		int max = table[row][0];
		for (int i = 1; i < table[0].length; i++) {
			if (table[row][i] > max) {
				max = table[row][i];
			}
		}
		return max;
	}

	// 生存CompareTable 中打印出的表格
	public int[][] ContingencyTable(ArrayList[] ntf, ArrayList[] pam) {
		int[][] table = new int[ntf.length][pam.length];
		for (int i = 0; i < ntf.length; i++) {
			for (int j = 0; j < pam.length; j++) {
				int comij = CountCommon(ntf[i], pam[j]);
				table[i][j] = comij;
			}
		}
		return table;
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

	public void AdjustPrecisionRecall() {
	}

	/**
	 * @param map
	 *            NTF聚类标签和PAM标签的一个对应关系
	 * @param ntf
	 * @param pam
	 *            pairs 个数， 不是传统的统计个数 由recall 和 precision 可以计算fscore
	 */
	public double[] PrecisionAndRecall(Map<String, String> map, int[][] table, List<String> ntftype,
			List<String> pamtype) {
		// recall = tp / (fn + tp)
		// precision = tp / (fp + tp)
		// f-score = (2 * recall * precision) / (recall + precision)
		double[] precisionandrecall = new double[2];
		int tp = 0, fn = 0, fp = 0;

		// 聚类中统计的是pairs 的个数
		
		for (int i = 0; i < table.length; i++) {
			String pam_type = getPamTypeFromMap(map, ntftype.get(i));
			int pam_index = pamtype.indexOf(pam_type);
			tp += countPairs(table[i][pam_index]);
			int sum = SumRowOrCol(table, i, true) - table[i][pam_index];
			fp += countPairs(sum);
		}
		
	
		for (int i = 0; i < table[0].length; i++) {
			String ntf_type = map.get(pamtype.get(i));
			int ntf_inddex = ntftype.indexOf(ntf_type);
			int sum = SumRowOrCol(table, i, false) - table[ntf_inddex][i];
			fn += countPairs(sum);
		}

		double recall = (double) tp / (double) (fn + tp);
		double precision = (double) tp / (double) (fp + tp);
		precisionandrecall[1] = recall;
		precisionandrecall[0] = precision;
		return precisionandrecall;
	}

	public int countPairs(int n) {
		return n * (n-1) / 2;
	}

	/**
	 * @param map
	 * @param value
	 *            由map的value找key
	 */
	public String getPamTypeFromMap(Map<String, String> map, String value) {
		Set<String> set = map.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String v = map.get(key);
			if (v == value || v.equals(value)) {
				return key;
			}
		}
		return null;
	}

	/**
	 * @param ntf
	 *            病人分组数据
	 * @param data
	 *            指定数据文件
	 * @return 通过参数指定数据data， 从不同文件读入，不确定是原始数据还是01矩阵作为距离计算的输入
	 */
	public double SumOfSquaredError(ArrayList[] ntf, double[][] data) {
		double sum = 0;
		for (int i = 0; i < ntf.length; i++) {
			sum += sumErrorInnerCluster(ntf[i], data);
		}
		return sum;
	}

	/**
	 * @param ntf
	 * @param data
	 * @return sil(C) 所有簇的平均sil值， 关键是一个元素o的sil值是o与所在簇和o与最邻近的簇之间计算得到的， 先找最邻近
	 */
	public double AllSilhouetteValue(ArrayList[] ntf, double[][] data) {
		double sum = 0;
		for (int i = 0; i < ntf.length; i++) {
			sum += KthClusterSilhouette(ntf, i, data);
		}
		sum = sum / (double) ntf.length;
		return sum;
	}

	/**
	 * @param ntf
	 * @param k
	 *            kth cluster, start with 0
	 * @param data
	 * @return 第k个簇的sil值定义为k簇中所有元素的sil值的平均值
	 */
	public double KthClusterSilhouette(ArrayList[] ntf, int k, double[][] data) {
		double sil = 0;
		List<Integer> kth = ntf[k];
		double sum = 0;
		for (int i = 0; i < kth.size(); i++) {
			sum += ObjectSilhouette(ntf, k, kth.get(i), data);
		}
		sil = sum / (double) kth.size();
		return sil;

	}

	public double ObjectSilhouette(ArrayList[] ntf, int k, int obj, double[][] data) {
		double localsil = ObjectDistance(ntf[k], obj, data, true);
		double neighboutsil = ObjectNeighbourSilhouette(ntf, k, obj, data);

		double kthobjsil = (neighboutsil - localsil) / Math.max(localsil, neighboutsil);
		return kthobjsil;

	}

	/**
	 * @param ntf
	 * @param k
	 * @param obj
	 * @param data
	 * @return 返回的是所有邻居中平均sil值最小的sil, 找到neighbour cluster
	 */
	public double ObjectNeighbourSilhouette(ArrayList[] ntf, int k, int obj, double[][] data) {
		List<Integer> local = ntf[k];
		double minsil = 2;// sil in (-1,1)
		int mink = k;
		for (int i = 0; i < ntf.length; i++) {
			if (i != k) {
				double neighbour = ObjectDistance(ntf[i], obj, data, false);
				if (neighbour < minsil) {
					minsil = neighbour;
					mink = i;
				}
			}
		}
		return minsil;
	}

	/**
	 * @param target
	 * @param obj
	 * @param data
	 * @param belong
	 *            标记obj是否属于target， 即是否簇间sil值
	 * @return
	 */
	public double ObjectDistance(List<Integer> target, int obj, double[][] data, boolean belong) {
		double[] local = getCol(data, obj);
		double sum = 0;

		for (int i = 0; i < target.size(); i++) {
			if (obj != target.get(i)) {
				sum += Difference(local, getCol(data, target.get(i)));
			}
		}
		if (belong) {
			sum = sum / (double) (target.size() - 1);
		} else
			sum = sum / (double) target.size();
		return sum;
	}

	/**
	 * @param first
	 * @param second
	 * @param obj
	 *            first中一个元素obj
	 * @param data
	 * @return 返回first中一个元素obj与 first 和 second两个簇的silhouette值
	 */
	public double ObjectSilhouetteOfTwoCluster(List<Integer> first, List<Integer> second, int obj, double[][] data) {
		double[] objcol = getCol(data, obj);
		double suma = 0;
		for (int i = 0; i < first.size(); i++) {
			if (first.get(i) != obj) {
				double[] temp = getCol(data, first.get(i));
				suma += Difference(objcol, temp);
			}
		}
		suma = suma / (double) (first.size() - 1);
		double sumb = 0;
		for (int i = 0; i < second.size(); i++) {
			double[] temp = getCol(data, second.get(i));
			sumb += Difference(objcol, temp);
		}
		sumb = sumb / (double) second.size();

		double sil = (sumb - suma) / Math.max(suma, sumb);
		return sil;

	}

	public double Purity() {
		return 0;
	}

	/**
	 * @param a
	 * @param b
	 * @return 返回差，不作平方运算
	 */
	public double Difference(double[] a, double[] b) {
		double sum = 0;
		for (int i = 0; i < b.length; i++) {
			sum += (a[i] - b[i]);
		}
		return sum;
	}

	/**
	 * @param pindex
	 * @param data
	 * @return 返回一个簇内部每个点与中心点的距离平方和，这里的距离使用欧几里得距离
	 */
	public double sumErrorInnerCluster(List<Integer> pindex, double[][] data) {
		double[] centroid = indexOfCentroidPatient(pindex, data);
		double sum = 0;
		for (int i = 0; i < pindex.size(); i++) {
			double[] cur = getCol(data, pindex.get(i));
			sum += SquaredDistance(cur, centroid, null);
		}
		return sum;
	}

	public double SquaredDistance(double[] a, double[] b, double[] w) {
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

	/**
	 * @param allpatients
	 *            包含所有病人编号的一个簇
	 * @param data
	 *            所有病人的所有属性的矩阵 如： 基因表达值 x 病人
	 * @return
	 */
	public double[] indexOfCentroidPatient(List<Integer> allpatients, double[][] data) {
		// 中心点是每个维度的平均值
		double[] centroid = new double[data.length];
		for (int i = 0; i < allpatients.size(); i++) {
			double[] cur = getCol(data, allpatients.get(i));
			for (int j = 0; j < cur.length; j++) {
				centroid[j] += cur[j] / allpatients.size();
			}
		}
		return centroid;
	}

	public Map<String, String> SimpleMap(int[][] table, List<String> ntftype, List<String> pamtype){
		Map<String, String> map = new HashMap<>();
		int[] sumrow = new int[table.length];
		int[] sumcol = new int[table[0].length];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				sumrow[i] += table[i][j];
			}
		}
		
		for (int i = 0; i < sumcol.length; i++) {
			for (int j = 0; j < sumrow.length; j++) {
				sumcol[i] += table[j][i];
			}
		}
		
		
		
		for (int i = 0; i < sumcol.length; i++) {
			String pam = pamtype.get(i);
			String ntf = "";
			int largerthanme = CountLargerThanMe(sumcol, i);
			for (int j = 0; j < sumrow.length; j++) {
				int larger = CountLargerThanMe(sumrow, j);
				if(larger == largerthanme){
					ntf = ntftype.get(j);
					map.put(pam, ntf);
					break;
				}
				
			}
		}
		return map;
	}
	
	public int CountLargerThanMe(int[] arr, int me){
		int count = 0;
		for(int i=0;i<arr.length;i++){
			if(me != i){
				if(arr[i] > arr[me]){
					count ++;
				}
				
			}
		}
		return count;
	}
	
	/**
	 * @param data
	 * @param col
	 * @return 获取矩阵data的col列
	 */
	public double[] getCol(double[][] data, int col) {
		double[] cols = new double[data.length];
		for (int i = 0; i < cols.length; i++) {
			cols[i] = data[i][col];
		}
		return cols;
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
}
