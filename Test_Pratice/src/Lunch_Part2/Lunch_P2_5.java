package Lunch_Part2;

import java.util.HashMap;

public class Lunch_P2_5 {
	
	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap <Integer, Integer> (10); // HashMap <key,value> 저장.
		double sum = 0.0;

		for(int i = 0; i<10; i++) {
			map.put(i,(int) ((Math.random()*100)+1));  // map 안에 i번부터 순서대로, 1 ~ 100까지 무작위 수를 대입
			}
		
		//keySet() == HashMap의 키 값만 호출
		for(Integer a :map.keySet()) {
			System.out.print(map.get(a)+ " ");  // map에 a에 키에 대응하는 value값을 가져온다.
		}
		for(Integer a :map.keySet()) {
				 sum += map.get(a);
		}
		System.out.println("\n합계 :" + sum);
		System.out.println("평균 :" + Math.round(sum/map.size())*10/10.0 ); // 먼저 합계에 /10 나누 후 반올림을 한 뒤, 다시 소수자리로 만듬.
		
	}

}
