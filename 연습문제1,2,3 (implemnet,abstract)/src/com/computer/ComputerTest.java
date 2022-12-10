package com.computer;

public class ComputerTest {

	public static void main(String[] args) {
		Computer arrays [] = new Computer[4]; //일단 배열을 만듬. ,
		
		//int []		= 	new int [4]
		// arrays 안에는 Computer 라는 클래스 타입의 객체 들어옴
		// Laptop과 tab은 둘다 Computer의 subclass 임
		//따라서 is -a 관계는 의해, laptop 과 tab의 instance는 배열이 가능하다.
		
		System.out.println("name   cpu   memory   ");
		arrays[0] = new Laptop("Lap01",2,1024,2000);
		arrays[1] = new Laptop("Lap02",3,2048,2300);
		arrays[2] = new Tab("Tab01",2,1024,1200);
		arrays[3] = new Tab("Tab02",3,2048,1300);
		
		
		// 기본정보 출력.
		for(Computer C : arrays) {
		System.out.println(C);
		}
		
	
		// 컴퓨터 사용.
		for (Computer tmp : arrays) {
			tmp.operate(55); // 동적 바이인딩 수행 
			//오버라이딩이 됨. //추상메서드는 하위클래서에서 재정의 되었으므로,
			System.out.println(tmp.getBettery()); // overriding된 메서드 호출.
			if (tmp instanceof Laptop) {
				// 내가 가진 tmpr가 Laptop 클래스인지 확인
				
				System.out.println(((Laptop)tmp).redering(100)); 
				//((Laptop)tmp).redering(100);
				
			}else {
				System.out.println(((Tab)tmp).redering(100));
				//((Tab)tmp).redering(100);
				//tmp = (Tab)tmp;
			}
		}
		
		
		/*
		Laptop lapArray [] = new Laptop[2];
		lapArray[0] = (Laptop) arrays[0];  // Laptop 클래스를 담을 수 있는 lapArray[] 셍성 후.  Computer 클래스인 arrays [0]을 삽입
		lapArray[1]  = (Laptop) arrays[1];
		
		System.out.println("---------------------------");
		for(Laptop l : lapArray) {
			System.out.print(l);
			System.out.print(" "+l.redering(100)+"\n");
		}
		
		Tab tabArray [] = new Tab[2];
		tabArray[0] = (Tab) arrays[2];
		tabArray[1] = (Tab) arrays[3];
		for(Tab t : tabArray) {
			System.out.print(t);
			System.out.println(" " +t.redering(100) + "");
		}
		
		*/
	}
	
}
