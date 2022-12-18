package Lunch_Part2;
class Human {
	public Human() {
		// TODO Auto-generated constructor stub
	}
	

	public Human(String name, int age, int height, int weight) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}


	protected String name;
	protected int age;
	protected int height;
	protected int weight;
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
	
	public String printInformation() {
		return name +" " +age + " " + height + " " + weight;
	}
	
}

class Student extends Human{
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String name, int age, int height, int weight, String number, String major) {
		super(name, age, height, weight);
		this.number = number;
		this.major = major;
	}

	private String number;
	private String major;

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

	public String printInformation() {
		return name +" " +age + " " + height + " " + weight +" " + number +" " +major ;
	}
}

public class Lunch_P2_2 {
	
	public static void main(String[] args) {
		Student array [] = new Student[3];
		array[0] = new Student("홍길동", 15, 171, 81, "201101", "영문");
		array[1] = new Student("한사람", 13, 183,72, "201102","건축");
		array[2] = new Student("임꺽정", 16, 175, 65, "201103", "무영");
		
		
		for(Student s : array) {
			System.out.println(s.printInformation());
		}
	}
	
	
}
