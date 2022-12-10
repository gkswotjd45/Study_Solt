package test;



// java Student  => 실행 ->  => method  area에 class 정보를 올림.
// 그런데 해당 클래스는 상속받은 클래스를 파악하여, 먼저 상속받은 클래스 정보를 담김.     
// Student 클래스에서 main() 찾음. 만약 없으면 튕김.
// 만약 있으면 jvm에 의해서 main이 호출된. => public static void main() 객체 없이도 다른패키지에서 실행가능 해야함으로
// stack에 메인 메서드를 위한 공간을 할당. 



// 상속을 이용하기 전, 객체지향의 상속을 이용해서 class을 다시 만들어 봄.
// java extend 라는 keyword를 제공.
// extends  뒤에 class가 나올 수 있는데, 자바는 이때, 단 하나의 클래스만 나올 수 있어요. (단일 상속 O) 
//(다중 상속 x)-> 여러 클래스에서 받는 형태는 x => extends keyword 다음에 class 는 단 1개만 나옴.
 

public class Student extends Person {	//person를 확장해서 student를 만들겠음. // person안의 클래스가 Student 안에 포함시킨다는 의미.

	/*
	public Student() {
		// 상위 클래스의 constructor 호출
		// super(); "자동으로 삽입" -> 상위 클래스는 인자를 받아드려야 하는 생성자가만 있으므로, 오류 해당 => 따라서 직접 Super(형식인자)를 직접 작성해야함
		super("홍길동"); // 생성자의 첫부분은 무조건 들어가 있어야 함. 만약 이 코드가 없으면 자동으로 삽입.
		// 상위 클래스의 생성자를 호출하는 코드.
		// Person () : 이 형태의 생성자를 호출.
		// super(100); 선언하면  -> person(100);  내가 입력한 인자에 따라 사용
		
		
		
		
		//default constructor
		// 현재 객체의 field를 초기화하는 부분 
		
		// 
	}
	
	*/
	
	public Student() {
		super();
		// 상위 클래스의 constructor 호출
		// super(); "자동으로 삽입" -> 상위 클래스는 인자를 받아드려야 하는 생성자가만 있으므로, 오류 해당 => 따라서 직접 Super(형식인자)를 직접 작성해야함
		// 생성자의 첫부분은 무조건 들어가 있어야 함. 만약 이 코드가 없으면 자동으로 삽입.
		// 상위 클래스의 생성자를 호출하는 코드.
		// Person () : 이 형태의 생성자를 호출.
		// super()
	}
	
	//private String name; 상속 받는 클래스에 이미 있음.
	//private String gender; 상속 받는 클래스에 이미 있음.
	// private int age; 상속 받는 클래스에 이미 있음.
	
	private String dept; // 학괴
	
	//method 
	
	/* 상속받은 클래스에 이미 있음.
	public void eat() {
		System.out.println("밥을 먹어요.");
		
	}
	*/
	public void study() {
		System.out.println("공부를 해요.");
		
	}
	// 상속받은 메소드를 하위클래스에서 재정의 (다시 정의)함. ->  method 오버라이딩 (메소드 overriding), 
	// m
	public void eat() { 
		//super.eat(); // 자기 상위되어 있는 클래스의 메서드를 호출.
		System.out.println("밥을 신나게 먹어요.");
	}
	public static void main(String[] args) {
		Student s = new Student(); //원래 원본
		
		// s 라는 지역변수를 Student 클래스에에서 만듬 (참조 변수)
		// String  " data type"   => 지정된 변수안에 들어올 값에 대한 제한을 거는 것을 의미.
		// s는 new Student 객체를 생성하는데 객체를 지칭하고 할때, 어떤 객체를 만드고자 하는 객체 타입에 따라 설정 = Student 클래스를 통해서 만드므로 70번지에 연결
		s.eat(); // s라는 instance 객체에 person 인스턴스를 내포함으로 해당 person 메서드인 eat()을 사용할 수 있음.
		
		
		Person s1 = new Student(); // s1은 person 만 가르키므로 50번지를 가르킴 , 따라서 person 인스턴스는 object 인스턴스를 내포하지만, Student 인스턴스는
									// 내포하지 않아 서용할 수 없음.
		
		
		s1.eat(); 					// java의 동적 바인드이러남. -> Student eat() 값이 나옴.  
									// 원래는 person의 eat() method를 호출 해야하는데, method overriding를 이용해 메소드를 다시 작성한 경우에는 
									// overriding된 method 대신 호출해요! 
		
								 	 // 이렇게 되면.. 결국 person에 있는 eat()은 호출할 방법이 없음. -> super.eat() 통해 상위타입의 method을 호출할 수 있음.
		
		
		// s1.study() 사용할 수 없음.
		
		Object s2 = new Student();  //s2는 Object를 가르킴 30번지를 , object 인스터는만 가르킴. 최상위 타입인 경우 사용하는데 제약이 많음.
			//s2.study() 사용허자 못함
			// s2.eat()  사용하지 못함.
		// 현재 s2라는 eat() 호출할수 없음. 실행시킬 수 없음 
		// 그럼 형변환을 이용하면 됨. casting 작업을 하면 됨 => () 이용.
		((Person)s2).eat(); //s2를 person 타입으로 변경. 임시로 변환 
		
		Student a = new Student();
		a.eat(); // "밥을 신나게 먹어요 호출" -> 메서드 오버라이딩
		
		
		
		
	}
}
