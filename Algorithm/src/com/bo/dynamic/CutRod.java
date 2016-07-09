package com.bo.dynamic;

public class CutRod {

	public static void main(String... args){
		int[] p = {0,1,5,8,9,10,17,17,20,24,30};
		//int cut = CutRod(p,2);
		//int cut = Bottom_Up_Cut_Rod(p, 10);
		//System.out.println(cut);
		int[][] cut = Extend_Bottom_Up_Cut_Rod(p, 10);
		for(int i=0;i<cut.length;i++){
			for(int j=0;j<cut[0].length;j++){
				System.out.print(cut[i][j] + "\t");
			}
			System.out.println();
		}
		
	}
	
	//recursive method, T(n) = 2^n
	public static int CutRod(int[] p, int n){
		if(n == 0)
			return 0;
		int q = 0;
		for(int i=1;i<= n;i++){
			q = Math.max(q, p[i]+CutRod(p,n-i));
		}
		System.out.println(q);
		return q;
	}
	
	//memoization top down
	//with inputment, r[0,,,n] needed  n^2
	public static int Memoized_Cut_Rod(int[] p, int n, int[] r){
		int q;
		if(r[n] >= 0)
			return r[n];
		if(n==0)
			q = 0;
		else{
			q = Integer.MIN_VALUE;
			for(int i=1;i<=n;i++){
				q = Math.max(q,p[i] + Memoized_Cut_Rod(p,n-i,r));
			}
		}
		r[n] = q;
		return q;
		
	}

	//bottom up cut rod but not list out cut plan  n^2
	public static int Bottom_Up_Cut_Rod(int[] p, int n){
		int[] r = new int[n+1];
		r[0] = 0;
		int q;
		
		for(int j=1;j<=n;j++){
			q = Integer.MIN_VALUE;
			for(int i=1;i<=j;i++){
				q = Math.max(q, p[i] + r[j-i]);
			}
			r[j] = q;
		}
		return r[n];
	}
	
	//bottom up cut rod with cut plan
	public static int[][] Extend_Bottom_Up_Cut_Rod(int[] p, int n){
		int[][] last = new int[2][n+1];
		//r for profit array
		int[] r = new int[n+1];
		//s fir cutting plan
		int[] s = new int[n+1];
		r[0] = 0;
		int q;
		for(int j=1;j<=n;j++){
			q = Integer.MIN_VALUE;
			for(int i=1;i<=j;i++){
				if(q < p[i] + r[j-i]){
					q = p[i] + r[j-i];
					//cut distance
					s[j] = i;
				}
			}
			r[j] = q;
		}
		last[0] = r;
		last[1] = s;
		return last;
	}
}
