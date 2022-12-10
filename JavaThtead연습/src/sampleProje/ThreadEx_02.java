package sampleProje;

//thread run () - block method 수행. 
class Thraedex_02_1 extends Thread{
	
	public Thraedex_02_1() {
		
	}
	public Thraedex_02_1(String name) {  //오버라이딩 Thread (String name) 수행하여 "명칭"설정.
		super();
		this.setName(name); // Thread 이름을 설정하는 method
		
	
		
	}
	@Override
	public void run() {
		for(int i = 0 ;i<5 ; i++) {
			System.out.println(this.getName()); // 현재 객체가 Thread이므로. // get Name()은 Thread 형태로 상속되어 있는 형태지만,
			//main에서 수행할때, Threadox_02_1 생성 후 setName()을 설정함.
		}
	}
}
public class ThreadEx_02 {
	
	public static void main(String[] args) {
		Thread t1 =new Thraedex_02_1("My Thread");
		
		//t1.run(); // blocking method -> 단지 객체의 메서드를 수행한 과정
		t1.start();  // thread로 동작시켜보아요 // 내가 만든 스레드를 Runnable 형태로 만들어 줌,
		// thread 마다 stack이 각각 할당/
		//Start(); 사용 개념 알아보기.
		System.out.println("main thread 종료!");
	}
}
