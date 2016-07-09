package com.bo.offer;
public class UglyNumber {

	//offer 34
	
	public static int[] UglyN(int index){
		if(index < 1)
			return null;
		int[] uglys = new int[index];
		uglys[0] = 1;
		int uglyindex = 1;
		
		int m2 = 0;
		int m3 = 0;
		int m5 = 0;
		
		while(uglyindex < index){
			int min = min(uglys[m2]*2,uglys[m3]*3,uglys[m5]*5);
			uglys[uglyindex] = min;
			
			while(uglys[m2]*2 <= uglys[uglyindex] && m2 <= uglyindex){
				m2++;
			}
			
			while(uglys[m3]*3 <= uglys[uglyindex] && m3 <= uglyindex){
				m3++;
			}
			while(uglys[m5]*5 <= uglys[uglyindex] && m5 <= uglyindex){
				m5++;
			}
			
			uglyindex ++;
		}
		//
		return uglys;
	}
	
	public static int min(int a, int b, int c){
		int t = a<b?a:b;
		return t<c?t:c;
	}
	
	public static int UglyN2(int index){
		if (index < 1) {
			return 0;
		}
		int num = 0;
		int count = 0;
		while(count < index){
			num ++;
			if(IsUgly(num)){
				count ++;
			}
		}
		return num;
	}
	
	public static boolean IsUgly(int data){
		while(data % 2 == 0){
			data = data / 2;
		}
		while(data % 3 == 0){
			data = data / 3;
		}
		
		while(data % 5 == 0){
			data = data / 5;
		}
		
		if(data == 1)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		int[] uglys = UglyN(12);
		for(int i:uglys)
			System.out.print(i+" ");
	}
}
