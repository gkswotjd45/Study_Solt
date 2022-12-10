package com.Test4;

import java.util.ArrayList;

public class ShapeTest {

	public static void main(String[] args) {
	
		 ArrayList<Shape> list = new ArrayList<Shape>(); //제네릭 타입을 사용해야함. 공통적으로 들어가는 타입을 입력해야함 따라서, Shape 클래스를 삽입.
		 list.add(new Rectangle(4,7,5)); // 시각형 객체
		 list.add(new Rectangle(5,4,6)); 
		 list.add(new Circle(6,6,7));  // 원 객체
		 list.add(new Circle(7,8,3));
		 // Arraylist 안에 사각형 객체 2개, 원 객체 2개 삽입되어 있는 상태.
		 
		 for (Shape s : list) {  //타입은 shape 이지만, 하위 클래스에서 오버라이딩을 수행하였기 때문에, 각각 인스턴스 객체의 재정의된 메서드가 불러옴.
			 System.out.println(s);
		 }
		
		 for (Shape a :list) {
			 ((Movable)a).move(10,10); // 먼저 (movable)로 형 현환후 move 메소드 수행 (하위 클래스에서 동적바이딩) 후 수행.
			 //(Movable)a.move(10,10) // s.movle(10,10) 한 후 Movable 형변환 //mova는 Movable에 추상메서드로 저장되었지만 실질 적으로 new Rectangle, Circle로 만들어서
			 //재정의 하였기 때문에 해당 클래스에 저장되어있는 형태.
			 System.out.println(a);
		 	}
		 
		 
	}

}
