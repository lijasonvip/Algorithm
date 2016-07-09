package com.bo.sort;

public class Count {
	
	//k is the most big number of integer array A
	public static int[] CountSort(int[] A, int k){
		int[] C = new int[k+1];
		int[] B = new int[A.length];
		
		//initialize C
		for(int i=0;i<A.length;i++){
			C[A[i]] = C[A[i]] + 1;
		}
		
		//C[i] is the count of number who little or equal than i
		for(int j=1;j<C.length;j++){
			C[j] = C[j] + C[j-1];
		}
		
		//B is output, B[C[A[j]]] = A[j], j downto 1
		for(int i=A.length-1;i>=0;i--){
			B[C[A[i]]-1] = A[i];
			C[A[i]] --;
		}
		return B;
	}
	
	public static void main(String... args){
		int test[] = {2, 5, 3, 0, 2, 3, 0, 3};
		int[] B = CountSort(test, 5);
		for(int b:B){
			System.out.println(b);
		}
	}
}
