package com.computer;

public abstract class Computer {
	
	//생성자 
	public Computer() {
		
	}
	
	public Computer(String name, int cpu, int memory,int bettery) {
		this.name = name;
		this.cpu = cpu;
		this.memory = memory;
		this.bettery = bettery;
	}
	//field
	private String name;
	private int cpu;
	private int memory;
	private int bettery;
	
	//추상 메서드
	public abstract void operate(int time);
	
	//getter & setter 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public int getBettery() {
		return bettery;
	}

	public void setBettery(int bettery) {
		this.bettery = bettery;
	}
	@Override
	public String toString() {
		return name +"  " + cpu + "  " + memory + "   " + bettery;
	}
}
