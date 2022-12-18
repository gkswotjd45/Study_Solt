package Lunch_Part2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Lunch_P2_4 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(10); // ArrayList 안에 제네릭으로 삽일할 Integer 만 들어갈 수 있게 항목을 지정함.
	
		System.out.print("변경 전 :");
		
		for(int i = 0; i<10; i++) {
			list.add((int) (Math.random()*9) +1); // list 항목에 저장할 n (1 ~ 10)까지 무작위 값.
			System.out.print(" "+ list.get(i));
		}
		
		
		Collections.sort(list); //무작위로 섞어서 삽입.
		
		
		System.out.print("\n변경 후 : ");
		for(Integer T :list) {  // 변경된 list 항목 출력.
			System.out.print(T + " ");
		}
	}
	
}
