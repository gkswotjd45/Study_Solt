package com.test;

import java.util.HashMap; //import java.util 패키지에 hashmap 이용.
import java.util.Set;

public class MapTest {
	
	
	public static void main(String[] args) {
		HashMap <String,String> map = new HashMap<String, String>(); //어떤 데이터 타입이든 다 올 수있지만, 제너릭으로 설정하여 해당 데이터 타입만 올 수 있게함.
		// 제네릭은 key, value 값이 들어가야 함으로 2개 선언 해야함.
		
		// 데이터 저장
		map.put("123", "Hello");// 데이터 삽입 시 put 메서드 이용.
		map.put("4", "안녕"); // 여러 개의 데이터를 삽일 할 때, key, value 값으로 사용.
		map.put("kk", "홍길동");
		map.put("aa","신사임당");
		map.put("123", "소리없은 아우성"); // 이미 키 값이 존재하는 경우, value 값은 대체 됨.
		
		
		// 데이터 추출
		map.get("123"); // 인자 123을 가지고 데이터 value 값을 가져옴.
		System.out.println(map.get("123")); 
		
		//map 안에 모든 key를 알고 싶음 (불러드리고자 할때)
		Set<String> s = map.keySet();// map이 갖고 있는 키를 set형태로 만듬.
		// 이건 조금 이따가 다시 함.
		for(String a : s) {
			System.out.println(a);
		}
		
		
		
	}
}
