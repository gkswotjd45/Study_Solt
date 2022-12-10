package com.test5;

public class Secretary extends Employee implements Bonus {
	
	public Secretary() {
		
	}
	public Secretary(String name, int number, String department, int salary) {
		super(name,number,department,salary);
	
	}
	@Override
	public void incentive(int pay) {  //인센티브 지급
		
		setSalary(getSalary()+(int)(pay*0.8)); // 정수와 실수를 곱하면 실수범위로 나타냄.
	}
	@Override
	public double tax() { //세금 지급
		return getSalary() * 0.1; 
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
