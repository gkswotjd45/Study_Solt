package test;
import java.lang.*; // 기본적으로 생략이 가능.

public class Person extends java.lang.Object{ // java에서 사용되는 모든 class는 object을 상속. 굳이 붙여 사용하지 않아도 됨.

	//1. constructor 
	/*
	public Person(String name) { // 형식인자가 있는 생성자만 작성.
		super(); // 현재 클래스의 상위 클래스인 object 클래스는 생성자들을 호출.
				 // Object()가 호출되어 실행! 
		// 인자도 받아드리지 않고, 하는 일도 없는 constructor 
		// default constructor 
		// 이쯤에서 공간이 생성하게 됨.
		
		// 자신의 초기화를 진행해야 함.
		
		System.out.println("Person의 생성자가 호출!!");
		this.name = name;
	}
	
	*/
	public Person() { // 형식인자가 있는 생성자만 작성.
		super(); // 현재 클래스의 상위 클래스인 object 클래스는 생성자들을 호출.
				 // Object()가 호출되어 실행! 
		// 인자도 받아드리지 않고, 하는 일도 없는 constructor 
		// default constructor 
		// 이쯤에서 공간이 생성하게 됨.ㄴ
	}
	
	//2. field 
	private String name; 
	private String gender;
	private int age;
		
	
	//3. method 
	//business logic method 
	public void eat() {
		System.out.println("밥을 먹어요.");
	};
	

	
}


