package com.bo.nature;

/**
 * @author bo
 *
 */
public class Main {

	// 1. 从甲基化和基因表达数据以及已有subtype结果的三个文件中找共同的病人编号，保存这写共同编号的文件
	// 2. 从甲基化和基因表达数据中切出只有这些共同病人的两种数据
	// 3. 将两种数据处理为01矩阵
	// 4. 使用NTF分解得到A,B,C三个结果文件
	// 5. 分析结果文件得到我们的算法对共同病人的分类标签
	// 6. 比对两种结果

	public static void main(String[] args) {
		String path = "/home/bo/graduate/data/cancer/";
		boolean hyperpositive = true;
		boolean is_weighted = true;
		boolean is_original = true;
		//BeforeNTF(path, hyperpositive);
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
				for(int k=0;k<2;k++){
					AfterNTF(path,getBool(i),getBool(j),getBool(k));
				}
			}
		}
		//AfterNTF(path,hyperpositive,is_weighted,is_original);
	}
	
	public static boolean getBool(int i){
		if (i == 0) {
			return false;
		}else {
			return true;
		}
	}

	public static void BeforeNTF(String path, boolean hyperpositive) {
		// 1. common patients. 共同病人从两个文件头中中切出第一行来处理得到norepeatmanifest.txt
		// 这个结果可以直接用
		// 2. 表达数据中所有的基因对应甲基化中所有的cg编号 结果文件allpairs.txt 可以直接使用
		// 3. CommonGenes.txt 是两个数据集对应的基因顺序文件 可以直接使用
		// 4. 表达数据和甲基化数据切割为共同病人的数据并转存为0.1矩阵，另外存一份表达数据只有共同病人的原始数据留作KNN使用

		// 也就是说如何处理表达数据为0，1矩阵的参数也在这里，后续可改
	
		TransferToMatrix ttm = new TransferToMatrix(path, hyperpositive);	//true: 超甲基化为1其他为0
																//false: 超甲基化为0，其他为1
		// ----------------->>>>>>
	
		ttm.WorkFlow(); // 将表达数据和甲基化数据转为0.1矩阵，这里的文件比较复杂
						// 甲基化在Calculate_Row() 函数中设置
						// 表达数据在gene_expression() 函数中设置

		// 5. cgxxx编号和基因名映射后得到allpairs.txt文件 在此基础上我们再次切割表达数据和甲基化数据为共同基因的数据
		Tumors802 t = new Tumors802(path, hyperpositive);
		t.ReOrderMethyAndCutSome();// 生成最终的两个矩阵文件同时，也切割了原始数据的文件作KNN用
		// 6. 5中生成的矩阵可以拿到Matlab下运算了，生成A.txt, B.txt, C.txt 三个文件

	}

	public static void AfterNTF(String path,boolean hyperpositive,boolean is_weighted,boolean is_original) {
		// 6. 上一步5中生成的矩阵可以拿到Matlab下运算了，生成A.txt, B.txt, C.txt 三个文件
		System.out.println(path);
		String parameter = "hyperpositive_"+hyperpositive + "_" + "is_weighted_"+is_weighted + "_"+"is_original_" + is_original;
		System.out.println(parameter);
		// 7. 结果分析，对我们选出来的共同病人进行类别划分（聚到不同的类中）
		//将Matlab分解的结果放到result目录下，ABC三个文件，分析的结果也会放在此处，
		ResultAnalyse ra = new ResultAnalyse(path, is_weighted,hyperpositive,is_original,parameter);
		//设置距离公式的选择和是否引入权重
		ra.WorkFlow();// 对病人分类后保存到NTFResult.txt 文件中

		// 8. 与现有的subtype结果进行对比，打印并保存分析结果
		
		Compare c = new Compare(path,parameter,hyperpositive);
		c.CompareTable();

		// 9. Clustering Evaluation

	}

	/**
	 * 这里记录程序的修改和更新 
	 * 1. 修改文件路径问题，保存读取文件的健壮性，保存时若存在文件则先删除 DONE! 
	 * 2. 转存原数据为0,1矩阵时使用参数控制的方法，增强灵活性 Done! 
	 * 3. 增加聚类分析功能
	 * 4. 增加聚类个数参数R设置的功能
	 * 5. KNN不同距离公式的选择，
	 * 6. 距离计算的时候选择原始数据还是01数据 Done!
	 * 7. 是否使用权重等参数的设置 Done!
	 * 
	 */
	public void UpdateAndBugsFixing() {
	}

}
