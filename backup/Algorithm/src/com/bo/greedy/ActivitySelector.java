package com.bo.greedy;

import java.util.LinkedList;
import java.util.List;

//local optimization to get global optimization
public class ActivitySelector {

	//global result list to store those index of activities
	static List<Integer> activities = new LinkedList<Integer>();
	
	//recursive activity selector
	//running time O(n)
	//activity sequence have already sorted by finish time
	public static int Recursive_Activity_Selector(int[] s, int[] f, int k, int n){
		int m = k+1;
		//find first activity am that start after ak
		while(m < n && s[m] < f[k]){
			m = m+1;
		}
		if(m < n){
			
			activities.add(m);
			return Recursive_Activity_Selector(s, f, m, n);
		}
		else
			return 0;
	}
	
	//iteration greedy
	public static void Greedy_Activity_Select(int[] s, int[] f){
		int n = s.length;
		//activities.add(1);
		//k=1 and m=2
		int k=0;
		for(int m=1;m<n;m++){
			if(s[m] >= f[k]){
				activities.add(m);
				k = m;
			}
		}
	}
	public static void main(String... args){
		//first activity always in
		//activities.add(1); when 0,0 is not taken into consideration. and activities in recursive add m+1
		int[] s = {0,1,3,0,5,3,5,6,8,8,2,12};
		int[] f = {0,4,5,6,7,9,9,10,11,12,14,16};
		Recursive_Activity_Selector(s,f,0,s.length);
		System.out.println("Recursive activity select: ");
		for(int i:activities){
			System.out.println(i);
		}
		
		activities.clear();
		Greedy_Activity_Select(s,f);
		System.out.println("Non-Recursive activity select: ");
		for(int i:activities){
			System.out.println(i);
		}
		
	}
}
