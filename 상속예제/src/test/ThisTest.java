package test;

public class ThisTest extends Object{
	
	
	// 1.Constructor 
	// default constructor는 항상 작성해야 함. 명시적으로 작성해 주세요.
	public ThisTest() {
		// TODO Auto-generated constructor 
		this(100); // this () 현재 클래스의 다른 생성자를 호출할때 사용. , 자기클래스의 다른 생성자를 호출 (인자가 int)인.
	
	}
	// constructor overloading으로 또 다른 생성자를 하나 만듦
	public ThisTest(int k) {
		super();// 상위 클래스의 생성자를 호출하기 위해.
		System.out.println("인자가 하나 있는 생성자.");
	}
	// 2. field method는 바로 존재하지 않음.
	
	public static void main (String [] args) {
		ThisTest a = new ThisTest();
		
	}
}
