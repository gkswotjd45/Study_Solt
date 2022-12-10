package com.test5;

import java.awt.desktop.ScreenSleepEvent;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Company {
	
	public static void main(String[] args) {
		HashMap<Integer, Employee> map = new HashMap<Integer,Employee>();
		 
		map.put(1,new Secretary("Heilery",1,"Secretary",800)); //1이 interger 클래스여서 auto boxing 하여 객체화 시킴.
		map.put(2,new Sales("Clinten",2,"sales",1200));
		
		
		//map에 있는 모든 객체에 출력.
		//key를 알아야 데이터를 출력.
		for(Integer i : map.keySet()) { //차례대로 키값만 얻음
			System.out.println(map.get(i)); //get 메서드를 통해 키 값을 표현.
		}
		
		
		// 모든 객체에 인센티브를 100씩 지급.
		// 타입 캐스팅과 동적바인딩 내용을 이해.
		for(Integer i : map.keySet()) { //차례대로 ㄱ키값만 얻음
			((Bonus)(map.get(i))).incentive(100); //get 메서드를 통해 키 값을 표현. // Bonus는 인터페이스지만, 비서부서와, 자금부서는 각각 인터페이스를 참조하므로
		}
		
		for(Integer i : map.keySet()) {
			System.out.println(map.get(i) + "  " + map.get(i).tax());
		}
		
		
		/*
		for(Entry<Integer, Employee> a : map.entrySet()) {
			System.out.println(a);
		}
		*/
		/* 내가 작성
		Secretary a1 = (Secretary) map.get(1);
		Sales s1 = (Sales) map.get(2);
		System.out.println(a1);
		System.out.println(s1);

		a1.incentive(100);
		s1.incentive(100);
		
		System.out.println(a1);
		System.out.println(s1);
		*/
		
	
	}
}
