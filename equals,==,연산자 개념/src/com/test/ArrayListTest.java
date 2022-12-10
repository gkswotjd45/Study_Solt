package com.test;

import java.util.ArrayList;

// import 사용하여 클래스 생성시 패키지 명 명시하지 않아도 수행.

public class ArrayListTest {
	public static void main(String args[]) {
		// ArrayList 하나 생성해 봄.
		
		ArrayList<Object> a = new ArrayList<Object>();// ctrl + shift + o , 굳이 칸수를 지정하지 않아도 됨.// Object 클래스는 최상위 클래스로 모든 객체들은
		// 상속 받으므로,
		a.add("Hello");
		a.add(new Student("홍길동",20));
		a.add(new Integer(100)); // 틀린건 아니지만, 웬만하면 쓰지 마세요. 
		a.add(100); // -> 내부적으로 a.add(new Integer(100)); 자동 수행함. = auto Boxing
		a.add(3.14); // auto Boxing 발생. 사용하는 Double class 사용.
		// 빨간색: 코드 에러 , 노랑색 글씨 : 주의 표시(경고) -> warning도 잡아야 함.
		
		ArrayList<String> b = new ArrayList<String>(); // 어떤 데이터 타입을 쓸지 명시해야 함. ,Generic 사용.
		b.add("Hello");
		b.add("홍길동");
		b.add(new String("소리 없는 아우성")); // 다만 저장 공간이 heap 따로 성정.
		// b.add(100); //Integer 갹채가 들어 오므로 오류처리.
		
		
		 
		
	}
}