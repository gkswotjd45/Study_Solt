package bank;

public class Customer {

	//1. 생성자 작성 ,
	//같은 패키지 안에 있는 다른 class에 의해서 사용된다면 ⇒ public 사용 할 필요 X 
	//if) 생성자가 다른 package 안에 있는 class에 의해서 사용된다면, public 키워드가 필요함.
	// 지금은 우리는 같으 프로젝트 안에 다른 package에서 Customer class의  instance를 생성할 예정. 따라서 생성자에 public 붙여야 함.
	public Customer() {
		// default 생성자. Parameter (인자)도 없고 하는 일도 없는 생성자.
		// if) default 생성자를 기입 안할 시, 컴파일러시에 확인 후 알아서 추가시켜 줌. ⇒ 반드시 포함시켜줌.
		// if 생성자 앞에 리턴타입이 없어야 함 있다면 메서드로 인식함 ex) void Customer(), int Customer()
	}
	
	// 그런데 생성자는 1개 이상 있을 수 있다고 함.
	// 하나의 클래스 안의 생성자의 이름은 모두 동일하기 때문에 인자의 개수, 인자의 타입으로 생성자를 구분해야 한다.
	// 생성자는 어떤 일을 수행 => 만들어진 인스턴스의 초기회를 담당.
	// 인스턴스의 필드 초기값을 설정하는 일
	public Customer(String name) {
		this.name = name; //string의 name을 객체 Customer name으로 초기화 시킴으로, 객체의 인스턴스화를 수행.
	}
	//2. field 선언 , 원래 필드가 모두 instance variable가 아님. public 사용 시 아무권한 없는 사용자가 임의로 수정이 가능함. 
	public String name;// 고객 이름, public 있어야 다른 패키지에서 사용 가능함. , instance variable
	public String accountNumber; // 계좌번호 , instance variable
	public long balance; //잔액 , instance variable
	
	//3.method 선언 
	// 잔액을 확인한다 라는 기능을 만들것.  = 잔액을 알아내서 리턴한다.
	// 1. 기능 자체의 잔액을 조사해서 출력까지 진행 or 기능은 잔액을 조사해서 잔액의 값만 리턴을 시키고 출력을 따로 수행.
	public long getBalance() {
		return this.balance; //this는 변수, 그 안에 어떤 값이 들어있냐하면, 메모리 주소 값이 들어 있어.
							 //instance 에 대한  reference 주소가 들어있음.
							// 현재 사용하는 객체의 대한 주소가 들어있게 됨.
	}
	
	// 입금하는 기능 (리턴 값이 없는 형태의 method로 만듬) 
	public void deposit(long money) {
		this.balance += money; // this. balance =  this. balance + money
	}

	// 출금하는 기능 (출금 후 잔액으로 리턴값으로 사용) 
	public long withdraw(long money) {
		// 만약 잔액이 출금요청액보다 적으면 출금이 되면 안됨.
		if(this.balance < money) {
			System.out.println("돈이 없습니다. 확인 부탁합니다.");
		} else {
			this.balance -= money;
		}
		return this.balance;
	}
}
