package com.test;

class MyClass extends Exception{ //에외상황의
	
	
}

public class CustomExceptionTest  {
	
	public static void main(String args[]){
		// is -a 관계에 의해 myClass는 Exception class 이다.
		System.out.println("시작");
		
		try {
		throw new MyClass(); // exception 객체가 생성 (아직은 그냥 객체 단계)
		 				     // 이런 exception 객체를 던저야 되요! (내가 수동적으로 객체를 만든 경우)
							 // 의도를 갖고 처리. (Exception을 발생 시킴)
		
		}catch(Exception e) {
			
		} 
		System.out.println("끝");
	}
}
