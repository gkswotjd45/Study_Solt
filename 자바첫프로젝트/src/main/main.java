package main;

import test.Customer;

public class main {
	public static void main(String[] args) {
		// 프로그램 entry point 지정.
		// 이제, 간단하게 인스턴스를 class로 부터 생성.
		System.out.println("프로그램 시작");
		
		Customer hong = new Customer(); //인스턴스를 new 키워드를 통해 초기화, Customer 클래스를 통해 생성 Reference data type 
										// new Customer() 생성자를 생성후 Customer 클래스의 hong 객체 생성.
									    // hong 인스턴스는 메모리 시작주소를 갖으며, 주소를 통해 찾아감.
		
										// hong ⇒ reference varialbe(참조 변수)
										// 메모리 주소에 대한 참조값(해쉬값)이 들어가 있기 때문에 이변수를 이렇게 불음
										// instance는 메모리에 존재 (메모리 공간 중 어디에 위치 ⇒ Heap 불리는 영역에 존재)
										// 우리가 사용하는 건 결국 instance를 사용하는 것. 이 공간을 사용하기 위해 참조변수를 이용하는 것.
										// 그래서 우리가 편하게 (일반적으로) hong을 객체라고 이야기 표현. ⇒ 실제로. 원래 new Customer()가 객체임.
		int k = 100; 					// primitive data type 
		
		hong.balance =  1000;
		Customer shin = new Customer();
		
		
	}
}
