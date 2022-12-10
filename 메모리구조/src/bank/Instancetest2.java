package bank;

public class Instancetest2 { // JVM에 할당 되는 순서 파악하는 용도 및 static block 호출되는 위치.

	// field 
	static int a= staticCall("1번째 출력입니다."); //method 영역에 클래스 생성 후에 반드시 해당 메서드의 결과 값이 있어여함. 1st
	//static 필드 으로 지정하면, 해당 메서드 역시 static으로 지정해야함. 
	int b = staticCall("2번째 출력입니다.");
	
	
	
	public static int staticCall(String msg) { //method 영역에 클래스 영역에 할당 후 해당 메서드도 return 100이 저장 됨.
		System.out.println(msg);
		return 100;
	}
	
	// static block 
	// 이 static block은 언제 실행 되나요?
	
	static { //main () 이전에 실행됨 . 
		System.out.println("static block 입니다. a 의 값은 : " +a);
		//프로그램에서 사용되는 라이브러리를 미리 로딩하고자 할 때. 이질적인 다른 라이브러리를 사용할 때 main()에서 처리하는 것이 부담., 실질적으로 main()로 많은 것을 할당 
		//하지 않음.
	}
	
	public Instancetest2() {
		this.b = staticCall("3번째 출력입니다."); // 객체 생성시 b 값이 초기화 
	}
	
	public static void main(String[] args) {
		System.out.println("4번째 출력입니다."); //main 프로그램 실행 2st
		int c = staticCall("5번째 출력입니다.");  //main 이후 메서드 수행
		Instancetest2 d = new Instancetest2(); // instancetest2  인스턴스 d 생성되므로, int b 메서드 출력 후 b에 100을 저장, 
		//new instancetest2() 생성자 실행 
	}
}
