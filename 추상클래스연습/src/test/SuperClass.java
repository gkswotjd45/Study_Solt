package test;

public abstract class SuperClass {
		
	// 생성자
	public SuperClass() {}
	// field 
	
	// method
	// 일반 method (method 정의)
	public void sayHello() {
		System.out.println("안녕하세요.");
	}
	
	// method 선언 -> 완전한 형태의 method가 아님. -> abstract keyword를 이용해서 표시.
	public abstract void eat(); //abstract 메서드가 선언되면 해당 클래스가 abstract class에 해당.
	
}
 class SubClass extends SuperClass {

	
	// 불완전한 메서드를 재정의되기 때문에 완전한 메서드 형태가 됨 따라서 완전한 클래스가 되으모써 -> 인스턴스 객체 생성.
	@Override 
	public void eat() {
		// TODO Auto-generated method stub
		
	} // public 클래스는 무조건 1개만 지정 따라서 public 제외한 Class 지정.ㄴ
	//상속받은 
}
