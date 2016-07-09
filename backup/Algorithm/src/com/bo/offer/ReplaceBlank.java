package com.bo.offer;

public class ReplaceBlank {

	/**
	 * @param in
	 * @return replace all blank with '%20'
	 * not O(n^2) but in O(n), use two pointer to replace
	 */
	public static String ReplaceBlank(String in) {
		//string to ch without /0 not like C/C++
		char[] ch = in.toCharArray();
		int blank_count = 0;
		for (int i = 0; i < ch.length; i++) {
			if(ch[i] == ' '){
				blank_count ++;
			}
		}
		//new length is old length + count*2 not three because old blank position can make a room for one of the new char
		char[] newch = new char[ch.length+blank_count*2];
		int i = ch.length-1;
		int j = newch.length-1;
		while(i>=0 && j>=0){
			if(ch[i] == ' '){
				i--;
				newch[j--] = '0';
				newch[j--] = '2';
				newch[j--] = '%';
			}else{
				newch[j--] = ch[i--];
			}
		}
		return String.valueOf(newch);
	}
	
	/**
	 * @param A and B is two int array and already sorted
	 * @param A is big enough to hold A+B
	 * @return merge B into A and keep the order sorted
	 * @idea start from end like ReplaceBlank method
	 */
	public static int[] MergeToOne(int[] A, int[] B){
		int alen = 0;
		for(int i=0;i<A.length;i++){
			if(A[i] != 0){
				alen++;
			}else{
				break;
			}
		}
		
		alen--; //array index
		int blen = B.length-1;
		int length = alen + blen+1; //fix the double 
		
		while(alen >= 0 && length >= 0 && blen >=0){
			if(A[alen] > B[blen]){
				A[length--] = A[alen--];
			}else{
				A[length--] = B[blen--];
			}
		}
		
		while(alen>=0){
			A[length--] = A[alen--];
		}
		while(blen>=0){
			A[length--] = B[blen--];
		}
		
		return A;
	}
	
	public static void main(String[] args) {
		String r = ReplaceBlank("hello hello hello");
		System.out.println(r);
		
		int[] A = new int[100];
		A[0] = -1;A[1] = 3;A[2] = 5;A[3] = 7;A[4] = 9;
		int[] B = {2, 4, 6, 8, 10, 12, 14, 16};
		int[] C = MergeToOne(A,B);
	}
}
