package sampleproj;

// 이렇게 만든 클래스는 당연히 Thread가 아니예요. 단지
// Runnable이라는 특별한 interface를 구현한 클래스일 뿐이예요
// 그래서 getName() method를 이용하려면..
// 
class ThreadEx_01_2 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<5; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	
}

// 이 밑에 있는 클래스는 당연히 Thread예요..is-a 관계에 의해!
class ThreadEx_01_1 extends Thread {

	public ThreadEx_01_1() {

	}
	
	// 새로운 독립적인 실행 흐름이라매..
	// 프로그램의 entry point에 준하는 무언가 시작 method가 있다는 의미.
	@Override
	public void run() {
		// for문 같은 경우..
		// 1. 집합자료구조를 이용하는 경우 => for ~ each구문 
		// for(String s : list) { }
		// 2. 원하는 횟수만큼 반복할 경우 => 일반 for문
		for(int i=0; i<5; i++) {
			System.out.println(getName());
		}
	}
}

// 예전설명
// java ThreadEx_01 => 실행하면..
// JVM이 main method를 호출해서 프로그램을 시작해요!

// Thread관점에서 다시 설명
// java ThreadEx_01 => 실행하면..
// JVM이 내부적으로 Thread를 하나 생성.
// 이 Thread가 main method를 호출해서 실행
// 이 Thread를 main thread라고 불러요!

// process(프로그램)는 main method가 시작되면 시작하는거고
// main method가 종료되는 전체 프로그램이 종료되요! => 틀려요!

// 정확하게는 process안에 있는 모든 Thread가 종료되어야지
// process가 종료되게 되요
public class ThreadEx_01 {
	
	public static void main(String[] args) {

		Runnable r2 = new ThreadEx_01_2(); // Thread가 아니예요!
		Thread t2 = new Thread(r2);  // Thread가 되요!
		
		ThreadEx_01_1 t1 = new ThreadEx_01_1();
		// t1은 Thread instance가 되요!
		// t1은 현재 객체일 뿐이야..아직 동작하지 않고 있어요!
		// 이 thread는 독립적인 실행 흐름을 가지고 있어요!
		// run()안에 그 내용을 기술하고 있어요!
		// 이제 이 thread를 실행시킬꺼예요!
//		t1.run();
		// 이렇게 호출하면.. 단순히 객체의 method를 호출하는 거지
		// Thread를 실행시킨건 아니예요!
		// 거의 대부분의 method는 blocking method예요!
		// method 수행이 끝나고 return될 때까지 대기!		
		// 아하.. 이렇게 하는게 아니라..다른 method를 이용해야 해요!
		t1.start();  // thread-0
		t2.start();  // thread-1
		// start()는 non-blocking method!
		// start()를 이용해서 Thread를 실행시켜요!
		System.out.println("main thread 종료!!");
	}
}
