package bank;

// default 생성자 및 형식인자를 포함한 동일한 명칭의 생성자 선언.
public class OverloadingTest {

	//1. constructor
	// instance 를 생성할 때 초기화해주는 역할을 담당.
	public OverloadingTest() {
		// default 생성자.
		// java의 모든 class default 생성자를 가질 수 있도록 코드를 생성. 기본적으롤 java가 삽입 하지만, 명시적으로 아는게 중요.
		// 
	}
	public OverloadingTest(String a, String b, int c) { 
		//a,b,c 인자들은 parameter = 형식인자.
		this.name = a;
		this.mobile = b;
		this.age = c;
	}
	
	//2. field 
	public String name; // 사람이름, instance가 생성되어야 활용 가능.
	public String mobile; // 전화번호
	public int age;
	
	
	//3. method
	public static void main(String[] args) {
		OverloadingTest tmp = new OverloadingTest();
		// instance가 생성되면, instance 안에 name, mobile, age의 공간이 생성
		// 이 공간은 해당 데이터 타입으로 각각 초기화 진행. n
		//name = ""(empty string) - 문자열이 있지만, 아무 것도 없는 상태. 빈문자열, 공백 문자열
		// age = 0으로 초기화. 
		tmp.name = "홍길동";
		tmp.mobile = "010-1111-2222";
		tmp.age = 20;
		//"홍길동","010-1111-2222",20 => arguments = 실인자(실제 값을 갖고 있는 항목), actual parameter
		OverloadingTest tmp2 = new OverloadingTest("홍길동","010-1111-2222",20); 
		
		
	}
}
