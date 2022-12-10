package finaltest;

// 1. java finaltes.subclass - 실행
// 2. 제일 먼저 Class에 대한 정보가 method area에 올라가야 함.
// 3. subclass는 superclass가 먼저 적재되어야 함. 
// 4. super class 생성자, 필드. 메서드 정보 담김.
// 5. method area에 class 의 정보가 잘 저장되면, 그다음에는 당연히 프로그램 시작 포인트에서 프로그램을 시작해야 함 -main()


public class SubClass extends SuperClass {

	//constructor 
	public SubClass() { 
		super(100);       // 7. subClass 객체를 만들려면 상위 클래스의 객체를 먼저 만들어야함. => 상위클래스의 생성자 super(int i)로 이동. => 생성자의 첫줄은 this(). super()
						  // 반드시 나옴.
		
		// 16. 메모리에 공간을 확보.(안스턴스를 위해)
		// 이후 우리 객체의 초기화를 수행해야함  -> 이후 superclass 공간에 필드영역이 할당시켜야 함. = 6번 출력.
		
		// 이 아래가 객체 초기화 작업. 수행 후
		   
		// 17. 바로 8번 문장수행.
		staticCall("8번 문장입니다.");
		
		
		super.myFunc(); // 18. 현재 작업하는 객체의 상위 클래스 (super Class) 타입 객체의 메서드를 호출 - 5번 출력.
	}
	
	// default 생성자 완료 후 할당 해제 
	
	//field 
	int c = staticCall("6번 문장입니다."); // 16.인스턴트 필드 출력 후 값을 반환하여 수행.
	static int d = staticCall("7번문장입니다.");
	
	
	// method
	// 19. method overriding을 할 것임. -> 내용만 다시 작성한다는의미.
	@Override // annotation이라고 함.
	public void myFunc() { //
		System.out.println("9번 문장입니다.");
	}
	
	public static void main(String[] args) {  // 6. stack 영역에 main()영역에 할당
		System.out.println("10번문장입니다.");
		SuperClass obj = new SubClass(); // 7. 지역변수 이면서, 참조변수에 해당 (Super Class) , new SubClass() 생성자로 바로 (호출) 이동.
		obj.myFunc(); // 19. 원래 super class my func() 를 수행해야 하지만, 메서드 오버라이딩을 수행하기 때문에. 오버라이딩 한 클래스에 있는 myFunc() 메소드를 호출. 9번 출력 

	}
	
	
	
	
}
