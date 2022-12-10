package test;

public class Main {
	public static void main(String args[]) {
		
		//abstract class로 부터 instancne를 생성해 봄
		// 인스턴스 객체를 만들 수 없음. -> 추상 메서드로 존재하기 때문에 인스턴스를 만들 수 없음.
		// SuperClass a = new SuperClass();
		
		SuperClass b = new SubClass();
		
		
	}
}
