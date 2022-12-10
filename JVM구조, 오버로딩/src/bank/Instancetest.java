package bank;

// javac InstanceTest. java -> compile 한 후
// java bank.InstanceTest -> JVM을 통해 실행가기 전에  InstanceTest.class 에 대한 정보를 method 영억 할당.

public class Instancetest {
	
	// 1. 생성자 constructor
	public Instancetest() {
		
	}
	
	//2.field
	//class variable : class만 있으면 class 이름으로 사용할 수 있는 변수.
	public static int a = 100; //static 지정되면 => a의 공간이 생성되는 곳은 . method area 영역임
								//instance를 만들지 않고도 사용을 할 수 있음.
	
	//instance variable : instance가 있어여지 사용할 수 있는 variable 
	int b =100; // b는 instance가 생성된 후에 그안에 공간이 생성됨.
				// 공간이 생성되곳은 당연히 heap.영역.
	
	//3 . method 
	public void instanceCall(String msg) {
		System.out.println(msg);
	} // instance가 있어야 호출 가능 = 만들어야지만 사용가능.
	public static void staticCall(String msg) {
		System.out.println(msg);
	} // instance가 없어도 호출 가능 	
	
	public static void main(String[] args) {
		int k = 100; // local variable (지역변수) => stack에 저장되고
					 // 메소드가 종료하면 당연히 없어짐.
		Instancetest hong = new Instancetest();
		hong.a = 300; // instance를 이용해서 static field를 이용할 수 있음.
		Instancetest.a = 100;//static field 이기 때문에 class 를 이용해서 사용.
		
		hong.b = 300;
		// Instancetest.b = 500; // Error 사용할 수 없음. why? method 영역에 클래스 정보의 int b의 접보를 할당 안하였기 때문임.
		
	}
}
