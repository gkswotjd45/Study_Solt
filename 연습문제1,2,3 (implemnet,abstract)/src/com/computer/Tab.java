package com.computer;

public class Tab extends Computer  implements Graphic {
	
	public Tab() {
		
	}
	
	public Tab(String name, int cpu, int memory,int bettery) {
		super(name,cpu,memory,bettery);
	}
	
	

	@Override
	public double redering(int size) {
		
		return Math.round((double)size/(getCpu()*getMemory())*30*100);
	}

	@Override
	public void operate(int time) { //5 시간 사용시 배터리 감소.
		 int result =  (time / 5); 
		 setBettery(getBettery() - result);
		//setBettery((int)(getBettery() - (time *0.2)));
		
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	

}
