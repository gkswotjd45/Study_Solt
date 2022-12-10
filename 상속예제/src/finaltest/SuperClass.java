package finaltest;

public class SuperClass {

	//constructor
	public SuperClass() {
		// 
		//10. super(); 샐략된 형태 -> 상위클래스인 obj 객체를 만들기 위해 명시되어야 함. 상위 클래스 instance 생성부분,
		
		// 
		// 11. 바로 메모리에 공간 확보 과정을 수행.(안스턴스를 생성 위해)
		// 이후 우리 객체의 초기화를 수행해야함  -> 이후 superclass 공간에 필드영역이 할당시켜야 함. = 1번 출력. - int a를 print()로 출력 후 int a로 반환.
		
		staticCall("3번 문장입니다.");
		// 12. 이후 3번 문장이 바로 수행.
		
	}  //13. 생성자 실행 완료 후 stack 영역에 공간 할당 해제
	
	//constructor overloading으로 또 다른 생성자를 하나 정리
	public SuperClass(int i) { //8. 매개변수가 선언된 생성자를 접근
		this(); //9. 현재 class의 다른 생성자를 호출하는 기능. 
		// 14. 다른 생성자 공간 완료 및  해제 후 바로 4번 문장 수행.
		staticCall("4번문장입니다.");
	}
	// 해당 생성자를 수행 완료 후 다시 super(100)으로 리턴; 
	
	
	
	// field = 기본적으로 private으로 설정. 우리문제에서는 너무 복잡해서 그냥 package로 설정.
	int a = staticCall("1번문장입니다.");  //11. a라는 필드는 instance가 생성되어야 공간이 실제로 만들어지고 사용할 수 있음.
	// 그래서 a를 instance variable 이라고 불음 그래서 만들어지는 공간은 heap 영역.
	static int b = staticCall("2번문장입니다/");
	//b라는 field는 instance가 없어도 사용이 가능하다. 왜냐하면 b의 공간은 ///
	// method area에 만들어지기 떄문에.
	// 이러한 field 를 우리는 class variable 이이라고 불러요. 만들어지는 공간은  method area.
	
	
	//method 
	//method는 일반적으로 public으로 지정.
	//method의 결과값을 리턴한다는 의미는 메서드가 호출한 곳으로 값을 대치 하는의미
	public static int staticCall(String a) {
		System.out.println(a);
		return 100;
	}
	
	public void myFunc() {
		System.out.println("5번문장입니다.");
	}
}
