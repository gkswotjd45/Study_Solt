package com.test;

public class Main {

	
	
	
	public static void main(String args[])  {
	   Student s1 = new Student("홍길동", 20);  //메모리 주소 가정 50
	   Student s2 = new Student("신사임당", 30);  // 메모리 주소 가정 70
	   Student s3 = new Student("홍길동", 20);
	   
	   String v1 = new String("안녕");
	   String v2 = "안녕";
	   
	   
	   // 두 객체가 같은 객체인가요? =
	   // 1. 진짜 두 개의 instance가 같은 메모리공간을 공유하고 있나요?
	   // 2. instance의 내용이 같은가?
	   
	   // 일반적으로 똑같니?  => 이 연산자는 "==" -> 논리 연산자 (변수 안의 값을 그냥 비교.)
	   // 이 연산의 결과는 true, false 논리값으로 결과가 리턴.
	   System.out.println(s1 == s2); // s1과 s2의 주소가 동일함? false, s2의 내용이 "홍길동" 20) 이여도 다름. , 메모리 주소 비교 연산자 == 
	  
	   System.out.println(s1.equals(s3)); // object가 상속 준 형태 s2를 인자로 s1을 비교. , false = 
	   
	   // 기본적으로 메모리 주소 값만 비교 '==' 동일.
	   //  => equals 오버라이딩 (재정의)하여 새롭게 다른 용도로 수행 가능함. (다중 객체 비교시)
	   // 앞으로 객체 비교시 equals 사용 (Reference 필드) 
	   // == primitive 필드(int, double, long) 비교시 '=='
	   
	   System.out.println(s1.toString()); // toString() 문자열로 변환.// toString - 오버라이딩으로 재정의하여 표현.

   }
   
}

