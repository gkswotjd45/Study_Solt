package com.test;

import java.util.HashSet;

public class SetTest {
	public static void main(String[] args) {
		HashSet<String> set = new HashSet<String>(); // 제너릭으로 데이터 값을 설정. // 중복이 배제 되며, 호출 시 무작위로 불러내야 한다.
		
		// 데이터를 저장하는 용이.
		set.add("1234");
		set.add("홍길동");
		set.add("신사임당");
		set.add("Hello");
		
		// set 안에 저장된 데이터를 꺼낼려면 어떻게 해야함?
		// set은 순서가 없음. -> 순서를 이용해서 꺼낼 수 없음.
		// set은 key가 없음. -> key를 이용해서 꺼낼 수 없음.
		
		for(String s : set) {  //set 집합 자료구조에 하나씩 s에 담아서 출력.
			System.out.println(s);  // 순서에 상관 없이 표출
		}
		
		
	}
}
