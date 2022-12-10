package com.computer;

public class Laptop extends Computer implements Graphic { //Computer 상속, Graphic 인터페이스를 구현.
	
	// 당연히 추상 method를 overriding 해야 // 우리 Class가 추상 class가 되지 않음.
	public Laptop() {
		
	}
	
	public Laptop(String name, int cpu, int memory,int bettery) {
		super(name,cpu,memory,bettery); //상위 클래스 생성자를 호출 (객체 값을 넣어줘야하는 데 해당 필드는 상위 class에 있으므로 상위 클래스로 접근해야 함.
	}

	@Override
	public void operate(int time) {
		//입력으로 들어온 시간 10 단위 당 1씩 배터리가 감소.
		// ㅑnt result =  (time / 10); 
		// 프로그램에서 연산 (+,-,*,/)은 일단 같은 데이터 타입끼리 발생.
		//
		//setBettery((int)(getBettery() - (time *0.1)));
		 int result =  (time / 10); 
		 setBettery(getBettery() - result);
		
	}
	@Override	
	public double redering(int size) {
		
		return Math.round((double)size/(getCpu()*getMemory())*20*100)/100.0; // size가 int형이므로 강제로 형변환을 수행해애 함 . int 실수로 
		//변화하면 double 형으로 받아드라
		
	}
	@Override
	public String toString() {
		return super.toString();
		//return getName() + " " + getCpu() + " " + getMemory() + " " + getBettery();
	}

}
