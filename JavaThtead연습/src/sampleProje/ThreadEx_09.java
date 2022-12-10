package sampleProje;

class ThreadEx_09_1  implements Runnable{
								         // 자체적으로 필드가 개설되어 있어,
	volatile boolean suspended = false; //중지, 나중에 supended가 정지가 되었는지 안되었는지  //volatile_ 캐시 사욯랄때는 코어내용확이하지 말고, 메모리가서 확인해보라는 의미
	volatile boolean stopped = false;  // 정지.
	Thread t; // 런어블 객체가 나를 불러오는 객체가 누구인지 확안하는 용도.ㄴ

	
	void setThread(Thread t) { // Runnable 인터페이스에서 interrput를 설정할 수 없어서, 따로 객체를 만들어 인터럽트를 걸기 위해 사용.
		this.t = t;
	}
	@Override
	public void run() {	
							
						
		while(!stopped) { // stopped가 false인 상태이므로 !연산자가 반대 연산자 이므로 true 상
			if(!suspended) {  // 일반적으로 supended(증단)이 되지 않으면, 
				System.out.println(Thread.currentThread().getName());	
				try { 
					Thread.sleep(1000);
					//stopped가 멈춰있지 않은 상태고, suspend 조건이 true인 경우 수행
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				Thread.yield();  // if(!suspend)이게 실제로 중지되는 상태가 아니여서, 계속 동작을 하니, Yield를 메서드를 통해 Thread를 양보하여 다른메서드에게 넘검. 
				//ststic 메서드 -> Running 상태에서 해제해줌, wihle문이 반복적으로 수행이 아님.
			}
			
		}
	}
	
	public void suspend() { //일시적으로 멈추는 메서드
		suspended = true; //중단
		// 아마도 1초 뒤에 상태를 확인해서 일지중지 작업을 수행.
		// 최대한 빨리 일시중지상태에 출입하려면, 현재 스레드에 대해서 인터럽트를 걸어야한다.
		t.interrupt(); //   // 1+ 앞파만큼 진행했다가, 중단되는 것보다 인터럽트로 중단되는 형태. 
	}
	public void resume() { // 멈춤처리를 반전시키면서 다시 시작메서드
		suspended = false;  // 재개
	}
	public void stop() { //스레드를 정지시킴.
		stopped = true;  // 정지.
		t.interrupt(); // 1+ 앞파만큼 진행했다가, 중단되는 것보다 인터럽트로 종료되는 형태. 
	}
}
public class ThreadEx_09 {
	
	public static void main(String[] args) {
		// Runnable 객체를 공휴하면 안돼요. -> Runnable 인터페이스 자체 안에,  //r1,r2,r3가 개별적으로 수행하기 위해 진행.
		ThreadEx_09_1  r1 = new ThreadEx_09_1();  // thread를 따로따로 만들어줌.
		ThreadEx_09_1  r2  = new ThreadEx_09_1();  // r1, r2,r3 
		ThreadEx_09_1  r3 = new ThreadEx_09_1();

		Thread t1 = new Thread(r1,"*");
		Thread t2 = new Thread(r2,"**");
		Thread t3 = new Thread(r3,"***");
		
		r1.setThread(t1); // r1이 실행하는 주체는 t1.
		r2.setThread(t2);
		r3.setThread(t3);
		
		t1.start(); // 스레드 시작.
		t2.start();
		t3.start();
		// 과정을 따라서 이해햐아함.
		try {
			Thread.sleep(2000); // main thread sleep
			// 첫번째 thread를 일시 정지할꺼에요.
			
			r1.suspend(); // thread를 제어하는 것이 아니라, t1.suspended()가 아님, thread 직접제어하지 않고 스레드가 가지고 있는 Runnable 객체의 필드 값을 
						 // feild 값을 조절해서 RUnnbale 객체가 가지고 있는 run() method 의 로직을 변화시키는 것예요.
						// r1 멈춤.
						// cpu 실제로 멈추지 않는 상태임. 진짜 처뻔째 쓰레드가 중지된 형태는 아님, 실제로는 게쏙 도는 형태가 아님.
						// 서스팬드가 호출되는 시점은 run() 1초마 깨고 자고 함므로 
			Thread.sleep(2000);
			r2.suspend(); //r2 멈춤
			Thread.sleep(2000);
			r1.resume(); //r1 깨움 // 이거 왜 나오지 않을끼? 로직은 문제 없음// 원인은 이 쓰레드 메모리 값은 제대로 갖고 있지만, Core에서 제대로 된 값이 아님.
			r1.stop();
			r2.stop();
			Thread.sleep(2000);
			r3.stop();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
