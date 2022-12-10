package sampleProje;

import javax.swing.JOptionPane;

class ThreadEx_06_1 extends Thread{	
	
	@Override
	public void run() { // 스레드가 어떤일을 수행할지 오버라이딩함.
		// TODO Auto-generated method stub
		int i = 10;
		//		// ㅑ가 0이 되면 중지 또는 인터럽트가 발생 하면 해당 스레드는 중지.
		while(i != 0 && !(this.isInterrupted())) { // i가 0이 아닐동안 수행. i가 10임 그리고 인터럽트가 안되면 게속 수행. -> 인터럽트가 된지 안되는지 확인하는 조건을 포함.
			System.out.println(i--); // 10 출력 후 i값을 감소시킴. => 후얀신자 
			// isInterrupted 는 단옥으로 메서드만 나온 경우 this. => 현재 사용되는 객체인 레퍼런스(참조)는 thread임.
			// ㅑsInterrupted는 인터럽트가 해당디면 1 
			// !(ㅑsInterrupted) 인터럽트가 걸렸지만 1인샅태 ! (not 연산자) 이므로 0 (false)에 해당.
			// busy-waiting _ cpu를 소모하면서 시간을 끄는 행위
			
			
			for (long k = 0; k<2500000000L; k++); // cpu 100% 사용.
		}
		System.out.println("카운트가 종료되었습니다.!"); // 10부터 감소하여 값을 출력.
	}
	
}

public class ThreadEx_06 {
	
	public static void main(String[] args) {
		Thread t = new  ThreadEx_06_1(); //Thread instance를 생성
		
		t.start(); // thread를 Runnable 상태로 전이시킴.
					// 바로 실행은 안되지만, 언젠가는 Thread 스케줄러에 의해 실행이 될 거예요.
		String data = JOptionPane.showInputDialog("값을 입력하세요!"); // 해당 메서드가 static 으로 지정되어 있음. 앞에 클래스 형태임으로, 
													// GuI 컴퍼넌트 기존에 수행하는 방법은 CUI 퍼넌트 방식.
													// 확인을 누르는 순간  String data로 들어감.
													// block 메서드 _ 확인을 누르기 전까지 수행이 대기 상태 . 메인 스레드가 hold 상태.
		System.out.println(data);
		
		t.interrupt(); // 스레드를 방해함._ 인터럽트는 스레드 state 내부 변수의 값을 변경. // 인터럽느는 중지되는 게 아닐 
						// 스레드를 interrput는 시킴 
						// thread가 중지된다던지 일시 정지된다던지 하는 일을 발생하지 않아요.
						// 로직으로 Thread의 해동을 제어해야 함.
						
						
		// t.suspend(); // deprecated 된 // 메인 스레드는 끝났지만, t 스레드가 중지된 상태여서 일단 프로세스가 살아있는 상태.
		//t.stop(); //  deprecated 된 , 스레드를 강제로 정지
		
	}
}
