package com.bo.dynamic;

public class MatrixChain {

	//two sign of dynamic programming
	//1. overlapping of subproblem
	//2. optimal structure
	
	//matrix chain order p = <p0,p1,p2,,,pn> to represent A1A2,,,An n matrix
	//m[i,j] = 0                					if i == j
	//m[i,j] = m[i,k] + m[k,j] + pi-1 *　pk * pj     if i<j
	public static int[][][] Matrix_Chain_Order(int[] p){
		// n is count of matrix chain
		int n = p.length -1;

		int[][][] result = new int[2][n+1][n+1];
		
		
		//for index convenience. allocate n+1 size
		//m[i,j] to store chain cost of Ai.... Aj
		int[][] m = new int[n+1][n+1];
		//record optimal value m[i,j]'s cutting point
		int[][] s = new int[n+1][n+1];
		//cost of single matrix(where i==i)
		for(int l=1;l<=n;l++)
			m[l][l] = 0;
		
		// len is length of current chain
		for(int len=2;len<=n;len++){
			for(int i=1;i<=n-len+1;i++){
				int j = i + len - 1;
				m[i][j] = Integer.MAX_VALUE;
				for(int k=i;k<=j-1;k++){
					int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
					if(q<m[i][j]){
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		result[0] = m;
		result[1] = s;
		
		return result;
		//实现算法的关键在于控制当前链的长度
	}
	
	public static void main(String... args){
		int[] p = {30,35,15,5,10,20,25};
		int[][][] result = Matrix_Chain_Order(p);
		Print_Optimal_Parens(result,1,6);
	}
	
	
	//print optimal parens
	//s[i,j] store the min cut point k for m[i,j] of chain Ai...Aj
	public static void Print_Optimal_Parens(int[][][] result, int i, int j){
		//result[0] stores m[i][j]
		//result[1] stores s[i][j]
		if(i==j)
			System.out.print("A");
		else{
			System.out.print("(");
			Print_Optimal_Parens(result,i,result[1][i][j]);
			Print_Optimal_Parens(result,result[1][i][j]+1,j);
			System.out.print(")");
		}
			
	}
	public int[][] Matrix_Multiply(int[][] A, int[][] B){
		if(A[0].length != B.length){
			return null;
		}
		int[][] C = new int[A.length][B[0].length];
		for(int i=0;i<A.length;i++){
			for(int j=0;j<B[0].length;j++){
				C[i][j] = 0;
				for(int k=0;k<A[0].length;k++){
					C[i][j] = C[i][j] + A[i][k]*B[k][j];
				}
			}
		}
		return C;
	}
	
	
}
