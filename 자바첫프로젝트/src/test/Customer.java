package test;
import java.lang.String; // package 명시를 하지 않고 사용 가능, 자동 삽입가능 하게 해주는 역할.
import java.lang.*;// * 모든 항목 (패키지명)을 명시않고 작성.

public class Customer {
	
	// 1. 생성자들. → 인스턴스를 사용하기 위해 초기화 시키는 역할.
	// 클래스안에 생성자들을 이용해서 class로 부터 instance 생성할 수 있음. 생성자가 없는 클래스는 의미 없음.
	// 생성자가 없는 class는 존재하지 않아요!
	// 일단 public (public을 쓸수도 있고 아닐 수 도 있음)
	// 메소드 형태가 나옴
	// 생성자는 메소드가 아니다 →  라턴타입이 존재하지 않음
	// 입력인자를 가질 수 있음
	// 중괄호 안에 instance의 초기화 코드가 들어옴
	// 당연히 리턴구문은 존재하지 않음.
	// 이엃게 만든걸 우리는 생성자라고 일컬음.
	public Customer() {
		//생설될 인스턴스의 초기화코드가 들어옴
	}
	// 2. 필드들.
	// 변수들이 옴 => camel case 표햔법으로 나타냄.
	// 이름
	 String customerName; //고객이름, reference data type , String은 클래스임. 
	 //> 우리가 만들지 않은 class. java가 제공한 class, 프로그램을 쉽게 하기위해 제공(class library),
	// 이 많은 class들은 package로 묶어서 제공, 당연히 이 class를 사용할 때 package를 명시 해야함 java.lang.
	 public long balance; // 잔고, 잔액 ,primitive data type , public ⇒ package에 상관없이 누구나 사용. 일반적으로 package의 제한이 걸리지만,
	 String customerAccount; //고객 계좌
	 int customerAge; // 고객 나이, primitive data type 
	
	 // 3. 메서드들
	 // 일반적으로 method는 특별한 용도가 아닌 이상 public 기본으로 지정.
	 // 리턴타입 →  메소드는 우리가 알고 있는 함수형태, 함수는 입력을 받아서 로직 처리한 후 그 결과물을 생성해서 결과물을 함수를 호출한 곳으로 돌려주기 위해서 사용. 
	 //→  이 돌려주는 값을 리턴 값으로 니티넴. →  이 리턴 값이 어떤 데이터 타입인지를 method를 정의할 때 선언.
	 public int getAge(int kk) {
		 //kk는 parameter라고 하고 method의 입력을 받아들이는 역할을 수행.
		 // business logic 처리진행.
		 System.out.println("나이를 알려주는 기능이예요!");
		 // if, for.. 
		 return 30;
	 }
	 
	 
	 
}

