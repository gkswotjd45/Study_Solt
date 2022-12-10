package sampleProje;

import javax.swing.plaf.basic.BasicTableHeaderUI;

class ThreadEx0_07_1 implements Runnable{ //런어블 인터페이스를 구현한 클래스. // 이 안에 인터페이스는 run() 추상메서드가 있음.
	@Override
	public void run() {
		while (true) {
			// 현재 수행중인 Thread를 찾아 Thread의 이름을 출력.
			System.out.println(Thread.currentThread().getName()); // 클래스가 가지고 있는 현재 실행 중인 쓰레드의 이름.
																 // 현재 수행하는 쓰레드를 지칭.
			try { // 1초마다 현재 쓰레드의 이름을 호출.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
			}
			System.out.println();
		}
		
	}
}
public class ThreadEx_07 {
	public static void main(String[] args) {
		//하나의 런어블 객체가 쓰레드 3개가 같이 공유
		Runnable r = new ThreadEx0_07_1(); // runnable 인터페이스를 구현한 클래스의 인스턴스를 생성.
		Thread t1 = new Thread(r,"*"); // 기본 Run() 에는 하는일이 없음
		Thread t2 = new Thread(r,"**"); // 
		Thread t3 = new Thread(r,"***");
		
		t1.start();
		t2.start();
		t3.start();
		 try {
			Thread.sleep(2000); // main thread가 졸음. // 메인스레드가 2초 자는동안 다른 임의의 스레드t1,t2,t3이 무작위로 1번씩 출력되는 형태.
			t1.suspend(); //실제로 t1을 일시중지시킴. 그 이후 t2,t3 스레드가 수행, /
			Thread.sleep(2000); // 일부로 메인을 제워시키서 수행시킴.
			t2.suspend(); // t2을 일시중지시킴. 그 이후 t3만 수행
			Thread.sleep(2000);
			t1.resume(); // t1이 살아남. 따라서 t3과 t1이 무작위로 수행.
			Thread.sleep(2000);
			t1.stop(); // t1쓰레드가 정지시킴 => t3만 수행함.
			t2.stop(); // t2. 쓰레드가 정지시킴 = t3만 나옴.
			Thread.sleep(2000); // main thread가 졸음. 
			t3.stop(); // t3 쓰레드가 정지시킴.
			// -> Dead Lock이 발생함.
			
		} catch (Exception e) { //sleep 메서드를 사용하기 때문에 try. castch 구문
			
		}
	
		}
}


