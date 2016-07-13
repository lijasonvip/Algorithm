package com.bo.leetcode;
//http://www.acmerblog.com/leetcode-solution-gas-station-6354.html
public class GasStation {

	//
	
	/**
	 * @param gas i个加油站有gas[i] 的油
	 * @param cost 汽车从i站到i+1站需要耗费油量cost[i]
	 *  油箱无限制 找到一个出发站点让车能环走一圈
	 * @return
	 */
	public static int CircuitTravel(int[] gas, int[] cost){
		int total = 0;
		int j = -1;
		for(int i=0,sum=0;i<gas.length;++i){
			sum += gas[i] - cost[i]; // i站能不能跑到下一站
			total += gas[i] - cost[i]; //能不能跑一圈 大于零说明可以
			if (sum < 0) {
				j = i;
				sum = 0;
			}
		}
		return total >= 0 ? j + 1 : -1; 
	}
	
}
