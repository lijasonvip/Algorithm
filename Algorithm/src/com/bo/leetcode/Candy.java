package com.bo.leetcode;
//http://www.acmerblog.com/leetcode-solution-candy-6262.html
public class Candy {

	//孩子站成一列赋予权重
	//每个孩子至少一个糖 高权重的要比邻居糖多 最少要给出多少个糖
	
	public static int candy(int[] ratings){
		int cntArr[] = new int[ratings.length];
		//每个孩子给一个
        for(int i=0; i<cntArr.length; i++) cntArr[i]=1;
        //右边比左边孩子大则比左边孩子的糖多一个
        for(int i=1; i<cntArr.length; i++){
            if(ratings[i] > ratings[i-1])
                cntArr[i] = cntArr[i-1]+1;
        }
        //倒着走
        int ans = cntArr[cntArr.length-1];
        for(int i=cntArr.length-2; i>=0; i--){
        	//左边比右边大且左边的孩子糖少 左边的孩子比右边的多拿一个
            if(ratings[i] > ratings[i+1] && cntArr[i] <= cntArr[i+1])
                cntArr[i] = cntArr[i+1]+1;
            ans += cntArr[i];
        }
        return ans;
	}
}
