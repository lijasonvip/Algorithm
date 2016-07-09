package com.bo.offer;

public class Fibonacci {

	//http://blog.csdn.net/dadoneo/article/details/6776272
	
	public static int Fibonacci_Recursion(int n){
		if(n == 0)
			return 0;
		if(n == 1)
			return 1;
		return Fibonacci_Recursion(n-1) + Fibonacci_Recursion(n-2);
	}
	
	public static int  Fibonacci_Iteration(int n) {
		int[] result = {0 , 1};
		if(n < 2)
			return result[n];
		int fib = 1;
		int f_n_2 = 0;
		int f_n_1 = 1;
		for(int i=2;i<=n;i++){
			fib = f_n_1 + f_n_2;
			f_n_2 = f_n_1;
			f_n_1 = fib;
		}
		return fib;
	}
	
	public static void Fibonacci_Matrix() {
		
	}
	
	/**
	 * @param m
	 * @param n
	 * @return
	 * not finished yet
	 */
	public static Matrix2By2 Matrix_Pow(Matrix2By2 m, int n) {
		Matrix2By2 matrix = new Matrix2By2();
//		if(n == 1)
//			matrix = new Matrix2By2(1,1,1,0);
//		else if (n % 2 == 0) {
//			matrix = Matrix_Pow(m, n/2);
//		}else if (n % 2 == 1){
//			matrix = Matrix_Pow(matrix, (n-1)/2);
//			
//		}
		
		return matrix;
	}
	
	public static Matrix2By2 Matrix_Multiply(Matrix2By2 m1, Matrix2By2 m2){
		Matrix2By2 result = new Matrix2By2(m1.m00*m2.m00+m1.m01*m2.m10,m1.m00*m2.m01+m1.m01*m2.m11,
											m1.m10*m2.m00*m1.m11*m2.m01,m1.m10*m2.m01+m1.m11*m2.m11);
		return result;
	}
}



class Matrix2By2{
	int m00;
	int m01;
	int m10;
	int m11;
	
	public Matrix2By2(){}
	
	public Matrix2By2(int m00, int m01, int m10, int m11){
		this.m00 = m00;
		this.m01 = m01;
		this.m10 = m10;
		this.m11 = m11;
		
	}
}