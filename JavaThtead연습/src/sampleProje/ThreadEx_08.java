package sampleProje;

class ThreadEx_08_1  implements Runnable{
								         // 자체적으로 필드가 개설되어 있어,
	volatile boolean suspended = false; //나중에 supended가 정지가 되었는지 안되었는지  //volatile_ 캐시 사욯랄때는 코어내용확이하지 말고, 메모리가서 확인해보라는 의미
	volatile boolean stopped = false;  //
	
	@Override
	public void run() {
						
		while(!stopped) { // stopped가 false인 상태이므로, !연산자가 반대 연산자 이므로 true 임 조건이 true면 계속 수행. 
			if(!suspended) { // suspened 필드가 false 상태이면, !연산자에 의해 true 이므로 계속 수행 
				System.out.println(Thread.currentThread().getName());
				
				try { //stopped가 멈춰있지 않은 상태고, supsend 조건이 true인 경우
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			} // 안멈취지 않으로 계속 숳ㅇ. // 조건에 따라서 멈출지 안 멈출지.
		}
	}
	
	public void suspend() { //일시적으로 멈추는 메서드
		suspended = true; //중단하기 위해 필드 값 true
	}
	public void resume() { // 멈춤처리를 반전시키면서 다시 시작 메서드
		suspended = false;  // 재개
	}
	public void stop() { //스레드를 정지시킴.
		stopped = true;  // 정지.
	}
}
public class ThreadEx_08 {
	
	public static void main(String[] args) {
		// Runnable 객체를 공휴하면 안돼요. //r1,r2,r3가 개별적으로 수행하기 위해 진행.
		ThreadEx_08_1  r1 = new ThreadEx_08_1();  // thread를 따로따로 만들어줌.
		ThreadEx_08_1  r2  = new ThreadEx_08_1();  // r1, r2,r3 
		ThreadEx_08_1  r3 = new ThreadEx_08_1();

		Thread t1 = new Thread(r1,"*");
		Thread t2 = new Thread(r2,"**");
		Thread t3 = new Thread(r3,"***");
		
		t1.start(); // 스레드 시작.
		t2.start();
		t3.start();
		// 과정을 따라서 이해햐아함.
		try {
			Thread.sleep(2000); // main thread sleep
			
			// 첫번째 thread를 일시 정지할꺼에요.
			r1.suspend(); // thread를 제어하는 것이 아니라, t1.suspended()가 아님, thread 직접제어하지 않고 스레드가 가지고 있는 Runnable 객체의 필드 값을 
						 // feild 값을 조절해서 RUnnbale 객체가 가지고 있는 run() method 의 로직을 변화시키는 것예요.
						// r1 멈춤. (r1이 중지)
						// cpu 실제로 멈추지 않는 상태임. 진짜 첫번째 쓰레드가 중지된 형태는 아님, 실제로는 계속 도는 형태.
			Thread.sleep(2000);
			r2.suspend(); //r2 멈춤
			Thread.sleep(2000);
			r1.resume(); //r1 깨움 // 이거 왜 나오지 않을끼? 로직은 문제 없음// 원인은 이 쓰레드 메모리 값은 제대로 갖고 있지만, Core에서 제대로 된 값이 아님.
			r1.stop(); //r1이 정지 되므로 "*"출력 안함.
			r2.stop(); //r2이 정지 되므로 "**"출력 안함.
			Thread.sleep(2000);
			r3.stop(); //r2이 정지 되므로 "***"출력 안함.
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
