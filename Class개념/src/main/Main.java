package main;

import bank.*; //bank 패키지 안에 모든 클래스를 포함.

public class Main {
	
	// 1. 생성자
	// default 생성자 들어옴.
	
	//2. field 존재할 수 잇어요. 하지만 필요 없음.
	
	//3. method 는  entry point인 main method만 존재.
	public static void main(String[] args) {
		
		//1. 홍길동 instance를 생상 
		//Customer hong = new Customer();
		//2. 생성한 객체에 이릉을 홍길동으 setting
		//hong.name = "홍길동";
		
		Customer hong = new Customer("홍길동");
		
		//3. 홍길동이 잔액을 확인. 
		long result = hong.getBalance();
		System.out.println("잔액은 :  "  + result);
		
		//4. 홍길동이 입금
		hong.deposit(2000);
		result = hong.getBalance();
		System.out.println("잔액은 :" +result);
		
		//5. 홍길동이 3000원 출금.
		long rest = hong.withdraw(3000);
		System.out.println("잔액은 :"+rest);
		
		//6. 홍길동이 1000원 출금
		rest = hong.withdraw(1000);
		System.out.println("잔액은 :"+rest);
	}
}
