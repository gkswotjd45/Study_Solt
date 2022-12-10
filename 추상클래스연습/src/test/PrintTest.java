package test;

public class PrintTest {
	//생성자
	
	//field 
	private String name;
	private int age;
	
	@Override 
	public String toString() { // 객체 생성 후 문자열을 리턴 해주는 것을 재정의하여 인스턴스 정보를 표현. 
		// TODO Auto-generated method stub
		return "이름은 :" + name + ", 나이는" + age;
	
	}
	//method 
	
	public static void main(String[] args) {
		// printTest라는 class의 instance를 생성.
		PrintTest a =new PrintTest();
		// 객체의 정보를 출력하고 싶음.
		System.out.println("안녕하세요.");//인자는 문자열로 변환이 가능한 값이 와야 함.
		System.out.println(100); // 숫자는 인자로 변환이 가능
		System.out.println(a); // a는 instance를 지칭하고 있음. 
								// 이 메모리 주소값을 문자열로 어떻게 변경하나요.?
								//  = 객체를 문자열로 변환한 값을 출력.
		
		System.out.println(a.toString()); // instacne를 문자열로 호출하는 수행. // 자바로 부터 받은 객체는 toString() object로 호출.
		// Object.toString() - 이 메소드는 원래 메모리 주소의 해쉬값을 문자열로 변환하는 일을 수행
		// 상속받은 메소드들을 오버라이딩 으로 재정의 가 가능.
		
		
	}

}
