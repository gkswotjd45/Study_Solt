package com.uni;

public class Student extends Human {
	
	public Student() {
		
	}
	
	private String number;
	private String major;
	
	public Student(String name, int age, int height,int weight, String number,String major) {
		super(name,age,weight,weight);
		this.major = major;
		this.number = number;
	
	} 

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	
	@Override
	public String printInformation() {
		//학생의 정보를 출력하는 것.
		//Human 정보 + 학생 특유 의 정보
		return super.printInformation() + " " + number + " " + major ;
		// 휴먼 정보 + 학생의 정보를 결합하는 정보
	}
}
