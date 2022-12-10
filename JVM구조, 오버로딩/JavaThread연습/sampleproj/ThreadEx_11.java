package sampleproj;

class ThreadEx_11_1 extends Thread {
	
	// 상수 필드를 하나 선언할꺼예요.
	// 상수 필드는 관용적으로 대문자를 사용하고 snake case를 이용.
	final static int MAX_MEMORY = 1000;
	int usedMemory = 0;
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000); // 10초간 쿨쿨자요!
			} catch (Exception e) {
			}
			gc();  // memory 청소해서 memory용량을 다시 확보하는 method	
			System.out.println("남은 메모리량 : " + freeMemory());
		}
	}
	
	public void gc() {
		usedMemory = usedMemory - 300;
		if(usedMemory < 0) {
			usedMemory = 0;
		}
	}
	public int totalMemory() {   // 전체 메모리량 리턴하는 메소드
		return MAX_MEMORY;
	}
	public int freeMemory() {    // 전체 메모리에서 사용된 메모리량을 빼서
		                         // 현재 가용한 메모리량을 알아내요.
		return MAX_MEMORY - usedMemory;
	}
	
}

public class ThreadEx_11 {

	public static void main(String[] args) {
		// 메모리를 사용하고 청소하는 로직을 작성.
	}
}







