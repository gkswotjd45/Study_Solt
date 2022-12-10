package sampleProje;

class Account { // "공용객체", 계좌를 의미. // 일반클래스 일반 객체로 만듬. // 잔액데이터와 데이터를 다루는 메서드 출금, // 클래스를 쓰레드가 사용하도록 수행.
	//construtcor
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Account(int balance) {
		super();
		this.balance = balance;
	}
	
	//field
	private int balance; //잔액 으미 

	//bisiness method
	
	public void withdraw(int money) { //잔액이 원하는 출금한 금액 보다 같거나 크면 
		
		//여기에 있는 코드는 동기화가 되어있음. 
		synchronized(this) { //synchronized는 쓰레드에서 항상 나옴.
			//balance가 현재 300 남았을 때, Thread 1 -> 200 차감  Thread 2 -> 200원 차감. 동시에 차감. => Thread 1 수행후 100원이되고, Threa는 100 -200 원이르모 
			// -100원이 됨 = > 쓰레드가 공용객체를 사용할 때 발생하는 문제점을 ㅎ해결하는 방범을 구현해야함.! 2가지 방안 1.syncronized 붙임 (동기화 메서드)
			// if  synchronized 메서드를 만들면 이메서드를 실행하는 Thread는 먼저 Lock (Moniter) 획득.
			// 나머지 쓰레드른 Block 발생.
			// 하나의 쓰레드가 메서드를 호출하면 나머지 하나는 block (해제되는 시점은 첫번째 쓰레드가 수행이 완료된 이후 rock이 해제 후 홀드된 2번째 스레드 수행 O 
			
			if(balance>=money) {
				try {
					Thread.sleep(1000);  //눈 깜빡할 사이에 수행되므로 확인할 용도로 흐름을 늦춤,
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				balance -= money; //잔액에서 출금 금액을 차감시킴.
			} 
		}
		
		// 그 외의 부분은 동기화가 안됨. //메서드 호출는 여러스레드가 들어갈 수는 있지만, synchronized(this) 부분에서 Hold됨 (메서드 진입 시가 아닐, 메서드 안에 해당 부분을
		// hold 시킴)
		
	}
	
	public int getBalance() { // 값을 알아오고 
		return balance;
	}
	public void setBalance(int balance) { // 값을 반환해주는 
		this.balance = balance;
	}
	
}

class ThreadEx_12_1 implements Runnable{
	
	
	
	//field 
	Account acc = new Account(1000); //Account라는 필드를 갖고 있음. // Account 객체 생성시 잔액의 초기값 1000원 줌.
	
	@Override
	public void run() { // 그때 사용하는 객체를 필드로 들고 있음
		while(acc.getBalance() > 0 ) { //잔액이 0보다 큰 경우 출금이 가능한 경우.
			int money = (int) (Math.random() * 3 + 1)  * 100; // 1 ~ 4 사이의 실수 , 100 ~ 400 사이의 정수 출력.
			acc.withdraw(money); //공용객체의 출금처리
			System.out.println("남은 금액은 : " + acc.getBalance());
		} 
		
	}
}

public class ThreadEx_12 {
  public static void main(String[] args) {
	 ThreadEx_12_1 r = new ThreadEx_12_1();
	 Thread t1 = new Thread(r);
	 Thread t2 = new Thread(r);
	 
	 t1.start();
	 t2.start();
  }
}
