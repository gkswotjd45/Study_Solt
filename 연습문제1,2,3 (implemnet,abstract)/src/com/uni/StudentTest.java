package com.uni;

public class StudentTest {

	public static void main(String[] args) {
		
		
		//배열을 만들때는 new 다음에 데이터 타입이 옴.
		int a[] = new int [3]; // 배열을 객체에 해당  
		a[0] = 50;
		a[1] = 100; // array 배열에 해당. 데이터 타입이 동일, 
		
		
		Student arrays[] = new Student[3]; // Student가 데이터 타입 미년서, 클래스에 객채 3가지를 생성.
		//arrays 공간안에 Student 객체를 각각 할당시킴.
		//데이터 입력
		arrays[0] = new Student("홍길동",15,171,81,"201101","영문");
		arrays[1] = new Student("한사람",13,183,72,"201102","건축");
		arrays[2] = new Student("임꺽정",16,175,65,"201103","무용");
		
		
		//출력
		// 집합자료형 : 배열과 같이 여러개의 데이터를 한꺼번에 저장하고 있는 객체.
		for (Student s : arrays) {
							// for ( 집합자료형; )
			System.out.println(	s.printInformation());
	
			
		}

	}

}
