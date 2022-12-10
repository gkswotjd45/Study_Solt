package com.test;
import java.lang.*;

public class Student extends java.lang.Object { // 원래 클래스 선언시 기본 형태.
	


	// constructor 
	public Student () {
						// 인자가 없는 생성자.
	}
	
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	
	// field 
	private String name;
	private int age;

	
	// method 
	@Override
	public String toString() {
		return this.name + ", " + this.age;
	}
	
	//getter & setter 
	public String getName() {
		return name;
	}
	
	
	@Override
	public boolean equals(Object obj) {
			// equals method의 기능을 재정의
		    // 이름과 나이가 같으면, (true)같다 라고 새롭게 정의
			// 비교할려면 클래스 타입 필도로 매개변수 선언.
			Student target = (Student)obj;
			boolean result = false;
			/*
			if((this.name == target.name) && (this.age == target.age)) {
				// 객체 이름과 인자의 객체 이름 , 호출되는 나이와 인자의 나이가 동일할 때
				result = true; // 결과 값을 true 저장.
			}
			return result;
		*/
			if ((this.name.equals(target.name))&&(this.age == target.age)) {  //age 는 숫자여서 '==' 사용.
				result =true; 
			}
			return result;
			
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

}
