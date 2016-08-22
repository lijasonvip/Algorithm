package com.bo.designpattern;

public class Strategy {
	public static void main(String[] args) {
		TravelStrategy travel = new TrainStrategy();
		PersonContext t = new PersonContext(travel);
		t.getResult();
	}
}

//出行旅游
interface TravelStrategy{
	public void travelAlgorithm();
}

//具体策略
class AirPlanelStrategy implements TravelStrategy{
	@Override
	public void travelAlgorithm() {
		System.out.println("travel by airplane");
	}
}

class TrainStrategy implements TravelStrategy{
	@Override
	public void travelAlgorithm() {
		System.out.println("travel by train");
	}
}

class BicycleStrategy implements TravelStrategy{
	@Override
	public void travelAlgorithm() {
		System.out.println("travel by bicycle");
	}
}

//环境类
class PersonContext{
	private TravelStrategy strategy = null;
	public PersonContext(TravelStrategy travel){
		this.strategy = travel;
	}
	
	//旅行策略
	public void setStrategy(TravelStrategy stra) {
		this.strategy = stra;
	}
	
	public void getResult(){
		if (strategy != null) {
			strategy.travelAlgorithm();
		}
	}
}
