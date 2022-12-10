package com.uni;

public class Human {

	public Human() {
		
	}

	public Human(String name, int age, int height, int weight) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	private String name; 
	private int age;
	private int height;
	private int weight;
	
	public String printInformation() { // 4개의 정보를 String 형태로 만듬 // 필드의 정보를 4개의 문자를 하나로 합침.
		return name +" " + age + " "+ height+ " "+  weight;
	}

	public String getName() {
		return name;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
