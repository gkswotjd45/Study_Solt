package sampleProje;

// 이렇게 만든 클래스는 당연히 Thread가 아니에요. 단지
// Runnable이란 특별한 Interface를 구현한 클래스일 뿐이예요.
// 그래서 getName() 메서드를 이용하려면..
// 현재 
class TreadEx_01_2 implements Runnable {


	@Override  // 클래스를 상속받는 것이 아니라 구현하는 방식. 
	public void run() {
		for(int i = 0; i<5; i++) {
			System.out.println(Thread.currentThread().getName()); // this.를 생략함 이때 this.getName() 개념 확인
		}
		
	}// 메서드가 현재 동작중인 스레디를 가져옴.
	
}
	
// 이 밑에 있는 클래스는 당연히 Thread예요.. is-a 관계에 의해!
class ThreadEx_01_1 extends Thread { // 스레드를 상속해서 스레드 생성 후 수행.

	public ThreadEx_01_1() {
		// TODO Auto-generated constructor stub
	}
	// 새로운 독립적인 실행 흐름.
	// 마치 프로그램의 entry point에 준하는 무언가 시작 method가 있다는 의미.
	@Override
	public void run() { //독립적인 실행 흐름.
		// 내가 실행하고 싶은 코드를 작성
		// for문 같은 경우.
		// 1. 집합자료구조를 이용하는 경우 => for each구문 (list, map) 용이
		// for (String s : list){}
		// 2. 내가 원하는 횟수만큼 반복할 겨웅 -> 일반 for문
		for(int i = 0; i<5; i++) {
			System.out.println(getName()); // this.를 생략함 이때 this.getName() 개념 확인 // getname 사요하려면 thread 클래스에 있음.
		}
			
	} // 프고르맹의 entry point 
	
	
}
//지비 예전 설명
//java ThraadEx_01 => 실행하면..
//JVM main method을 호출해서 프로그램을 시작.

// Thread 관점에서 다시 설명
// javaThradeEx_01  => 실행하면
// Jvm이 내부적으로 Thread를 하나 생성.
// 이 스레드가 main이라는 메서드를 호출해서 실행.
// 이 Thread를  main thread라고 불림.
// process(프로그램) 의 종류는 main method가 시작되면 시작하는 거고, 
// main method가 종료되는 전체프로그램이 종료되요. = 틀림

// 정확하게는 process 안에있는 모든 Thread 가 종료되어야지 process가 종료되게 되요.


public class ThreadEx_01{
	
	
	//main을 호출하는 스레들 실행하는 개념.
	public static void main(String[] args) { //JVM이 클래스를 읽어와서 내부적 스레드를 생성하여 스레드가 main 메서드를 호출시킨는 개념.
		
		
		Runnable r2  = new TreadEx_01_2(); // Thread가 아님
		Thread t2 = new Thread(r2);  // Thread가 되요.
		
		
		
		// 이 main method는 당연히 하나의 Thread에 의해서 실행되는 method
		// 
		ThreadEx_01_1 t1 = new ThreadEx_01_1();
		// t1 Thread는 instance가 됨.
		// t1은 햔제 갹체일 뿐임. 아직 동학하지 않고 있음.
		// 이 thread 독립적인 실행 흐름을 가지고 있음.
		// run() 안에 그 내용을 기술하고 있음.
		// 이제 이 Thread를 실행시킴.
		//t1.run(); // 이렇게 호출하면. . 단순히 객체의 method를 호출하는 거지 -> 거의 대부분의 메서드는 blocking method에요.
		// method 수행이 끝나고 Return 될 때까지 대기하는 상태.
		// thread를 실행시킨 건 아님.
		// thread 호출과 실행의 의미는 다름.
		// 아하, 이렇게 하는 것이 아니라, 다른 메서드를 이용해야함.
		t1.start(); // Thread를 시작해. start를 이옿해서 Thread를 실행시킴. // 내부의 오버라이딩 된 Run을 찾아가서 실행
		//non-blocking 기법. 2개의 실행흐름으로 수행되여 sysout 출력. 다른 하나는 Thread 스케줄링에서 언젠가 실행시켜 주며, 스레드가 모두 수행후 종료되면 

		// 완전히 종료.
		// 이 thread는 특이한 클래스 임으로 별도의 메서드를 이용해야함
		//thread-0
		t2.start(); // thread-1
		System.out.println("Main thread 종료.");
		
	}
	
}
