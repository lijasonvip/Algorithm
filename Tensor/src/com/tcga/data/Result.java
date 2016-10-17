package com.tcga.data;

public class Result {

	private double data;
	private int index;
	
	public Result(){}
	
	public Result(double data, int index){
		this.data = data;
		this.index = index;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
