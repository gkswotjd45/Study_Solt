package com.test;

class Customer{
	public String name;
	public Long balance;
	
	
}


public class ExceptionTest {
	/*
	public static void main(String args[]) {
		
		System.out.println("예외상황을 발생시켜요");
		/*
		try {
		int result = 10 /0; // 피연사자가 정수이므로 문법상 오류이지 않지만, 10/ 0 으로 나누면 무한대 값이 도출해야 함. (but 실행 상 runtime에 이상한 상황
		//이 발생함. => 이 예외 상황에 대한 클래스가 존재.
		//ArithmeticException 를 throw 까지 던진상태. (코드가 실행되면서)
		}catch (NullPointerException e) { // 여기서 잡을 예외가 e가 맞는지. 여부확인.
			System.out.println("널 포인터 익셉션");  // sysout 은 보여주는 용도 이므로, 실제로 처리하는 용도가 아님. => 해결책을 명시해야 하는 코드 작성.
			// 원래는 예외상황처리코드가 나와야함.
		}catch (ArithmeticException e) {
			System.out.println("수학연산 잘못 되었어요! ");
		}catch (Exception e) { //exception 클래스는 예외처리의 최상위 클래스 이므로, 모든 예외 상황을 처리
								// 따라서 하위 항목에 개별적인 예외항목 하고 이후에, 최상위 Exception 클래스를 선언하는 게 맞음.
			System.out.println("그외 예외처리 항목");
		}finally {
			// 이 공간은 무조건 수행됨. (예외가 있거나, 없거나)
		}
		
		System.out.println("여기는 출력되나요?");
		
		
		Customer a = null; // null Refernce를 참조하고 있지 않음., 객체가 존재하지 않음.
		a.name = "홍길동";  // nullPointException 발생. -> 강제종료.
		}
		*/
		
		public static void main(String args[]) throws Exception { // 원래 main 메서드에서 발생한 경우 Exception 클래스 메서드에서 처리하애 하므로, 
																	// main 에서 try/catch 선언 안해도 됨. "나를 호출한 곳에서 처리하는 곳에서 치리."
																	// main() 메서드를 호출한 곳으로 excetion를 하도록 함. 하지만 실질적으로
																	// Main() 에서는 JVM에서 수행할 수 없음.
			System.out.println();
			int result = 10 /0; // 피연사자가 정수이므로 문법상 오류이지 않지만, 10/ 0 으로 나누면 무한대 값이 도출해야 함. (but 실행 상 runtime에 이상한 상황
			System.out.println("여기는 출력되나요?");
			
			
		}
}
